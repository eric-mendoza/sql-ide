package com.cusbromen.semanticControl;
import com.cusbromen.antlr.SqlBaseVisitor;
import com.cusbromen.antlr.SqlParser;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@SuppressWarnings("unchecked") // JSON's fault
public class Visitor extends SqlBaseVisitor<String> {
    private List<String> semanticErrorsList, successMessages, verboseParser; // list for semantic errors found
    private JSONParser jsonParser;
    private JSONObject newTable, newColumns, newColumn, newConstraints;  // This is going to be used to construct a table step by step
    private String dbsJsonPath, dbInUse, newTableName, newColumnName, newTypeColumn;
    private SymbolTableHashMap symbolTable;
    private String lastDbUsedPath = "metadata/lastDbUsed";
    private boolean syntaxError;

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

        // TODO rename solo mueve el archivo de metadata, no el futuro Btree, hay que hacerlo recursivo para mover todos
        int result = symbolTable.renameDb(oldName, newName);

        if (result == 1){
            semanticErrorsList.add("Database <strong>" + oldName + "</strong> doesn't exist. Line: " + ctx.start.getLine());
            return "error";
        }

        else if (result == 2){
            semanticErrorsList.add("Database <strong>" + newName + "</strong>  already exists. Line: " + ctx.start.getLine());
            return "error";
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
        Long records = 0L;
        for (String tableId : tableNames) {
            table = (JSONObject) db.get(tableId);
            records += (Long) table.get("noRecords");
        }

        // Warning message TODO: This message has to be displayed on the gui, use the delete input function
        System.out.println("Are you sure you want to delete database '" + dbId + "' which contains '" + records + "' records? (y/n)");
        boolean delete = getMessageAnswer();

        if (delete){
            // See if is dbInUse
            if (dbInUse.equals(dbId)){
                dbInUse = null;
            }

            // Delete from symbolTable
            symbolTable.deleteDb(dbId);

            successMessages.add("Database <strong>" + dbId + "</strong> was deleted</strong>.");
        } else {
            successMessages.add("Database <strong>" + dbId + "</strong> wasn't deleted</strong>.");
        }

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

            // Analyze columns for the table and add them to newColumn and all the constraints too
            String visit = visit(ctx.table_element_list());
            if (visit.equals("error")) {
                semanticErrorsList.add("Error: Couldn't create table <strong>" + newTableName + "</strong>. Line: " + ctx.start.getLine());
                return "error";
            }

            newTable.put("columns", newColumns);
            newTable.put("constraints", newConstraints);


            int createTableResult = symbolTable.createTable(newTableName, newTable);

            if (createTableResult == 0) {
                semanticErrorsList.add("Database <strong>" + dbInUse + "</strong> doesn't exist. Line: " + ctx.start.getLine());
            } else if (createTableResult == 1) {
                successMessages.add("Successfully created table <strong>" + newTableName + "</strong>.");
            } else if (createTableResult == 2) {
                semanticErrorsList.add("Error: Table <strong>" + newTableName + "</strong> already exists in database <strong>" + dbInUse + "</strong>. Line: " + ctx.start.getLine());
            }
            return "void";
        } else {
            semanticErrorsList.add("Error: You haven't selected a DB yet, table wasn't created. Line: " + ctx.start.getLine());
            return "error";
        }
    }

    /** 'ALTER' 'TABLE' (alter_rename | alter_action) ';' */
    @Override
    public String visitAlter_table(SqlParser.Alter_tableContext ctx) {

        if (ctx.alter_rename() != null){
            String[] names = visit(ctx.alter_rename()).split(" ");
            String oldName = names[0];
            String newName = names[1];

            int renameResult = symbolTable.renameTable(dbInUse, oldName, newName);

            if (renameResult == 0) {
                semanticErrorsList.add("Error: You haven't selected a DB yet, table wasn't renamed. Line: " + ctx.start.getLine());
            } else if (renameResult == 1) {
                successMessages.add("Successfully renamed table <strong>" + oldName + "</strong> to <strong>" + newName + "</strong>.");
            } else if (renameResult == 2) {
                semanticErrorsList.add("The table <strong>" + newName + "</strong> already exists in database <strong>" + dbInUse + "</strong>.");
            } else if (renameResult == 3) {
                semanticErrorsList.add("The table y<strong>" + oldName + "</strong> does not exist in database <strong>" + dbInUse + "</strong>.");
            }

        } else {
            int alterTableResult = symbolTable.alterTable(dbInUse);


        }

        return "void";
    }

    /**'DROP' 'TABLE' ID ';'*/
    @Override
    public String visitDrop_table(SqlParser.Drop_tableContext ctx) {
        String tableId = ctx.ID().getSymbol().getText();



        return super.visitDrop_table(ctx);
    }

    /** 'SHOW' 'TABLES' ';' */
    @Override
    public String visitShow_tables(SqlParser.Show_tablesContext ctx) {
        String returnedVal = symbolTable.showTables(dbInUse);

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
        return super.visitShow_cols_from(ctx);
    }

    /** 'INSERT' 'INTO' ID (ID (',' ID)*)* 'VALUES' (data_type (',' data_type)*) ';' */
    @Override
    public String visitInsert_into(SqlParser.Insert_intoContext ctx) {
        return super.visitInsert_into(ctx);
    }

    /** 'UPDATE' ID 'SET' ID '=' (',' ID)* ('WHERE' condition)* ';' */
    @Override
    public String visitUpdate(SqlParser.UpdateContext ctx) {
        return super.visitUpdate(ctx);
    }

    /** 'DELETE' 'FROM' ID ('WHERE' condition)* ';' */
    @Override
    public String visitDelete(SqlParser.DeleteContext ctx) {
        return super.visitDelete(ctx);
    }

    /** 'SELECT' ('*' | ID (',' ID)*)
     'FROM' ID (',' ID)*
     'WHERE' (condition)
     ('ORDER' 'BY' order_by_statement (',' order_by_statement)*)* ';'
     */
    @Override
    public String visitSelect(SqlParser.SelectContext ctx) {
        return super.visitSelect(ctx);
    }

    /** ID ('ASC' | 'DESC') */
    @Override
    public String visitOrder_by_statement(SqlParser.Order_by_statementContext ctx) {
        return super.visitOrder_by_statement(ctx);
    }

    /** ID rel_exp ID */
    @Override
    public String visitCondition(SqlParser.ConditionContext ctx) {
        return super.visitCondition(ctx);
    }

    /** ID 'RENAME' 'TO' ID */
    @Override
    public String visitAlter_rename(SqlParser.Alter_renameContext ctx) {
        return ctx.ID(0).getSymbol().getText() + " " + ctx.ID(1).getSymbol().getText();
    }

    /** ID action */
    @Override
    public String visitAlter_action(SqlParser.Alter_actionContext ctx) {
        return super.visitAlter_action(ctx);
    }

    /** 'ADD' 'COLUMN' ID ('CONSTRAINT' c_constraint)* */
    @Override
    public String visitAddColumn(SqlParser.AddColumnContext ctx) {
        return super.visitAddColumn(ctx);
    }

    /** 'ADD' 'CONSTRAINT' c_constraint */
    @Override
    public String visitAddConstraint(SqlParser.AddConstraintContext ctx) {
        return super.visitAddConstraint(ctx);
    }

    /** 'DROP' 'COLUMN' ID */
    @Override
    public String visitDropColumn(SqlParser.DropColumnContext ctx) {
        return super.visitDropColumn(ctx);
    }

    /** 'DROP' 'CONSTRAINT' ID */
    @Override
    public String visitDropConstraint(SqlParser.DropConstraintContext ctx) {
        return super.visitDropConstraint(ctx);
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

    /** ID data_type_def (column_constraint)? */
    @Override
    public String visitTable_element(SqlParser.Table_elementContext ctx) {
        // Get column name
        newColumnName = ctx.ID().getSymbol().getText();

        // Get props of new column
        newColumn = new JSONObject();
        newTypeColumn = visit(ctx.data_type_def());

        if (newTypeColumn.equals("error")) return "error";  // Stop execution

        newColumn.put("type", newTypeColumn);

        SqlParser.Column_constraintContext constraint = ctx.column_constraint();
        if (constraint != null){
            newColumn.put("constraint", visit(constraint));
        }

        // Add newColumn to newColumns
        newColumns.put(newColumnName, newColumn);
        return "void";
    }

    /** data_type ('CONSTRAINT' c_constraint)? */
    @Override
    public String visitData_type_def(SqlParser.Data_type_defContext ctx) {
        // Analyze table constraints
        SqlParser.C_constraintContext tableConstraint = ctx.c_constraint();
        if (tableConstraint != null){
            String result = visit(tableConstraint);  // this is going to add the table constraints
            if (result.equals("error")){
                return "error";  // Stop all the execution
            }
        }
        return visit(ctx.data_type());  // Return only the column type
    }


    @Override
    public String visitData_type(SqlParser.Data_typeContext ctx) {
        return ctx.start.getText();
    }

    /** keys_constraint | length_constraint*/
    @Override
    public String visitC_constraint(SqlParser.C_constraintContext ctx) {
        SqlParser.Keys_constraintContext keys_constraintContext = ctx.keys_constraint();
        if (keys_constraintContext != null){
            return visit(keys_constraintContext);
        } else {
            return visit(ctx.length_constraint());
        }
    }

    /** 'NOT' 'NULL' */
    @Override
    public String visitColumn_constraint(SqlParser.Column_constraintContext ctx) {
        return "NOT NULL";
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

        // Analyse the constraint name, it should begin with FK_ and end with table name
        String constraintId = ids.get(0).getSymbol().getText();
        boolean correctName = constraintId.startsWith("FK_");
        boolean correctLastName = constraintId.startsWith(newTableName, 3);

        if (!correctName){
            semanticErrorsList.add("Error: By convention, a foreign key should begin with <strong>FK_</strong>. You can't use <strong>" + constraintId + "</strong>. Line: " + ctx.getStart().getLine());
            return "error";
        }

        if (!correctLastName){
            semanticErrorsList.add("Error: By convention, a foreign key should end with the name of the table, in this case: <strong>" + newTableName +"</strong>. You can't use <strong>" + constraintId + "</strong>. Line: " + ctx.getStart().getLine());
            return "error";
        }

        // See if table of reference exists
        String referencedTableId = referencesIds.get(0).getSymbol().getText();
        JSONObject referencedTable = symbolTable.getTable(referencedTableId);
        if (referencedTable == null){
            semanticErrorsList.add("Error: The table <strong>" + referencedTableId +"</strong> which you are trying to reference doesn't exists. Line: " + ctx.getStart().getLine());
            return "error";
        } else {
            // TODO Add reference prop to referenced table
        }

        // Verify references between both tables
        // First: both lists of column ID's should have the same size
        JSONArray referencedPrimaryKey = symbolTable.getPrimaryKey(referencedTableId);
        JSONObject referencedTableColumns = ((JSONObject) referencedTable.get("columns"));
        int sizeReferenced;
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
        if (ids.size() > 1){
            if (referencesIds.size() > 1){
                // Columns specified on both parts
                for (int i = 1; i < ids.size(); i++) {
                    // Get columns name
                    column = ids.get(i).getSymbol().getText();
                    referencedColumn = referencesIds.get(i).getSymbol().getText();

                    // See if the column exists in actual table
                    if ((newColumns.get(column) == null) || newColumnName.equals(column)){
                        semanticErrorsList.add("Error: Couldn't create FOREIGN KEY. The column <strong>" + column +"</strong> doesn't exists in table <strong>" + newTableName + "</strong>. Line: " + ctx.getStart().getLine());
                        return "error";
                    }

                    // See if columns exists in referenced table
                    if (referencedTableColumns.get(referencedColumn) == null){
                        semanticErrorsList.add("Error: Couldn't create FOREIGN KEY. The column <strong>" + referencedColumn + "</strong> doesn't exists in table <strong>" + referencedTableId + "</strong>. Line: " + ctx.getStart().getLine());
                        return "error";
                    }

                    // See if the types are the same
                    actualColumnJson = (JSONObject) newColumns.get(column);
                    referencedColumnJson = (JSONObject) referencedTableColumns.get(referencedColumn);
                    String actualColumnJsonType = (String) actualColumnJson.get("type");
                    String referencedColumnJsonType = (String) referencedColumnJson.get("type");
                    if (!actualColumnJsonType.equals(referencedColumnJsonType)){
                        semanticErrorsList.add("Error: Couldn't create FOREIGN KEY because types don't match. The column <strong>" + column + "</strong> is <strong>" + actualColumnJsonType + "</strong>, while the column <strong>" + referencedColumn + "</strong> is <strong>" + referencedColumnJsonType + "</strong>. Line: " + ctx.getStart().getLine());
                        return "error";
                    }

                    // Add columns to Json
                    referencedColumns.add(referencedColumn);
                    columnsOfKey.add(column);
                }
            } else {
                // Columns not specified on referenced, we must get primary key of referenced table
                for (int i = 1; i < ids.size(); i++) {
                    // Get columns name
                    column = ids.get(i).getSymbol().getText();
                    referencedColumn = (String) referencedPrimaryKey.get(i - 1);

                    // See if the column exists in actual table
                    if ((newColumns.get(column) == null) || newColumnName.equals(column)){
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
                    actualColumnJson = (JSONObject) newColumns.get(column);
                    referencedColumnJson = (JSONObject) columns;
                    String actualColumnJsonType = (String) actualColumnJson.get("type");
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
                // If primary key of referenced table is composite, show error
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
        return super.visitCheck(ctx);
    }

    /** (ID | NUMBER) (logic_exp | rel_exp) (ID | NUMBER) */
    @Override
    public String visitCheck_exp(SqlParser.Check_expContext ctx) {
        return super.visitCheck_exp(ctx);
    }

    /** '(' NUMBER ')' */
    @Override
    public String visitLength_constraint(SqlParser.Length_constraintContext ctx) {
        return ctx.NUMBER().getSymbol().getText();
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

    public List<String> getVerboseParser() { return verboseParser;}
}
