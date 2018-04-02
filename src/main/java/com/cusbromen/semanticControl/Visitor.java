package com.cusbromen.semanticControl;
import com.cusbromen.antlr.SqlBaseVisitor;
import com.cusbromen.antlr.SqlParser;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.HtmlRenderer;
import javafx.util.Pair;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked") // JSON's fault
public class Visitor extends SqlBaseVisitor<String> {
    private List<String> semanticErrorsList, successMessages, verboseParser; // list for semantic errors found
    private ArrayList<String> newTablesIds;
    private JSONParser jsonParser;
    private JSONArray newConditionPostFix;
    private JSONObject newTable, newColumns, newColumn, newConstraints;  // This is going to be used to construct a table step by step
    private String dbsJsonPath, dbInUse, newTableName, newColumnName, newTypeColumn;
    private ArrayList<String[]> newReferencedTables, newOrderBy; // it's going to be used to save referenced tables temporary
    private SymbolTableHashMap symbolTable;
    private String lastDbUsedPath = "metadata/lastDbUsed";
    private boolean syntaxError, addingConstraint, primaryKeyCreated, columnNullable;
    private Layout layout, consolePanelLayout;
    private Grid<Queue<String>> showDataGrid;
    private ArrayList<String> newColumnsNames;

    public Visitor() {
        this.semanticErrorsList = new ArrayList<>();
        successMessages = new ArrayList<>();
        verboseParser = new ArrayList<>();
        jsonParser = new JSONParser();  // Used to read an existing JSON file
        dbsJsonPath = "metadata/dbs.json";
        symbolTable = new SymbolTableHashMap(verboseParser);
        dbInUse = loadLastUsedDb();
        symbolTable.readMetadata(dbsJsonPath, jsonParser, dbInUse);  // Load the metadata of dbs before working
        syntaxError = false;
        addingConstraint = false;

        if (!getDbInUse().equals("none")) {
            showNotificationDbInUse(); // TODO lo quito para mientras huehuehue
        }
        // TODO: Este mensaje tendrá que ser mostrado por la gui
        System.out.println("DB in use: " + getDbInUse());
    }


    /** 'CREATE' 'DATABASE' ID ';' */
    @Override
    public String visitCreate_database(SqlParser.Create_databaseContext ctx) {
        String id = ctx.ID().getSymbol().getText();

        int created = symbolTable.createDb(id);
        // DB already exists
        if (created == 1) {
            semanticErrorsList.add("Database <strong>" + id + "</strong> already exists. Line: " + ctx.start.getLine());
            return "error";
        }
        successMessages.add("Database <strong>" + id + "</strong> successfully created.");
        return "void";
    }

    /** 'ALTER' 'DATABASE' ID 'RENAME' 'TO' ID ';' */
    @Override
    public String visitAlter_database(SqlParser.Alter_databaseContext ctx) {
        String oldName = ctx.ID(0).getSymbol().getText();
        String newName = ctx.ID(1).getSymbol().getText();

        //Verify if it's actual database
        int result = symbolTable.renameDb(oldName, newName);

        if (result == 1){
            semanticErrorsList.add("Database <strong>" + oldName + "</strong> doesn't exist. Line: " + ctx.start.getLine());
            return "error";
        }

        else if (result == 2){
            semanticErrorsList.add("Database <strong>" + newName + "</strong>  already exists. Line: " + ctx.start.getLine());
            return "error";
        }
        // verify if it was the actual db in use
        if (oldName.equals(dbInUse)){
            try {
                dbInUse = newName;
                symbolTable.loadDbMetadata(newName, jsonParser);
                PrintWriter writer = new PrintWriter(lastDbUsedPath, "UTF-8");

                // Write to file
                writer.write(newName);
                writer.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        successMessages.add("Successfully renamed database <strong>" + oldName + "</strong> to <strong>" + newName + "</strong>.");
        return "void";
    }

    /** 'DROP' 'DATABASE' ID ';' */
    @Override
    public String visitDrop_database(SqlParser.Drop_databaseContext ctx) {
        String dbId = ctx.ID().getSymbol().getText();

        // See if db exists
        if (!symbolTable.dbExists(dbId)){
            semanticErrorsList.add("Couldn't delete database, <strong>" + dbId + "</strong>  doesn't exists. Line: " + ctx.start.getLine());
            return "error";
        }

        // Get the number of records on db
        JSONObject db = symbolTable.getDb(dbId, jsonParser);
        Set<String> tableNames = db.keySet();
        JSONObject table;
        Integer records = 0;
        for (String tableId : tableNames) {
            table = (JSONObject) db.get(tableId);
            records += ((Number) table.get("noRecords")).intValue();
        }

        // Warning message TODO: This message has to be displayed on the gui, use the delete input function
        dropConfirmationDialog(dbId, records);

        //System.out.println("Are you sure you want to delete database '" + dbId + "' which contains '" + records + "' records? (y/n)");
        //boolean delete = getMessageAnswer();

        return "void";
    }

    /** 'SHOW' 'DATABASES' ';' */
    @Override
    public String visitShow_databases(SqlParser.Show_databasesContext ctx) {
        String returnedVal = symbolTable.showDatabases();

        if (returnedVal.equals("0")) {
            semanticErrorsList.add("NO DATABASES.");
        } else {
            successMessages.add("Successfull operation. <strong>SHOW DATABASES</strong>: <br>" + returnedVal);
        }

        return "void";
    }

    /** 'USE' 'DATABASE' ID ';' */
    @Override
    public String visitUse_database(SqlParser.Use_databaseContext ctx) {
        String db = ctx.ID().getSymbol().getText();
        if (symbolTable.dbExists(db)){
            if (!db.equals(dbInUse)){
                dbInUse = db;
                successMessages.add("Successfully selected database <strong>" + db + "</strong>.");

                try {
                    // See if lastdb dir exists
                    File lastDb = new File(lastDbUsedPath);

                    if (!lastDb.exists()){
                        lastDb.createNewFile();
                    }

                    PrintWriter writer = new PrintWriter(lastDb, "UTF-8");
                    // Write to file
                    writer.write(dbInUse);

                    writer.close();

                    // Load database metadata to memory
                    symbolTable.loadDbMetadata(dbInUse, jsonParser);

                } catch (IOException e){
                    return "error";
                }

                return "void";
            } else {
                successMessages.add("Database <strong>" + db + "</strong> is already selected for use.");
                return "void";
            }

        } else {
            semanticErrorsList.add("Error: Database <strong>" + db + "</strong> doesn't exist. Line: " + ctx.start.getLine());
            return "error";
        }
    }

    /** 'CREATE' 'TABLE' ID table_element_list ';' */
    @Override
    public String visitCreate_table(SqlParser.Create_tableContext ctx) {
        if (dbInUse != null){
            newTableName = ctx.ID().getText();

            newTable = new JSONObject();

            // Initialize table with 0 records
            newTable.put("noRecords", 0);

            // Create new field for columns and for table constraints
            newColumns = new JSONObject();
            newConstraints = new JSONObject();
            newReferencedTables = new ArrayList<>();

            // Analyze columns for the table and add them to newColumn and all the constraints too
            String visit = visit(ctx.table_element_list());
            if (visit.equals("error")) {
                semanticErrorsList.add("Error: Couldn't create table <strong>" + newTableName + "</strong>. Line: " + ctx.start.getLine());
                return "error";
            }

            newTable.put("columns", newColumns);
            newTable.put("constraints", newConstraints);


            int createTableResult = symbolTable.createTable(newTableName, newTable, newReferencedTables);

            if (createTableResult == 0) {
                semanticErrorsList.add("Database <strong>" + dbInUse + "</strong> doesn't exist. Line: " + ctx.start.getLine());
            } else if (createTableResult == 1) {
                successMessages.add("Successfully created table <strong>" + newTableName + "</strong>.");
            } else if (createTableResult == 2) {
                semanticErrorsList.add("Error: Table <strong>" + newTableName + "</strong> already exists in database <strong>" + dbInUse + "</strong>. Line: " + ctx.start.getLine());
            } else if (createTableResult == 3) {
                semanticErrorsList.add("Error: Two foreign keys have the same ID. Couldn't create table <strong>" + newTableName + "</strong>. Line: " + ctx.start.getLine());
            } else if (createTableResult == 4){
                semanticErrorsList.add("Error: The primary keys must be declared as NOT NULL. Line: " + ctx.getStart().getLine());
                return "error";
            }
            return "void";
        } else {
            semanticErrorsList.add("Error: You haven't selected a DB yet, table wasn't created. Line: " + ctx.start.getLine());
            return "error";
        }
    }

    /** 'ALTER' 'TABLE' ID (alter_rename | alter_action) ';' */
    @Override
    public String visitAlter_table(SqlParser.Alter_tableContext ctx) {

        if (ctx.alter_rename() != null){
            String oldName = ctx.ID().getSymbol().getText();
            String newName = visit(ctx.alter_rename());

            int renameResult = symbolTable.renameTable(oldName, newName);

            if (renameResult == 0) {
                semanticErrorsList.add("Error: You haven't selected a DB yet, table wasn't renamed. Line: " + ctx.start.getLine());
            } else if (renameResult == 1) {
                successMessages.add("Successfully renamed table <strong>" + oldName + "</strong> to <strong>" + newName + "</strong>.");
            } else if (renameResult == 2) {
                semanticErrorsList.add("The table <strong>" + newName + "</strong> already exists in database <strong>" + dbInUse + "</strong>.");
            } else if (renameResult == 3) {
                semanticErrorsList.add("The table <strong>" + oldName + "</strong> does not exist in database <strong>" + dbInUse + "</strong>.");
            }

        } else {
            newTableName = ctx.ID().getSymbol().getText();

            if (symbolTable.getTable(newTableName) == null){
                semanticErrorsList.add("Error: Table <strong>" + newTableName + "</strong> doesn't exists. Line: " + ctx.start.getLine());
                return "error";
            }

            String result = visit(ctx.alter_action());
            if (result.equals("error")) {
                semanticErrorsList.add("Error: Couldn't alter table <strong>" + newTableName + "</strong>. Line: " + ctx.start.getLine());
                return "error";
            }
        }

        return "void";
    }

    /**'DROP' 'TABLE' ID ';'*/
    @Override
    public String visitDrop_table(SqlParser.Drop_tableContext ctx) {
        // Verify if we have selected a DB
        if (dbInUse != null){
            String tableId = ctx.ID().getSymbol().getText();

            // See if table exists
            if (!symbolTable.tableExists(tableId)){
                semanticErrorsList.add("Couldn't delete table, <strong>" + tableId + "</strong>  doesn't exists. Line: " + ctx.start.getLine());
                return "error";
            }

            // Get table to verify ingoing references
            JSONObject table = symbolTable.getTable(tableId);
            Object referencesObject = table.get("ingoingReferences");
            if (referencesObject == null){
                // We can remove table
                symbolTable.deleteTable(tableId);
            } else {
                JSONObject references = (JSONObject) referencesObject;
                Set<String> constraintNames = references.keySet();
                if (constraintNames.size() == 0){
                    // we can remove table
                    symbolTable.deleteTable(tableId);
                } else {
                    // Get ingoing references
                    String referencesString = "";
                    for (String ref : constraintNames) {
                        referencesString += "<br>*    " + ref + " from table " + references.get(ref);
                    }
                    referencesString += "<br>";

                    semanticErrorsList.add("Couldn't delete table. <strong>" + tableId + "</strong> has the following ingoing references: " + referencesString +". Line: " + ctx.start.getLine());
                    return "error";
                }
            }

        } else {
            semanticErrorsList.add("Couldn't delete table. You must select a DB first. Line: " + ctx.start.getLine());
            return "error";
        }


        return "void";
    }

    /** 'SHOW' 'TABLES' ';' */
    @Override
    public String visitShow_tables(SqlParser.Show_tablesContext ctx) {
        String returnedVal = symbolTable.showTables();

        if (returnedVal.equals("0")) {
            semanticErrorsList.add("Error: You haven't selected a DB yet, can't show any tables. Line: " + ctx.start.getLine());
        } else {
            successMessages.add("Successfull operation. <strong>SHOW TABLES</strong> in database " + dbInUse + ": <br>" + returnedVal);
        }

        return "void";
    }

    /** 'SHOW' 'COLUMNS' 'FROM' ID */
    @Override
    public String visitShow_cols_from(SqlParser.Show_cols_fromContext ctx) {
        // Verify if we are using a DB
        if (dbInUse != null){
            String idTable = ctx.ID().getSymbol().getText();
            String result = symbolTable.showColumns(idTable);

            if (result.equals("0")) {
                semanticErrorsList.add("Error: Can't showo columns, table <strong>" + idTable + "</strong> doesn't exists. Line: " + ctx.start.getLine());
            } else {
                successMessages.add("Successfull operation. <strong>SHOW COLUMNS</strong> in table " + idTable + ": " + result + "<br>");
            }

        } else {
            semanticErrorsList.add("Error: You haven't selected a DB yet, can't show column. Line: " + ctx.start.getLine());
        }



        return super.visitShow_cols_from(ctx);
    }

    /** 'INSERT' 'INTO' ID (ID (',' ID)*)* 'VALUES' (column_insert (',' column_insert)*) ';' */
    @Override
    public String visitInsert_into(SqlParser.Insert_intoContext ctx) {
        List<TerminalNode> ids = ctx.ID();
        ArrayList<ArrayList<String>> rowsToInsert = new ArrayList<>();

        // Get row info
        List<SqlParser.Column_insertContext> rows = ctx.column_insert();
        for (SqlParser.Column_insertContext row : rows) {
            ArrayList<String> data = new ArrayList<>();
            List<SqlParser.DataContext> values = row.data();

            for (SqlParser.DataContext value : values) {
                data.add(value.type.getText());
            }

            rowsToInsert.add(data);
        }


        // Table to insert values
        String tableId = ids.remove(0).getSymbol().getText();

        String result = symbolTable.insert(tableId, ids, rowsToInsert);
        if (symbolTable.temporalErrorMessage == null){
            successMessages.add(result);
        } else {
            semanticErrorsList.add(result + " Line: " + ctx.start.getLine());
        }

        return "void";
    }

    /** '(' (data (',' data)*) ')' */
    @Override
    public String visitColumn_insert(SqlParser.Column_insertContext ctx) {



        return super.visitColumn_insert(ctx);
    }

    /** 'UPDATE' ID 'SET' ID '=' data (',' ID '=' data)* ('WHERE' check_exp)? ';' */
    @Override
    public String visitUpdate(SqlParser.UpdateContext ctx) {
        // get info from AST
        List<TerminalNode> idList = ctx.ID();                                   // list of IDs
        List<SqlParser.DataContext> dataList = ctx.data();                      // list of data values

        if (ctx.check_exp() != null){
            newColumns = ((JSONObject) symbolTable.getTable(idList.get(0).getText()).get("columns"));
            newConditionPostFix = new JSONArray();
            String conditionResult = visit(ctx.check_exp());
            if (conditionResult.equals("error")){
                return "error";
            }
        }

        String res = symbolTable.update(idList, dataList, newConditionPostFix);

        return "void";
    }

    /** 'DELETE' 'FROM' ID ('WHERE' condition)* ';' */
    @Override
    public String visitDelete(SqlParser.DeleteContext ctx) {
        return super.visitDelete(ctx);
    }

    /**
     *         'SELECT' ('*' | ID (',' ID)*)
     *         from
     *         ('WHERE' (check_exp))?
     *         ('ORDER' 'BY' order_by_statement (',' order_by_statement)*)* ';'
     */
    @Override
    public String visitSelect(SqlParser.SelectContext ctx) {
        // Make shure we are using a DB
        JSONObject db = symbolTable.getDbInUse();
        if (db == null){
            semanticErrorsList.add("Error: Couldn't make SELECT operation. You haven't selected a DB. Line: " + ctx.start.getLine());
            return "error";
        }

        // Get the lists of ids
        List<TerminalNode> columnsIdNodes = ctx.ID();
        List<TerminalNode> tablesIdNodes = ctx.from().ID();

        // Get tables columns
        newTablesIds = new ArrayList<>();
        newColumns = new JSONObject();  // Restart variable to make shure its empty
        for (TerminalNode tableNode : tablesIdNodes) {
            String tableId = tableNode.getSymbol().getText();

            // Verify if table exists and get columns
            JSONObject table = (JSONObject) db.get(tableId);
            if (table == null){
                semanticErrorsList.add("Error: Couldn't complete SELECT operation, table <strong>" + tableId +"</strong> doesn't exists. Line: " + ctx.start.getLine());
                return "error";
            }

            newColumns.putAll((JSONObject) table.get("columns"));  // get all the columns together
            newTablesIds.add(tableId);
        }

        // Get SELECT columnsid
        newColumnsNames = new ArrayList<>();
        for (TerminalNode columnNode : columnsIdNodes) {
            String columnId = columnNode.getSymbol().getText();

            // Verify if column exists on columns of tables
            if (newColumns.get(columnId) == null){
                semanticErrorsList.add("Error: Couldn't complete SELECT operation, column <strong>" + columnId +"</strong> doesn't exists on cross product of <strong>" + newTablesIds.toString() +"</strong> . Line: " + ctx.start.getLine());
                return "error";
            }

            newColumnsNames.add(columnId);

        }

        // Get condition array
        newConditionPostFix = new JSONArray();
        String conditionResult = visit(ctx.check_exp());
        if (conditionResult.equals("error")){
            return "error";
        }

        // Get Order by
        newOrderBy = new ArrayList<>();  // get order by order (id, order)
        List<SqlParser.Order_by_statementContext> orderByList = ctx.order_by_statement();
        for (SqlParser.Order_by_statementContext ctx2 : orderByList) {
            conditionResult = visit(ctx2);
            if (conditionResult.equals("error")){
                return "error";
            }

        }
        Pair<ArrayList<String>, ArrayList<Queue<String>>> result =  symbolTable.fancySearch(newColumnsNames, newTablesIds, newConditionPostFix, newOrderBy);
        addDataToGrid(result.getKey(), result.getValue());



        return "void";
    }

    /** ID  op = ('ASC' | 'DESC') */
    @Override
    public String visitOrder_by_statement(SqlParser.Order_by_statementContext ctx) {
        // Get ID
        String id = ctx.ID().getSymbol().getText();

        // Verify if ID is on selected rows to show
        if (newColumnsNames.size() > 0){
            if (!newTablesIds.contains(id)){
                semanticErrorsList.add("Error: Couldn't complete SELECT operation, the column <strong>" + id +"</strong> in ORDER BY isn't in SELECT clause . Line: " + ctx.start.getLine());
                return "error";
            }
        } else {
            if (newColumns.get(id) == null){
                semanticErrorsList.add("Error: Couldn't complete SELECT operation, the column <strong>" + id +"</strong> ORDER BY doesn't exists in tables. Line: " + ctx.start.getLine());
                return "error";
            }
        }

        newOrderBy.add(new String[]{id, ctx.op.getText()});

        return "void";
    }

    /** ID rel_exp ID */
    @Override
    public String visitCondition(SqlParser.ConditionContext ctx) {
        return super.visitCondition(ctx);
    }

    /** 'RENAME' 'TO' ID */
    @Override
    public String visitAlter_rename(SqlParser.Alter_renameContext ctx) {
        return ctx.ID().getSymbol().getText();
    }

    /** 'ADD' 'COLUMN' ID data_type constraint     */
    @Override
    public String visitAddColumn(SqlParser.AddColumnContext ctx) {
        if (dbInUse != null){
            newTable = symbolTable.getTable(newTableName);

            // Load variables for creation
            newColumns = (JSONObject) newTable.get("columns");
            newConstraints = (JSONObject) newTable.get("constraints");

            // Initialize new ones
            newColumnName = ctx.ID().getSymbol().getText();
            newColumn = new JSONObject();
            newTypeColumn = visit(ctx.data_type());
            newColumn.put("type", newTypeColumn);
            newReferencedTables = new ArrayList<>();

            // Make shure table doesn't exists already
            if (newColumns.get(newColumnName) != null){
                semanticErrorsList.add("Error: Couldn't create column. Column <strong>" + newColumnName + "</strong> already exists. Line: " + ctx.start.getLine());
                return "error";
            }

            // Analyze columns for the table and add them to newColumn and all the constraints too
            String visit = "";
            if (ctx.constraint() != null){
                visit = visit(ctx.constraint());
            }

            if (visit.equals("error")) {
                semanticErrorsList.add("Error: Couldn't create column <strong>" + newColumnName + "</strong>. Line: " + ctx.start.getLine());
                return "error";
            }

            // Add newColumn to newColumns
            newColumns.put(newColumnName, newColumn);

            int createTableResult = symbolTable.addConstraintsAndRefreshJson(newTableName, newReferencedTables);

            if (createTableResult == 1) {
                successMessages.add("Successfully created column <strong>" + newColumnName + "</strong>.");
            } else if (createTableResult == 3) {
                semanticErrorsList.add("Error: Two foreign keys have the same ID. Couldn't create column <strong>" + newColumnName + "</strong>. Line: " + ctx.start.getLine());
            }
            return "void";
        } else {
            semanticErrorsList.add("Error: You haven't selected a DB yet, table wasn't created. Line: " + ctx.start.getLine());
            return "error";
        }
    }

    /** 'ADD' 'CONSTRAINT' constraint */
    @Override
    public String visitAddConstraint(SqlParser.AddConstraintContext ctx) {
        if (dbInUse != null){
            addingConstraint = true;
            newTable = symbolTable.getTable(newTableName);

            // Load variables for creation
            newColumns = (JSONObject) newTable.get("columns");
            newConstraints = (JSONObject) newTable.get("constraints");

            // Initialize new ones
            newColumnName = null;
            newColumn = null;
            newTypeColumn = null;
            newReferencedTables = new ArrayList<>();

            // Analyze columns for the table and add them to newColumn and all the constraints too
            String visit = visit(ctx.constraint());

            if (visit.equals("error")) {
                return "error";
            }

            int result = symbolTable.addConstraintsAndRefreshJson(newTableName, newReferencedTables);

            if (result == 1) {
                successMessages.add("Successfully created CONSTRAINT <strong>" + newColumnName + "</strong>.");
            } else if (result == 3) {
                semanticErrorsList.add("Error: Two foreign keys have the same ID. Couldn't create CONSTRAINT in table <strong>" + newTableName + "</strong>. Line: " + ctx.start.getLine());
            }

            addingConstraint = false;
            return "void";
        } else {
            semanticErrorsList.add("Error: You haven't selected a DB yet, table wasn't created. Line: " + ctx.start.getLine());
            return "error";
        }
    }

    /** 'DROP' 'COLUMN' ID */
    @Override
    public String visitDropColumn(SqlParser.DropColumnContext ctx) {
        String columnId = ctx.ID().getSymbol().getText();
        int result = symbolTable.dropColumn(newTableName, columnId);
        if (result == 0){
            semanticErrorsList.add("Error: Table <strong>" + newTableName + "</strong> doesn't exists in DB in use. Line: " + ctx.start.getLine());
            return "error";
        } else if (result == 1){
            semanticErrorsList.add("Error: You haven't selected a database yet. Line: " + ctx.start.getLine());
            return "error";
        } else if (result == 2){
            semanticErrorsList.add("Error: You can't delete the column <strong>" + columnId + "</strong> because it's being used by a CONSTRAINT. Drop the constraint if you want to delete the column. Line: " + ctx.start.getLine());
            return "error";
        }

        successMessages.add("Successfull operation. <strong>DROP COLUMN</strong> in table " + newTableName + ".");
        return "void";
    }

    /** 'DROP' 'CONSTRAINT' ID */
    @Override
    public String visitDropConstraint(SqlParser.DropConstraintContext ctx) {
        String constraintId = ctx.ID().getSymbol().getText();
        int result = symbolTable.dropConstraint(newTableName, constraintId);
        if (result == 0){
            semanticErrorsList.add("Error: Constraint <strong>" + constraintId + "</strong> doesn't exists. Line: " + ctx.start.getLine());
            return "error";
        } else if (result == 1){
            semanticErrorsList.add("Error: Table <strong>" + newTableName + "</strong> doesn't exists in DB in use. Line: " + ctx.start.getLine());
            return "error";
        } else if (result == 2){
            semanticErrorsList.add("Error: You haven't selected a database yet. Line: " + ctx.start.getLine());
            return "error";
        }

        successMessages.add("Successfull operation. <strong>DROP CONSTRAINT</strong> in table " + newTableName + ".");
        return "void";
    }

    /** '(' table_element (',' table_element)* ')' */
    @Override
    public String visitTable_element_list(SqlParser.Table_element_listContext ctx) {
        List<SqlParser.Table_elementContext> elements = ctx.table_element();
        String result;
        for (SqlParser.Table_elementContext element: elements) {
            result = visit(element);
            if (result.equals("error")) return "error";
        }
        return "void";
    }

    /** ID data_type_def constraint */
    @Override
    public String visitTable_element(SqlParser.Table_elementContext ctx) {
        // Get column name
        newColumnName = ctx.ID().getSymbol().getText();

        // Verify that any other column doesn't have the same name
        Object columnObject = newColumns.get(newColumnName);
        if (columnObject != null) {
            semanticErrorsList.add("Error: Two columns can't have the same ID. Column repeated <strong>" + newColumnName + "</strong>. Line: " + ctx.start.getLine());
            return "error";
        }

        // Get props of new column
        newColumn = new JSONObject();
        String result = visit(ctx.data_type_def());

        if (result.equals("error")) return "error";  // Stop execution

        newColumn.put("type", newTypeColumn);

        // Visit constraints
        result = visit(ctx.constraint());
        if (result.equals("error")) return "error";  // Stop execution

        // Add newColumn to newColumns
        newColumns.put(newColumnName, newColumn);
        return "void";
    }

    /** data_type (length_constraint)? */
    @Override
    public String visitData_type_def(SqlParser.Data_type_defContext ctx) {
        // Get column type
        newTypeColumn = visit(ctx.data_type());

        // If is type CHAR fix a default size
        if (newTypeColumn.equals("CHAR")){
            newColumn.put("length", 1);
        }

        // Analyze constraint
        if (ctx.length_constraint() != null){
            String result = visit(ctx.length_constraint());  // this is going to add the column constraints
            if (result.equals("error")){
                return "error";  // Stop all the execution
            }
        }
        return "void";  // Return only the column type
    }


    @Override
    public String visitData_type(SqlParser.Data_typeContext ctx) {
        return ctx.start.getText();
    }

    /** ((column_constraint)? ('CONSTRAINT' keys_constraint)? | ('CONSTRAINT' keys_constraint)? (column_constraint)?)*/
    @Override
    public String visitConstraint(SqlParser.ConstraintContext ctx) {
        columnNullable = false;

        // Visit children
        if (ctx.column_constraint() != null){
            String result = visit(ctx.column_constraint());
            if (result.equals("error")) return "error";
        }

        if (ctx.keys_constraint() != null){
            String result = visit(ctx.keys_constraint());
            if (result.equals("error")) return "error";
        }

        return "void";
    }

    /** 'NOT' 'NULL' */
    @Override
    public String visitColumn_constraint(SqlParser.Column_constraintContext ctx) {
        // Add constraint to column
        columnNullable = true;
        if (!addingConstraint) newColumn.put("nullable", "false");
        else {
            semanticErrorsList.add("Error: You can't apply NOT NULL to a table. Line: " + ctx.getStart().getLine());
            return "error";
        }
        return "void";
    }

    /** ID 'PRIMARY' 'KEY' ('(' ID (',' ID)* ')')* */
    @Override
    public String visitPrimaryKey(SqlParser.PrimaryKeyContext ctx) {
        List<TerminalNode> ids = ctx.ID();

        // Analyse the constraint name, it should begin with PK_ and end with table name
        String constraintId = ids.get(0).getSymbol().getText();
        boolean correctName = constraintId.startsWith("PK_");
        boolean correctLastName = constraintId.startsWith(newTableName, 3);


        if (!correctName){
            semanticErrorsList.add("Error: By convention, the primary key of a table should begin with <strong>PK_</strong>. You can't use <strong>" + constraintId + "</strong>. Line: " + ctx.getStart().getLine());
            return "error";
        }

        if (!correctLastName){
            semanticErrorsList.add("Error: By convention, the primary key of a table should end with the name of the table, in this case: <strong>" + newTableName +"</strong>. You can't use <strong>" + constraintId + "</strong>. Line: " + ctx.getStart().getLine());
            return "error";
        }

        // Verify if primary key wasn't declared before
        if (newConstraints.get(constraintId) != null) {
            semanticErrorsList.add("Error: You can't declare more than one primary key. Line: " + ctx.getStart().getLine());
            return "error";
        }

        // If there are columnNames for the primary key, we have to verify that they exist
        String column;
        JSONArray columnsOfKey = new JSONArray();
        if (ids.size() > 1){
            for (int i = 1; i < ids.size(); i++) {
                column = ids.get(i).getSymbol().getText();  // get column

                // See if the column exists
                if ((newColumns.get(column) != null) || newColumnName.equals(column)){
                    columnsOfKey.add(column);
                } else {
                    semanticErrorsList.add("Error: Couldn't create PRIMARY KEY. The column <strong>" + column +"</strong> doesn't exists or hasn't been declared yet. Line: " + ctx.getStart().getLine());
                    return "error";
                }
            }
        } else {
            columnsOfKey.add(newColumnName);
        }
        newConstraints.put(constraintId, columnsOfKey);
        return "void";
    }

    /** ID 'FOREIGN' 'KEY' ('(' ID (',' ID)* ')')* 'REFERENCES' ID ('(' ID (',' ID)* ')')**/
    @Override
    public String visitForeignKey(SqlParser.ForeignKeyContext ctx) {
        List<TerminalNode> ids = ctx.ID();
        List<TerminalNode> referencesIds = ctx.foreignKeyReferences().ID();

        // If we are just adding a Constraint, it's a MUST to specify the column names
        if (addingConstraint){
            if (ids.size() <= 1){
                semanticErrorsList.add("Error: Couldn't add foreign key. You must specify which local columns you are trying to referenc, eg: 'FK_name FOREIGN KEY (localColumn) REFERENCES...'. Line: " + ctx.getStart().getLine());
                return "error";
            }
        }

        // Analyse the constraint name, it should begin with FK_ and
        String constraintId = ids.get(0).getSymbol().getText();
        boolean correctName = constraintId.startsWith("FK_");

        if (!correctName){
            semanticErrorsList.add("Error: By convention, a foreign key should begin with <strong>FK_</strong>. You can't use <strong>" + constraintId + "</strong>. Line: " + ctx.getStart().getLine());
            return "error";
        }

        // See if table referenced exists
        String referencedTableId = referencesIds.get(0).getSymbol().getText();
        JSONObject referencedTable = symbolTable.getTable(referencedTableId);
        if (referencedTable == null){
            semanticErrorsList.add("Error: The table <strong>" + referencedTableId +"</strong> which you are trying to reference doesn't exists. Line: " + ctx.getStart().getLine());
            return "error";
        } else {
            String[] reference = {referencedTableId, constraintId};
            newReferencedTables.add(reference);
        }

        // Verify references between both tables
        // First: both lists of column ID's should have the same size
        JSONArray referencedPrimaryKey = symbolTable.getPrimaryKey(referencedTableId);  // get primary key of referenced table
        JSONObject referencedTableColumns = ((JSONObject) referencedTable.get("columns"));
        int sizeReferenced;
        // If they didn't specified the columns of the key
        if (referencesIds.size() == 1){
            // Obtain size of primary key of referenced table
            if (ids.size() > 1) sizeReferenced = referencedPrimaryKey.size() + 1;
            else sizeReferenced = referencedPrimaryKey.size();
        } else {
            if (ids.size() > 1) sizeReferenced = referencesIds.size();
            else sizeReferenced = referencesIds.size() - 1;
        }

        if (ids.size() != sizeReferenced){
            semanticErrorsList.add("Error: Number of referenced columns don't match with tables of FOREIGN KEY. Line: " + ctx.getStart().getLine());
            return "error";
        }

        // Second: If there are columnNames for the foreign key, we have to verify that they exist in actual table and in referenced table
        String column, referencedColumn;
        JSONObject actualColumnJson, referencedColumnJson;
        JSONArray columnsOfKey = new JSONArray();
        JSONArray referencedColumns = new JSONArray();
        boolean usingActualColumn;
        if (ids.size() > 1){
            if (referencesIds.size() > 1){
                // CASE 1: Columns specified on both parts
                for (int i = 1; i < ids.size(); i++) {
                    // Get columns name
                    column = ids.get(i).getSymbol().getText();
                    referencedColumn = referencesIds.get(i).getSymbol().getText();

                    // See if the column exists in actual table
                    usingActualColumn = column.equals(newColumnName);
                    if ((newColumns.get(column) == null) && !usingActualColumn){
                        semanticErrorsList.add("Error: Couldn't create FOREIGN KEY. The column <strong>" + column +"</strong> doesn't exists in table <strong>" + newTableName + "</strong>. Line: " + ctx.getStart().getLine());
                        return "error";
                    }

                    // See if columns exists in referenced table
                    if (referencedTableColumns.get(referencedColumn) == null){
                        semanticErrorsList.add("Error: Couldn't create FOREIGN KEY. The column <strong>" + referencedColumn + "</strong> doesn't exists in table <strong>" + referencedTableId + "</strong>. Line: " + ctx.getStart().getLine());
                        return "error";
                    }

                    // See if the types are the same
                    String actualColumnJsonType;
                    // referenced column type
                    referencedColumnJson = (JSONObject) referencedTableColumns.get(referencedColumn);
                    String referencedColumnJsonType = (String) referencedColumnJson.get("type");
                    // local column
                    if (!usingActualColumn){
                        // Get column
                        actualColumnJson = (JSONObject) newColumns.get(column);
                        actualColumnJsonType = (String) actualColumnJson.get("type");
                    } else {
                        actualColumnJsonType = newTypeColumn;
                    }

                    if (!actualColumnJsonType.equals(referencedColumnJsonType)){
                        semanticErrorsList.add("Error: Couldn't create FOREIGN KEY because types don't match. The column <strong>" + column + "</strong> is <strong>" + actualColumnJsonType + "</strong>, while the column <strong>" + referencedColumn + "</strong> is <strong>" + referencedColumnJsonType + "</strong>. Line: " + ctx.getStart().getLine());
                        return "error";
                    }

                    // Add columns to Json
                    referencedColumns.add(referencedColumn);
                    columnsOfKey.add(column);
                }
            } else {
                // CASE 2: Columns not specified on referenced, we must get primary key of referenced table
                for (int i = 1; i < ids.size(); i++) {
                    // Get columns name
                    column = ids.get(i).getSymbol().getText();
                    referencedColumn = (String) referencedPrimaryKey.get(i - 1);

                    // See if the column exists in actual table
                    usingActualColumn = column.equals(newColumnName);
                    if ((newColumns.get(column) == null) && !usingActualColumn){
                        semanticErrorsList.add("Error: Couldn't create FOREIGN KEY. The column <strong>" + column +"</strong> doesn't exists in table <strong>" + newTableName + "</strong>. Line: " + ctx.getStart().getLine());
                        return "error";
                    }

                    // See if columns exists in referenced table
                    Object columns = referencedTableColumns.get(referencedColumn);
                    if (columns == null){
                        semanticErrorsList.add("Error: Couldn't create FOREIGN KEY. The column <strong>" + referencedColumn + "</strong> doesn't exists in table <strong>" + referencedTableId + "</strong>. Line: " + ctx.getStart().getLine());
                        return "error";
                    }

                    // See if the types are the same
                    // local column
                    String actualColumnJsonType;
                    if (!usingActualColumn){
                        // Get column
                        actualColumnJson = (JSONObject) newColumns.get(column);
                        actualColumnJsonType = (String) actualColumnJson.get("type");
                    } else {
                        actualColumnJsonType = newTypeColumn;
                    }
                    // referenced column type
                    referencedColumnJson = (JSONObject) columns;
                    String referencedColumnJsonType = (String) referencedColumnJson.get("type");

                    if (!actualColumnJsonType.equals(referencedColumnJsonType)){
                        semanticErrorsList.add("Error: Couldn't create FOREIGN KEY because types don't match. The column <strong>" + column + "</strong> is <strong>" + actualColumnJsonType + "</strong>, while the column <strong>" + referencedColumn + "</strong> is <strong>" + referencedColumnJsonType + "</strong>. Line: " + ctx.getStart().getLine());
                        return "error";
                    }

                    // Add columns to Json
                    referencedColumns.add(referencedColumn);
                    columnsOfKey.add(column);
                }
            }
        }

        else {
            if (referencesIds.size() == 1){
                // CASE 3: If primary key of referenced table is composite, show error
                if (referencedPrimaryKey.size() > 1){
                    semanticErrorsList.add("Error: Couldn't create FOREIGN KEY, because the number of referenced columns don't match. Tip: Maybe the primary key of the referenced table is composite. Line: " + ctx.getStart().getLine());
                    return "error";
                }

                // Verify types
                String columnReferencedId = (String) referencedPrimaryKey.get(0);
                String typePrimaryKey = (String)((JSONObject) referencedTableColumns.get(columnReferencedId)).get("type");

                if (!typePrimaryKey.equals(newTypeColumn)){
                    semanticErrorsList.add("Error: Couldn't create FOREIGN KEY because types don't match. The column <strong>" + newColumnName + "</strong> is <strong>" + newTypeColumn + "</strong>, while the column <strong>" + columnReferencedId + "</strong> is <strong>" + typePrimaryKey + "</strong>. Line: " + ctx.getStart().getLine());
                    return "error";
                }


                // Add columns to Json
                columnsOfKey.add(newColumnName);
                referencedColumns.add(columnReferencedId);
            } else {
                // CASE 4
                if (referencesIds.size() > 2){
                    semanticErrorsList.add("Error: Couldn't create FOREIGN KEY, because the number of referenced columns don't match. Line: " + ctx.getStart().getLine());
                    return "error";
                }

                // Get column reference name
                referencedColumn = referencesIds.get(1).getSymbol().getText();

                // See if columns exists in referenced table
                if (referencedTableColumns.get(referencedColumn) == null){
                    semanticErrorsList.add("Error: Couldn't create FOREIGN KEY. The column <strong>" + referencedColumn + "</strong> doesn't exists in table <strong>" + referencedTableId + "</strong>. Line: " + ctx.getStart().getLine());
                    return "error";
                }

                // See if the types are the same
                referencedColumnJson = (JSONObject) referencedTableColumns.get(referencedColumn);
                String actualColumnJsonType = newTypeColumn;
                String referencedColumnJsonType = (String) referencedColumnJson.get("type");
                if (!actualColumnJsonType.equals(referencedColumnJsonType)){
                    semanticErrorsList.add("Error: Couldn't create FOREIGN KEY because types don't match. The column <strong>" + newColumnName + "</strong> is <strong>" + actualColumnJsonType + "</strong>, while the column <strong>" + referencedColumn + "</strong> is <strong>" + referencedColumnJsonType + "</strong>. Line: " + ctx.getStart().getLine());
                    return "error";
                }

                // Add columns to Json
                referencedColumns.add(referencedColumn);
                columnsOfKey.add(newColumnName);


            }
        }
        JSONObject fkProps = new JSONObject();
        fkProps.put("columns", columnsOfKey);
        fkProps.put("referencedColumns", referencedColumns);
        fkProps.put("referencedTable", referencedTableId);
        newConstraints.put(constraintId, fkProps);
        return "void";
    }

    /** ID 'CHECK' '(' check_exp ')' */
    @Override
    public String visitCheck(SqlParser.CheckContext ctx) {
        String constraintId = ctx.ID().getSymbol().getText();

        if (!constraintId.startsWith("CH_")){
            semanticErrorsList.add("Error: By convention, the primary key of a table should begin with <strong>CH_</strong>. You can't use <strong>" + constraintId + "</strong>. Line: " + ctx.getStart().getLine());
            return "error";
        }

        // Verify if constraint wasn't declared before
        if (newConstraints.get(constraintId) != null) {
            semanticErrorsList.add("Error: There are two constraints with the same id <strong>" + constraintId +"</strong>. Line: " + ctx.getStart().getLine());
            return "error";
        }

        // Create array to save condition in postfix
        newConditionPostFix = new JSONArray();
        String conditionResult = visit(ctx.check_exp());

        if (conditionResult.equals("error")){
            return "error";
        }

        // Add check constraint to constraints of table
        newConstraints.put(constraintId, newConditionPostFix);
        return "void";
    }

    /** NOT check_exp */
    @Override
    public String visitNotExpr(SqlParser.NotExprContext ctx) {
        // First visit check_exp
        String result = visit(ctx.check_exp());
        if (result.equals("error")) return "error";

        // Add NOT operator
        newConditionPostFix.add("NOT");

        return "void";
    }

    /** check_exp OR check_exp  */
    @Override
    public String visitOrExpr(SqlParser.OrExprContext ctx) {
        // Visit subexpr1
        String result = visit(ctx.check_exp(0));
        if (result.equals("error")) return "error";

        // Visit subexpr2
        result = visit(ctx.check_exp(1));
        if (result.equals("error")) return "error";

        // Add OR operator
        newConditionPostFix.add("OR");
        return "void";
    }

    /** op1=(ID|FLOAT) rel_exp op2=(ID|FLOAT) */
    @Override
    public String visitRelExpr(SqlParser.RelExprContext ctx) {
        // Get operator symbol
        String symbol = ctx.rel_exp().getText();

        // Get operator 1
        Token op1Ctx = ctx.op1;
        String op1Type, op2Type, op1, op2;
        if (op1Ctx.getType() == SqlParser.ID){
            // It's an ID
            op1 = op1Ctx.getText();

            // Verify that column exists and get type
            Object columnObject = newColumns.get(op1);
            if (columnObject != null){
                op1Type = (String) ((JSONObject) columnObject).get("type");
            }

            else if (newColumnName.equals(op1)){
                op1Type = newTypeColumn;
            }

            else {
                semanticErrorsList.add("Error: Couldn't create CONSTRAINT CHECK, the column <strong>" + op1 +"</strong> doesn't exists. Line: " + ctx.getStart().getLine());
                return "error";
            }

            if (op1Type.equals("INT") || op1Type.equals("FLOAT")){
                op1Type = "NUM";
            }


            // Add to postfix
            newConditionPostFix.add(op1);
        } else {
            // TODO verificar que reconoce correctamente si es FLOAT/NUMBER/INT
            // It's a number
            Float number = Float.valueOf(op1Ctx.getText());
            op1 = String.valueOf(number);
            op1Type = "NUM";

            // Add to postfix
            newConditionPostFix.add(op1);
        }

        Token op2Ctx = ctx.op2;
        if (SqlParser.ID == op2Ctx.getType()){
            // It's an ID
            op2 = op2Ctx.getText();

            // Verify that column exists and get type
            Object columnObject = newColumns.get(op2);
            if (columnObject != null){
                op2Type = (String) ((JSONObject) columnObject).get("type");
            }

            else if (newColumnName.equals(op2)){
                op2Type = newTypeColumn;
            }

            else {
                semanticErrorsList.add("Error: Could not create CONSTRAINT CHECK, the column <strong>" + op2 +"</strong> doesn't exists. Line: " + ctx.getStart().getLine());
                return "error";
            }

            if (op1Type.equals("INT") || op1Type.equals("FLOAT")){
                op2Type = "NUM";
            }

            // Add to postfix
            newConditionPostFix.add(op2);
        } else {
            // It's a number
            Float number = Float.valueOf(op2Ctx.getText());
            op2 = String.valueOf(number);
            op2Type = "NUM";

            // Add to postfix
            newConditionPostFix.add(op2);
        }

        // Verify if types are comparable (They have to have the same type)
        if (!op1Type.equals(op2Type)){
            semanticErrorsList.add("Error: Could not create CONSTRAINT CHECK, the types aren't comparable in <strong>" + op1 + "(" + op1Type +  ")</strong> " + symbol + " <strong>" + op2 + "(" + op2Type +  ")</strong>. Line: " + ctx.getStart().getLine());
            return "error";
        }

        // Add symbol to postfix
        newConditionPostFix.add(symbol);
        return "void";
    }

    /** '(' check_exp ')'  */
    @Override
    public String visitParenExpr(SqlParser.ParenExprContext ctx) {
        return visit(ctx.check_exp());
    }

    /** check_exp AND check_exp */
    @Override
    public String visitAndExpr(SqlParser.AndExprContext ctx) {
        // Visit subexpr1
        String result = visit(ctx.check_exp(0));
        if (result.equals("error")) return "error";

        // Visit subexpr2
        result = visit(ctx.check_exp(1));
        if (result.equals("error")) return "error";

        // Add AND operator
        newConditionPostFix.add("AND");
        return "void";
    }

    /** '(' NUMBER ')' */
    @Override
    public String visitLength_constraint(SqlParser.Length_constraintContext ctx) {
        // Verify that column type is CHAR
        if (!newTypeColumn.equals("CHAR")){
            semanticErrorsList.add("Error: Column <strong>" + newColumnName + "</strong> can't have length. Only CHAR data type columns can. Line: " + ctx.getStart().getLine());
            return "error";
        }

        Integer length = Integer.valueOf(ctx.NUMBER().getSymbol().getText());

        // Make shure that size is bigger than 0
        if (length < 1){
            semanticErrorsList.add("Error: Column <strong>" + newColumnName + "</strong> length is invalid. It has to be bigger than 1. Line: " + ctx.getStart().getLine());
            return "error";
        }

        newColumn.put("length", length);
        return "void";
    }

    public List<String> getSemanticErrorsList() {
        return semanticErrorsList;
    }

    public List<String> getSuccessMessages() {
        return successMessages;
    }

    public boolean getMessageAnswer(){
        // TODO Use this method to display and receive answer on gui
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        return answer.equals("y");
    }

    private String loadLastUsedDb() {
        File lastDb = new File(lastDbUsedPath);
        if (lastDb.exists()){
            try {
                BufferedReader fileReader = new BufferedReader(new FileReader(lastDb));
                return fileReader.readLine();

            } catch (IOException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public String getDbInUse(){
        if (dbInUse != null){
            return dbInUse;
        } else {
            return "none";
        }
    }

    private void dropConfirmationDialog(String dbId, Integer records) {
        final Window confirmationWindow = new Window("Confirm action.");
        confirmationWindow.setHeight(25.0f, Sizeable.Unit.PERCENTAGE);
        confirmationWindow.setWidth(40.0f, Sizeable.Unit.PERCENTAGE);
        confirmationWindow.center();
        confirmationWindow.setResizable(false);

        Label infoLbl = new Label("Are you sure you want to delete database <strong>" + dbId + "</strong> which contains <strong>" + records + "</strong> records?", ContentMode.HTML);
        infoLbl.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);

        final VerticalLayout confVLayout = new VerticalLayout();
        confVLayout.setSizeFull();
        confVLayout.addComponent(infoLbl);
        confVLayout.setSpacing(true);

        // Confirmation buttons
        Button confirmBtn = new Button("Drop database");
        confirmBtn.setIcon(VaadinIcons.CHECK);
        confirmBtn.setSizeFull();
        confirmBtn.setStyleName("danger");

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setIcon(VaadinIcons.CLOSE);
        cancelBtn.setSizeFull();
        cancelBtn.setStyleName("primary");

        confirmBtn.addClickListener(event -> {
            // See if is dbInUse
            if (dbInUse.equals(dbId)){
                dbInUse = null;
            }

            // Delete from symbolTable
            symbolTable.deleteDb(dbId);

            successMessages.add("Database <strong>" + dbId + "</strong> was deleted</strong>.");
            Notification notification = new Notification("Success!", "Deleted database " + dbId);
            notification.setDelayMsec(2000);
            notification.setPosition(Position.TOP_CENTER);
            notification.show(Page.getCurrent());

            layout.getUI().getUI().removeWindow(confirmationWindow);
            Page.getCurrent().reload();
        });

        cancelBtn.addClickListener(event -> {
            Notification notification = new Notification("Canceled operation", "Alright boss!");
            notification.setDelayMsec(2000);
            notification.setPosition(Position.TOP_CENTER);
            notification.show(Page.getCurrent());
            layout.getUI().getUI().removeWindow(confirmationWindow);
        });

        final HorizontalLayout confBtnsLayout = new HorizontalLayout();
        confBtnsLayout.setSizeFull();
        confBtnsLayout.addComponents(cancelBtn, confirmBtn);
        confVLayout.addComponent(confBtnsLayout);

        confirmationWindow.setContent(confVLayout);

        layout.getUI().getUI().addWindow(confirmationWindow);
    }

    private void showNotificationDbInUse() {
        Notification notification = new Notification("Using dabatase " + getDbInUse(), "Initializing...");
        notification.setDelayMsec(2000);
        notification.setPosition(Position.TOP_CENTER);
        notification.show(Page.getCurrent());
    }

    public void loadDbMetadata() {
        symbolTable.loadDbMetadata(dbInUse, jsonParser);
    }

    public void setDbInUse(String db) {
        dbInUse = db;
    }

    public boolean hasSyntaxError() {
        return syntaxError;
    }

    public void setSyntaxError(boolean syntaxError) {
        this.syntaxError = syntaxError;
    }

    public void addToVerboseParser() {
        verboseParser.addAll(symbolTable.getVerboseParser());
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public void setConsolePanelLayout(Layout layout) {
        this.consolePanelLayout = layout;
    }

    public void refreshInfoLists() {
        this.semanticErrorsList.clear();
        this.verboseParser.clear();
        this.successMessages.clear();
    }

    public List<String> getVerboseParser() { return verboseParser;}

    // TODO to show data from trees

    /**
     * To show data from a query in a table
     * @param colNames, names for the columns, in order
     * @param rowList, List of Queues, each Queue is a Row, must contain data in order according to the order of colNames
     */
    private void addDataToGrid(List<String> colNames, List<Queue<String>> rowList) {
        showDataGrid = new Grid<>();
        showDataGrid.setWidth("100%");

        for (String colName : colNames) {
            showDataGrid.addColumn(row -> row.poll(), new HtmlRenderer()).setCaption(colName);
        }

        ListDataProvider<Queue<String>> dataProvider = DataProvider.ofCollection(rowList);
        showDataGrid.setDataProvider(dataProvider);

        consolePanelLayout.addComponent(showDataGrid);

        // TODO: ejemplo de como funciona mostrar los datos en la tabla borrar los comentarios al final de todo xD
//        List<String> colNames = new ArrayList<>();
//        colNames.add("Column 1");
//        colNames.add("Column 2");
//        colNames.add("Column 3");
//
//        for (String colName : colNames) {
//            showDataGrid.addColumn(event -> event.poll(), new HtmlRenderer()).setCaption(colName);
//        }
//
//        List<Queue<String>> rowList = new ArrayList<>();
//
//        Queue<String> row1 = new LinkedList<>();
//        Queue<String> row2 = new LinkedList<>();
//        Queue<String> row3 = new LinkedList<>();
//
//        row1.add("data1");
//        row1.add("data2");
//        row1.add("data3");
//
//        row2.add("data4");
//        row2.add("data5");
//        row2.add("data6");
//
//        row3.add("data7");
//        row3.add("data8");
//        row3.add("data9");
//
//        rowList.add(row1);
//        rowList.add(row2);
//        rowList.add(row3);
//
//        ListDataProvider<Queue<String>> dataProvider = DataProvider.ofCollection(rowList);
//        showDataGrid.setDataProvider(dataProvider);
//
//        consolePanelLayout.addComponent(showDataGrid);
    }
}
