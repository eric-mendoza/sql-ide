// Generated from /home/emendoza/IdeaProjects/sql-ide/src/main/java/sql.g4 by ANTLR 4.7
package com.cusbromen.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link sqlParser}.
 */
public interface sqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link sqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(sqlParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(sqlParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#create_database}.
	 * @param ctx the parse tree
	 */
	void enterCreate_database(sqlParser.Create_databaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#create_database}.
	 * @param ctx the parse tree
	 */
	void exitCreate_database(sqlParser.Create_databaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#alter_database}.
	 * @param ctx the parse tree
	 */
	void enterAlter_database(sqlParser.Alter_databaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#alter_database}.
	 * @param ctx the parse tree
	 */
	void exitAlter_database(sqlParser.Alter_databaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#drop_database}.
	 * @param ctx the parse tree
	 */
	void enterDrop_database(sqlParser.Drop_databaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#drop_database}.
	 * @param ctx the parse tree
	 */
	void exitDrop_database(sqlParser.Drop_databaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#show_databases}.
	 * @param ctx the parse tree
	 */
	void enterShow_databases(sqlParser.Show_databasesContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#show_databases}.
	 * @param ctx the parse tree
	 */
	void exitShow_databases(sqlParser.Show_databasesContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#use_database}.
	 * @param ctx the parse tree
	 */
	void enterUse_database(sqlParser.Use_databaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#use_database}.
	 * @param ctx the parse tree
	 */
	void exitUse_database(sqlParser.Use_databaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#create_table}.
	 * @param ctx the parse tree
	 */
	void enterCreate_table(sqlParser.Create_tableContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#create_table}.
	 * @param ctx the parse tree
	 */
	void exitCreate_table(sqlParser.Create_tableContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#alter_table}.
	 * @param ctx the parse tree
	 */
	void enterAlter_table(sqlParser.Alter_tableContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#alter_table}.
	 * @param ctx the parse tree
	 */
	void exitAlter_table(sqlParser.Alter_tableContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#drop_table}.
	 * @param ctx the parse tree
	 */
	void enterDrop_table(sqlParser.Drop_tableContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#drop_table}.
	 * @param ctx the parse tree
	 */
	void exitDrop_table(sqlParser.Drop_tableContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#show_tables}.
	 * @param ctx the parse tree
	 */
	void enterShow_tables(sqlParser.Show_tablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#show_tables}.
	 * @param ctx the parse tree
	 */
	void exitShow_tables(sqlParser.Show_tablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#show_cols_from}.
	 * @param ctx the parse tree
	 */
	void enterShow_cols_from(sqlParser.Show_cols_fromContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#show_cols_from}.
	 * @param ctx the parse tree
	 */
	void exitShow_cols_from(sqlParser.Show_cols_fromContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#insert_into}.
	 * @param ctx the parse tree
	 */
	void enterInsert_into(sqlParser.Insert_intoContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#insert_into}.
	 * @param ctx the parse tree
	 */
	void exitInsert_into(sqlParser.Insert_intoContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#update}.
	 * @param ctx the parse tree
	 */
	void enterUpdate(sqlParser.UpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#update}.
	 * @param ctx the parse tree
	 */
	void exitUpdate(sqlParser.UpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#delete}.
	 * @param ctx the parse tree
	 */
	void enterDelete(sqlParser.DeleteContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#delete}.
	 * @param ctx the parse tree
	 */
	void exitDelete(sqlParser.DeleteContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#select}.
	 * @param ctx the parse tree
	 */
	void enterSelect(sqlParser.SelectContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#select}.
	 * @param ctx the parse tree
	 */
	void exitSelect(sqlParser.SelectContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#order_by_statement}.
	 * @param ctx the parse tree
	 */
	void enterOrder_by_statement(sqlParser.Order_by_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#order_by_statement}.
	 * @param ctx the parse tree
	 */
	void exitOrder_by_statement(sqlParser.Order_by_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(sqlParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(sqlParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#alter_rename}.
	 * @param ctx the parse tree
	 */
	void enterAlter_rename(sqlParser.Alter_renameContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#alter_rename}.
	 * @param ctx the parse tree
	 */
	void exitAlter_rename(sqlParser.Alter_renameContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#alter_action}.
	 * @param ctx the parse tree
	 */
	void enterAlter_action(sqlParser.Alter_actionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#alter_action}.
	 * @param ctx the parse tree
	 */
	void exitAlter_action(sqlParser.Alter_actionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addColumn}
	 * labeled alternative in {@link sqlParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAddColumn(sqlParser.AddColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addColumn}
	 * labeled alternative in {@link sqlParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAddColumn(sqlParser.AddColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addConstraint}
	 * labeled alternative in {@link sqlParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAddConstraint(sqlParser.AddConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addConstraint}
	 * labeled alternative in {@link sqlParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAddConstraint(sqlParser.AddConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropColumn}
	 * labeled alternative in {@link sqlParser#action}.
	 * @param ctx the parse tree
	 */
	void enterDropColumn(sqlParser.DropColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropColumn}
	 * labeled alternative in {@link sqlParser#action}.
	 * @param ctx the parse tree
	 */
	void exitDropColumn(sqlParser.DropColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropConstraint}
	 * labeled alternative in {@link sqlParser#action}.
	 * @param ctx the parse tree
	 */
	void enterDropConstraint(sqlParser.DropConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropConstraint}
	 * labeled alternative in {@link sqlParser#action}.
	 * @param ctx the parse tree
	 */
	void exitDropConstraint(sqlParser.DropConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(sqlParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(sqlParser.Table_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#table_element_list}.
	 * @param ctx the parse tree
	 */
	void enterTable_element_list(sqlParser.Table_element_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#table_element_list}.
	 * @param ctx the parse tree
	 */
	void exitTable_element_list(sqlParser.Table_element_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#table_element}.
	 * @param ctx the parse tree
	 */
	void enterTable_element(sqlParser.Table_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#table_element}.
	 * @param ctx the parse tree
	 */
	void exitTable_element(sqlParser.Table_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#data_type_def}.
	 * @param ctx the parse tree
	 */
	void enterData_type_def(sqlParser.Data_type_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#data_type_def}.
	 * @param ctx the parse tree
	 */
	void exitData_type_def(sqlParser.Data_type_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#data_type}.
	 * @param ctx the parse tree
	 */
	void enterData_type(sqlParser.Data_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#data_type}.
	 * @param ctx the parse tree
	 */
	void exitData_type(sqlParser.Data_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#column_constraint}.
	 * @param ctx the parse tree
	 */
	void enterColumn_constraint(sqlParser.Column_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#column_constraint}.
	 * @param ctx the parse tree
	 */
	void exitColumn_constraint(sqlParser.Column_constraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#c_constraint}.
	 * @param ctx the parse tree
	 */
	void enterC_constraint(sqlParser.C_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#c_constraint}.
	 * @param ctx the parse tree
	 */
	void exitC_constraint(sqlParser.C_constraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryKey}
	 * labeled alternative in {@link sqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryKey(sqlParser.PrimaryKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryKey}
	 * labeled alternative in {@link sqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryKey(sqlParser.PrimaryKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code foreignKey}
	 * labeled alternative in {@link sqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 */
	void enterForeignKey(sqlParser.ForeignKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code foreignKey}
	 * labeled alternative in {@link sqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 */
	void exitForeignKey(sqlParser.ForeignKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code check}
	 * labeled alternative in {@link sqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 */
	void enterCheck(sqlParser.CheckContext ctx);
	/**
	 * Exit a parse tree produced by the {@code check}
	 * labeled alternative in {@link sqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 */
	void exitCheck(sqlParser.CheckContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#check_exp}.
	 * @param ctx the parse tree
	 */
	void enterCheck_exp(sqlParser.Check_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#check_exp}.
	 * @param ctx the parse tree
	 */
	void exitCheck_exp(sqlParser.Check_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#logic_exp}.
	 * @param ctx the parse tree
	 */
	void enterLogic_exp(sqlParser.Logic_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#logic_exp}.
	 * @param ctx the parse tree
	 */
	void exitLogic_exp(sqlParser.Logic_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#rel_exp}.
	 * @param ctx the parse tree
	 */
	void enterRel_exp(sqlParser.Rel_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#rel_exp}.
	 * @param ctx the parse tree
	 */
	void exitRel_exp(sqlParser.Rel_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link sqlParser#length_constraint}.
	 * @param ctx the parse tree
	 */
	void enterLength_constraint(sqlParser.Length_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link sqlParser#length_constraint}.
	 * @param ctx the parse tree
	 */
	void exitLength_constraint(sqlParser.Length_constraintContext ctx);
}