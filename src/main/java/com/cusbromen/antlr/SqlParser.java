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
		T__45=46, T__46=47, T__47=48, NOT=49, OR=50, AND=51, REFERENCES=52, NUMBER=53, 
		INT=54, FLOAT=55, DATE=56, CHAR=57, ID=58, NEWLINE=59, WHITESPACE=60, 
		COMMENTS=61, ErrorCharacter=62;
	public static final int
		RULE_expression = 0, RULE_create_database = 1, RULE_alter_database = 2, 
		RULE_drop_database = 3, RULE_show_databases = 4, RULE_use_database = 5, 
		RULE_create_table = 6, RULE_alter_table = 7, RULE_drop_table = 8, RULE_show_tables = 9, 
		RULE_show_cols_from = 10, RULE_insert_into = 11, RULE_column_insert = 12, 
		RULE_data = 13, RULE_update = 14, RULE_delete = 15, RULE_select = 16, 
		RULE_from = 17, RULE_order_by_statement = 18, RULE_condition = 19, RULE_alter_rename = 20, 
		RULE_alter_action = 21, RULE_table_element_list = 22, RULE_table_element = 23, 
		RULE_data_type_def = 24, RULE_data_type = 25, RULE_column_constraint = 26, 
		RULE_constraint = 27, RULE_keys_constraint = 28, RULE_foreignKeyReferences = 29, 
		RULE_check_exp = 30, RULE_rel_exp = 31, RULE_length_constraint = 32;
	public static final String[] ruleNames = {
		"expression", "create_database", "alter_database", "drop_database", "show_databases", 
		"use_database", "create_table", "alter_table", "drop_table", "show_tables", 
		"show_cols_from", "insert_into", "column_insert", "data", "update", "delete", 
		"select", "from", "order_by_statement", "condition", "alter_rename", "alter_action", 
		"table_element_list", "table_element", "data_type_def", "data_type", "column_constraint", 
		"constraint", "keys_constraint", "foreignKeyReferences", "check_exp", 
		"rel_exp", "length_constraint"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'CREATE'", "'DATABASE'", "';'", "'ALTER'", "'RENAME'", "'TO'", 
		"'DROP'", "'SHOW'", "'DATABASES'", "'USE'", "'TABLE'", "'TABLES'", "'COLUMNS'", 
		"'FROM'", "'INSERT'", "'INTO'", "'('", "','", "')'", "'VALUES'", "'UPDATE'", 
		"'SET'", "'='", "'WHERE'", "'DELETE'", "'SELECT'", "'*'", "'ORDER'", "'BY'", 
		"'ASC'", "'DESC'", "'ADD'", "'COLUMN'", "'CONSTRAINT'", "'INT'", "'FLOAT'", 
		"'DATE'", "'CHAR'", "'NULL'", "'PRIMARY'", "'KEY'", "'FOREIGN'", "'CHECK'", 
		"'<'", "'<='", "'>'", "'>='", "'<>'", "'NOT'", "'OR'", "'AND'", "'REFERENCES'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "NOT", "OR", "AND", "REFERENCES", "NUMBER", "INT", "FLOAT", "DATE", 
		"CHAR", "ID", "NEWLINE", "WHITESPACE", "COMMENTS", "ErrorCharacter"
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
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__14) | (1L << T__20) | (1L << T__24) | (1L << T__25))) != 0)) {
				{
				setState(80);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(66);
					create_database();
					}
					break;
				case 2:
					{
					setState(67);
					alter_database();
					}
					break;
				case 3:
					{
					setState(68);
					drop_database();
					}
					break;
				case 4:
					{
					setState(69);
					show_databases();
					}
					break;
				case 5:
					{
					setState(70);
					use_database();
					}
					break;
				case 6:
					{
					setState(71);
					create_table();
					}
					break;
				case 7:
					{
					setState(72);
					alter_table();
					}
					break;
				case 8:
					{
					setState(73);
					drop_table();
					}
					break;
				case 9:
					{
					setState(74);
					show_tables();
					}
					break;
				case 10:
					{
					setState(75);
					show_cols_from();
					}
					break;
				case 11:
					{
					setState(76);
					insert_into();
					}
					break;
				case 12:
					{
					setState(77);
					update();
					}
					break;
				case 13:
					{
					setState(78);
					delete();
					}
					break;
				case 14:
					{
					setState(79);
					select();
					}
					break;
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(85);
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
			setState(87);
			match(T__0);
			setState(88);
			match(T__1);
			setState(89);
			match(ID);
			setState(90);
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
			setState(92);
			match(T__3);
			setState(93);
			match(T__1);
			setState(94);
			match(ID);
			setState(95);
			match(T__4);
			setState(96);
			match(T__5);
			setState(97);
			match(ID);
			setState(98);
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
			setState(100);
			match(T__6);
			setState(101);
			match(T__1);
			setState(102);
			match(ID);
			setState(103);
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
			setState(105);
			match(T__7);
			setState(106);
			match(T__8);
			setState(107);
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
			setState(109);
			match(T__9);
			setState(110);
			match(T__1);
			setState(111);
			match(ID);
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
			setState(114);
			match(T__0);
			setState(115);
			match(T__10);
			setState(116);
			match(ID);
			setState(117);
			table_element_list();
			setState(118);
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
			setState(120);
			match(T__3);
			setState(121);
			match(T__10);
			setState(122);
			match(ID);
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(123);
				alter_rename();
				}
				break;
			case T__6:
			case T__31:
				{
				setState(124);
				alter_action();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(127);
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
			setState(129);
			match(T__6);
			setState(130);
			match(T__10);
			setState(131);
			match(ID);
			setState(132);
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
			setState(134);
			match(T__7);
			setState(135);
			match(T__11);
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
			setState(138);
			match(T__7);
			setState(139);
			match(T__12);
			setState(140);
			match(T__13);
			setState(141);
			match(ID);
			setState(142);
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
		public List<Column_insertContext> column_insert() {
			return getRuleContexts(Column_insertContext.class);
		}
		public Column_insertContext column_insert(int i) {
			return getRuleContext(Column_insertContext.class,i);
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
			setState(144);
			match(T__14);
			setState(145);
			match(T__15);
			setState(146);
			match(ID);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(147);
				match(T__16);
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID) {
					{
					{
					setState(148);
					match(ID);
					setState(153);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__17) {
						{
						{
						setState(149);
						match(T__17);
						setState(150);
						match(ID);
						}
						}
						setState(155);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					setState(160);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(161);
				match(T__18);
				}
			}

			setState(164);
			match(T__19);
			{
			setState(165);
			column_insert();
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__17) {
				{
				{
				setState(166);
				match(T__17);
				setState(167);
				column_insert();
				}
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(173);
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

	public static class Column_insertContext extends ParserRuleContext {
		public List<DataContext> data() {
			return getRuleContexts(DataContext.class);
		}
		public DataContext data(int i) {
			return getRuleContext(DataContext.class,i);
		}
		public Column_insertContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_insert; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitColumn_insert(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_insertContext column_insert() throws RecognitionException {
		Column_insertContext _localctx = new Column_insertContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_column_insert);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(T__16);
			{
			setState(176);
			data();
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__17) {
				{
				{
				setState(177);
				match(T__17);
				setState(178);
				data();
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(184);
			match(T__18);
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

	public static class DataContext extends ParserRuleContext {
		public Token type;
		public TerminalNode NUMBER() { return getToken(SqlParser.NUMBER, 0); }
		public TerminalNode INT() { return getToken(SqlParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(SqlParser.FLOAT, 0); }
		public TerminalNode DATE() { return getToken(SqlParser.DATE, 0); }
		public TerminalNode CHAR() { return getToken(SqlParser.CHAR, 0); }
		public DataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitData(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_data);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			((DataContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER) | (1L << INT) | (1L << FLOAT) | (1L << DATE) | (1L << CHAR))) != 0)) ) {
				((DataContext)_localctx).type = (Token)_errHandler.recoverInline(this);
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

	public static class UpdateContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public List<DataContext> data() {
			return getRuleContexts(DataContext.class);
		}
		public DataContext data(int i) {
			return getRuleContext(DataContext.class,i);
		}
		public Check_expContext check_exp() {
			return getRuleContext(Check_expContext.class,0);
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
		enterRule(_localctx, 28, RULE_update);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(T__20);
			setState(189);
			match(ID);
			setState(190);
			match(T__21);
			setState(191);
			match(ID);
			setState(192);
			match(T__22);
			setState(193);
			data();
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__17) {
				{
				{
				setState(194);
				match(T__17);
				setState(195);
				match(ID);
				setState(196);
				match(T__22);
				setState(197);
				data();
				}
				}
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(203);
				match(T__23);
				setState(204);
				check_exp(0);
				}
			}

			setState(207);
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
		public Check_expContext check_exp() {
			return getRuleContext(Check_expContext.class,0);
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
		enterRule(_localctx, 30, RULE_delete);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			match(T__24);
			setState(210);
			match(T__13);
			setState(211);
			match(ID);
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(212);
				match(T__23);
				setState(213);
				check_exp(0);
				}
			}

			setState(216);
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
		public FromContext from() {
			return getRuleContext(FromContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public List<Order_by_statementContext> order_by_statement() {
			return getRuleContexts(Order_by_statementContext.class);
		}
		public Order_by_statementContext order_by_statement(int i) {
			return getRuleContext(Order_by_statementContext.class,i);
		}
		public Check_expContext check_exp() {
			return getRuleContext(Check_expContext.class,0);
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
		enterRule(_localctx, 32, RULE_select);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(T__25);
			setState(228);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__26:
				{
				setState(219);
				match(T__26);
				}
				break;
			case ID:
				{
				setState(220);
				match(ID);
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__17) {
					{
					{
					setState(221);
					match(T__17);
					setState(222);
					match(ID);
					}
					}
					setState(227);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(230);
			from();
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(231);
				match(T__23);
				{
				setState(232);
				check_exp(0);
				}
				}
			}

			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__27) {
				{
				{
				setState(235);
				match(T__27);
				setState(236);
				match(T__28);
				setState(237);
				order_by_statement();
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__17) {
					{
					{
					setState(238);
					match(T__17);
					setState(239);
					order_by_statement();
					}
					}
					setState(244);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(250);
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

	public static class FromContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SqlParser.ID, i);
		}
		public FromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlVisitor ) return ((SqlVisitor<? extends T>)visitor).visitFrom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FromContext from() throws RecognitionException {
		FromContext _localctx = new FromContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(T__13);
			setState(253);
			match(ID);
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__17) {
				{
				{
				setState(254);
				match(T__17);
				setState(255);
				match(ID);
				}
				}
				setState(260);
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

	public static class Order_by_statementContext extends ParserRuleContext {
		public Token op;
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
		enterRule(_localctx, 36, RULE_order_by_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(ID);
			setState(262);
			((Order_by_statementContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__29 || _la==T__30) ) {
				((Order_by_statementContext)_localctx).op = (Token)_errHandler.recoverInline(this);
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
		enterRule(_localctx, 38, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			match(ID);
			setState(265);
			rel_exp();
			setState(266);
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
		enterRule(_localctx, 40, RULE_alter_rename);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(T__4);
			setState(269);
			match(T__5);
			setState(270);
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
		enterRule(_localctx, 42, RULE_alter_action);
		try {
			setState(286);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new AddColumnContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(272);
				match(T__31);
				setState(273);
				match(T__32);
				setState(274);
				match(ID);
				setState(275);
				data_type();
				setState(276);
				constraint();
				}
				break;
			case 2:
				_localctx = new AddConstraintContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(278);
				match(T__31);
				setState(279);
				constraint();
				}
				break;
			case 3:
				_localctx = new DropColumnContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(280);
				match(T__6);
				setState(281);
				match(T__32);
				setState(282);
				match(ID);
				}
				break;
			case 4:
				_localctx = new DropConstraintContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(283);
				match(T__6);
				setState(284);
				match(T__33);
				setState(285);
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
		enterRule(_localctx, 44, RULE_table_element_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(T__16);
			setState(289);
			table_element();
			setState(294);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__17) {
				{
				{
				setState(290);
				match(T__17);
				setState(291);
				table_element();
				}
				}
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(297);
			match(T__18);
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
		enterRule(_localctx, 46, RULE_table_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(ID);
			setState(300);
			data_type_def();
			setState(301);
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
		enterRule(_localctx, 48, RULE_data_type_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			data_type();
			setState(305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(304);
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
		enterRule(_localctx, 50, RULE_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
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
		enterRule(_localctx, 52, RULE_column_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			match(NOT);
			setState(310);
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
		enterRule(_localctx, 54, RULE_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(312);
					column_constraint();
					}
				}

				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__33) {
					{
					setState(315);
					match(T__33);
					setState(316);
					keys_constraint();
					}
				}

				}
				break;
			case 2:
				{
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__33) {
					{
					setState(319);
					match(T__33);
					setState(320);
					keys_constraint();
					}
				}

				setState(324);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(323);
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
		enterRule(_localctx, 56, RULE_keys_constraint);
		int _la;
		try {
			setState(371);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				_localctx = new PrimaryKeyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(328);
				match(ID);
				setState(329);
				match(T__39);
				setState(330);
				match(T__40);
				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(331);
					match(T__16);
					setState(332);
					match(ID);
					setState(337);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__17) {
						{
						{
						setState(333);
						match(T__17);
						setState(334);
						match(ID);
						}
						}
						setState(339);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(340);
					match(T__18);
					}
					}
					setState(345);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				_localctx = new ForeignKeyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(346);
				match(ID);
				setState(347);
				match(T__41);
				setState(348);
				match(T__40);
				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(349);
					match(T__16);
					setState(350);
					match(ID);
					setState(355);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__17) {
						{
						{
						setState(351);
						match(T__17);
						setState(352);
						match(ID);
						}
						}
						setState(357);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(358);
					match(T__18);
					}
					}
					setState(363);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(364);
				foreignKeyReferences();
				}
				break;
			case 3:
				_localctx = new CheckContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(365);
				match(ID);
				setState(366);
				match(T__42);
				setState(367);
				match(T__16);
				setState(368);
				check_exp(0);
				setState(369);
				match(T__18);
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
		public TerminalNode REFERENCES() { return getToken(SqlParser.REFERENCES, 0); }
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
		enterRule(_localctx, 58, RULE_foreignKeyReferences);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			match(REFERENCES);
			setState(374);
			match(ID);
			setState(387);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(375);
				match(T__16);
				setState(376);
				match(ID);
				setState(381);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__17) {
					{
					{
					setState(377);
					match(T__17);
					setState(378);
					match(ID);
					}
					}
					setState(383);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(384);
				match(T__18);
				}
				}
				setState(389);
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
		int _startState = 60;
		enterRecursionRule(_localctx, 60, RULE_check_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(391);
				match(NOT);
				setState(392);
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
				setState(393);
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
				setState(394);
				rel_exp();
				setState(395);
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
			case T__16:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(397);
				match(T__16);
				setState(398);
				check_exp(0);
				setState(399);
				match(T__18);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(411);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(409);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
					case 1:
						{
						_localctx = new AndExprContext(new Check_expContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_check_exp);
						setState(403);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(404);
						match(AND);
						setState(405);
						check_exp(4);
						}
						break;
					case 2:
						{
						_localctx = new OrExprContext(new Check_expContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_check_exp);
						setState(406);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(407);
						match(OR);
						setState(408);
						check_exp(3);
						}
						break;
					}
					} 
				}
				setState(413);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
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
		enterRule(_localctx, 62, RULE_rel_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__22) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47))) != 0)) ) {
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
		enterRule(_localctx, 64, RULE_length_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416);
			match(T__16);
			setState(417);
			match(NUMBER);
			setState(418);
			match(T__18);
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
		case 30:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3@\u01a7\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7"+
		"\2S\n\2\f\2\16\2V\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\5\t\u0080\n\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\7\r\u009a\n\r\f\r\16\r\u009d\13\r\7\r\u009f\n\r\f\r\16"+
		"\r\u00a2\13\r\3\r\5\r\u00a5\n\r\3\r\3\r\3\r\3\r\7\r\u00ab\n\r\f\r\16\r"+
		"\u00ae\13\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16\u00b6\n\16\f\16\16\16\u00b9"+
		"\13\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\7\20\u00c9\n\20\f\20\16\20\u00cc\13\20\3\20\3\20\5\20\u00d0\n\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\5\21\u00d9\n\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\22\7\22\u00e2\n\22\f\22\16\22\u00e5\13\22\5\22\u00e7\n\22"+
		"\3\22\3\22\3\22\5\22\u00ec\n\22\3\22\3\22\3\22\3\22\3\22\7\22\u00f3\n"+
		"\22\f\22\16\22\u00f6\13\22\7\22\u00f8\n\22\f\22\16\22\u00fb\13\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\7\23\u0103\n\23\f\23\16\23\u0106\13\23\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0121\n\27\3\30"+
		"\3\30\3\30\3\30\7\30\u0127\n\30\f\30\16\30\u012a\13\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\32\3\32\5\32\u0134\n\32\3\33\3\33\3\34\3\34\3\34\3\35"+
		"\5\35\u013c\n\35\3\35\3\35\5\35\u0140\n\35\3\35\3\35\5\35\u0144\n\35\3"+
		"\35\5\35\u0147\n\35\5\35\u0149\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\7\36\u0152\n\36\f\36\16\36\u0155\13\36\3\36\7\36\u0158\n\36\f\36\16\36"+
		"\u015b\13\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u0164\n\36\f\36\16"+
		"\36\u0167\13\36\3\36\7\36\u016a\n\36\f\36\16\36\u016d\13\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\5\36\u0176\n\36\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\7\37\u017e\n\37\f\37\16\37\u0181\13\37\3\37\7\37\u0184\n\37\f\37\16\37"+
		"\u0187\13\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u0194\n \3 \3 \3 \3 "+
		"\3 \3 \7 \u019c\n \f \16 \u019f\13 \3!\3!\3\"\3\"\3\"\3\"\3\"\2\3>#\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@B\2\7\3"+
		"\2\67;\3\2 !\3\2%(\4\2\679<<\4\2\31\31.\62\2\u01b8\2T\3\2\2\2\4Y\3\2\2"+
		"\2\6^\3\2\2\2\bf\3\2\2\2\nk\3\2\2\2\fo\3\2\2\2\16t\3\2\2\2\20z\3\2\2\2"+
		"\22\u0083\3\2\2\2\24\u0088\3\2\2\2\26\u008c\3\2\2\2\30\u0092\3\2\2\2\32"+
		"\u00b1\3\2\2\2\34\u00bc\3\2\2\2\36\u00be\3\2\2\2 \u00d3\3\2\2\2\"\u00dc"+
		"\3\2\2\2$\u00fe\3\2\2\2&\u0107\3\2\2\2(\u010a\3\2\2\2*\u010e\3\2\2\2,"+
		"\u0120\3\2\2\2.\u0122\3\2\2\2\60\u012d\3\2\2\2\62\u0131\3\2\2\2\64\u0135"+
		"\3\2\2\2\66\u0137\3\2\2\28\u0148\3\2\2\2:\u0175\3\2\2\2<\u0177\3\2\2\2"+
		">\u0193\3\2\2\2@\u01a0\3\2\2\2B\u01a2\3\2\2\2DS\5\4\3\2ES\5\6\4\2FS\5"+
		"\b\5\2GS\5\n\6\2HS\5\f\7\2IS\5\16\b\2JS\5\20\t\2KS\5\22\n\2LS\5\24\13"+
		"\2MS\5\26\f\2NS\5\30\r\2OS\5\36\20\2PS\5 \21\2QS\5\"\22\2RD\3\2\2\2RE"+
		"\3\2\2\2RF\3\2\2\2RG\3\2\2\2RH\3\2\2\2RI\3\2\2\2RJ\3\2\2\2RK\3\2\2\2R"+
		"L\3\2\2\2RM\3\2\2\2RN\3\2\2\2RO\3\2\2\2RP\3\2\2\2RQ\3\2\2\2SV\3\2\2\2"+
		"TR\3\2\2\2TU\3\2\2\2UW\3\2\2\2VT\3\2\2\2WX\7\2\2\3X\3\3\2\2\2YZ\7\3\2"+
		"\2Z[\7\4\2\2[\\\7<\2\2\\]\7\5\2\2]\5\3\2\2\2^_\7\6\2\2_`\7\4\2\2`a\7<"+
		"\2\2ab\7\7\2\2bc\7\b\2\2cd\7<\2\2de\7\5\2\2e\7\3\2\2\2fg\7\t\2\2gh\7\4"+
		"\2\2hi\7<\2\2ij\7\5\2\2j\t\3\2\2\2kl\7\n\2\2lm\7\13\2\2mn\7\5\2\2n\13"+
		"\3\2\2\2op\7\f\2\2pq\7\4\2\2qr\7<\2\2rs\7\5\2\2s\r\3\2\2\2tu\7\3\2\2u"+
		"v\7\r\2\2vw\7<\2\2wx\5.\30\2xy\7\5\2\2y\17\3\2\2\2z{\7\6\2\2{|\7\r\2\2"+
		"|\177\7<\2\2}\u0080\5*\26\2~\u0080\5,\27\2\177}\3\2\2\2\177~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0082\7\5\2\2\u0082\21\3\2\2\2\u0083\u0084\7\t\2"+
		"\2\u0084\u0085\7\r\2\2\u0085\u0086\7<\2\2\u0086\u0087\7\5\2\2\u0087\23"+
		"\3\2\2\2\u0088\u0089\7\n\2\2\u0089\u008a\7\16\2\2\u008a\u008b\7\5\2\2"+
		"\u008b\25\3\2\2\2\u008c\u008d\7\n\2\2\u008d\u008e\7\17\2\2\u008e\u008f"+
		"\7\20\2\2\u008f\u0090\7<\2\2\u0090\u0091\7\5\2\2\u0091\27\3\2\2\2\u0092"+
		"\u0093\7\21\2\2\u0093\u0094\7\22\2\2\u0094\u00a4\7<\2\2\u0095\u00a0\7"+
		"\23\2\2\u0096\u009b\7<\2\2\u0097\u0098\7\24\2\2\u0098\u009a\7<\2\2\u0099"+
		"\u0097\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u0096\3\2\2\2\u009f"+
		"\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3\2"+
		"\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a5\7\25\2\2\u00a4\u0095\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\7\26\2\2\u00a7\u00ac\5"+
		"\32\16\2\u00a8\u00a9\7\24\2\2\u00a9\u00ab\5\32\16\2\u00aa\u00a8\3\2\2"+
		"\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af"+
		"\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b0\7\5\2\2\u00b0\31\3\2\2\2\u00b1"+
		"\u00b2\7\23\2\2\u00b2\u00b7\5\34\17\2\u00b3\u00b4\7\24\2\2\u00b4\u00b6"+
		"\5\34\17\2\u00b5\u00b3\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2"+
		"\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bb"+
		"\7\25\2\2\u00bb\33\3\2\2\2\u00bc\u00bd\t\2\2\2\u00bd\35\3\2\2\2\u00be"+
		"\u00bf\7\27\2\2\u00bf\u00c0\7<\2\2\u00c0\u00c1\7\30\2\2\u00c1\u00c2\7"+
		"<\2\2\u00c2\u00c3\7\31\2\2\u00c3\u00ca\5\34\17\2\u00c4\u00c5\7\24\2\2"+
		"\u00c5\u00c6\7<\2\2\u00c6\u00c7\7\31\2\2\u00c7\u00c9\5\34\17\2\u00c8\u00c4"+
		"\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb"+
		"\u00cf\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00ce\7\32\2\2\u00ce\u00d0\5"+
		"> \2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d2\7\5\2\2\u00d2\37\3\2\2\2\u00d3\u00d4\7\33\2\2\u00d4\u00d5\7\20"+
		"\2\2\u00d5\u00d8\7<\2\2\u00d6\u00d7\7\32\2\2\u00d7\u00d9\5> \2\u00d8\u00d6"+
		"\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\7\5\2\2\u00db"+
		"!\3\2\2\2\u00dc\u00e6\7\34\2\2\u00dd\u00e7\7\35\2\2\u00de\u00e3\7<\2\2"+
		"\u00df\u00e0\7\24\2\2\u00e0\u00e2\7<\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e5"+
		"\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e6\u00dd\3\2\2\2\u00e6\u00de\3\2\2\2\u00e7\u00e8\3\2"+
		"\2\2\u00e8\u00eb\5$\23\2\u00e9\u00ea\7\32\2\2\u00ea\u00ec\5> \2\u00eb"+
		"\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00f9\3\2\2\2\u00ed\u00ee\7\36"+
		"\2\2\u00ee\u00ef\7\37\2\2\u00ef\u00f4\5&\24\2\u00f0\u00f1\7\24\2\2\u00f1"+
		"\u00f3\5&\24\2\u00f2\u00f0\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2"+
		"\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7"+
		"\u00ed\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2"+
		"\2\2\u00fa\u00fc\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00fd\7\5\2\2\u00fd"+
		"#\3\2\2\2\u00fe\u00ff\7\20\2\2\u00ff\u0104\7<\2\2\u0100\u0101\7\24\2\2"+
		"\u0101\u0103\7<\2\2\u0102\u0100\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102"+
		"\3\2\2\2\u0104\u0105\3\2\2\2\u0105%\3\2\2\2\u0106\u0104\3\2\2\2\u0107"+
		"\u0108\7<\2\2\u0108\u0109\t\3\2\2\u0109\'\3\2\2\2\u010a\u010b\7<\2\2\u010b"+
		"\u010c\5@!\2\u010c\u010d\7<\2\2\u010d)\3\2\2\2\u010e\u010f\7\7\2\2\u010f"+
		"\u0110\7\b\2\2\u0110\u0111\7<\2\2\u0111+\3\2\2\2\u0112\u0113\7\"\2\2\u0113"+
		"\u0114\7#\2\2\u0114\u0115\7<\2\2\u0115\u0116\5\64\33\2\u0116\u0117\58"+
		"\35\2\u0117\u0121\3\2\2\2\u0118\u0119\7\"\2\2\u0119\u0121\58\35\2\u011a"+
		"\u011b\7\t\2\2\u011b\u011c\7#\2\2\u011c\u0121\7<\2\2\u011d\u011e\7\t\2"+
		"\2\u011e\u011f\7$\2\2\u011f\u0121\7<\2\2\u0120\u0112\3\2\2\2\u0120\u0118"+
		"\3\2\2\2\u0120\u011a\3\2\2\2\u0120\u011d\3\2\2\2\u0121-\3\2\2\2\u0122"+
		"\u0123\7\23\2\2\u0123\u0128\5\60\31\2\u0124\u0125\7\24\2\2\u0125\u0127"+
		"\5\60\31\2\u0126\u0124\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126\3\2\2\2"+
		"\u0128\u0129\3\2\2\2\u0129\u012b\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u012c"+
		"\7\25\2\2\u012c/\3\2\2\2\u012d\u012e\7<\2\2\u012e\u012f\5\62\32\2\u012f"+
		"\u0130\58\35\2\u0130\61\3\2\2\2\u0131\u0133\5\64\33\2\u0132\u0134\5B\""+
		"\2\u0133\u0132\3\2\2\2\u0133\u0134\3\2\2\2\u0134\63\3\2\2\2\u0135\u0136"+
		"\t\4\2\2\u0136\65\3\2\2\2\u0137\u0138\7\63\2\2\u0138\u0139\7)\2\2\u0139"+
		"\67\3\2\2\2\u013a\u013c\5\66\34\2\u013b\u013a\3\2\2\2\u013b\u013c\3\2"+
		"\2\2\u013c\u013f\3\2\2\2\u013d\u013e\7$\2\2\u013e\u0140\5:\36\2\u013f"+
		"\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0149\3\2\2\2\u0141\u0142\7$"+
		"\2\2\u0142\u0144\5:\36\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144"+
		"\u0146\3\2\2\2\u0145\u0147\5\66\34\2\u0146\u0145\3\2\2\2\u0146\u0147\3"+
		"\2\2\2\u0147\u0149\3\2\2\2\u0148\u013b\3\2\2\2\u0148\u0143\3\2\2\2\u0149"+
		"9\3\2\2\2\u014a\u014b\7<\2\2\u014b\u014c\7*\2\2\u014c\u0159\7+\2\2\u014d"+
		"\u014e\7\23\2\2\u014e\u0153\7<\2\2\u014f\u0150\7\24\2\2\u0150\u0152\7"+
		"<\2\2\u0151\u014f\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0151\3\2\2\2\u0153"+
		"\u0154\3\2\2\2\u0154\u0156\3\2\2\2\u0155\u0153\3\2\2\2\u0156\u0158\7\25"+
		"\2\2\u0157\u014d\3\2\2\2\u0158\u015b\3\2\2\2\u0159\u0157\3\2\2\2\u0159"+
		"\u015a\3\2\2\2\u015a\u0176\3\2\2\2\u015b\u0159\3\2\2\2\u015c\u015d\7<"+
		"\2\2\u015d\u015e\7,\2\2\u015e\u016b\7+\2\2\u015f\u0160\7\23\2\2\u0160"+
		"\u0165\7<\2\2\u0161\u0162\7\24\2\2\u0162\u0164\7<\2\2\u0163\u0161\3\2"+
		"\2\2\u0164\u0167\3\2\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166"+
		"\u0168\3\2\2\2\u0167\u0165\3\2\2\2\u0168\u016a\7\25\2\2\u0169\u015f\3"+
		"\2\2\2\u016a\u016d\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c"+
		"\u016e\3\2\2\2\u016d\u016b\3\2\2\2\u016e\u0176\5<\37\2\u016f\u0170\7<"+
		"\2\2\u0170\u0171\7-\2\2\u0171\u0172\7\23\2\2\u0172\u0173\5> \2\u0173\u0174"+
		"\7\25\2\2\u0174\u0176\3\2\2\2\u0175\u014a\3\2\2\2\u0175\u015c\3\2\2\2"+
		"\u0175\u016f\3\2\2\2\u0176;\3\2\2\2\u0177\u0178\7\66\2\2\u0178\u0185\7"+
		"<\2\2\u0179\u017a\7\23\2\2\u017a\u017f\7<\2\2\u017b\u017c\7\24\2\2\u017c"+
		"\u017e\7<\2\2\u017d\u017b\3\2\2\2\u017e\u0181\3\2\2\2\u017f\u017d\3\2"+
		"\2\2\u017f\u0180\3\2\2\2\u0180\u0182\3\2\2\2\u0181\u017f\3\2\2\2\u0182"+
		"\u0184\7\25\2\2\u0183\u0179\3\2\2\2\u0184\u0187\3\2\2\2\u0185\u0183\3"+
		"\2\2\2\u0185\u0186\3\2\2\2\u0186=\3\2\2\2\u0187\u0185\3\2\2\2\u0188\u0189"+
		"\b \1\2\u0189\u018a\7\63\2\2\u018a\u0194\5> \7\u018b\u018c\t\5\2\2\u018c"+
		"\u018d\5@!\2\u018d\u018e\t\5\2\2\u018e\u0194\3\2\2\2\u018f\u0190\7\23"+
		"\2\2\u0190\u0191\5> \2\u0191\u0192\7\25\2\2\u0192\u0194\3\2\2\2\u0193"+
		"\u0188\3\2\2\2\u0193\u018b\3\2\2\2\u0193\u018f\3\2\2\2\u0194\u019d\3\2"+
		"\2\2\u0195\u0196\f\5\2\2\u0196\u0197\7\65\2\2\u0197\u019c\5> \6\u0198"+
		"\u0199\f\4\2\2\u0199\u019a\7\64\2\2\u019a\u019c\5> \5\u019b\u0195\3\2"+
		"\2\2\u019b\u0198\3\2\2\2\u019c\u019f\3\2\2\2\u019d\u019b\3\2\2\2\u019d"+
		"\u019e\3\2\2\2\u019e?\3\2\2\2\u019f\u019d\3\2\2\2\u01a0\u01a1\t\6\2\2"+
		"\u01a1A\3\2\2\2\u01a2\u01a3\7\23\2\2\u01a3\u01a4\7\67\2\2\u01a4\u01a5"+
		"\7\25\2\2\u01a5C\3\2\2\2%RT\177\u009b\u00a0\u00a4\u00ac\u00b7\u00ca\u00cf"+
		"\u00d8\u00e3\u00e6\u00eb\u00f4\u00f9\u0104\u0120\u0128\u0133\u013b\u013f"+
		"\u0143\u0146\u0148\u0153\u0159\u0165\u016b\u0175\u017f\u0185\u0193\u019b"+
		"\u019d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}