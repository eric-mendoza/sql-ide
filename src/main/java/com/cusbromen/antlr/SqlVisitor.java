// Generated from /home/emendoza/IdeaProjects/sql-ide/src/main/java/Sql.g4 by ANTLR 4.7
package com.cusbromen.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SqlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SqlParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#create_database}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_database(SqlParser.Create_databaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#alter_database}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter_database(SqlParser.Alter_databaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#drop_database}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrop_database(SqlParser.Drop_databaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#show_databases}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_databases(SqlParser.Show_databasesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#use_database}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUse_database(SqlParser.Use_databaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#create_table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_table(SqlParser.Create_tableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#alter_table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter_table(SqlParser.Alter_tableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#drop_table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrop_table(SqlParser.Drop_tableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#show_tables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_tables(SqlParser.Show_tablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#show_cols_from}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_cols_from(SqlParser.Show_cols_fromContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#insert_into}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_into(SqlParser.Insert_intoContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#update}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate(SqlParser.UpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#delete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete(SqlParser.DeleteContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#select}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect(SqlParser.SelectContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#order_by_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrder_by_statement(SqlParser.Order_by_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(SqlParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#alter_rename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlter_rename(SqlParser.Alter_renameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addColumn}
	 * labeled alternative in {@link SqlParser#alter_action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddColumn(SqlParser.AddColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addConstraint}
	 * labeled alternative in {@link SqlParser#alter_action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddConstraint(SqlParser.AddConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropColumn}
	 * labeled alternative in {@link SqlParser#alter_action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropColumn(SqlParser.DropColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropConstraint}
	 * labeled alternative in {@link SqlParser#alter_action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropConstraint(SqlParser.DropConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#table_element_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_element_list(SqlParser.Table_element_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#table_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_element(SqlParser.Table_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#data_type_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_type_def(SqlParser.Data_type_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#data_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_type(SqlParser.Data_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#column_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_constraint(SqlParser.Column_constraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraint(SqlParser.ConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryKey}
	 * labeled alternative in {@link SqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryKey(SqlParser.PrimaryKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code foreignKey}
	 * labeled alternative in {@link SqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeignKey(SqlParser.ForeignKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code check}
	 * labeled alternative in {@link SqlParser#keys_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheck(SqlParser.CheckContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#foreignKeyReferences}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeignKeyReferences(SqlParser.ForeignKeyReferencesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link SqlParser#check_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(SqlParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link SqlParser#check_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(SqlParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relExpr}
	 * labeled alternative in {@link SqlParser#check_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExpr(SqlParser.RelExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link SqlParser#check_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(SqlParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link SqlParser#check_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(SqlParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#rel_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRel_exp(SqlParser.Rel_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link SqlParser#length_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLength_constraint(SqlParser.Length_constraintContext ctx);
}