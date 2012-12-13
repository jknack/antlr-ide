/*
[The "BSD licence"]
Copyright (c) 2007-2008 Leon Jen-Yuan Su
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
grammar gUnit;

options {language=Java;}

tokens {
	OK = 'OK';
	FAIL = 'FAIL';
	DOC_COMMENT;
}

@header {
package org.deved.antlride.gunit.internal.core.parser;

import org.deved.antlride.gunit.core.model.dltk.ast.DASTGUnitTest;
}

@lexer::header {package org.deved.antlride.gunit.internal.core.parser;}

@members {
 List<DASTGUnitTest> statements = null;
 
 protected String text(Token token) {
	if (token == null)
		return "";
	String text = token.getText();
	return text == null ? "" : text;
 }

 protected int start(Token token) {
 	return start((CommonToken)token);
 }
 
 protected int start(CommonToken token) {
	return token.getStartIndex();
 }
 
 protected int end(Token token) {
 	return end((CommonToken)token);
 }
 
 protected int end(CommonToken token) {
	return token.getStopIndex() + 1;
 }
 
}

gUnitDef[List<DASTGUnitTest> statements]
@init {
this.statements = statements;
}	
  :
  'gunit' id ('walks' id)? ';' 
		header? testsuite*
	;

header	:	'@header' ACTION
	;
		
testsuite
	:	(	r=RULE_REF ('walks' RULE_REF)? 
		|	r=TOKEN_REF
		)
		{statements.add(new DASTGUnitTest(text(r), start(r), end(r)));}
		':' 
		testcase+
	;

testcase
	:	input expect
	;

input
	:	STRING 
	|	ML_STRING
	|	file
	;
	
expect
	:	OK
	|	FAIL
	|	'returns' RETVAL
	|	'->' output
	;

output
	:	STRING 
	|	ML_STRING
	|	AST
	|	ACTION
	;

file	
	:	id EXT?
	;

id 
	:	TOKEN_REF
	|	RULE_REF
	;

// L E X I C A L   R U L E S

SL_COMMENT
 	:	'//' ~('\r'|'\n')* '\r'? '\n' {$channel=HIDDEN;}
	;

ML_COMMENT
	:	'/*' {$channel=HIDDEN;} .* '*/'
	;

STRING	:	'"' ( ESC | ~('\\'|'"') )* '"' {setText(getText().substring(1, getText().length()-1));}
	;

ML_STRING
	:	
		'<<' .* '>>' 
	;

TOKEN_REF
	:	'A'..'Z' ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	;

RULE_REF
	:	'a'..'z' ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	;

EXT	:	'.'('a'..'z'|'A'..'Z'|'0'..'9')+;

RETVAL	:	NESTED_RETVAL {setText(getText().substring(1, getText().length()-1));}
	;

fragment
NESTED_RETVAL :
	'['
	(	options {greedy=false;}
	:	NESTED_RETVAL
	|	.
	)*
	']'
	;

AST	:	NESTED_AST (' '? NESTED_AST)*;

fragment
NESTED_AST :
	'('
	(	options {greedy=false;}
	:	NESTED_AST
	|	.
	)*
	')'
	;

ACTION
	:	NESTED_ACTION {setText(getText().substring(1, getText().length()-1));}
	;

fragment
NESTED_ACTION :
	'{'
	(	options {greedy=false; k=3;}
	:	NESTED_ACTION
	|	STRING_LITERAL
	|	CHAR_LITERAL
	|	.
	)*
	'}'
	;

fragment
CHAR_LITERAL
	:	'\'' ( ESC | ~('\''|'\\') ) '\''
	;

fragment
STRING_LITERAL
	:	'"' ( ESC | ~('\\'|'"') )* '"'
	;

fragment
ESC	:	'\\'
		(	'n'
		|	'r'
		|	't'
		|	'b'
		|	'f'
		|	'"'
		|	'\''
		|	'\\'
		|	'>'
		|	'u' XDIGIT XDIGIT XDIGIT XDIGIT
		|	. // unknown, leave as it is
		)
	;
	
fragment
XDIGIT :
		'0' .. '9'
	|	'a' .. 'f'
	|	'A' .. 'F'
	;

WS	:	(	' '
		|	'\t'
		|	'\r'? '\n'
		)+
		{$channel=HIDDEN;}
	;
