// Generated from /home/emendoza/IdeaProjects/sql-ide/src/main/java/Sql.g4 by ANTLR 4.7
package com.cusbromen.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SqlParser}.
 */
public interface SqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SqlParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SqlParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#create_database}.
	 * @param ctx the parse tree
	 */
	void enterCreate_database(SqlParser.Create_databaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#create_database}.
	 * @param ctx the parse tree
	 */
	void exitCreate_database(SqlParser.Create_databaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#alter_database}.
	 * @param ctx the parse tree
	 */
	void enterAlter_database(SqlParser.Alter_databaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#alter_database}.
	 * @param ctx the parse tree
	 */
	void exitAlter_database(SqlParser.Alter_databaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#drop_database}.
	 * @param ctx the parse tree
	 */
	void enterDrop_database(SqlParser.Drop_databaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#drop_database}.
	 * @param ctx the parse tree
	 */
	void exitDrop_database(SqlParser.Drop_databaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#show_databases}.
	 * @param ctx the parse tree
	 */
	void enterShow_databases(SqlParser.Show_databasesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#show_databases}.
	 * @param ctx the parse tree
	 */
	void exitShow_databases(SqlParser.Show_databasesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#use_database}.
	 * @param ctx the parse tree
	 */
	void enterUse_database(SqlParser.Use_databaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#use_database}.
	 * @param ctx the parse tree
	 */
	void exitUse_database(SqlParser.Use_databaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#create_table}.
	 * @param ctx the parse tree
	 */
	void enterCreate_table(SqlParser.Create_tableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#create_table}.
	 * @param ctx the parse tree
	 */
	void exitCreate_table(SqlParser.Create_tableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#alter_table}.
	 * @param ctx the parse tree
	 */
	void enterAlter_table(SqlParser.Alter_tableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#alter_table}.
	 * @param ctx the parse tree
	 */
	void exitAlter_table(SqlParser.Alter_tableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#drop_table}.
	 * @param ctx the parse tree
	 */
	void enterDrop_table(SqlParser.Drop_tableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#drop_table}.
	 * @param ctx the parse tree
	 */
	void exitDrop_table(SqlParser.Drop_tableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#show_tables}.
	 * @param ctx the parse tree
	 */
	void enterShow_tables(SqlParser.Show_tablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#show_tables}.
	 * @param ctx the parse tree
	 */
	void exitShow_tables(SqlParser.Show_tablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#show_cols_from}.
	 * @param ctx the parse tree
	 */
	void enterShow_cols_from(SqlParser.Show_cols_fromContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#show_cols_from}.
	 * @param ctx the parse tree
	 */
	void exitShow_cols_from(SqlParser.Show_cols_fromContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#insert_into}.
	 * @param ctx the parse tree
	 */
	void enterInsert_into(SqlParser.Insert_intoContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#insert_into}.
	 * @param ctx the parse tree
	 */
	void exitInsert_into(SqlParser.Insert_intoContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#update}.
	 * @param ctx the parse tree
	 */
	void enterUpdate(SqlParser.UpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#update}.
	 * @param ctx the parse tree
	 */
	void exitUpdate(SqlParser.UpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#delete}.
	 * @param ctx the parse tree
	 */
	void enterDelete(SqlParser.DeleteContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#delete}.
	 * @param ctx the parse tree
	 */
	void exitDelete(SqlParser.DeleteContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#select}.
	 * @param ctx the parse tree
	 */
	void enterSelect(SqlParser.SelectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#select}.
	 * @param ctx the parse tree
	 */
	void exitSelect(SqlParser.SelectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#order_by_statement}.
	 * @param ctx the parse tree
	 */
	void enterOrder_by_statement(SqlParser.Order_by_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#order_by_statement}.
	 * @param ctx the parse tree
	 */
	void exitOrder_by_statement(SqlParser.Order_by_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(SqlParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(SqlParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#alter_rename}.
	 * @param ctx the parse tree
	 */
	void enterAlter_rename(SqlParser.Alter_renameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#alter_rename}.
	 * @param ctx the parse tree
	 */
	void exitAlter_rename(SqlParser.Alter_renameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#alter_action}.
	 * @param ctx the parse tree
	 */
	void enterAlter_action(SqlParser.Alter_actionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#alter_action}.
	 * @param ctx the parse tree
	 */
	void exitAlter_action(SqlParser.Alter_actionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addColumn}
	 * labeled alternative in {@link SqlParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAddColumn(SqlParser.AddColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addColumn}
	 * labeled alternative in {@link SqlParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAddColumn(SqlParser.AddColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addConstraint}
	 * labeled alternative in {@link SqlParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAddConstraint(SqlParser.AddConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addConstraint}
	 * labeled alternative in {@link SqlParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAddConstraint(SqlParser.AddConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropColumn}
	 * labeled alternative in {@link SqlParser#action}.
	 * @param ctx the parse tree
	 */
	void enterDropColumn(SqlParser.DropColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropColumn}
	 * labeled alternative in {@link SqlParser#action}.
	 * @param ctx the parse tree
	 */
	void exitDropColumn(SqlParser.DropColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropConstraint}
	 * labeled alternative in {@link SqlParser#action}.
	 * @param ctx the parse tree
	 */
	void enterDropConstraint(SqlParser.DropConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropConstraint}
	 * labeled alternative in {@link SqlParser#action}.
	 * @param ctx the parse tree
	 */
	void exitDropConstraint(SqlParser.DropConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(SqlParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(SqlParser.Table_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#table_element_list}.
	 * @param ctx the parse tree
	 */
	void enterTable_element_list(SqlParser.Table_element_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#table_element_list}.
	 * @param ctx the parse tree
	 */
	void exitTable_element_list(SqlParser.Table_element_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#table_element}.
	 * @param ctx the parse tree
	 */
	void enterTable_element(SqlParser.Table_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#table_element}.
	 * @param ctx the parse tree
	 */
	void exitTable_element(SqlParser.Table_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#data_type_def}.
	 * @param ctx the parse tree
	 */
	void enterData_type_def(SqlParser.Data_type_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#data_type_def}.
	 * @param ctx the parse tree
	 */
	void exitData_type_def(SqlParser.Data_type_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#data_type}.
	 * @param ctx the parse tree
	 */
	void enterData_type(SqlParser.Data_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#data_type}.
	 * @param ctx the parse tree
	 */
	void exitData_type(SqlParser.Data_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#column_constraint}.
	 * @param ctx the parse tree
	 */
	void enterColumn_constraint(SqlParser.Column_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#column_constraint}.
	 * @param ctx the parse tree
	 */
	void exitColumn_constraint(SqlParser.Column_constraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#c_constraint}.
	 * @param ctx the parse tree
	 */
	void enterC_constraint(SqlParser.C_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#c_constraint}.
	 * @param ctx the parse tree
	 */
	void exitC_constraint(SqlParser.C_constraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryKey}
	 * labeled alternative in {@link SqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryKey(SqlParser.PrimaryKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryKey}
	 * labeled alternative in {@link SqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryKey(SqlParser.PrimaryKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code foreignKey}
	 * labeled alternative in {@link SqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 */
	void enterForeignKey(SqlParser.ForeignKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code foreignKey}
	 * labeled alternative in {@link SqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 */
	void exitForeignKey(SqlParser.ForeignKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code check}
	 * labeled alternative in {@link SqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 */
	void enterCheck(SqlParser.CheckContext ctx);
	/**
	 * Exit a parse tree produced by the {@code check}
	 * labeled alternative in {@link SqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 */
	void exitCheck(SqlParser.CheckContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#check_exp}.
	 * @param ctx the parse tree
	 */
	void enterCheck_exp(SqlParser.Check_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#check_exp}.
	 * @param ctx the parse tree
	 */
	void exitCheck_exp(SqlParser.Check_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#logic_exp}.
	 * @param ctx the parse tree
	 */
	void enterLogic_exp(SqlParser.Logic_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#logic_exp}.
	 * @param ctx the parse tree
	 */
	void exitLogic_exp(SqlParser.Logic_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#rel_exp}.
	 * @param ctx the parse tree
	 */
	void enterRel_exp(SqlParser.Rel_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#rel_exp}.
	 * @param ctx the parse tree
	 */
	void exitRel_exp(SqlParser.Rel_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqlParser#length_constraint}.
	 * @param ctx the parse tree
	 */
	void enterLength_constraint(SqlParser.Length_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqlParser#length_constraint}.
	 * @param ctx the parse tree
	 */
	void exitLength_constraint(SqlParser.Length_constraintContext ctx);
}