package com.cusbromen.semanticControl;
import com.cusbromen.antlr.sqlBaseVisitor;
import com.cusbromen.antlr.sqlParser;

import java.util.ArrayList;
import java.util.List;

public class Visitor extends sqlBaseVisitor<String> {
    private List<String> semanticErrorsList; // list for semantic errors found

    public Visitor() {
        this.semanticErrorsList = new ArrayList<>();
    }

    @Override
    public String visitExpression(sqlParser.ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    /** 'CREATE' 'DATABASE' ID ';' */
    @Override
    public String visitCreate_database(sqlParser.Create_databaseContext ctx) {
        return super.visitCreate_database(ctx);
    }

    @Override
    public String visitAlter_database(sqlParser.Alter_databaseContext ctx) {
        return super.visitAlter_database(ctx);
    }

    @Override
    public String visitDrop_database(sqlParser.Drop_databaseContext ctx) {
        return super.visitDrop_database(ctx);
    }

    @Override
    public String visitShow_databases(sqlParser.Show_databasesContext ctx) {
        return super.visitShow_databases(ctx);
    }

    @Override
    public String visitUse_database(sqlParser.Use_databaseContext ctx) {
        return super.visitUse_database(ctx);
    }

    @Override
    public String visitCreate_table(sqlParser.Create_tableContext ctx) {
        return super.visitCreate_table(ctx);
    }

    @Override
    public String visitAlter_table(sqlParser.Alter_tableContext ctx) {
        return super.visitAlter_table(ctx);
    }

    @Override
    public String visitDrop_table(sqlParser.Drop_tableContext ctx) {
        return super.visitDrop_table(ctx);
    }

    @Override
    public String visitShow_tables(sqlParser.Show_tablesContext ctx) {
        return super.visitShow_tables(ctx);
    }

    @Override
    public String visitShow_cols_from(sqlParser.Show_cols_fromContext ctx) {
        return super.visitShow_cols_from(ctx);
    }

    @Override
    public String visitInsert_into(sqlParser.Insert_intoContext ctx) {
        return super.visitInsert_into(ctx);
    }

    @Override
    public String visitUpdate(sqlParser.UpdateContext ctx) {
        return super.visitUpdate(ctx);
    }

    @Override
    public String visitDelete(sqlParser.DeleteContext ctx) {
        return super.visitDelete(ctx);
    }

    @Override
    public String visitSelect(sqlParser.SelectContext ctx) {
        return super.visitSelect(ctx);
    }

    @Override
    public String visitOrder_by_statement(sqlParser.Order_by_statementContext ctx) {
        return super.visitOrder_by_statement(ctx);
    }

    @Override
    public String visitCondition(sqlParser.ConditionContext ctx) {
        return super.visitCondition(ctx);
    }

    @Override
    public String visitAlter_rename(sqlParser.Alter_renameContext ctx) {
        return super.visitAlter_rename(ctx);
    }

    @Override
    public String visitAlter_action(sqlParser.Alter_actionContext ctx) {
        return super.visitAlter_action(ctx);
    }

    @Override
    public String visitAddColumn(sqlParser.AddColumnContext ctx) {
        return super.visitAddColumn(ctx);
    }

    @Override
    public String visitAddConstraint(sqlParser.AddConstraintContext ctx) {
        return super.visitAddConstraint(ctx);
    }

    @Override
    public String visitDropColumn(sqlParser.DropColumnContext ctx) {
        return super.visitDropColumn(ctx);
    }

    @Override
    public String visitDropConstraint(sqlParser.DropConstraintContext ctx) {
        return super.visitDropConstraint(ctx);
    }

    @Override
    public String visitTable_name(sqlParser.Table_nameContext ctx) {
        return super.visitTable_name(ctx);
    }

    @Override
    public String visitTable_element_list(sqlParser.Table_element_listContext ctx) {
        return super.visitTable_element_list(ctx);
    }

    @Override
    public String visitTable_element(sqlParser.Table_elementContext ctx) {
        return super.visitTable_element(ctx);
    }

    @Override
    public String visitData_type_def(sqlParser.Data_type_defContext ctx) {
        return super.visitData_type_def(ctx);
    }

    @Override
    public String visitData_type(sqlParser.Data_typeContext ctx) {
        return super.visitData_type(ctx);
    }

    @Override
    public String visitColumn_constraint(sqlParser.Column_constraintContext ctx) {
        return super.visitColumn_constraint(ctx);
    }

    @Override
    public String visitC_constraint(sqlParser.C_constraintContext ctx) {
        return super.visitC_constraint(ctx);
    }

    @Override
    public String visitPrimaryKey(sqlParser.PrimaryKeyContext ctx) {
        return super.visitPrimaryKey(ctx);
    }

    @Override
    public String visitForeignKey(sqlParser.ForeignKeyContext ctx) {
        return super.visitForeignKey(ctx);
    }

    @Override
    public String visitCheck(sqlParser.CheckContext ctx) {
        return super.visitCheck(ctx);
    }

    @Override
    public String visitCheck_exp(sqlParser.Check_expContext ctx) {
        return super.visitCheck_exp(ctx);
    }

    @Override
    public String visitLogic_exp(sqlParser.Logic_expContext ctx) {
        return super.visitLogic_exp(ctx);
    }

    @Override
    public String visitRel_exp(sqlParser.Rel_expContext ctx) {
        return super.visitRel_exp(ctx);
    }

    @Override
    public String visitLength_constraint(sqlParser.Length_constraintContext ctx) {
        return super.visitLength_constraint(ctx);
    }

    public List<String> getSemanticErrorsList() {
        return semanticErrorsList;
    }

    public void setSemanticErrorsList(List<String> semanticErrorsList) {
        this.semanticErrorsList = semanticErrorsList;
    }
}
