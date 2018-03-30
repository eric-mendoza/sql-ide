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
		T__45=46, T__46=47, T__47=48, T__48=49, NOT=50, OR=51, AND=52, NUMBER=53, 
		INT=54, FLOAT=55, ID=56, NEWLINE=57, WHITESPACE=58, COMMENTS=59, ErrorCharacter=60;
	public static final int
		RULE_expression = 0, RULE_create_database = 1, RULE_alter_database = 2, 
		RULE_drop_database = 3, RULE_show_databases = 4, RULE_use_database = 5, 
		RULE_create_table = 6, RULE_alter_table = 7, RULE_drop_table = 8, RULE_show_tables = 9, 
		RULE_show_cols_from = 10, RULE_insert_into = 11, RULE_update = 12, RULE_delete = 13, 
		RULE_select = 14, RULE_order_by_statement = 15, RULE_condition = 16, RULE_alter_rename = 17, 
		RULE_alter_action = 18, RULE_table_element_list = 19, RULE_table_element = 20, 
		RULE_data_type_def = 21, RULE_data_type = 22, RULE_column_constraint = 23, 
		RULE_constraint = 24, RULE_keys_constraint = 25, RULE_foreignKeyReferences = 26, 
		RULE_check_exp = 27, RULE_rel_exp = 28, RULE_length_constraint = 29;
	public static final String[] ruleNames = {
		"expression", "create_database", "alter_database", "drop_database", "show_databases", 
		"use_database", "create_table", "alter_table", "drop_table", "show_tables", 
		"show_cols_from", "insert_into", "update", "delete", "select", "order_by_statement", 
		"condition", "alter_rename", "alter_action", "table_element_list", "table_element", 
		"data_type_def", "data_type", "column_constraint", "constraint", "keys_constraint", 
		"foreignKeyReferences", "check_exp", "rel_exp", "length_constraint"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'CREATE'", "'DATABASE'", "';'", "'ALTER'", "'RENAME'", "'TO'", 
		"'DROP'", "'SHOW'", "'DATABASES'", "'USE'", "'TABLE'", "'TABLES'", "'COLUMNS'", 
		"'FROM'", "'INSERT'", "'INTO'", "','", "'VALUES'", "'UPDATE'", "'SET'", 
		"'='", "'WHERE'", "'DELETE'", "'SELECT'", "'*'", "'ORDER'", "'BY'", "'ASC'", 
		"'DESC'", "'ADD'", "'COLUMN'", "'CONSTRAINT'", "'('", "')'", "'INT'", 
		"'FLOAT'", "'DATE'", "'CHAR'", "'NULL'", "'PRIMARY'", "'KEY'", "'FOREIGN'", 
		"'CHECK'", "'REFERENCES'", "'<'", "'<='", "'>'", "'>='", "'<>'", "'NOT'", 
		"'OR'", "'AND'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "NOT", "OR", "AND", "NUMBER", "INT", "FLOAT", "ID", "NEWLINE", 
		"WHITESPACE", "COMMENTS", "ErrorCharacter"
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
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__14) | (1L << T__18) | (1L << T__22) | (1L << T__23))) != 0)) {
				{
				setState(74);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(60);
					create_database();
					}
					break;
				case 2:
					{
					setState(61);
					alter_database();
					}
					break;
				case 3:
					{
					setState(62);
					drop_database();
					}
					break;
				case 4:
					{
					setState(63);
					show_databases();
					}
					break;
				case 5:
					{
					setState(64);
					use_database();
					}
					break;
				case 6:
					{
					setState(65);
					create_table();
					}
					break;
				case 7:
					{
					setState(66);
					alter_table();
					}
					break;
				case 8:
					{
					setState(67);
					drop_table();
					}
					break;
				case 9:
					{
					setState(68);
					show_tables();
					}
					break;
				case 10:
					{
					setState(69);
					show_cols_from();
					}
					break;
				case 11:
					{
					setState(70);
					insert_into();
					}
					break;
				case 12:
					{
					setState(71);
					update();
					}
					break;
				case 13:
					{
					setState(72);
					delete();
					}
					break;
				case 14:
					{
					setState(73);
					select();
					}
					break;
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(79);
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
			setState(81);
			match(T__0);
			setState(82);
			match(T__1);
			setState(83);
			match(ID);
			setState(84);
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
			setState(86);
			match(T__3);
			setState(87);
			match(T__1);
			setState(88);
			match(ID);
			setState(89);
			match(T__4);
			setState(90);
			match(T__5);
			setState(91);
			match(ID);
			setState(92);
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
			setState(94);
			match(T__6);
			setState(95);
			match(T__1);
			setState(96);
			match(ID);
			setState(97);
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
			setState(99);
			match(T__7);
			setState(100);
			match(T__8);
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
			setState(103);
			match(T__9);
			setState(104);
			match(T__1);
			setState(105);
			match(ID);
			setState(106);
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
			setState(108);
			match(T__0);
			setState(109);
			match(T__10);
			setState(110);
			match(ID);
			setState(111);
			table_element_list();
			setState(112);
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
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
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
			setState(114);
			match(T__3);
			setState(115);
			match(T__10);
			setState(116);
			match(ID);
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(117);
				alter_rename();
				}
				break;
			case T__6:
			case T__29:
				{
				setState(118);
				alter_action();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(121);
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
			setState(123);
			match(T__6);
			setState(124);
			match(T__10);
			setState(125);
			match(ID);
			setState(126);
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
			setState(128);
			match(T__7);
			setState(129);
			match(T__11);
			setState(130);
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
			setState(132);
			match(T__7);
			setState(133);
			match(T__12);
			setState(134);
			match(T__13);
			setState(135);
			match(ID);
			setState(136);
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
			setState(138);
			match(T__14);
			setState(139);
			match(T__15);
			setState(140);
			match(ID);
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(141);
				match(ID);
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(142);
					match(T__16);
					setState(143);
					match(ID);
					}
					}
					setState(148);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(154);
			match(T__17);
			{
			setState(155);
			data_type();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(156);
				match(T__16);
				setState(157);
				data_type();
				}
				}
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(163);
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
			setState(165);
			match(T__18);
			setState(166);
			match(ID);
			setState(167);
			match(T__19);
			setState(168);
			match(ID);
			setState(169);
			match(T__20);
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(170);
				match(T__16);
				setState(171);
				match(ID);
				}
				}
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(177);
				match(T__21);
				setState(178);
				condition();
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
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
			setState(186);
			match(T__22);
			setState(187);
			match(T__13);
			setState(188);
			match(ID);
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(189);
				match(T__21);
				setState(190);
				condition();
				}
				}
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(196);
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
			setState(198);
			match(T__23);
			setState(208);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__24:
				{
				setState(199);
				match(T__24);
				}
				break;
			case ID:
				{
				setState(200);
				match(ID);
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(201);
					match(T__16);
					setState(202);
					match(ID);
					}
					}
					setState(207);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(210);
			match(T__13);
			setState(211);
			match(ID);
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(212);
				match(T__16);
				setState(213);
				match(ID);
				}
				}
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(219);
			match(T__21);
			{
			setState(220);
			condition();
			}
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__25) {
				{
				{
				setState(221);
				match(T__25);
				setState(222);
				match(T__26);
				setState(223);
				order_by_statement();
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(224);
					match(T__16);
					setState(225);
					order_by_statement();
					}
					}
					setState(230);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(236);
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
			setState(238);
			match(ID);
			setState(239);
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
			setState(241);
			match(ID);
			setState(242);
			rel_exp();
			setState(243);
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
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
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
			setState(245);
			match(T__4);
			setState(246);
			match(T__5);
			setState(247);
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
		public Alter_actionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alter_action; }
	 
		public Alter_actionContext() { }
		public void copyFrom(Alter_actionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AddColumnContext extends Alter_actionContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public Data_typeContext data_type() {
			return getRuleContext(Data_typeContext.class,0);
		}
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public AddColumnContext(Alter_actionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAddColumn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropConstraintContext extends Alter_actionContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public DropConstraintContext(Alter_actionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitDropConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddConstraintContext extends Alter_actionContext {
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public AddConstraintContext(Alter_actionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAddConstraint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropColumnContext extends Alter_actionContext {
		public TerminalNode ID() { return getToken(SqlParser.ID, 0); }
		public DropColumnContext(Alter_actionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitDropColumn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Alter_actionContext alter_action() throws RecognitionException {
		Alter_actionContext _localctx = new Alter_actionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_alter_action);
		try {
			setState(263);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new AddColumnContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				match(T__29);
				setState(250);
				match(T__30);
				setState(251);
				match(ID);
				setState(252);
				data_type();
				setState(253);
				constraint();
				}
				break;
			case 2:
				_localctx = new AddConstraintContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				match(T__29);
				setState(256);
				constraint();
				}
				break;
			case 3:
				_localctx = new DropColumnContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(257);
				match(T__6);
				setState(258);
				match(T__30);
				setState(259);
				match(ID);
				}
				break;
			case 4:
				_localctx = new DropConstraintContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(260);
				match(T__6);
				setState(261);
				match(T__31);
				setState(262);
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
		enterRule(_localctx, 38, RULE_table_element_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			match(T__32);
			setState(266);
			table_element();
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(267);
				match(T__16);
				setState(268);
				table_element();
				}
				}
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(274);
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
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
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
		enterRule(_localctx, 40, RULE_table_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(ID);
			setState(277);
			data_type_def();
			setState(278);
			constraint();
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
		public Length_constraintContext length_constraint() {
			return getRuleContext(Length_constraintContext.class,0);
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
		enterRule(_localctx, 42, RULE_data_type_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			data_type();
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__32) {
				{
				setState(281);
				length_constraint();
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
		enterRule(_localctx, 44, RULE_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
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
		enterRule(_localctx, 46, RULE_column_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(NOT);
			setState(287);
			match(T__38);
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

	public static class ConstraintContext extends ParserRuleContext {
		public Column_constraintContext column_constraint() {
			return getRuleContext(Column_constraintContext.class,0);
		}
		public Keys_constraintContext keys_constraint() {
			return getRuleContext(Keys_constraintContext.class,0);
		}
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(289);
					column_constraint();
					}
				}

				setState(294);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__31) {
					{
					setState(292);
					match(T__31);
					setState(293);
					keys_constraint();
					}
				}

				}
				break;
			case 2:
				{
				setState(298);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__31) {
					{
					setState(296);
					match(T__31);
					setState(297);
					keys_constraint();
					}
				}

				setState(301);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(300);
					column_constraint();
					}
				}

				}
				break;
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
		enterRule(_localctx, 50, RULE_keys_constraint);
		int _la;
		try {
			setState(348);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				_localctx = new PrimaryKeyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(305);
				match(ID);
				setState(306);
				match(T__39);
				setState(307);
				match(T__40);
				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__32) {
					{
					{
					setState(308);
					match(T__32);
					setState(309);
					match(ID);
					setState(314);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__16) {
						{
						{
						setState(310);
						match(T__16);
						setState(311);
						match(ID);
						}
						}
						setState(316);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(317);
					match(T__33);
					}
					}
					setState(322);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				_localctx = new ForeignKeyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				match(ID);
				setState(324);
				match(T__41);
				setState(325);
				match(T__40);
				setState(338);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__32) {
					{
					{
					setState(326);
					match(T__32);
					setState(327);
					match(ID);
					setState(332);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__16) {
						{
						{
						setState(328);
						match(T__16);
						setState(329);
						match(ID);
						}
						}
						setState(334);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(335);
					match(T__33);
					}
					}
					setState(340);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(341);
				foreignKeyReferences();
				}
				break;
			case 3:
				_localctx = new CheckContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(342);
				match(ID);
				setState(343);
				match(T__42);
				setState(344);
				match(T__32);
				setState(345);
				check_exp(0);
				setState(346);
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
		enterRule(_localctx, 52, RULE_foreignKeyReferences);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(T__43);
			setState(351);
			match(ID);
			setState(364);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__32) {
				{
				{
				setState(352);
				match(T__32);
				setState(353);
				match(ID);
				setState(358);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(354);
					match(T__16);
					setState(355);
					match(ID);
					}
					}
					setState(360);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(361);
				match(T__33);
				}
				}
				setState(366);
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
		public Check_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_check_exp; }
	 
		public Check_expContext() { }
		public void copyFrom(Check_expContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NotExprContext extends Check_expContext {
		public TerminalNode NOT() { return getToken(SqlParser.NOT, 0); }
		public Check_expContext check_exp() {
			return getRuleContext(Check_expContext.class,0);
		}
		public NotExprContext(Check_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrExprContext extends Check_expContext {
		public List<Check_expContext> check_exp() {
			return getRuleContexts(Check_expContext.class);
		}
		public Check_expContext check_exp(int i) {
			return getRuleContext(Check_expContext.class,i);
		}
		public TerminalNode OR() { return getToken(SqlParser.OR, 0); }
		public OrExprContext(Check_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RelExprContext extends Check_expContext {
		public Token op1;
		public Token op2;
		public Rel_expContext rel_exp() {
			return getRuleContext(Rel_expContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(SqlParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(SqlParser.NUMBER, i);
		}
		public List<TerminalNode> INT() { return getTokens(SqlParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SqlParser.INT, i);
		}
		public List<TerminalNode> FLOAT() { return getTokens(SqlParser.FLOAT); }
		public TerminalNode FLOAT(int i) {
			return getToken(SqlParser.FLOAT, i);
		}
		public RelExprContext(Check_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitRelExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends Check_expContext {
		public Check_expContext check_exp() {
			return getRuleContext(Check_expContext.class,0);
		}
		public ParenExprContext(Check_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndExprContext extends Check_expContext {
		public List<Check_expContext> check_exp() {
			return getRuleContexts(Check_expContext.class);
		}
		public Check_expContext check_exp(int i) {
			return getRuleContext(Check_expContext.class,i);
		}
		public TerminalNode AND() { return getToken(SqlParser.AND, 0); }
		public AndExprContext(Check_expContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Check_expContext check_exp() throws RecognitionException {
		return check_exp(0);
	}

	private Check_expContext check_exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Check_expContext _localctx = new Check_expContext(_ctx, _parentState);
		Check_expContext _prevctx = _localctx;
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_check_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(368);
				match(NOT);
				setState(369);
				check_exp(5);
				}
				break;
			case NUMBER:
			case INT:
			case FLOAT:
			case ID:
				{
				_localctx = new RelExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(370);
				((RelExprContext)_localctx).op1 = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER) | (1L << INT) | (1L << FLOAT) | (1L << ID))) != 0)) ) {
					((RelExprContext)_localctx).op1 = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(371);
				rel_exp();
				setState(372);
				((RelExprContext)_localctx).op2 = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER) | (1L << INT) | (1L << FLOAT) | (1L << ID))) != 0)) ) {
					((RelExprContext)_localctx).op2 = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__32:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(374);
				match(T__32);
				setState(375);
				check_exp(0);
				setState(376);
				match(T__33);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(388);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(386);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new AndExprContext(new Check_expContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_check_exp);
						setState(380);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(381);
						match(AND);
						setState(382);
						check_exp(4);
						}
						break;
					case 2:
						{
						_localctx = new OrExprContext(new Check_expContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_check_exp);
						setState(383);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(384);
						match(OR);
						setState(385);
						check_exp(3);
						}
						break;
					}
					} 
				}
				setState(390);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
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
		enterRule(_localctx, 56, RULE_rel_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__20) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48))) != 0)) ) {
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
		enterRule(_localctx, 58, RULE_length_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			match(T__32);
			setState(394);
			match(NUMBER);
			setState(395);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 27:
			return check_exp_sempred((Check_expContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean check_exp_sempred(Check_expContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3>\u0190\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2M\n\2\f\2\16\2P\13\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\5\tz\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u0093\n\r"+
		"\f\r\16\r\u0096\13\r\7\r\u0098\n\r\f\r\16\r\u009b\13\r\3\r\3\r\3\r\3\r"+
		"\7\r\u00a1\n\r\f\r\16\r\u00a4\13\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\7\16\u00af\n\16\f\16\16\16\u00b2\13\16\3\16\3\16\7\16\u00b6\n"+
		"\16\f\16\16\16\u00b9\13\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\7\17\u00c2"+
		"\n\17\f\17\16\17\u00c5\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\7\20\u00ce"+
		"\n\20\f\20\16\20\u00d1\13\20\5\20\u00d3\n\20\3\20\3\20\3\20\3\20\7\20"+
		"\u00d9\n\20\f\20\16\20\u00dc\13\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\7\20\u00e5\n\20\f\20\16\20\u00e8\13\20\7\20\u00ea\n\20\f\20\16\20\u00ed"+
		"\13\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\5\24\u010a\n\24\3\25\3\25\3\25\3\25\7\25\u0110\n\25\f\25\16\25\u0113"+
		"\13\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\5\27\u011d\n\27\3\30\3"+
		"\30\3\31\3\31\3\31\3\32\5\32\u0125\n\32\3\32\3\32\5\32\u0129\n\32\3\32"+
		"\3\32\5\32\u012d\n\32\3\32\5\32\u0130\n\32\5\32\u0132\n\32\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\7\33\u013b\n\33\f\33\16\33\u013e\13\33\3\33\7"+
		"\33\u0141\n\33\f\33\16\33\u0144\13\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\7\33\u014d\n\33\f\33\16\33\u0150\13\33\3\33\7\33\u0153\n\33\f\33\16"+
		"\33\u0156\13\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u015f\n\33\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\7\34\u0167\n\34\f\34\16\34\u016a\13\34\3\34"+
		"\7\34\u016d\n\34\f\34\16\34\u0170\13\34\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\5\35\u017d\n\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\7\35\u0185\n\35\f\35\16\35\u0188\13\35\3\36\3\36\3\37\3\37\3\37\3\37"+
		"\3\37\2\38 \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8:<\2\6\3\2\36\37\3\2%(\3\2\67:\4\2\27\27/\63\2\u01a1\2N\3\2\2\2\4S\3"+
		"\2\2\2\6X\3\2\2\2\b`\3\2\2\2\ne\3\2\2\2\fi\3\2\2\2\16n\3\2\2\2\20t\3\2"+
		"\2\2\22}\3\2\2\2\24\u0082\3\2\2\2\26\u0086\3\2\2\2\30\u008c\3\2\2\2\32"+
		"\u00a7\3\2\2\2\34\u00bc\3\2\2\2\36\u00c8\3\2\2\2 \u00f0\3\2\2\2\"\u00f3"+
		"\3\2\2\2$\u00f7\3\2\2\2&\u0109\3\2\2\2(\u010b\3\2\2\2*\u0116\3\2\2\2,"+
		"\u011a\3\2\2\2.\u011e\3\2\2\2\60\u0120\3\2\2\2\62\u0131\3\2\2\2\64\u015e"+
		"\3\2\2\2\66\u0160\3\2\2\28\u017c\3\2\2\2:\u0189\3\2\2\2<\u018b\3\2\2\2"+
		">M\5\4\3\2?M\5\6\4\2@M\5\b\5\2AM\5\n\6\2BM\5\f\7\2CM\5\16\b\2DM\5\20\t"+
		"\2EM\5\22\n\2FM\5\24\13\2GM\5\26\f\2HM\5\30\r\2IM\5\32\16\2JM\5\34\17"+
		"\2KM\5\36\20\2L>\3\2\2\2L?\3\2\2\2L@\3\2\2\2LA\3\2\2\2LB\3\2\2\2LC\3\2"+
		"\2\2LD\3\2\2\2LE\3\2\2\2LF\3\2\2\2LG\3\2\2\2LH\3\2\2\2LI\3\2\2\2LJ\3\2"+
		"\2\2LK\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QR\7\2"+
		"\2\3R\3\3\2\2\2ST\7\3\2\2TU\7\4\2\2UV\7:\2\2VW\7\5\2\2W\5\3\2\2\2XY\7"+
		"\6\2\2YZ\7\4\2\2Z[\7:\2\2[\\\7\7\2\2\\]\7\b\2\2]^\7:\2\2^_\7\5\2\2_\7"+
		"\3\2\2\2`a\7\t\2\2ab\7\4\2\2bc\7:\2\2cd\7\5\2\2d\t\3\2\2\2ef\7\n\2\2f"+
		"g\7\13\2\2gh\7\5\2\2h\13\3\2\2\2ij\7\f\2\2jk\7\4\2\2kl\7:\2\2lm\7\5\2"+
		"\2m\r\3\2\2\2no\7\3\2\2op\7\r\2\2pq\7:\2\2qr\5(\25\2rs\7\5\2\2s\17\3\2"+
		"\2\2tu\7\6\2\2uv\7\r\2\2vy\7:\2\2wz\5$\23\2xz\5&\24\2yw\3\2\2\2yx\3\2"+
		"\2\2z{\3\2\2\2{|\7\5\2\2|\21\3\2\2\2}~\7\t\2\2~\177\7\r\2\2\177\u0080"+
		"\7:\2\2\u0080\u0081\7\5\2\2\u0081\23\3\2\2\2\u0082\u0083\7\n\2\2\u0083"+
		"\u0084\7\16\2\2\u0084\u0085\7\5\2\2\u0085\25\3\2\2\2\u0086\u0087\7\n\2"+
		"\2\u0087\u0088\7\17\2\2\u0088\u0089\7\20\2\2\u0089\u008a\7:\2\2\u008a"+
		"\u008b\7\5\2\2\u008b\27\3\2\2\2\u008c\u008d\7\21\2\2\u008d\u008e\7\22"+
		"\2\2\u008e\u0099\7:\2\2\u008f\u0094\7:\2\2\u0090\u0091\7\23\2\2\u0091"+
		"\u0093\7:\2\2\u0092\u0090\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2"+
		"\2\2\u0094\u0095\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0097"+
		"\u008f\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2"+
		"\2\2\u009a\u009c\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u009d\7\24\2\2\u009d"+
		"\u00a2\5.\30\2\u009e\u009f\7\23\2\2\u009f\u00a1\5.\30\2\u00a0\u009e\3"+
		"\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\u00a5\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\7\5\2\2\u00a6\31\3\2\2"+
		"\2\u00a7\u00a8\7\25\2\2\u00a8\u00a9\7:\2\2\u00a9\u00aa\7\26\2\2\u00aa"+
		"\u00ab\7:\2\2\u00ab\u00b0\7\27\2\2\u00ac\u00ad\7\23\2\2\u00ad\u00af\7"+
		":\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\u00b7\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\7\30"+
		"\2\2\u00b4\u00b6\5\"\22\2\u00b5\u00b3\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7"+
		"\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00b7\3\2"+
		"\2\2\u00ba\u00bb\7\5\2\2\u00bb\33\3\2\2\2\u00bc\u00bd\7\31\2\2\u00bd\u00be"+
		"\7\20\2\2\u00be\u00c3\7:\2\2\u00bf\u00c0\7\30\2\2\u00c0\u00c2\5\"\22\2"+
		"\u00c1\u00bf\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4"+
		"\3\2\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c7\7\5\2\2\u00c7"+
		"\35\3\2\2\2\u00c8\u00d2\7\32\2\2\u00c9\u00d3\7\33\2\2\u00ca\u00cf\7:\2"+
		"\2\u00cb\u00cc\7\23\2\2\u00cc\u00ce\7:\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00d1"+
		"\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d2\u00c9\3\2\2\2\u00d2\u00ca\3\2\2\2\u00d3\u00d4\3\2"+
		"\2\2\u00d4\u00d5\7\20\2\2\u00d5\u00da\7:\2\2\u00d6\u00d7\7\23\2\2\u00d7"+
		"\u00d9\7:\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2"+
		"\2\2\u00da\u00db\3\2\2\2\u00db\u00dd\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd"+
		"\u00de\7\30\2\2\u00de\u00eb\5\"\22\2\u00df\u00e0\7\34\2\2\u00e0\u00e1"+
		"\7\35\2\2\u00e1\u00e6\5 \21\2\u00e2\u00e3\7\23\2\2\u00e3\u00e5\5 \21\2"+
		"\u00e4\u00e2\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7"+
		"\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00df\3\2\2\2\u00ea"+
		"\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2"+
		"\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00ef\7\5\2\2\u00ef\37\3\2\2\2\u00f0\u00f1"+
		"\7:\2\2\u00f1\u00f2\t\2\2\2\u00f2!\3\2\2\2\u00f3\u00f4\7:\2\2\u00f4\u00f5"+
		"\5:\36\2\u00f5\u00f6\7:\2\2\u00f6#\3\2\2\2\u00f7\u00f8\7\7\2\2\u00f8\u00f9"+
		"\7\b\2\2\u00f9\u00fa\7:\2\2\u00fa%\3\2\2\2\u00fb\u00fc\7 \2\2\u00fc\u00fd"+
		"\7!\2\2\u00fd\u00fe\7:\2\2\u00fe\u00ff\5.\30\2\u00ff\u0100\5\62\32\2\u0100"+
		"\u010a\3\2\2\2\u0101\u0102\7 \2\2\u0102\u010a\5\62\32\2\u0103\u0104\7"+
		"\t\2\2\u0104\u0105\7!\2\2\u0105\u010a\7:\2\2\u0106\u0107\7\t\2\2\u0107"+
		"\u0108\7\"\2\2\u0108\u010a\7:\2\2\u0109\u00fb\3\2\2\2\u0109\u0101\3\2"+
		"\2\2\u0109\u0103\3\2\2\2\u0109\u0106\3\2\2\2\u010a\'\3\2\2\2\u010b\u010c"+
		"\7#\2\2\u010c\u0111\5*\26\2\u010d\u010e\7\23\2\2\u010e\u0110\5*\26\2\u010f"+
		"\u010d\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2"+
		"\2\2\u0112\u0114\3\2\2\2\u0113\u0111\3\2\2\2\u0114\u0115\7$\2\2\u0115"+
		")\3\2\2\2\u0116\u0117\7:\2\2\u0117\u0118\5,\27\2\u0118\u0119\5\62\32\2"+
		"\u0119+\3\2\2\2\u011a\u011c\5.\30\2\u011b\u011d\5<\37\2\u011c\u011b\3"+
		"\2\2\2\u011c\u011d\3\2\2\2\u011d-\3\2\2\2\u011e\u011f\t\3\2\2\u011f/\3"+
		"\2\2\2\u0120\u0121\7\64\2\2\u0121\u0122\7)\2\2\u0122\61\3\2\2\2\u0123"+
		"\u0125\5\60\31\2\u0124\u0123\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0128\3"+
		"\2\2\2\u0126\u0127\7\"\2\2\u0127\u0129\5\64\33\2\u0128\u0126\3\2\2\2\u0128"+
		"\u0129\3\2\2\2\u0129\u0132\3\2\2\2\u012a\u012b\7\"\2\2\u012b\u012d\5\64"+
		"\33\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012f\3\2\2\2\u012e"+
		"\u0130\5\60\31\2\u012f\u012e\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0132\3"+
		"\2\2\2\u0131\u0124\3\2\2\2\u0131\u012c\3\2\2\2\u0132\63\3\2\2\2\u0133"+
		"\u0134\7:\2\2\u0134\u0135\7*\2\2\u0135\u0142\7+\2\2\u0136\u0137\7#\2\2"+
		"\u0137\u013c\7:\2\2\u0138\u0139\7\23\2\2\u0139\u013b\7:\2\2\u013a\u0138"+
		"\3\2\2\2\u013b\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d"+
		"\u013f\3\2\2\2\u013e\u013c\3\2\2\2\u013f\u0141\7$\2\2\u0140\u0136\3\2"+
		"\2\2\u0141\u0144\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143"+
		"\u015f\3\2\2\2\u0144\u0142\3\2\2\2\u0145\u0146\7:\2\2\u0146\u0147\7,\2"+
		"\2\u0147\u0154\7+\2\2\u0148\u0149\7#\2\2\u0149\u014e\7:\2\2\u014a\u014b"+
		"\7\23\2\2\u014b\u014d\7:\2\2\u014c\u014a\3\2\2\2\u014d\u0150\3\2\2\2\u014e"+
		"\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0151\3\2\2\2\u0150\u014e\3\2"+
		"\2\2\u0151\u0153\7$\2\2\u0152\u0148\3\2\2\2\u0153\u0156\3\2\2\2\u0154"+
		"\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0157\3\2\2\2\u0156\u0154\3\2"+
		"\2\2\u0157\u015f\5\66\34\2\u0158\u0159\7:\2\2\u0159\u015a\7-\2\2\u015a"+
		"\u015b\7#\2\2\u015b\u015c\58\35\2\u015c\u015d\7$\2\2\u015d\u015f\3\2\2"+
		"\2\u015e\u0133\3\2\2\2\u015e\u0145\3\2\2\2\u015e\u0158\3\2\2\2\u015f\65"+
		"\3\2\2\2\u0160\u0161\7.\2\2\u0161\u016e\7:\2\2\u0162\u0163\7#\2\2\u0163"+
		"\u0168\7:\2\2\u0164\u0165\7\23\2\2\u0165\u0167\7:\2\2\u0166\u0164\3\2"+
		"\2\2\u0167\u016a\3\2\2\2\u0168\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169"+
		"\u016b\3\2\2\2\u016a\u0168\3\2\2\2\u016b\u016d\7$\2\2\u016c\u0162\3\2"+
		"\2\2\u016d\u0170\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f"+
		"\67\3\2\2\2\u0170\u016e\3\2\2\2\u0171\u0172\b\35\1\2\u0172\u0173\7\64"+
		"\2\2\u0173\u017d\58\35\7\u0174\u0175\t\4\2\2\u0175\u0176\5:\36\2\u0176"+
		"\u0177\t\4\2\2\u0177\u017d\3\2\2\2\u0178\u0179\7#\2\2\u0179\u017a\58\35"+
		"\2\u017a\u017b\7$\2\2\u017b\u017d\3\2\2\2\u017c\u0171\3\2\2\2\u017c\u0174"+
		"\3\2\2\2\u017c\u0178\3\2\2\2\u017d\u0186\3\2\2\2\u017e\u017f\f\5\2\2\u017f"+
		"\u0180\7\66\2\2\u0180\u0185\58\35\6\u0181\u0182\f\4\2\2\u0182\u0183\7"+
		"\65\2\2\u0183\u0185\58\35\5\u0184\u017e\3\2\2\2\u0184\u0181\3\2\2\2\u0185"+
		"\u0188\3\2\2\2\u0186\u0184\3\2\2\2\u0186\u0187\3\2\2\2\u01879\3\2\2\2"+
		"\u0188\u0186\3\2\2\2\u0189\u018a\t\5\2\2\u018a;\3\2\2\2\u018b\u018c\7"+
		"#\2\2\u018c\u018d\7\67\2\2\u018d\u018e\7$\2\2\u018e=\3\2\2\2\"LNy\u0094"+
		"\u0099\u00a2\u00b0\u00b7\u00c3\u00cf\u00d2\u00da\u00e6\u00eb\u0109\u0111"+
		"\u011c\u0124\u0128\u012c\u012f\u0131\u013c\u0142\u014e\u0154\u015e\u0168"+
		"\u016e\u017c\u0184\u0186";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}