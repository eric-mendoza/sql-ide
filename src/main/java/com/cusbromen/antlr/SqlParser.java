// Generated from /home/emendoza/IdeaProjects/sql-ide/src/main/java/Sql.g4 by ANTLR 4.7
package com.cusbromen.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		NUMBER=53, ID=54, NEWLINE=55, WHITESPACE=56, COMMENTS=57, ErrorCharacter=58;
	public static final int
		RULE_expression = 0, RULE_create_database = 1, RULE_alter_database = 2, 
		RULE_drop_database = 3, RULE_show_databases = 4, RULE_use_database = 5, 
		RULE_create_table = 6, RULE_alter_table = 7, RULE_drop_table = 8, RULE_show_tables = 9, 
		RULE_show_cols_from = 10, RULE_insert_into = 11, RULE_update = 12, RULE_delete = 13, 
		RULE_select = 14, RULE_order_by_statement = 15, RULE_condition = 16, RULE_alter_rename = 17, 
		RULE_alter_action = 18, RULE_action = 19, RULE_table_element_list = 20, 
		RULE_table_element = 21, RULE_data_type_def = 22, RULE_data_type = 23, 
		RULE_column_constraint = 24, RULE_c_constraint = 25, RULE_keys_constraint = 26, 
		RULE_foreignKeyReferences = 27, RULE_check_exp = 28, RULE_logic_exp = 29, 
		RULE_rel_exp = 30, RULE_length_constraint = 31;
	public static final String[] ruleNames = {
		"expression", "create_database", "alter_database", "drop_database", "show_databases", 
		"use_database", "create_table", "alter_table", "drop_table", "show_tables", 
		"show_cols_from", "insert_into", "update", "delete", "select", "order_by_statement", 
		"condition", "alter_rename", "alter_action", "action", "table_element_list", 
		"table_element", "data_type_def", "data_type", "column_constraint", "c_constraint", 
		"keys_constraint", "foreignKeyReferences", "check_exp", "logic_exp", "rel_exp", 
		"length_constraint"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'CREATE'", "'DATABASE'", "';'", "'ALTER'", "'RENAME'", "'TO'", 
		"'DROP'", "'SHOW'", "'DATABASES'", "'USE'", "'TABLE'", "'TABLES'", "'COLUMNS'", 
		"'FROM'", "'INSERT'", "'INTO'", "','", "'VALUES'", "'UPDATE'", "'SET'", 
		"'='", "'WHERE'", "'DELETE'", "'SELECT'", "'*'", "'ORDER'", "'BY'", "'ASC'", 
		"'DESC'", "'ADD'", "'COLUMN'", "'CONSTRAINT'", "'('", "')'", "'INT'", 
		"'FLOAT'", "'DATE'", "'CHAR'", "'NOT'", "'NULL'", "'PRIMARY'", "'KEY'", 
		"'FOREIGN'", "'CHECK'", "'REFERENCES'", "'AND'", "'OR'", "'<'", "'<='", 
		"'>'", "'>='", "'<>'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "NUMBER", "ID", "NEWLINE", "WHITESPACE", 
		"COMMENTS", "ErrorCharacter"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Sql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SqlParser.EOF, 0); }
		public List<Create_databaseContext> create_database() {
			return getRuleContexts(Create_databaseContext.class);
		}
		public Create_databaseContext create_database(int i) {
			return getRuleContext(Create_databaseContext.class,i);
		}
		public List<Alter_databaseContext> alter_database() {
			return getRuleContexts(Alter_databaseContext.class);
		}
		public Alter_databaseContext alter_database(int i) {
			return getRuleContext(Alter_databaseContext.class,i);
		}
		public List<Drop_databaseContext> drop_database() {
			return getRuleContexts(Drop_databaseContext.class);
		}
		public Drop_databaseContext drop_database(int i) {
			return getRuleContext(Drop_databaseContext.class,i);
		}
		public List<Show_databasesContext> show_databases() {
			return getRuleContexts(Show_databasesContext.class);
		}
		public Show_databasesContext show_databases(int i) {
			return getRuleContext(Show_databasesContext.class,i);
		}
		public List<Use_databaseContext> use_database() {
			return getRuleContexts(Use_databaseContext.class);
		}
		public Use_databaseContext use_database(int i) {
			return getRuleContext(Use_databaseContext.class,i);
		}
		public List<Create_tableContext> create_table() {
			return getRuleContexts(Create_tableContext.class);
		}
		public Create_tableContext create_table(int i) {
			return getRuleContext(Create_tableContext.class,i);
		}
		public List<Alter_tableContext> alter_table() {
			return getRuleContexts(Alter_tableContext.class);
		}
		public Alter_tableContext alter_table(int i) {
			return getRuleContext(Alter_tableContext.class,i);
		}
		public List<Drop_tableContext> drop_table() {
			return getRuleContexts(Drop_tableContext.class);
		}
		public Drop_tableContext drop_table(int i) {
			return getRuleContext(Drop_tableContext.class,i);
		}
		public List<Show_tablesContext> show_tables() {
			return getRuleContexts(Show_tablesContext.class);
		}
		public Show_tablesContext show_tables(int i) {
			return getRuleContext(Show_tablesContext.class,i);
		}
		public List<Show_cols_fromContext> show_cols_from() {
			return getRuleContexts(Show_cols_fromContext.class);
		}
		public Show_cols_fromContext show_cols_from(int i) {
			return getRuleContext(Show_cols_fromContext.class,i);
		}
		public List<Insert_intoContext> insert_into() {
			return getRuleContexts(Insert_intoContext.class);
		}
		public Insert_intoContext insert_into(int i) {
			return getRuleContext(Insert_intoContext.class,i);
		}
		public List<UpdateContext> update() {
			return getRuleContexts(UpdateContext.class);
		}
		public UpdateContext update(int i) {
			return getRuleContext(UpdateContext.class,i);
		}
		public List<DeleteContext> delete() {
			return getRuleContexts(DeleteContext.class);
		}
		public DeleteContext delete(int i) {
			return getRuleContext(DeleteContext.class,i);
		}
		public List<SelectContext> select() {
			return getRuleContexts(SelectContext.class);
		}
		public SelectContext select(int i) {
			return getRuleContext(SelectContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__14) | (1L << T__18) | (1L << T__22) | (1L << T__23))) != 0)) {
				{
				setState(78);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(64);
					create_database();
					}
					break;
				case 2:
					{
					setState(65);
					alter_database();
					}
					break;
				case 3:
					{
					setState(66);
					drop_database();
					}
					break;
				case 4:
					{
					setState(67);
					show_databases();
					}
					break;
				case 5:
					{
					setState(68);
					use_database();
					}
					break;
				case 6:
					{
					setState(69);
					create_table();
					}
					break;
				case 7:
					{
					setState(70);
					alter_table();
					}
					break;
				case 8:
					{
					setState(71);
					drop_table();
					}
					break;
				case 9:
					{
					setState(72);
					show_tables();
					}
					break;
				case 10:
					{
					setState(73);
					show_cols_from();
					}
					break;
				case 11:
					{
					setState(74);
					insert_into();
					}
					break;
				case 12:
					{
					setState(75);
					update();
					}
					break;
				case 13:
					{
					setState(76);
					delete();
					}
					break;
				case 14:
					{
					setState(77);
					select();
					}
					break;
				}
				}
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(83);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_databaseContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public Create_databaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_database; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitCreate_database(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_databaseContext create_database() throws RecognitionException {
		Create_databaseContext _localctx = new Create_databaseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_create_database);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(T__0);
			setState(86);
			match(T__1);
			setState(87);
			match(ID);
			setState(88);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Alter_databaseContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public Alter_databaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alter_database; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAlter_database(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Alter_databaseContext alter_database() throws RecognitionException {
		Alter_databaseContext _localctx = new Alter_databaseContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_alter_database);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(T__3);
			setState(91);
			match(T__1);
			setState(92);
			match(ID);
			setState(93);
			match(T__4);
			setState(94);
			match(T__5);
			setState(95);
			match(ID);
			setState(96);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Drop_databaseContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public Drop_databaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop_database; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitDrop_database(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Drop_databaseContext drop_database() throws RecognitionException {
		Drop_databaseContext _localctx = new Drop_databaseContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_drop_database);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__6);
			setState(99);
			match(T__1);
			setState(100);
			match(ID);
			setState(101);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Show_databasesContext extends ParserRuleContext {
		public Show_databasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_databases; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitShow_databases(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Show_databasesContext show_databases() throws RecognitionException {
		Show_databasesContext _localctx = new Show_databasesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_show_databases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(T__7);
			setState(104);
			match(T__8);
			setState(105);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Use_databaseContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public Use_databaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_use_database; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitUse_database(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Use_databaseContext use_database() throws RecognitionException {
		Use_databaseContext _localctx = new Use_databaseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_use_database);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(T__9);
			setState(108);
			match(T__1);
			setState(109);
			match(ID);
			setState(110);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_tableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public Table_element_listContext table_element_list() {
			return getRuleContext(Table_element_listContext.class,0);
		}
		public Create_tableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_table; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitCreate_table(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_tableContext create_table() throws RecognitionException {
		Create_tableContext _localctx = new Create_tableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_create_table);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__0);
			setState(113);
			match(T__10);
			setState(114);
			match(ID);
			setState(115);
			table_element_list();
			setState(116);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Alter_tableContext extends ParserRuleContext {
		public Alter_renameContext alter_rename() {
			return getRuleContext(Alter_renameContext.class,0);
		}
		public Alter_actionContext alter_action() {
			return getRuleContext(Alter_actionContext.class,0);
		}
		public Alter_tableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alter_table; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAlter_table(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Alter_tableContext alter_table() throws RecognitionException {
		Alter_tableContext _localctx = new Alter_tableContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_alter_table);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__3);
			setState(119);
			match(T__10);
			setState(122);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(120);
				alter_rename();
				}
				break;
			case 2:
				{
				setState(121);
				alter_action();
				}
				break;
			}
			setState(124);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Drop_tableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public Drop_tableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop_table; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitDrop_table(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Drop_tableContext drop_table() throws RecognitionException {
		Drop_tableContext _localctx = new Drop_tableContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_drop_table);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__6);
			setState(127);
			match(T__10);
			setState(128);
			match(ID);
			setState(129);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Show_tablesContext extends ParserRuleContext {
		public Show_tablesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_tables; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitShow_tables(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Show_tablesContext show_tables() throws RecognitionException {
		Show_tablesContext _localctx = new Show_tablesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_show_tables);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(T__7);
			setState(132);
			match(T__11);
			setState(133);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Show_cols_fromContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public Show_cols_fromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_cols_from; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitShow_cols_from(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Show_cols_fromContext show_cols_from() throws RecognitionException {
		Show_cols_fromContext _localctx = new Show_cols_fromContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_show_cols_from);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(T__7);
			setState(136);
			match(T__12);
			setState(137);
			match(T__13);
			setState(138);
			match(ID);
			setState(139);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Insert_intoContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public List<Data_typeContext> data_type() {
			return getRuleContexts(Data_typeContext.class);
		}
		public Data_typeContext data_type(int i) {
			return getRuleContext(Data_typeContext.class,i);
		}
		public Insert_intoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_into; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitInsert_into(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Insert_intoContext insert_into() throws RecognitionException {
		Insert_intoContext _localctx = new Insert_intoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_insert_into);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(T__14);
			setState(142);
			match(T__15);
			setState(143);
			match(ID);
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(144);
				match(ID);
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(145);
					match(T__16);
					setState(146);
					match(ID);
					}
					}
					setState(151);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(157);
			match(T__17);
			{
			setState(158);
			data_type();
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(159);
				match(T__16);
				setState(160);
				data_type();
				}
				}
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(166);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdateContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public UpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitUpdate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateContext update() throws RecognitionException {
		UpdateContext _localctx = new UpdateContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_update);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(T__18);
			setState(169);
			match(ID);
			setState(170);
			match(T__19);
			setState(171);
			match(ID);
			setState(172);
			match(T__20);
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(173);
				match(T__16);
				setState(174);
				match(ID);
				}
				}
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(180);
				match(T__21);
				setState(181);
				condition();
				}
				}
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(187);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeleteContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public DeleteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitDelete(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteContext delete() throws RecognitionException {
		DeleteContext _localctx = new DeleteContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_delete);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(T__22);
			setState(190);
			match(T__13);
			setState(191);
			match(ID);
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(192);
				match(T__21);
				setState(193);
				condition();
				}
				}
				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(199);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<Order_by_statementContext> order_by_statement() {
			return getRuleContexts(Order_by_statementContext.class);
		}
		public Order_by_statementContext order_by_statement(int i) {
			return getRuleContext(Order_by_statementContext.class,i);
		}
		public SelectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitSelect(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectContext select() throws RecognitionException {
		SelectContext _localctx = new SelectContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_select);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(T__23);
			setState(211);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__24:
				{
				setState(202);
				match(T__24);
				}
				break;
			case ID:
				{
				setState(203);
				match(ID);
				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(204);
					match(T__16);
					setState(205);
					match(ID);
					}
					}
					setState(210);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(213);
			match(T__13);
			setState(214);
			match(ID);
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(215);
				match(T__16);
				setState(216);
				match(ID);
				}
				}
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(222);
			match(T__21);
			{
			setState(223);
			condition();
			}
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__25) {
				{
				{
				setState(224);
				match(T__25);
				setState(225);
				match(T__26);
				setState(226);
				order_by_statement();
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(227);
					match(T__16);
					setState(228);
					order_by_statement();
					}
					}
					setState(233);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(239);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Order_by_statementContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public Order_by_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_order_by_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitOrder_by_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Order_by_statementContext order_by_statement() throws RecognitionException {
		Order_by_statementContext _localctx = new Order_by_statementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_order_by_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(ID);
			setState(242);
			_la = _input.LA(1);
			if ( !(_la==T__27 || _la==T__28) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public Rel_expContext rel_exp() {
			return getRuleContext(Rel_expContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(ID);
			setState(245);
			rel_exp();
			setState(246);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Alter_renameContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public Alter_renameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alter_rename; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAlter_rename(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Alter_renameContext alter_rename() throws RecognitionException {
		Alter_renameContext _localctx = new Alter_renameContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_alter_rename);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			match(ID);
			setState(249);
			match(T__4);
			setState(250);
			match(T__5);
			setState(251);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Alter_actionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public Alter_actionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alter_action; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAlter_action(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Alter_actionContext alter_action() throws RecognitionException {
		Alter_actionContext _localctx = new Alter_actionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_alter_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			match(ID);
			setState(254);
			action();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext {
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
	 
		public ActionContext() { }
		public void copyFrom(ActionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AddColumnContext extends ActionContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public List<C_constraintContext> c_constraint() {
			return getRuleContexts(C_constraintContext.class);
		}
		public C_constraintContext c_constraint(int i) {
			return getRuleContext(C_constraintContext.class,i);
		}
		public AddColumnContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAddColumn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropConstraintContext extends ActionContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public DropConstraintContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitDropConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddConstraintContext extends ActionContext {
		public C_constraintContext c_constraint() {
			return getRuleContext(C_constraintContext.class,0);
		}
		public AddConstraintContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAddConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropColumnContext extends ActionContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public DropColumnContext(ActionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitDropColumn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_action);
		int _la;
		try {
			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new AddColumnContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				match(T__29);
				setState(257);
				match(T__30);
				setState(258);
				match(ID);
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__31) {
					{
					{
					setState(259);
					match(T__31);
					setState(260);
					c_constraint();
					}
					}
					setState(265);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				_localctx = new AddConstraintContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				match(T__29);
				setState(267);
				match(T__31);
				setState(268);
				c_constraint();
				}
				break;
			case 3:
				_localctx = new DropColumnContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(269);
				match(T__6);
				setState(270);
				match(T__30);
				setState(271);
				match(ID);
				}
				break;
			case 4:
				_localctx = new DropConstraintContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(272);
				match(T__6);
				setState(273);
				match(T__31);
				setState(274);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_element_listContext extends ParserRuleContext {
		public List<Table_elementContext> table_element() {
			return getRuleContexts(Table_elementContext.class);
		}
		public Table_elementContext table_element(int i) {
			return getRuleContext(Table_elementContext.class,i);
		}
		public Table_element_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_element_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitTable_element_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_element_listContext table_element_list() throws RecognitionException {
		Table_element_listContext _localctx = new Table_element_listContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_table_element_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(T__32);
			setState(278);
			table_element();
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(279);
				match(T__16);
				setState(280);
				table_element();
				}
				}
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(286);
			match(T__33);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_elementContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public Data_type_defContext data_type_def() {
			return getRuleContext(Data_type_defContext.class,0);
		}
		public Column_constraintContext column_constraint() {
			return getRuleContext(Column_constraintContext.class,0);
		}
		public Table_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_element; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitTable_element(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_elementContext table_element() throws RecognitionException {
		Table_elementContext _localctx = new Table_elementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_table_element);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(ID);
			setState(289);
			data_type_def();
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__38) {
				{
				setState(290);
				column_constraint();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Data_type_defContext extends ParserRuleContext {
		public Data_typeContext data_type() {
			return getRuleContext(Data_typeContext.class,0);
		}
		public C_constraintContext c_constraint() {
			return getRuleContext(C_constraintContext.class,0);
		}
		public Data_type_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data_type_def; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitData_type_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Data_type_defContext data_type_def() throws RecognitionException {
		Data_type_defContext _localctx = new Data_type_defContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_data_type_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			data_type();
			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__31) {
				{
				setState(294);
				match(T__31);
				setState(295);
				c_constraint();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Data_typeContext extends ParserRuleContext {
		public Data_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitData_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Data_typeContext data_type() throws RecognitionException {
		Data_typeContext _localctx = new Data_typeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_constraintContext extends ParserRuleContext {
		public Column_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_constraint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitColumn_constraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_constraintContext column_constraint() throws RecognitionException {
		Column_constraintContext _localctx = new Column_constraintContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_column_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(T__38);
			setState(301);
			match(T__39);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class C_constraintContext extends ParserRuleContext {
		public Keys_constraintContext keys_constraint() {
			return getRuleContext(Keys_constraintContext.class,0);
		}
		public Length_constraintContext length_constraint() {
			return getRuleContext(Length_constraintContext.class,0);
		}
		public C_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_c_constraint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitC_constraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final C_constraintContext c_constraint() throws RecognitionException {
		C_constraintContext _localctx = new C_constraintContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_c_constraint);
		try {
			setState(305);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(303);
				keys_constraint();
				}
				break;
			case T__32:
				enterOuterAlt(_localctx, 2);
				{
				setState(304);
				length_constraint();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Keys_constraintContext extends ParserRuleContext {
		public Keys_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keys_constraint; }
	 
		public Keys_constraintContext() { }
		public void copyFrom(Keys_constraintContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CheckContext extends Keys_constraintContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public Check_expContext check_exp() {
			return getRuleContext(Check_expContext.class,0);
		}
		public CheckContext(Keys_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitCheck(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForeignKeyContext extends Keys_constraintContext {
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public ForeignKeyReferencesContext foreignKeyReferences() {
			return getRuleContext(ForeignKeyReferencesContext.class,0);
		}
		public ForeignKeyContext(Keys_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitForeignKey(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimaryKeyContext extends Keys_constraintContext {
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public PrimaryKeyContext(Keys_constraintContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitPrimaryKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Keys_constraintContext keys_constraint() throws RecognitionException {
		Keys_constraintContext _localctx = new Keys_constraintContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_keys_constraint);
		int _la;
		try {
			setState(350);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				_localctx = new PrimaryKeyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(307);
				match(ID);
				setState(308);
				match(T__40);
				setState(309);
				match(T__41);
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__32) {
					{
					{
					setState(310);
					match(T__32);
					setState(311);
					match(ID);
					setState(316);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__16) {
						{
						{
						setState(312);
						match(T__16);
						setState(313);
						match(ID);
						}
						}
						setState(318);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(319);
					match(T__33);
					}
					}
					setState(324);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				_localctx = new ForeignKeyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(325);
				match(ID);
				setState(326);
				match(T__42);
				setState(327);
				match(T__41);
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__32) {
					{
					{
					setState(328);
					match(T__32);
					setState(329);
					match(ID);
					setState(334);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__16) {
						{
						{
						setState(330);
						match(T__16);
						setState(331);
						match(ID);
						}
						}
						setState(336);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(337);
					match(T__33);
					}
					}
					setState(342);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(343);
				foreignKeyReferences();
				}
				break;
			case 3:
				_localctx = new CheckContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(344);
				match(ID);
				setState(345);
				match(T__43);
				setState(346);
				match(T__32);
				setState(347);
				check_exp();
				setState(348);
				match(T__33);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForeignKeyReferencesContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public ForeignKeyReferencesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreignKeyReferences; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitForeignKeyReferences(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForeignKeyReferencesContext foreignKeyReferences() throws RecognitionException {
		ForeignKeyReferencesContext _localctx = new ForeignKeyReferencesContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_foreignKeyReferences);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			match(T__44);
			setState(353);
			match(ID);
			setState(366);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__32) {
				{
				{
				setState(354);
				match(T__32);
				setState(355);
				match(ID);
				setState(360);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(356);
					match(T__16);
					setState(357);
					match(ID);
					}
					}
					setState(362);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(363);
				match(T__33);
				}
				}
				setState(368);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Check_expContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(SqlParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(SqlParser.NUMBER, i);
		}
		public Logic_expContext logic_exp() {
			return getRuleContext(Logic_expContext.class,0);
		}
		public Rel_expContext rel_exp() {
			return getRuleContext(Rel_expContext.class,0);
		}
		public Check_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_check_exp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitCheck_exp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Check_expContext check_exp() throws RecognitionException {
		Check_expContext _localctx = new Check_expContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_check_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			_la = _input.LA(1);
			if ( !(_la==NUMBER || _la==ID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(372);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__38:
			case T__45:
			case T__46:
				{
				setState(370);
				logic_exp();
				}
				break;
			case T__20:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
				{
				setState(371);
				rel_exp();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(374);
			_la = _input.LA(1);
			if ( !(_la==NUMBER || _la==ID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Logic_expContext extends ParserRuleContext {
		public Logic_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_exp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitLogic_exp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logic_expContext logic_exp() throws RecognitionException {
		Logic_expContext _localctx = new Logic_expContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_logic_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__38) | (1L << T__45) | (1L << T__46))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rel_expContext extends ParserRuleContext {
		public Rel_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rel_exp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitRel_exp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rel_expContext rel_exp() throws RecognitionException {
		Rel_expContext _localctx = new Rel_expContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_rel_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Length_constraintContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(SqlParser.NUMBER, 0); }
		public Length_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_length_constraint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitLength_constraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Length_constraintContext length_constraint() throws RecognitionException {
		Length_constraintContext _localctx = new Length_constraintContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_length_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			match(T__32);
			setState(381);
			match(NUMBER);
			setState(382);
			match(T__33);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3<\u0183\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2Q\n\2\f"+
		"\2\16\2T\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\5\t}\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r"+
		"\u0096\n\r\f\r\16\r\u0099\13\r\7\r\u009b\n\r\f\r\16\r\u009e\13\r\3\r\3"+
		"\r\3\r\3\r\7\r\u00a4\n\r\f\r\16\r\u00a7\13\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\7\16\u00b2\n\16\f\16\16\16\u00b5\13\16\3\16\3\16\7"+
		"\16\u00b9\n\16\f\16\16\16\u00bc\13\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\7\17\u00c5\n\17\f\17\16\17\u00c8\13\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\20\7\20\u00d1\n\20\f\20\16\20\u00d4\13\20\5\20\u00d6\n\20\3\20\3"+
		"\20\3\20\3\20\7\20\u00dc\n\20\f\20\16\20\u00df\13\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\7\20\u00e8\n\20\f\20\16\20\u00eb\13\20\7\20\u00ed\n"+
		"\20\f\20\16\20\u00f0\13\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\7\25"+
		"\u0108\n\25\f\25\16\25\u010b\13\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\5\25\u0116\n\25\3\26\3\26\3\26\3\26\7\26\u011c\n\26\f\26\16"+
		"\26\u011f\13\26\3\26\3\26\3\27\3\27\3\27\5\27\u0126\n\27\3\30\3\30\3\30"+
		"\5\30\u012b\n\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33\5\33\u0134\n\33\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u013d\n\34\f\34\16\34\u0140\13"+
		"\34\3\34\7\34\u0143\n\34\f\34\16\34\u0146\13\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\7\34\u014f\n\34\f\34\16\34\u0152\13\34\3\34\7\34\u0155\n"+
		"\34\f\34\16\34\u0158\13\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u0161"+
		"\n\34\3\35\3\35\3\35\3\35\3\35\3\35\7\35\u0169\n\35\f\35\16\35\u016c\13"+
		"\35\3\35\7\35\u016f\n\35\f\35\16\35\u0172\13\35\3\36\3\36\3\36\5\36\u0177"+
		"\n\36\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3!\2\2\"\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@\2\7\3\2\36\37\3\2%(\3"+
		"\2\678\4\2))\60\61\4\2\27\27\62\66\2\u018d\2R\3\2\2\2\4W\3\2\2\2\6\\\3"+
		"\2\2\2\bd\3\2\2\2\ni\3\2\2\2\fm\3\2\2\2\16r\3\2\2\2\20x\3\2\2\2\22\u0080"+
		"\3\2\2\2\24\u0085\3\2\2\2\26\u0089\3\2\2\2\30\u008f\3\2\2\2\32\u00aa\3"+
		"\2\2\2\34\u00bf\3\2\2\2\36\u00cb\3\2\2\2 \u00f3\3\2\2\2\"\u00f6\3\2\2"+
		"\2$\u00fa\3\2\2\2&\u00ff\3\2\2\2(\u0115\3\2\2\2*\u0117\3\2\2\2,\u0122"+
		"\3\2\2\2.\u0127\3\2\2\2\60\u012c\3\2\2\2\62\u012e\3\2\2\2\64\u0133\3\2"+
		"\2\2\66\u0160\3\2\2\28\u0162\3\2\2\2:\u0173\3\2\2\2<\u017a\3\2\2\2>\u017c"+
		"\3\2\2\2@\u017e\3\2\2\2BQ\5\4\3\2CQ\5\6\4\2DQ\5\b\5\2EQ\5\n\6\2FQ\5\f"+
		"\7\2GQ\5\16\b\2HQ\5\20\t\2IQ\5\22\n\2JQ\5\24\13\2KQ\5\26\f\2LQ\5\30\r"+
		"\2MQ\5\32\16\2NQ\5\34\17\2OQ\5\36\20\2PB\3\2\2\2PC\3\2\2\2PD\3\2\2\2P"+
		"E\3\2\2\2PF\3\2\2\2PG\3\2\2\2PH\3\2\2\2PI\3\2\2\2PJ\3\2\2\2PK\3\2\2\2"+
		"PL\3\2\2\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2"+
		"SU\3\2\2\2TR\3\2\2\2UV\7\2\2\3V\3\3\2\2\2WX\7\3\2\2XY\7\4\2\2YZ\78\2\2"+
		"Z[\7\5\2\2[\5\3\2\2\2\\]\7\6\2\2]^\7\4\2\2^_\78\2\2_`\7\7\2\2`a\7\b\2"+
		"\2ab\78\2\2bc\7\5\2\2c\7\3\2\2\2de\7\t\2\2ef\7\4\2\2fg\78\2\2gh\7\5\2"+
		"\2h\t\3\2\2\2ij\7\n\2\2jk\7\13\2\2kl\7\5\2\2l\13\3\2\2\2mn\7\f\2\2no\7"+
		"\4\2\2op\78\2\2pq\7\5\2\2q\r\3\2\2\2rs\7\3\2\2st\7\r\2\2tu\78\2\2uv\5"+
		"*\26\2vw\7\5\2\2w\17\3\2\2\2xy\7\6\2\2y|\7\r\2\2z}\5$\23\2{}\5&\24\2|"+
		"z\3\2\2\2|{\3\2\2\2}~\3\2\2\2~\177\7\5\2\2\177\21\3\2\2\2\u0080\u0081"+
		"\7\t\2\2\u0081\u0082\7\r\2\2\u0082\u0083\78\2\2\u0083\u0084\7\5\2\2\u0084"+
		"\23\3\2\2\2\u0085\u0086\7\n\2\2\u0086\u0087\7\16\2\2\u0087\u0088\7\5\2"+
		"\2\u0088\25\3\2\2\2\u0089\u008a\7\n\2\2\u008a\u008b\7\17\2\2\u008b\u008c"+
		"\7\20\2\2\u008c\u008d\78\2\2\u008d\u008e\7\5\2\2\u008e\27\3\2\2\2\u008f"+
		"\u0090\7\21\2\2\u0090\u0091\7\22\2\2\u0091\u009c\78\2\2\u0092\u0097\7"+
		"8\2\2\u0093\u0094\7\23\2\2\u0094\u0096\78\2\2\u0095\u0093\3\2\2\2\u0096"+
		"\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009b\3\2"+
		"\2\2\u0099\u0097\3\2\2\2\u009a\u0092\3\2\2\2\u009b\u009e\3\2\2\2\u009c"+
		"\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e\u009c\3\2"+
		"\2\2\u009f\u00a0\7\24\2\2\u00a0\u00a5\5\60\31\2\u00a1\u00a2\7\23\2\2\u00a2"+
		"\u00a4\5\60\31\2\u00a3\u00a1\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3"+
		"\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8"+
		"\u00a9\7\5\2\2\u00a9\31\3\2\2\2\u00aa\u00ab\7\25\2\2\u00ab\u00ac\78\2"+
		"\2\u00ac\u00ad\7\26\2\2\u00ad\u00ae\78\2\2\u00ae\u00b3\7\27\2\2\u00af"+
		"\u00b0\7\23\2\2\u00b0\u00b2\78\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\3\2"+
		"\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00ba\3\2\2\2\u00b5"+
		"\u00b3\3\2\2\2\u00b6\u00b7\7\30\2\2\u00b7\u00b9\5\"\22\2\u00b8\u00b6\3"+
		"\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		"\u00bd\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00be\7\5\2\2\u00be\33\3\2\2"+
		"\2\u00bf\u00c0\7\31\2\2\u00c0\u00c1\7\20\2\2\u00c1\u00c6\78\2\2\u00c2"+
		"\u00c3\7\30\2\2\u00c3\u00c5\5\"\22\2\u00c4\u00c2\3\2\2\2\u00c5\u00c8\3"+
		"\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c9\u00ca\7\5\2\2\u00ca\35\3\2\2\2\u00cb\u00d5\7\32\2"+
		"\2\u00cc\u00d6\7\33\2\2\u00cd\u00d2\78\2\2\u00ce\u00cf\7\23\2\2\u00cf"+
		"\u00d1\78\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d0\3\2"+
		"\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5"+
		"\u00cc\3\2\2\2\u00d5\u00cd\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\7\20"+
		"\2\2\u00d8\u00dd\78\2\2\u00d9\u00da\7\23\2\2\u00da\u00dc\78\2\2\u00db"+
		"\u00d9\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2"+
		"\2\2\u00de\u00e0\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e1\7\30\2\2\u00e1"+
		"\u00ee\5\"\22\2\u00e2\u00e3\7\34\2\2\u00e3\u00e4\7\35\2\2\u00e4\u00e9"+
		"\5 \21\2\u00e5\u00e6\7\23\2\2\u00e6\u00e8\5 \21\2\u00e7\u00e5\3\2\2\2"+
		"\u00e8\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ed"+
		"\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00e2\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee"+
		"\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00ee\3\2"+
		"\2\2\u00f1\u00f2\7\5\2\2\u00f2\37\3\2\2\2\u00f3\u00f4\78\2\2\u00f4\u00f5"+
		"\t\2\2\2\u00f5!\3\2\2\2\u00f6\u00f7\78\2\2\u00f7\u00f8\5> \2\u00f8\u00f9"+
		"\78\2\2\u00f9#\3\2\2\2\u00fa\u00fb\78\2\2\u00fb\u00fc\7\7\2\2\u00fc\u00fd"+
		"\7\b\2\2\u00fd\u00fe\78\2\2\u00fe%\3\2\2\2\u00ff\u0100\78\2\2\u0100\u0101"+
		"\5(\25\2\u0101\'\3\2\2\2\u0102\u0103\7 \2\2\u0103\u0104\7!\2\2\u0104\u0109"+
		"\78\2\2\u0105\u0106\7\"\2\2\u0106\u0108\5\64\33\2\u0107\u0105\3\2\2\2"+
		"\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u0116"+
		"\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d\7 \2\2\u010d\u010e\7\"\2\2\u010e"+
		"\u0116\5\64\33\2\u010f\u0110\7\t\2\2\u0110\u0111\7!\2\2\u0111\u0116\7"+
		"8\2\2\u0112\u0113\7\t\2\2\u0113\u0114\7\"\2\2\u0114\u0116\78\2\2\u0115"+
		"\u0102\3\2\2\2\u0115\u010c\3\2\2\2\u0115\u010f\3\2\2\2\u0115\u0112\3\2"+
		"\2\2\u0116)\3\2\2\2\u0117\u0118\7#\2\2\u0118\u011d\5,\27\2\u0119\u011a"+
		"\7\23\2\2\u011a\u011c\5,\27\2\u011b\u0119\3\2\2\2\u011c\u011f\3\2\2\2"+
		"\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u0120\3\2\2\2\u011f\u011d"+
		"\3\2\2\2\u0120\u0121\7$\2\2\u0121+\3\2\2\2\u0122\u0123\78\2\2\u0123\u0125"+
		"\5.\30\2\u0124\u0126\5\62\32\2\u0125\u0124\3\2\2\2\u0125\u0126\3\2\2\2"+
		"\u0126-\3\2\2\2\u0127\u012a\5\60\31\2\u0128\u0129\7\"\2\2\u0129\u012b"+
		"\5\64\33\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b/\3\2\2\2\u012c"+
		"\u012d\t\3\2\2\u012d\61\3\2\2\2\u012e\u012f\7)\2\2\u012f\u0130\7*\2\2"+
		"\u0130\63\3\2\2\2\u0131\u0134\5\66\34\2\u0132\u0134\5@!\2\u0133\u0131"+
		"\3\2\2\2\u0133\u0132\3\2\2\2\u0134\65\3\2\2\2\u0135\u0136\78\2\2\u0136"+
		"\u0137\7+\2\2\u0137\u0144\7,\2\2\u0138\u0139\7#\2\2\u0139\u013e\78\2\2"+
		"\u013a\u013b\7\23\2\2\u013b\u013d\78\2\2\u013c\u013a\3\2\2\2\u013d\u0140"+
		"\3\2\2\2\u013e\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0141\3\2\2\2\u0140"+
		"\u013e\3\2\2\2\u0141\u0143\7$\2\2\u0142\u0138\3\2\2\2\u0143\u0146\3\2"+
		"\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0161\3\2\2\2\u0146"+
		"\u0144\3\2\2\2\u0147\u0148\78\2\2\u0148\u0149\7-\2\2\u0149\u0156\7,\2"+
		"\2\u014a\u014b\7#\2\2\u014b\u0150\78\2\2\u014c\u014d\7\23\2\2\u014d\u014f"+
		"\78\2\2\u014e\u014c\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150"+
		"\u0151\3\2\2\2\u0151\u0153\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u0155\7$"+
		"\2\2\u0154\u014a\3\2\2\2\u0155\u0158\3\2\2\2\u0156\u0154\3\2\2\2\u0156"+
		"\u0157\3\2\2\2\u0157\u0159\3\2\2\2\u0158\u0156\3\2\2\2\u0159\u0161\58"+
		"\35\2\u015a\u015b\78\2\2\u015b\u015c\7.\2\2\u015c\u015d\7#\2\2\u015d\u015e"+
		"\5:\36\2\u015e\u015f\7$\2\2\u015f\u0161\3\2\2\2\u0160\u0135\3\2\2\2\u0160"+
		"\u0147\3\2\2\2\u0160\u015a\3\2\2\2\u0161\67\3\2\2\2\u0162\u0163\7/\2\2"+
		"\u0163\u0170\78\2\2\u0164\u0165\7#\2\2\u0165\u016a\78\2\2\u0166\u0167"+
		"\7\23\2\2\u0167\u0169\78\2\2\u0168\u0166\3\2\2\2\u0169\u016c\3\2\2\2\u016a"+
		"\u0168\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016d\3\2\2\2\u016c\u016a\3\2"+
		"\2\2\u016d\u016f\7$\2\2\u016e\u0164\3\2\2\2\u016f\u0172\3\2\2\2\u0170"+
		"\u016e\3\2\2\2\u0170\u0171\3\2\2\2\u01719\3\2\2\2\u0172\u0170\3\2\2\2"+
		"\u0173\u0176\t\4\2\2\u0174\u0177\5<\37\2\u0175\u0177\5> \2\u0176\u0174"+
		"\3\2\2\2\u0176\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u0179\t\4\2\2\u0179"+
		";\3\2\2\2\u017a\u017b\t\5\2\2\u017b=\3\2\2\2\u017c\u017d\t\6\2\2\u017d"+
		"?\3\2\2\2\u017e\u017f\7#\2\2\u017f\u0180\7\67\2\2\u0180\u0181\7$\2\2\u0181"+
		"A\3\2\2\2\36PR|\u0097\u009c\u00a5\u00b3\u00ba\u00c6\u00d2\u00d5\u00dd"+
		"\u00e9\u00ee\u0109\u0115\u011d\u0125\u012a\u0133\u013e\u0144\u0150\u0156"+
		"\u0160\u016a\u0170\u0176";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}