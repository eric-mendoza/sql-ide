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
        symbolTable.readMetadata(dbsJsonPath, jsonParser);  // Load the metadata of dbs before working
        dbInUse = loadLastUsedDb();
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
            semanticErrorsList.add("Error: Database '" + id + "' already exists! Line: " + ctx.start.getLine());
            return "error";
        }

        successMessages.add("Message: Successful creation of database '" + id + "'.");
        return "void";
    }

    /** 'ALTER' 'DATABASE' ID 'RENAME' 'TO' ID ';' */
    @Override
    public String visitAlter_database(SqlParser.Alter_databaseContext ctx) {
        String oldName = ctx.ID(0).getSymbol().getText();
        String newName = ctx.ID(1).getSymbol().getText();

        int result = symbolTable.renameDb(oldName, newName);

        if (result == 1){
            semanticErrorsList.add("Error: The database '" + oldName + "' doesn't exists! Line: " + ctx.start.getLine());
            return "error";
        }

        else if (result == 2){
            semanticErrorsList.add("Error: The database '" + newName + "' already exists! Line: " + ctx.start.getLine());
            return "error";
        }

        successMessages.add("Message: Database successfully renamed from '" + oldName + "' to '" + newName + "'.");
        return "void";
    }

    @Override
    public String visitDrop_database(SqlParser.Drop_databaseContext ctx) {
        return super.visitDrop_database(ctx);
    }

    @Override
    public String visitShow_databases(SqlParser.Show_databasesContext ctx) {
        return super.visitShow_databases(ctx);
    }

    /** 'USE' 'DATABASE' ID ';' */
    @Override
    public String visitUse_database(SqlParser.Use_databaseContext ctx) {
        String db = ctx.ID().getSymbol().getText();
        if (symbolTable.dbExists(db)){
            if (!db.equals(dbInUse)){
                dbInUse = db;
                successMessages.add("Message: You are using database '" + db + "' now.");

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

                } catch (IOException e){
                    return "error";
                }

                return "void";
            } else {
                successMessages.add("Message: Database '" + db + "' was already in use.");
                return "void";
            }

        } else {
            semanticErrorsList.add("Error: Database '" + db + "' doesn't exists! Line: " + ctx.start.getLine());
            return "error";
        }
    }

    @Override
    public String visitCreate_table(SqlParser.Create_tableContext ctx) {
        return super.visitCreate_table(ctx);
    }

    @Override
    public String visitAlter_table(SqlParser.Alter_tableContext ctx) {
        return super.visitAlter_table(ctx);
    }

    @Override
    public String visitDrop_table(SqlParser.Drop_tableContext ctx) {
        return super.visitDrop_table(ctx);
    }

    @Override
    public String visitShow_tables(SqlParser.Show_tablesContext ctx) {
        return super.visitShow_tables(ctx);
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

    @Override
    public String visitAlter_rename(SqlParser.Alter_renameContext ctx) {
        return super.visitAlter_rename(ctx);
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

    public boolean hasSyntaxError() {
        return syntaxError;
    }

    public void setSyntaxError(boolean syntaxError) {
        this.syntaxError = syntaxError;
    }
}
