lexer grammar PBFLexer;

@header {
package parser3;

//import util.*;				//NOTE: comment in debug
}

@members {

public Token nextToken() {
	while (true) {
		state.token = null;
		state.channel = Token.DEFAULT_CHANNEL;
		state.tokenStartCharIndex = input.index();
		state.tokenStartCharPositionInLine = input.getCharPositionInLine();
		state.tokenStartLine = input.getLine();
		state.text = null;
		if ( input.LA(1)==CharStream.EOF ) {
			return Token.EOF_TOKEN;
		}
		try {
			mTokens();
			if ( state.token==null ) {
				emit();
			}
			else if ( state.token==Token.SKIP_TOKEN ) {
				continue;
			}
			return state.token;
		}
		catch (RecognitionException re) {
			reportError(re);
		}
	}
}

	public void reportError(RecognitionException e) {
		// if we've already reported an error and have not matched a token
		// yet successfully, don't report any errors.
		if ( state.errorRecovery ) {
			//System.err.print("[SPURIOUS] ");
			return;
		}
		state.syntaxErrors++; // don't count spurious
		state.errorRecovery = true;

		int linenum = e.line;
		int posnum = e.charPositionInLine;
		
		String message = getErrorMessage(e, this.getTokenNames());
//		throw new ParsingException(linenum, posnum, message, e);			//NOTE: comment in debug
		//displayRecognitionError(this.getTokenNames(), e);
	}


protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
		throws RecognitionException {	
	throw new MismatchedTokenException(ttype, input);
}

}

@rulecatch {

catch (RecognitionException ex) {
	reportError(ex);
}

}

/*** LEXER ***/


/* Fragments */

fragment
NonZeroDigit
	:	'1'..'9'
	;

fragment
Digit
	:	'0'
	|	NonZeroDigit
	;

fragment
Exponent
	:	ExponentIndicator Sign? Digit+
	;

fragment
ExponentIndicator
	:	'E'
	|	'e'
	;
	
fragment
Sign
	:	PLUS
	|	MINUS
	;

fragment
IdentifierStart
	:	'A'..'Z'
	|	'a'..'z'
	|	'_'
	;

fragment
IdentifierPart
	:	IdentifierStart
	|	Digit
	;

/*
fragment
IdentifierPartDot
	:	IdentifierPart
	|	'.'
	;
*/
/* Whitespace */

WS 
	: 
	(' '|'\t'|'\r'|'\n')+ {$channel = HIDDEN;}
	;


/* Comments */

LINE_COMMENT
	:	'//' ~('\n'|'\r')*  ('\r\n' | '\r' | '\n') {$channel = HIDDEN;}
	|	'//' ~('\n'|'\r')* {$channel = HIDDEN;}
	;   
	
BLOCK_COMMENT
	:	'/*' (options {greedy=false;} : . )* '*/' {$channel = HIDDEN;}
	;


/* Literals */

INT_LITERAL
	:	'0'
	|	NonZeroDigit Digit*
	;

DOUBLE_LITERAL
	:	Digit+ '.' Digit* Exponent?
	|	'.' Digit+ Exponent?
	|	Digit+ Exponent
	;
	
STRING_LITERAL
    :   '"' (~('"' | '\r' | '\n'))* '"' 
    ;


/* Separator */

/* Brackets */

LPAREN	: '(' ;

RPAREN	: ')' ;

LBRACKET	: '[' ;

RBRACKET	: ']' ;

LBRACE	: '{' ;

RBRACE	: '}' ;

GT			: '>' ;

LT			: '<' ;        
              

/* Punctuation */

SEMI		: ';' ;

COMMA		: ',' ;

DOT		: '.' ;
    
COLON		: ':' ;


/* Operators */

PLUS		: '+' ;

MINUS		: '-' ;

STAR		: '*' ;

SLASH		: '/' ;

EQ			: '=' ;

EQEQ		: '==' ;

BANGEQ	: '!=' ;




/* Keywords */

LIBRARY		: 'library';

MODEL			: 'model';

COMPARTMENT	: 'compartment';

PROCESS		: 'process';

ENTITY		: 'entity';

TEMPLATE		: 'template';

INF			: 'inf';

UNSPECIFIED	: 'unspecified';

INCOMPLETE	: 'incomplete';


/* Null value */

NULL			: 'null';


/* Identifier */

ID
	:	IdentifierStart IdentifierPart*
	;

/*
ID_DOT
	:	IdentifierStart IdentifierPartDot*
	;
	
*/