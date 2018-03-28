// SQL grammar for ANTLR v4
// @authors: Jose Custodio, Eric Mendoza, Gabriel Brolo

grammar Sql;

// -------------------------------- LEXER --------------------------------------
fragment DIGIT:     '0'..'9' ;
NUMBER:             DIGIT(DIGIT)* ;
ID:                 (('a'..'z'|'A'..'Z' | '_') ((DIGIT)*))+ ;
NEWLINE:            '\r'? '\n' ;
WHITESPACE:         [\t\r\n\f ]+ ->channel(HIDDEN) ;
COMMENTS:           '--' ~('\r' | '\n' )*  -> channel(HIDDEN) ;
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
    |   select)*
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
    :   'CREATE' 'TABLE' table_name table_element_list ';'
    ;

alter_table
    :   'ALTER' 'TABLE' (alter_rename | alter_action) ';'
    ;

drop_table
    :   'DROP' 'TABLE' ID ';'
    ;

show_tables
    :    'SHOW' 'TABLES' ';'
    ;

show_cols_from
    :   'SHOW' 'COLUMNS' 'FROM' ID
    ;

insert_into
    :   'INSERT' 'INTO' ID (ID (',' ID)*)* 'VALUES' (data_type (',' data_type)*) ';'
    ;

update
    :   'UPDATE' ID 'SET' ID '=' (',' ID)* ('WHERE' condition)* ';'
    ;

delete
    :   'DELETE' 'FROM' ID ('WHERE' condition)* ';'
    ;

select
    :   'SELECT' ('*' | ID (',' ID)*)
        'FROM' ID (',' ID)*
        'WHERE' (condition)
        ('ORDER' 'BY' order_by_statement (',' order_by_statement)*)* ';'
    ;

order_by_statement
    :   ID ('ASC' | 'DESC')
    ;

condition
    :   ID rel_exp ID
    ;

alter_rename
    :   ID 'RENAME' 'TO' ID
    ;

alter_action
    :   ID action
    ;

action
    :   'ADD' 'COLUMN' ID ('CONSTRAINT' c_constraint)*           #addColumn
    |   'ADD' 'CONSTRAINT' c_constraint                             #addConstraint
    |   'DROP' 'COLUMN' ID                                          #dropColumn
    |   'DROP' 'CONSTRAINT' ID                                      #dropConstraint
    ;

table_name
    :   (ID '.')? ID
    ;

table_element_list
    :   '(' table_element (',' table_element)* ')'
    ;

table_element
    :   ID data_type_def (column_constraint)?
    ;

data_type_def
    :   data_type ('CONSTRAINT' c_constraint)?
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

c_constraint
    :   keys_constraint
    |   length_constraint
    ;

keys_constraint
    :   'PK_' ID 'PRIMARY' 'KEY' ('(' ID (',' ID)* ')')*                                               #primaryKey
    |   'FK_' ID 'FOREIGN' 'KEY' ('(' ID (',' ID)* ')')* 'REFERENCES' ID ('(' ID (',' ID)* ')')*       #foreignKey
    |   'CH_' ID 'CHECK' '(' check_exp ')'                                                             #check
    ;

check_exp
    :   (ID | NUMBER) (logic_exp | rel_exp) (ID | NUMBER)
    ;

logic_exp
    :   'AND'
    |   'OR'
    |   'NOT'
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
