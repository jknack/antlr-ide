/* ANTLR v3 Grammar for Objective Modula-2
 *
 *  Author: Benjamin Kowarsch
 *
 *  Copyright (C) 2009 Sunrise Telephone Systems KK. All rights reserved.
 *
 *  License:
 *
 *  Permission is hereby granted to review and test this software for the sole
 *  purpose of supporting the effort by the licensor to define and develop the
 *  Objective Modula-2 language. It is not permissible under any circumstances
 *  to  use the software  for the purpose  of creating derivative languages or 
 *  dialects.  This permission is valid until 31 December 2009, 24:00h GMT.
 *
 *  Future licensing:
 *
 *  The licensor undertakes to eventually release this software under a proper
 *  open source license  AFTER  the Objective Modula-2 language definition has
 *  been finalised and a conforming and working reference compiler completed.
 *
 *  More information about Objective Modula-2:
 *
 *  http://www.sunrisetel.net/software/devtools/objective-modula-2.shtml
 *
 */
 
grammar objm2;  // status: May 21, 2009

options {
	backtrack = false;
	k = 1;
}

tokens {

	// Reserved Words

	AND            = 'AND';
	ARRAY          = 'ARRAY';
	BEGIN          = 'BEGIN';
	BY             = 'BY';
	BYCOPY         = 'BYCOPY';
	BYREF          = 'BYREF';
	CASE           = 'CASE';
	CLASS          = 'CLASS';
	CONST          = 'CONST';
	CONTINUE       = 'CONTINUE';
	CRITICAL       = 'CRITICAL';
	DEFINITION     = 'DEFINITION';
	DIV            = 'DIV';
	DO             = 'DO';
	ELSE           = 'ELSE';
	ELSIF          = 'ELSIF';
	END            = 'END';
	EXIT           = 'EXIT';
	FOR            = 'FOR';
	FROM           = 'FROM';
	IF             = 'IF';
	IMPLEMENTATION = 'IMPLEMENTATION';
	IMPORT         = 'IMPORT';
	IN             = 'IN';
	INOUT          = 'INOUT';
	INSTANCE       = 'INSTANCE';
	LOOP           = 'LOOP';
	METHOD         = 'METHOD';
	MOD            = 'MOD';
	MODULE         = 'MODULE';
	NOT            = 'NOT';
	OF             = 'OF';
	ON             = 'ON';
	OPTIONAL       = 'OPTIONAL';
	OR             = 'OR';
	OUT            = 'OUT';
	POINTER        = 'POINTER';
	PRIVATE        = 'PRIVATE';
	PROCEDURE      = 'PROCEDURE';
	PROTECTED      = 'PROTECTED';
	PROTOCOL       = 'PROTOCOL';
	PUBLIC         = 'PUBLIC';
	RECORD         = 'RECORD';
	REFINES        = 'REFINES';
	REPEAT         = 'REPEAT';
	REQUIRED       = 'REQUIRED';
	RETURN         = 'RETURN';
	SELF           = 'SELF';
	SET            = 'SET';
	SUPER          = 'SUPER';
	THEN           = 'THEN';
	TO             = 'TO';
	TRY            = 'TRY';
	TYPE           = 'TYPE';
	UNION          = 'UNION';
	UNTIL          = 'UNTIL';
	VAR            = 'VAR';
	WHILE          = 'WHILE';

	// Operators
	
	ASSIGN_OP           = ':=';
	MESSAGE_PREFIX      = '`';
	LOGICAL_AND_OP      = '&';
	LOGICAL_NOT_OP      = '~';
	EQUAL_OP            = '=';
	NOT_EQUAL_OP        = '#';
	PASCAL_NOT_EQUAL_OP = '<>';
	C_NOT_EQUAL_OP      = '!=';
	GREATER_OP          = '>';
	GREATER_OR_EQUAL_OP = '>=';
	LESS_OP             = '<';
	LESS_OR_EQUAL_OP    = '<=';
	PLUS_OP             = '+';
	INCREMENT_OP        = '++';
	MINUS_OP            = '-';
	DECREMENT_OP        = '--';
	MULTIPLY_OP         = '*';
	DIVIDE_OP           = '/';
	POINTER_OP          = '^';
	
	// Punctuation
	
	DOT = '.';
	DOTDOT = '..';
}

// ---------------------------------------------------------------------------
// P A R S E R   G R A M M A R
// ---------------------------------------------------------------------------


// Compilation Units

compilation_unit : 
	  program
 	| definition_module
 	| implementation_module
 	;
 	
program :
	'MODULE' module_id library_implementation_body module_id '.'
	;

definition_module :
	'DEFINITION' 'MODULE' module_id
	( library_declaration_body
	| class_declaration_body
	| category_declaration_body
	)
	module_id '.'
	;

implementation_module :
	'IMPLEMENTATION' 'MODULE' module_id
	( library_implementation_body 
	| class_implementation_body
	| category_implementation_body 
	)
	module_id '.'
	;

protocol_module :
	'PROTOCOL' 'MODULE' protocol_id ';'
	( any_import )*
	( const_declaration | type_declaration )*
	( ( 'OPTIONAL' | 'REQUIRED' )? method_declaration_header ';' )*
	'END' protocol_id '.'
	;

library_declaration_body :
	';'
	( library_or_class_import )*
	( declaration_permitted_in_library_interface )*
	'END'
	;

class_declaration_body :
	':' super_class_id ';'
	( any_import )*
	( declaration_permitted_in_class_interface )*
	'END'
	;

category_declaration_body :
	'REFINES' class_id ';'
	( any_import )*
	( declaration_permitted_in_category_interface )*
	'END'
	;

library_implementation_body :
	( '[' priority ']' )? ';'
	( library_or_class_import )*
	block
	;

class_implementation_body :
	':' super_class_id ';'
	( any_import )*
	( declaration_permitted_in_class )*
	'END'
	;

category_implementation_body :
	'REFINES' class_id ';'
	( any_import )*
	( declaration_permitted_in_class )*
	'END'
	;

module_id : IDENT; // synonym

class_id : IDENT; // synonym

super_class_id : IDENT; // synonym

protocol_id : IDENT; // synonym

priority : const_expression ; // synonym


// Import Lists

any_import :
	( 'IMPORT' ( 'CLASS' | 'PROTOCOL')? module_list ) ';' ( PRAGMA_FRAMEWORK )?
	| unqualified_import
	;

library_or_class_import :
	( 'IMPORT' ( 'CLASS' )? module_list ) ';' ( PRAGMA_FRAMEWORK )?
	| unqualified_import
	;

unqualified_import :
	'FROM' module_id 'IMPORT' ident_list ';'
	;

module_list : ident_list ; // synonym


// Declarations

declaration_permitted_in_library_interface :
	  const_declaration
	| type_declaration
	| library_variable_declaration
	| procedure_declaration_header ';'
	;

declaration_permitted_in_class_interface :
	  const_declaration
	| type_declaration
	| instance_variable_declaration
	| method_declaration_header ';'
	;

declaration_permitted_in_category_interface :
	  const_declaration
	| type_declaration
	| method_declaration_header ';'
	;

declaration_permitted_in_block :
	  const_declaration
	| type_declaration
	| variable_declaration
	| procedure_declaration
	;

declaration_permitted_in_class :
	  declaration_permitted_in_block
	| method_declaration
	;

const_declaration :
	'CONST' ( IDENT '=' const_expression ';' )*
	;

type_declaration :
	'TYPE' ( IDENT ( '=' type_designator )? )* ';'
	;

variable_declaration :
	'VAR' ( ident_list ':' type_designator ';' )*
	;

library_variable_declaration :
	'VAR' ( ident_list ':' type_designator ';' )*
	( PRAGMA_IMMUTABLE )?
	;

instance_variable_declaration :
	( 'PUBLIC' | 'PROTECTED' | 'PRIVATE' )? 'VAR'
	( ident_list ':' type_designator ';'  )*
	;

procedure_declaration_header :
	'PROCEDURE' procedure_id
	'(' ( formal_param_section )? ( ';' formal_param_section )* ')'
	( ':' result_type )?
	;

formal_param_section :
	( 'VAR' )? ident_list ':' formal_type 
	;

method_declaration_header :
	( 'CLASS' | 'INSTANCE' )
	'METHOD' ( IDENT | method_arg ) ( method_arg )* ':' result_type
	;

procedure_id : IDENT ; // synonym

result_type : qualified_ident ; // synonym


// Data Types

type_designator :
	  array_type
	| derived_enumeration_type
	| non_array_type_designator
	;

non_array_type_designator :
	  qualified_ident
	| simple_enumeration_type
	| record_type
	| set_type
	| pointer_type
	| procedure_type
	;

simple_enumeration_type :
	'(' ident_list ')'
	;

derived_enumeration_type :
	'UNION' 'OF' enum_type_id ( ',' enum_type_id )*
	;

array_type :
	'ARRAY' array_index
	( 'OF' ('ARRAY' array_index 'OF' )* | ( ',' array_index )+ 'OF' )
	non_array_type_designator
	;

array_index :
	simple_enumeration_type
	| ( '[' ( '0' '..' )? const_expression ']')
	;

record_type :
	'RECORD' ident_list ':' type_designator
	( ';' ident_list ':' type_designator )*
	'END'
	;

set_type :
	'SET' 'OF' ( qualified_ident | simple_enumeration_type )
	;

pointer_type :
	'POINTER' 'TO' type_designator
	;

procedure_type :
	'PROCEDURE'
	( '(' ( ( 'VAR' )? formal_type ( ',' ( 'VAR' )? formal_type )* )? ')' )?
	( ':' result_type )?
	;

formal_type :
	( 'ARRAY' 'OF' )? qualified_ident
	;

enum_type_id : qualified_ident ; // synonym


// Variable and Value Designators

variable_designator :
	qualified_ident
	( ( '[' expression_list ']' | '^' ) ( '.' IDENT )*  )*
	;

value_designator :
	qualified_ident
	( ( '[' expression_list ']' | '^' ) ( '.' IDENT )*  )*
	( actual_parameters )?
	;

actual_parameters :
	'(' ( expression_list )? ')'
	;

qualified_ident :
	IDENT ( '.' IDENT )*
	;

ident_list :
	IDENT ( ',' IDENT )*
	;


// Blocks

procedure_declaration :
	procedure_declaration_header ';'  // header
	( PRAGMA_FORWARD  // declaration only
	| block procedure_id )  // implementation
	;

method_declaration :
	method_declaration_header ';'  // header
	( PRAGMA_FORWARD  // declaration only
	| block IDENT )  // implementation
	;

method_arg :
	LABELED_IDENT '(' IDENT ':' IDENT ')'
	;

block :
	( declaration_permitted_in_block )*
	( 'BEGIN' statement_sequence )?
	'END'
	;

// Statements

statement_sequence :
	statement ( ';' statement )*
	;

statement :
	( assignment_or_procedure_call
	| message
	| loop_statement
	| for_statement
	| return_statement
	| try_statement
	| critical_statement
	| 'EXIT'
	)?
	;

// combined rule for assignment, incr-or-decr-statement and procedure call
assignment_or_procedure_call :
	variable_designator
	( ':=' expression
	| '++'
	| '--'
	| ( actual_parameters )?
	)
	;

message :
	'[' IDENT ( IDENT )?
		( LABELED_IDENT expression )+
	']'
	;

if_statement :
	'IF' expression 'THEN' statement_sequence
	( 'ELSIF' expression 'THEN' statement_sequence )*
	( 'ELSE' statement_sequence )?
	'END'
	;

case_statement :
	'CASE' expression 'OF'
	( case_label_list ':' statement_sequence )?
	( '|' case_label_list ':' statement_sequence )*
	( 'ELSE' statement_sequence )?
	'END'
	;

case_label_list :
	const_range ( ',' const_range )*
	;

while_statement :
	'WHILE' expression 'DO' statement_sequence
	'END'
	;

repeat_statement :
	'REPEAT' statement_sequence
	'UNTIL' expression
	;

loop_statement :
	'LOOP' statement_sequence
	'END'
	;

for_statement :
	'FOR' IDENT ':=' expression 'TO' expression ( 'BY' const_expression )?
	'DO' statement_sequence
	'END'
	;

return_statement :
	'RETURN' ( expression )?
	;

try_statement :
	'TRY' statement_sequence
	'ON' expression 'DO' statement_sequence
	'CONTINUE' statement_sequence
	'END'
	;

critical_statement :
	'CRITICAL' '(' IDENT ')'
	statement_sequence
	'END'
	;

// Expressions

expression_list :
	expression ( ',' expression )*
	;

expression :
	simple_expression
	( relational_operator simple_expression )?
	;

simple_expression :
	( sign )? term ( term_operator term )*
	;

term :
	factor ( factor_operator factor )*
	;

factor :
	  literal
	| value_designator
	| message
	| unary_expression
	| '(' expression ')'
	;

unary_expression :
	unary_operator factor
	;

const_expression :
	simple_const_expression
	( relational_operator simple_const_expression )?
	;

simple_const_expression :
	( sign )? const_term ( term_operator const_term )*
	;

const_term :
	const_factor ( factor_operator const_factor )*
	;

const_factor :
	  literal
	| qualified_ident ( unprefixed_const_set )?
	| unprefixed_const_set
	| const_unary_expression
	| '(' const_expression ')'
	;

unprefixed_const_set :
	'{' ( const_range ( ',' const_range )* )? '}'
	;

const_range :
	const_expression ( '..' const_expression )?
	;

const_unary_expression :
	unary_operator const_factor
	;

// Operators

sign :
	'+' | '-'
	;

unary_operator :
	'NOT' | '~'
	;

relational_operator :
	  '='
	| '#'
	| '<>'
	| '!='
	| '<'
	| '<='
	| '>'
	| '>='
	| 'IN'
	;

term_operator :
	  '+'
	| '-'
	| 'OR'
	;

factor_operator :
	  '*'
	| '/'
	| 'DIV'
	| 'MOD'
	| 'AND'
	| '&'
	;

literal :
	  OCTAL_WHOLE_NUMBER_LITERAL
	| DECIMAL_WHOLE_NUMBER_LITERAL
	| SEDECIMAL_WHOLE_NUMBER_LITERAL
	| REAL_NUMBER_LITERAL
	| SEVEN_BIT_ASCII_CHARACTER_CODE_LITERAL
	| UNICODE_CHARACTER_CODE_LITERAL
	| STRING_LITERAL
	;


// ---------------------------------------------------------------------------
// L E X E R   G R A M M A R
// ---------------------------------------------------------------------------

// ignore ASCII TAB, whitespace, ASCII CR, ASCII LF and ASCII NUL

WHITESPACE : (  ' ' | '\t' | '\u000C' | '\r' | '\n' )+ { $channel = HIDDEN; };

// Identifiers

IDENT :
	( '_' | '$' | LETTER )
    ( '_' | '$' | LETTER | DIGIT )*
    ;

LABELED_IDENT :
	( '_' | '$' | LETTER )
	( '_' | '$' | LETTER | DIGIT )*
	( ':' )?
    ;

// Numeric Literals

OCTAL_WHOLE_NUMBER_LITERAL
	: ( OCTAL_DIGIT )+ 'B'
	;

DECIMAL_WHOLE_NUMBER_LITERAL
	: ( DIGIT )+
	;

SEDECIMAL_WHOLE_NUMBER_LITERAL
	: ( ( DIGIT ) ( NON_DECIMAL_DIGIT )? )+ 'H'
	;

REAL_NUMBER_LITERAL
	: ( DIGIT )+ '.' ( DIGIT )+
	  ( 'E' ( '+' | '-' )? ( DIGIT )+ )?
	;

SEVEN_BIT_ASCII_CHARACTER_CODE_LITERAL
	: ( OCTAL_DIGIT )+ 'C'
	;

UNICODE_CHARACTER_CODE_LITERAL
	: ( DIGIT ( NON_DECIMAL_DIGIT )? )+ 'U'
	;

// Character literals

STRING_LITERAL :	
	  SINGLE_QUOTED_STRING
	| DOUBLE_QUOTED_STRING
	;

// Pragmas

PRAGMA_FORWARD : '<*' 'FORWARD' '*>' ;

PRAGMA_FRAMEWORK : '<*' 'FRAMEWORK' '*>' ;

PRAGMA_IMMUTABLE : '<*' 'IMMUTABLE' '*>' ;

PRAGMA_IBACTION : '<*' 'IBAction' '*>' { $channel = HIDDEN; } ;

PRAGMA_IBOUTLET : '<*' 'IBOutlet' '*>' { $channel = HIDDEN; } ;

// Comments

C_COMMENT :
	'/*' ( options { greedy = false; } : . )* '*/'
	{ $channel = HIDDEN; } ;

M2_COMMENT :
	'(*'( options { greedy = false; } : . )* ( M2_COMMENT )* '*)'
	{ $channel = HIDDEN; } ;	

BCPL_COMMENT :
	'//' ~('\n'|'\r')* ( '\r' )? '\n'
	{ $channel = HIDDEN; } ;

// Lexer Grammar Macros

fragment SINGLE_QUOTED_STRING :
	'\''
	( ' ' | '!' | '#' | '$' | '%' | '&' | '(' | ')' | '*' | '+' |
	  ',' | '-' | '.' | '/' | ':' | ';' | '<' | '=' | '>' | '?' |
	  '@' | '[' | ']' | '^' | '_' | '`' | '{' | '|' | '}' | '~' |
	  '"' | DIGIT | LETTER | ESCAPED_CHARACTER )*
	'\''	
	;

fragment DOUBLE_QUOTED_STRING :	
	'"'
	( ' ' | '!' | '#' | '$' | '%' | '&' | '(' | ')' | '*' | '+' |
	  ',' | '-' | '.' | '/' | ':' | ';' | '<' | '=' | '>' | '?' |
	  '@' | '[' | ']' | '^' | '_' | '`' | '{' | '|' | '}' | '~' |
	  '\'' | DIGIT | LETTER | ESCAPED_CHARACTER )*
	'"'
	;

fragment LETTER : 'A' .. 'Z' | 'a' .. 'z' ;

fragment DIGIT : OCTAL_DIGIT | '8' | '9' ;

fragment ESCAPED_CHARACTER : ( '\\' ( '\'' | '"' | '\\' | 'n' | 'r' | 't' | '0' ) ) ;

fragment OCTAL_DIGIT : '0' .. '7' ;
	
fragment NON_DECIMAL_DIGIT : 'A' .. 'F' ;

fragment SEDECIMAL_DIGIT : DIGIT | NON_DECIMAL_DIGIT ;

// END OF FILE
