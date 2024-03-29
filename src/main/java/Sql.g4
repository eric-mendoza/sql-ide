// SQL grammar for ANTLR v4
// @authors: Jose Custodio, Eric Mendoza, Gabriel Brolo

grammar Sql;

// -------------------------------- LEXER --------------------------------------
NOT: 'NOT';
OR : 'OR';
AND : 'AND';
REFERENCES: 'REFERENCES';

fragment DIGIT:     '0'..'9' ;
NUMBER:             DIGIT(DIGIT)* ;
INT:                ('-' NUMBER) | (NUMBER);
FLOAT:              INT '.'? NUMBER?;
DATE:               NUMBER '-' NUMBER '-' NUMBER;
CHAR :              '\'' ( ~['\r\n\\] | '\\' ['\\] )+ '\'';
ID:                 (('a'..'z'|'A'..'Z' | '_') ((DIGIT)*))+ ;
NEWLINE:            '\r'? '\n'  -> skip;
WHITESPACE:         [\t\r\n\f ]+ -> skip ;
COMMENTS:           '--' ~('\r' | '\n' )*  -> skip ;
ErrorCharacter
    :   .
    ;

// ------------------------------- PARSER --------------------------------------
expression
    :   (create_database
    |   alter_database
    |   drop_database
    |   show_databases
    |   use_database
    |   create_table
    |   alter_table
    |   drop_table
    |   show_tables
    |   show_cols_from
    |   insert_into
    |   update
    |   delete
    |   select)* EOF
    ;

create_database
    :    'CREATE' 'DATABASE' ID ';'
    ;

alter_database
    :   'ALTER' 'DATABASE' ID 'RENAME' 'TO' ID ';'
    ;

drop_database
    :  'DROP' 'DATABASE' ID ';'
    ;

show_databases
    :   'SHOW' 'DATABASES' ';'
    ;

use_database
    :   'USE' 'DATABASE' ID ';'
    ;

create_table
    :   'CREATE' 'TABLE' ID table_element_list ';'
    ;

alter_table
    :   'ALTER' 'TABLE' ID (alter_rename | alter_action) ';'
    ;

drop_table
    :   'DROP' 'TABLE' ID ';'
    ;

show_tables
    :    'SHOW' 'TABLES' ';'
    ;

show_cols_from
    :   'SHOW' 'COLUMNS' 'FROM' ID ';'
    ;

insert_into
    :   'INSERT' 'INTO' ID ('(' (ID (',' ID)*)* ')')? 'VALUES' (column_insert (',' column_insert)*) ';'
    ;

column_insert
    :   '(' (data (',' data)*) ')'
    ;

data
    :   type = (NUMBER | INT | FLOAT | DATE | CHAR)
    ;


update
    :   'UPDATE' ID 'SET' ID '=' data (',' ID '=' data)* ('WHERE' check_exp)? ';'
    ;

delete
    :   'DELETE' 'FROM' ID ('WHERE' check_exp)? ';'
    ;

select
    :   'SELECT' ('*' | ID (',' ID)*)
        from
        ('WHERE' (check_exp))?
        ('ORDER' 'BY' order_by_statement (',' order_by_statement)*)* ';'
    ;

from
    :   'FROM' ID (',' ID)*
    ;

order_by_statement
    :   ID op = ('ASC' | 'DESC')
    ;

condition
    :   ID rel_exp ID
    ;

alter_rename
    :   'RENAME' 'TO' ID
    ;

alter_action
    :   'ADD' 'COLUMN' ID data_type constraint                                  #addColumn
    |   'ADD'  constraint                                               #addConstraint
    |   'DROP' 'COLUMN' ID                                            #dropColumn
    |   'DROP' 'CONSTRAINT' ID                                        #dropConstraint
    ;

table_element_list
    :   '(' table_element (',' table_element)* ')'
    ;

table_element
    :   ID data_type_def constraint
    ;

data_type_def
    :   data_type (length_constraint)?
    ;

data_type
    :   'INT'
    |   'FLOAT'
    |   'DATE'
    |   'CHAR'
    ;

column_constraint
    :   'NOT' 'NULL'
    ;

constraint
    :   ((column_constraint)? ('CONSTRAINT' keys_constraint)? | ('CONSTRAINT' keys_constraint)? (column_constraint)?)
    ;

keys_constraint
    :   ID 'PRIMARY' 'KEY' ('(' ID (',' ID)* ')')*                                               #primaryKey
    |   ID 'FOREIGN' 'KEY' ('(' ID (',' ID)* ')')* foreignKeyReferences                          #foreignKey
    |   ID 'CHECK' '(' check_exp ')'                                                             #check
    ;

foreignKeyReferences
    :   REFERENCES ID ('(' ID (',' ID)* ')')*
    ;

check_exp
    :   NOT check_exp                                                                       #notExpr
    |   op1=(ID | NUMBER | INT | FLOAT) rel_exp op2=(ID | NUMBER | INT | FLOAT)             #relExpr
    |   check_exp AND check_exp                                                             #andExpr
    |   check_exp OR check_exp                                                              #orExpr
    |   '(' check_exp ')'                                                                   #parenExpr
    ;

rel_exp
    :   '<'
    |   '<='
    |   '>'
    |   '>='
    |   '<>'
    |   '='
    ;

length_constraint
    :   '(' NUMBER ')'
    ;
