// Generated from /home/emendoza/IdeaProjects/sql-ide/src/main/java/sql.g4 by ANTLR 4.7
package com.cusbromen.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link sqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface sqlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link sqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(sqlParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#create_database}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_database(sqlParser.Create_databaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#alter_database}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter_database(sqlParser.Alter_databaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#drop_database}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrop_database(sqlParser.Drop_databaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#show_databases}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_databases(sqlParser.Show_databasesContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#use_database}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUse_database(sqlParser.Use_databaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#create_table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_table(sqlParser.Create_tableContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#alter_table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter_table(sqlParser.Alter_tableContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#drop_table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrop_table(sqlParser.Drop_tableContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#show_tables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_tables(sqlParser.Show_tablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#show_cols_from}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_cols_from(sqlParser.Show_cols_fromContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#insert_into}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_into(sqlParser.Insert_intoContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#update}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate(sqlParser.UpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#delete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete(sqlParser.DeleteContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#select}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect(sqlParser.SelectContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#order_by_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrder_by_statement(sqlParser.Order_by_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(sqlParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#alter_rename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter_rename(sqlParser.Alter_renameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#alter_action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter_action(sqlParser.Alter_actionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addColumn}
	 * labeled alternative in {@link sqlParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddColumn(sqlParser.AddColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addConstraint}
	 * labeled alternative in {@link sqlParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddConstraint(sqlParser.AddConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropColumn}
	 * labeled alternative in {@link sqlParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropColumn(sqlParser.DropColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropConstraint}
	 * labeled alternative in {@link sqlParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropConstraint(sqlParser.DropConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#table_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_name(sqlParser.Table_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#table_element_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_element_list(sqlParser.Table_element_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#table_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_element(sqlParser.Table_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#data_type_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_type_def(sqlParser.Data_type_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#data_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_type(sqlParser.Data_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#column_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_constraint(sqlParser.Column_constraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#c_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_constraint(sqlParser.C_constraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryKey}
	 * labeled alternative in {@link sqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryKey(sqlParser.PrimaryKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code foreignKey}
	 * labeled alternative in {@link sqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeignKey(sqlParser.ForeignKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code check}
	 * labeled alternative in {@link sqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheck(sqlParser.CheckContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#check_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheck_exp(sqlParser.Check_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#logic_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic_exp(sqlParser.Logic_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#rel_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRel_exp(sqlParser.Rel_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#length_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLength_constraint(sqlParser.Length_constraintContext ctx);
}