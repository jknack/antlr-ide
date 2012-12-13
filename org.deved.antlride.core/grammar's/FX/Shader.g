//
// ANTLR grammar for HLSL files
//
// (C) 2009 Christian Schladetsch
// (C) 2009 Blue Lion Software
// Permission to use for any purpose is given, as long as this copyright
// information is included in any derived works.

grammar Shader;

options
{
	language = 'CSharp';
	output = AST;
	backtrack = true; 
	memoize = true;
}

// virtual tokens for AST tree nodes
tokens
{
	SAMPLER;
	SAMPLER_FIELD_TEXTURE;
	SAMPLER_FIELD_ATTRIB;
	
	STRUCT;
	FUNCTION;
	LOCAL_VAR;
	CAST;
	
	SEMANTIC;
	USER_SEMANTIC;
	
	SUBSCRIPT;
	UNIFORM;
	INDEX;
	FUNCTION_CALL;
	ARG_LIST;
	FORMAL_ARG;
	FORMAL_ARG_LIST;
	EMPTY_FORMAL_ARG_LIST;
	IDENT_LIST;
	ASSIGN;
	BLOCK;
	EMPTY;
	
	IF;
	IF_ELSE;
	ELSE;
	
	ASSERT;
	LAMBDA;
	FIELD_ACCESS;
	DEBUG_BREAK;
	BREAK;
	TRACE;
	LITERAL;
}

@header
{
	using System.Collections.Generic;
	using System.Diagnostics;
}

@members
{
	public bool trace = false;
	void Trace(string s)
	{
		if (!trace)
			return;
		s = "parse: " + s;
		Debug.WriteLine(s);
		Console.WriteLine(s);
	}
}

fx_file
	:	toplevel*
	;
	
toplevel
	:	global
	|	structure
	|	function
	|	compile_fragment
	;

global
	:	sampler
	|	uniform
	|	texture
	;
	
uniform
	:	ty=ident id=ident sub=subscript? sem=semantic? ';' 
		-> ^(UNIFORM $ty $id $sub? $sem?)
	;
	
subscript
	:	'[' num=expression ']' -> ^(SUBSCRIPT $num)
	;
	
structure
	:	'struct' ident '{' fields+=structure_field* '}' ';' 
		-> ^(STRUCT ident $fields*)
	;
	
structure_field
	:	uniform
	;

function
	:	ret=ident name=ident '(' args=formal_arglist ')' semantic? block
		-> ^(FUNCTION $ret $name $args semantic? block)
	;
	
semantic
	:	':' n=Identifier -> ^(SEMANTIC $n)
	|	':' r=UserSemantic -> ^(USER_SEMANTIC $r)
	;
	
sampler
	:	'sampler2D' id=ident '=' 'sampler_state' '{' fields+=sampler_field* '}' ';' 
		-> ^(SAMPLER $id $fields*)
	;
	
sampler_field
	:	'Texture' '=' '<' ident '>' ';'		{Trace("sampler_field_texture");} -> ^('Texture' ident)
	|	ident '='^ (ident | literal) ';'!
	;

texture
	:	'texture'^ ident ';'!
	;
	
compile_fragment
	:	('vertexfragment'^ | 'pixelfragment'^) ident '='! ('compile' | 'compile_fragment') ! ident ident arguments ';'!
	;
	
ident
	:	id=Identifier
	;	
	
block
	:	'{' statements+=statement* '}' -> ^(BLOCK $statements*)
	;
	
formal_arglist
	:	formal_arg (',' formal_arg)*
		-> formal_arg+
	|	-> /* nothing */
	;
	
formal_arg
	:	dir=direction? ty=type_name id=ident sub=subscript? sem=semantic?
		-> ^(FORMAL_ARG $ty $id $dir? $sub? $sem?)
	;
	
direction
	:	'in' | 'out'
	;
	
type_name
	:	ident
	|	'sampler2D'
	;
	
literal
	:	d=DecimalLiteral// -> ^(LITERAL $d)
	|	f=FloatingPointLiteral// -> ^(LITERAL $f)
	|	h=HexLiteral //-> ^(LITERAL $h)
	;

//---------------------------------------------------------------------------------
//					 S t a t e m e n t 
//---------------------------------------------------------------------------------

statement
	:	block
	|	if_block
	|	for_loop
	|	'delete'^ expression
	|	lc='assert' e=parExpression ';' -> ^(ASSERT[$lc] $e)
	|	'return' expression? ';' 
		-> ^('return' expression)
	//|	'while' parExpression statement
	//|	'do' statement 'while' parExpression ';'
	|	'asm_break' ';'!
	|	'break' ';'!
	|	'continue' ';'!
	|	';'!
	|	statementExpression ';'!
	;
	
for_loop
	:	'for' '(' start=expression? ';' cond=expression? ';' next=expression? ')' body=statement
		-> ^('for' $cond $next $body $start )
	;
	
// TODO: there is a more efficient way to do this, and still generate the correct AST tree
if_block
	:	'if' exp=parExpression true_=statement 'else' false_=statement -> ^(IF_ELSE $true_ $false_ $exp)
	|	'if' exp=parExpression true_=statement -> ^(IF $true_ $exp)
	;	
	
statementExpression
	:	expression
	|	ty=ident id=ident ('=' e=expression)?
		-> ^(LOCAL_VAR $ty $id $e?)
	;
	
//---------------------------------------------------------------------------------
//					 E x p r e s s i o n
//---------------------------------------------------------------------------------

expressionList
	:	expression (','! expression)*
	;

expression
	:	conditionalExpression (assignmentOperator^ expression)?
	;
	
assignmentOperator
	:	'='
	|	'+='
	|	'-='
	|	'*='
	|	'/='
	|	'&='
	|	'|='
	|	'^='
	|	'%='
	;

conditionalExpression
	:	conditionalOrExpression ( '?' expression ':' expression )?
	;

conditionalOrExpression
	:	conditionalAndExpression ( '||'^ conditionalAndExpression )*
	;

conditionalAndExpression
	:	inclusiveOrExpression ( '&&'^ inclusiveOrExpression )*
	;

inclusiveOrExpression
	:	exclusiveOrExpression ( '|'^ exclusiveOrExpression )*
	;

exclusiveOrExpression
	:	andExpression ( '^'^ andExpression )*
	;

andExpression
	:	equalityExpression ( '&'^ equalityExpression )*
	;

equalityExpression
	:	instanceOfExpression ( ('=='^ | '!='^) instanceOfExpression )*
	;

instanceOfExpression
	:	relationalExpression
	;

relationalExpression
	:	shiftExpression ( relationalOp^ shiftExpression )*
	;
	
relationalOp
	:	('<' '=' | '>' '=' | '<' | '>')
	;

shiftExpression
	:	additiveExpression ( shiftOp^ additiveExpression )*
	;

// TODO: need a sem pred to check column on these >>>
shiftOp
	:	('<' '<' | '>' '>')
	;

additiveExpression
	:	multiplicativeExpression ( ('+'^ | '-'^) multiplicativeExpression )*
	;

multiplicativeExpression
	:	unaryExpression ( ( '*'^ | '/'^ | '%'^ ) unaryExpression )*
	;
	
unaryExpression
	:	'+'^ unaryExpression
	|	'-'^ unaryExpression
	|	'++'^ unaryExpression
	|	'--'^ unaryExpression
	|	unaryExpressionNotPlusMinus
	;
	
unaryExpressionNotPlusMinus
	:	'~'^ unaryExpression
	| 	'!'^ unaryExpression
	|	postfixExpression ('++'^|'--'^)?
	;
	
postfixExpression
	:	(primary->primary) // set return tree to just primary
		( arguments
			-> ^(FUNCTION_CALL arguments $postfixExpression)
		|	'[' ie=expression ']'
			-> ^(INDEX $postfixExpression $ie)
			
		|	'.' p=primary
			-> ^('.' $postfixExpression $p)
		)*
	;
	
primary
	:	cast_expression
	|	parExpression
	|	literal
	|	ident
	;
	
cast_expression
	:	'(' ty=type_name ')' e=expression
		-> ^(CAST $ty $e)
	;
	
parExpression
	:	'(' expression ')' -> expression
	;
	
arguments
	:	'(' expressionList? ')' -> ^(ARG_LIST expressionList)
	;

//--------------------------------------------------------------------
//						L E X E R 
//--------------------------------------------------------------------

HexLiteral : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? ;

DecimalLiteral : ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;

OctalLiteral : '0' ('0'..'7')+ IntegerTypeSuffix? ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
IntegerTypeSuffix : ('l'|'L') ;

FloatingPointLiteral
	:	('0'..'9')+ '.' ('0'..'9')* Exponent? FloatTypeSuffix?
	|	'.' ('0'..'9')+ Exponent? FloatTypeSuffix?
	|	('0'..'9')+ (	  Exponent FloatTypeSuffix?
						| FloatTypeSuffix
					)
	;

fragment
Exponent : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
FloatTypeSuffix : ('f'|'F'|'d'|'D') ;

CharacterLiteral
	:	'\'' ( EscapeSequence | ~('\''|'\\') ) '\''
	;

StringLiteral
	:  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
	;

fragment
EscapeSequence
	:	'\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
	|	UnicodeEscape
	|	OctalEscape
	;

fragment
OctalEscape
	:	'\\' ('0'..'3') ('0'..'7') ('0'..'7')
	|	'\\' ('0'..'7') ('0'..'7')
	|	'\\' ('0'..'7')
	;

fragment
UnicodeEscape
	:	'\\' 'u' HexDigit HexDigit HexDigit HexDigit
	;

UserSemantic
	:	'r_' Identifier
	;
	
Identifier 
	:	Letter (Letter|JavaIDDigit)*
	;

QuotedIdentifier 
	:	'`' Identifier
	;

/**I found this char range in JavaCC's grammar, but Letter and Digit overlap.
	Still works, but...
 */
fragment
Letter
	:  '\u0024' |
		'\u0041'..'\u005a' |
		'\u005f' |
		'\u0061'..'\u007a' |
		'\u00c0'..'\u00d6' |
		'\u00d8'..'\u00f6' |
		'\u00f8'..'\u00ff' |
		'\u0100'..'\u1fff' |
		'\u3040'..'\u318f' |
		'\u3300'..'\u337f' |
		'\u3400'..'\u3d2d' |
		'\u4e00'..'\u9fff' |
		'\uf900'..'\ufaff'
	;

fragment
JavaIDDigit
	:  '\u0030'..'\u0039' |
		'\u0660'..'\u0669' |
		'\u06f0'..'\u06f9' |
		'\u0966'..'\u096f' |
		'\u09e6'..'\u09ef' |
		'\u0a66'..'\u0a6f' |
		'\u0ae6'..'\u0aef' |
		'\u0b66'..'\u0b6f' |
		'\u0be7'..'\u0bef' |
		'\u0c66'..'\u0c6f' |
		'\u0ce6'..'\u0cef' |
		'\u0d66'..'\u0d6f' |
		'\u0e50'..'\u0e59' |
		'\u0ed0'..'\u0ed9' |
		'\u1040'..'\u1049'
	;

WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
	;

COMMENT
	:	'/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
	;

LINE_COMMENT
	:	'//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
	;

// EOF
