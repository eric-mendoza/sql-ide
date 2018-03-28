package com.cusbromen.semanticControl;
import com.cusbromen.antlr.SqlBaseVisitor;
import com.cusbromen.antlr.SqlParser;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Visitor extends SqlBaseVisitor<String> {
    private List<String> semanticErrorsList, successMessages; // list for semantic errors found
    private JSONParser jsonParser;
    private String dbsJsonPath, dbInUse;
    private SymbolTableHashMap symbolTable;
    private String lastDbUsedPath = "metadata/lastDbUsed";
    private boolean syntaxError;

    public Visitor() {
        this.semanticErrorsList = new ArrayList<>();
        successMessages = new ArrayList<>();
        jsonParser = new JSONParser();  // Used to read an existing JSON file
        dbsJsonPath = "metadata/dbs.json";
        symbolTable = new SymbolTableHashMap();
        dbInUse = loadLastUsedDb();
        symbolTable.readMetadata(dbsJsonPath, jsonParser, dbInUse);  // Load the metadata of dbs before working
        syntaxError = false;

        // TODO: Este mensaje tendrá que ser mostrado por la gui
        System.out.println("DB in use: " + getDbInUse());
    }

    @Override
    public String visitExpression(SqlParser.ExpressionContext ctx) {
        return super.visitExpression(ctx);
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

    @Override
    public String visitDrop_database(SqlParser.Drop_databaseContext ctx) {
        return super.visitDrop_database(ctx);
    }

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

                    // Load database metadata
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
            semanticErrorsList.add("Database <strong>" + db + "</strong> doesn't exist. Line: " + ctx.start.getLine());
            return "error";
        }
    }

    /** 'CREATE' 'TABLE' table_name table_element_list ';' */
    @Override
    public String visitCreate_table(SqlParser.Create_tableContext ctx) {
        String tableName = ctx.table_name().ID(0).getText();
        List<SqlParser.Table_elementContext> elementsList = ctx.table_element_list().table_element();
        int createTableResult = symbolTable.createTable(dbInUse, tableName, elementsList, jsonParser);

        if (createTableResult == 0) {
            semanticErrorsList.add("Database <strong>" + dbInUse + "</strong> doesn't exist. Line: " + ctx.start.getLine());
        } else if (createTableResult == 1) {
            successMessages.add("Successfully created table <strong>" + ctx.table_name().ID(0).getText() + "</strong>.");
        } else if (createTableResult == 2) {
            semanticErrorsList.add("Error: Table <strong>" + ctx.table_name().ID(0).getText() + "</strong> already exists in database <strong>" + dbInUse + "</strong>.");
        } else if (createTableResult == 3){
            semanticErrorsList.add("Error: You haven't selected a DB yet, table wasn't created. Line: " + ctx.start.getLine());
        }
        return "void";
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

            if (alterTableResult == 0) {
                semanticErrorsList.add("Error: You haven't selected a DB yet, table wasn't altered. Line: " + ctx.start.getLine());
            } else if (alterTableResult == 1) {
                successMessages.add("Successfull operation.");
            } else if (alterTableResult == 2) {
                semanticErrorsList.add("The table you are trying to create already exists in database <strong>" + dbInUse + "</strong>.");
            } else if (alterTableResult == 3) {
                semanticErrorsList.add("The table you are trying to rename does not exist in database <strong>" + dbInUse + "</strong>.");
            }
        }

        return "void";
    }

    @Override
    public String visitDrop_table(SqlParser.Drop_tableContext ctx) {
        return super.visitDrop_table(ctx);
    }

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

    @Override
    public String visitShow_cols_from(SqlParser.Show_cols_fromContext ctx) {
        return super.visitShow_cols_from(ctx);
    }

    @Override
    public String visitInsert_into(SqlParser.Insert_intoContext ctx) {
        return super.visitInsert_into(ctx);
    }

    @Override
    public String visitUpdate(SqlParser.UpdateContext ctx) {
        return super.visitUpdate(ctx);
    }

    @Override
    public String visitDelete(SqlParser.DeleteContext ctx) {
        return super.visitDelete(ctx);
    }

    @Override
    public String visitSelect(SqlParser.SelectContext ctx) {
        return super.visitSelect(ctx);
    }

    @Override
    public String visitOrder_by_statement(SqlParser.Order_by_statementContext ctx) {
        return super.visitOrder_by_statement(ctx);
    }

    @Override
    public String visitCondition(SqlParser.ConditionContext ctx) {
        return super.visitCondition(ctx);
    }

    /** ID 'RENAME' 'TO' ID */
    @Override
    public String visitAlter_rename(SqlParser.Alter_renameContext ctx) {
        return ctx.ID(0).getSymbol().getText() + " " + ctx.ID(1).getSymbol().getText();
    }

    @Override
    public String visitAlter_action(SqlParser.Alter_actionContext ctx) {
        return super.visitAlter_action(ctx);
    }

    @Override
    public String visitAddColumn(SqlParser.AddColumnContext ctx) {
        return super.visitAddColumn(ctx);
    }

    @Override
    public String visitAddConstraint(SqlParser.AddConstraintContext ctx) {
        return super.visitAddConstraint(ctx);
    }

    @Override
    public String visitDropColumn(SqlParser.DropColumnContext ctx) {
        return super.visitDropColumn(ctx);
    }

    @Override
    public String visitDropConstraint(SqlParser.DropConstraintContext ctx) {
        return super.visitDropConstraint(ctx);
    }

    @Override
    public String visitTable_name(SqlParser.Table_nameContext ctx) {
        return super.visitTable_name(ctx);
    }

    @Override
    public String visitTable_element_list(SqlParser.Table_element_listContext ctx) {
        return super.visitTable_element_list(ctx);
    }

    @Override
    public String visitTable_element(SqlParser.Table_elementContext ctx) {
        return super.visitTable_element(ctx);
    }

    @Override
    public String visitData_type_def(SqlParser.Data_type_defContext ctx) {
        return super.visitData_type_def(ctx);
    }

    @Override
    public String visitData_type(SqlParser.Data_typeContext ctx) {
        return super.visitData_type(ctx);
    }

    @Override
    public String visitColumn_constraint(SqlParser.Column_constraintContext ctx) {
        return super.visitColumn_constraint(ctx);
    }

    @Override
    public String visitC_constraint(SqlParser.C_constraintContext ctx) {
        return super.visitC_constraint(ctx);
    }

    @Override
    public String visitPrimaryKey(SqlParser.PrimaryKeyContext ctx) {
        return super.visitPrimaryKey(ctx);
    }

    @Override
    public String visitForeignKey(SqlParser.ForeignKeyContext ctx) {
        return super.visitForeignKey(ctx);
    }

    @Override
    public String visitCheck(SqlParser.CheckContext ctx) {
        return super.visitCheck(ctx);
    }

    @Override
    public String visitCheck_exp(SqlParser.Check_expContext ctx) {
        return super.visitCheck_exp(ctx);
    }

    @Override
    public String visitLogic_exp(SqlParser.Logic_expContext ctx) {
        return super.visitLogic_exp(ctx);
    }

    @Override
    public String visitRel_exp(SqlParser.Rel_expContext ctx) {
        return super.visitRel_exp(ctx);
    }

    @Override
    public String visitLength_constraint(SqlParser.Length_constraintContext ctx) {
        return super.visitLength_constraint(ctx);
    }

    public List<String> getSemanticErrorsList() {
        return semanticErrorsList;
    }

    public List<String> getSuccessMessages() {
        return successMessages;
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
}
