package com.cusbromen.semanticControl;
import com.cusbromen.antlr.SqlBaseVisitor;
import com.cusbromen.antlr.SqlParser;

import java.util.ArrayList;
import java.util.List;

public class Visitor extends SqlBaseVisitor<String> {
    private List<String> semanticErrorsList; // list for semantic errors found

    public Visitor() {
        this.semanticErrorsList = new ArrayList<>();
    }

    @Override
    public String visitExpression(SqlParser.ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    @Override
    public String visitCreate_database(SqlParser.Create_databaseContext ctx) {
        return super.visitCreate_database(ctx);
    }

    @Override
    public String visitAlter_database(SqlParser.Alter_databaseContext ctx) {
        return super.visitAlter_database(ctx);
    }

    @Override
    public String visitDrop_database(SqlParser.Drop_databaseContext ctx) {
        return super.visitDrop_database(ctx);
    }

    @Override
    public String visitShow_databases(SqlParser.Show_databasesContext ctx) {
        return super.visitShow_databases(ctx);
    }

    @Override
    public String visitUse_database(SqlParser.Use_databaseContext ctx) {
        return super.visitUse_database(ctx);
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

    public void setSemanticErrorsList(List<String> semanticErrorsList) {
        this.semanticErrorsList = semanticErrorsList;
    }
}
