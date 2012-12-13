parser grammar PBFParser;

options {
  tokenVocab   = PBFLexer;
  output       = AST;
  ASTLabelType = CommonTree;
}

tokens {
  FILE;
  DECL;
  DEFS;
  SUPER;
  TYPE;
  PARAM;
  ARG;
  CALL;
  ITER;
  STRUCT;
  LIST;
  INTER;

  /* Lists */ /* Entities */ /* Processes */
  TES;
  TPS; // Template
  IES;
  IPS; // Instance
  FES;
  FPS; // Full
  MES;
  MPS; // Mixed
  IKS;

  /* Definitions */ /* Entity */ /* Process */
  TE;
  TP; // Template
  IE;
  IP; // Instance
  FE;
  FP; // Full
  ME;
  MP; // Mixed
}

@header {
package parser3;

//import util.*;			//NOTE: comment in debug
}

@members {
enum Type {
	LIBRARY, MODEL, INCOMPLETE_MODEL, FULL_MODEL, MIXED_MODEL,
}

Type fileType;

private String concat(List tokens) {
	StringBuilder buf = new StringBuilder();
	for (Object token : tokens) {
		buf.append(((CommonToken) token).getText());
	}
	return buf.toString();
}

public void reportError(RecognitionException e) {
	// if we've already reported an error and have not matched a token
	// yet successfully, don't report any errors.
	if (state.errorRecovery) {
		//System.err.print("[SPURIOUS] ");
		return;
	}
	state.syntaxErrors++; // don't count spurious
	state.errorRecovery = true;

	int linenum = e.line;
	int posnum = e.charPositionInLine;

	String message = getErrorMessage(e, this.getTokenNames());
	//throw new ParsingException(linenum, posnum, message, e); //NOTE: comment in debug
	//displayRecognitionError(this.getTokenNames(), e);
}

protected Object recoverFromMismatchedToken(IntStream input, int ttype,
		BitSet follow) throws RecognitionException {
	throw new MismatchedTokenException(ttype, input);
}
}

@rulecatch {
catch (RecognitionException ex) {
	reportError(ex);
}
}

/* Parser */

literal
  :
  INT_LITERAL
  | DOUBLE_LITERAL
  | STRING_LITERAL
  ;

keyword
  :
  LIBRARY
  | MODEL
  | PROCESS
  | ENTITY
  | TEMPLATE
  | INF
  | UNSPECIFIED
  | COMPARTMENT
  ;

/* Types */

structure
  :
  keyword* ID
  (
    block (EQ value)?
    | eq=EQ value
  )
    ->
      ^(
        STRUCT[$ID, "STRUCT"] ID keyword* block?
        ^(ID[$eq, "value"] value)?
       )
  ;

structureNull
  :
  keyword* ID
  (
    blockNull (EQ valueNull)?
    | eq=EQ valueNull
  )
    ->
      ^(
        STRUCT[$ID, "STRUCT"] ID keyword* blockNull?
        ^(ID[$eq, "value"] valueNull)?
       )
  ;

block
  :
  LBRACE! propDefs RBRACE!
  ;

blockNull
  :
  LBRACE! propDefsNull RBRACE!
  ;

propDefs
  :
  (pd+=propDef (SEMI pd+=propDef)* SEMI?)?
    -> $pd*
  ;

propDefsNull
  :
  (pd+=propDefNull (SEMI pd+=propDefNull)* SEMI?)?
    -> $pd*
  ;

propDef
  :
  ID^ COLON! valueList
  ;

propDefNull
  :
  ID^ COLON! valueListNull
  ;

valueList
  :
  value (COMMA! value)*
  ;

valueListNull
  :
  valueNull (COMMA! valueNull)*
  ;

value
  :
  (
    INT_LITERAL
    (
      COMMA
      | SEMI
      | RBRACE
      | RBRACKET
      | RPAREN
    )
  )
    => INT_LITERAL
  |
  (
    DOUBLE_LITERAL
    (
      COMMA
      | SEMI
      | RBRACE
      | RBRACKET
      | RPAREN
    )
  )
    => DOUBLE_LITERAL
  |
  (
    STRING_LITERAL
    (
      COMMA
      | SEMI
      | RBRACE
      | RBRACKET
      | RPAREN
    )
  )
    => STRING_LITERAL
  | interval
  |
  (
    ID
    (
      COMMA
      | SEMI
      | RBRACE
      | RBRACKET
      | RPAREN
    )
  )
    => ID
  | expression
  | structure
  | list
  ;

valueNull
  :
  (
    NULL
    (
      COMMA
      | SEMI
      | RBRACE
      | RBRACKET
      | RPAREN
    )
  )
    => NULL
  |
  (
    INT_LITERAL
    (
      COMMA
      | SEMI
      | RBRACE
      | RBRACKET
      | RPAREN
    )
  )
    => INT_LITERAL
  |
  (
    DOUBLE_LITERAL
    (
      COMMA
      | SEMI
      | RBRACE
      | RBRACKET
      | RPAREN
    )
  )
    => DOUBLE_LITERAL
  |
  (
    STRING_LITERAL
    (
      COMMA
      | SEMI
      | RBRACE
      | RBRACKET
      | RPAREN
    )
  )
    => STRING_LITERAL
  | interval
  |
  (
    ID
    (
      COMMA
      | SEMI
      | RBRACE
      | RBRACKET
      | RPAREN
    )
  )
    => ID
  | expression
  | structureNull
  | list
  ;

list
  :
  LBRACKET value (COMMA value)* RBRACKET
    -> value*
  ;

interval
  :
  lt=LT lb=signedNumberOrInfinity COMMA ub=signedNumberOrInfinity GT
    ->
      ^(INTER[$lt, "INTER"] $lb $ub)
  ;

/* Numbers with infinity */

signedNumberOrInfinity
  :
  signedNumber
  | signedInfinity
  ;

signedNumber
  :
  signedInteger
  | signedDouble
  ;

signedInteger
  :
  sign^? INT_LITERAL
  ;

signedDouble
  :
  sign^? DOUBLE_LITERAL
  ;

signedInfinity
  :
  sign^? INF
  ;

integerOrInfinity
  :
  INT_LITERAL
  | INF
  ;

/* Paramters */

parameters
  :
  LPAREN! parameterList? RPAREN!
  ;

parameterList
  :
  parameter (COMMA! parameter)*
  ;

parameter
  :
  instID=ID c=COLON tempID=ID cc=card?
    ->
      ^(
        PARAM[$instID, "PARAM"] $instID
        ^(ID[$c, "template"] $tempID)
        ^(ID[$tempID, "card"] card?)
       )
  ;

card
  :
  lt=LT lb=integerOrInfinity (COMMA ub=integerOrInfinity)? GT
    ->
      ^(INTER[$lt, "INTER"] $lb $ub?)
  ;

/* Colon notaions */

superEntity
  :
  COLON! ID
  ;

entityType
  :
  COLON! ID
  ;

superProcess
  :
  COLON! ID
  ;

processType
  :
  COLON! ID
  ;

compType
  :
  COLON! ID
  ;

/* Entities and Processes */

templateEntity
  :
  t=TEMPLATE ENTITY id=ID se=superEntity block
    ->
      ^(
        STRUCT[$t, "TE"] ID
        ^(ID[$se.tree.getToken(), "super"] $se)
        block?
       )
  | t=TEMPLATE ENTITY id=ID block
    ->
      ^(
        STRUCT[$t, "TE"] ID
        ^(ID[$id, "super"] ID[$id, "Entity"])
        block?
       )
  ;

templateProcess
  :
  t=TEMPLATE PROCESS id=ID par=parameters? sp=superProcess block
    ->
      ^(
        STRUCT[$t, "TP"] ID
        ^(ID[$id, "parameters"] $par?)
        ^(ID[$sp.tree.getToken(), "super"] $sp)
        block?
       )
  | t=TEMPLATE PROCESS id=ID par=parameters? block
    ->
      ^(
        STRUCT[$t, "TP"] ID
        ^(ID[$id, "parameters"] $par?)
        ^(ID[$id, "super"] ID[$id, "Process"])
        block?
       )
  ;

instanceEntity
  :
  i=ENTITY id=ID et=entityType block
    ->
      ^(
        STRUCT[$i, "IE"] ID
        ^(ID[$et.tree.getToken(), "template"] $et)
        block?
       )
  ;

instanceProcess
  :
  i=PROCESS id=ID arg=arguments1? pt=processType block
    ->
      ^(
        STRUCT[$i, "IP"] ID
        ^(ID[$id, "arguments"] $arg?)
        ^(ID[$pt.tree.getToken(), "template"] $pt)
        block?
       )
  ;

incompleteEntity
  :
  i=ENTITY id=ID et=entityType blockNull
    ->
      ^(
        STRUCT[$i, "IE"] ID
        ^(ID[$et.tree.getToken(), "template"] $et)
        blockNull?
       )
  ;

incompleteProcess
  :
  i=PROCESS id=ID arg=arguments1? pt=processType blockNull
    ->
      ^(
        STRUCT[$i, "IP"] ID
        ^(ID[$id, "arguments"] $arg?)
        ^(ID[$pt.tree.getToken(), "template"] $pt)
        blockNull?
       )
  ;

fullEntity
  :
  f=ENTITY id=ID block
    ->
      ^(STRUCT[$f, "FE"] ID block?)
  ;

fullProcess
  :
  f=PROCESS id=ID arg=arguments1? block
    ->
      ^(
        STRUCT[$f, "FP"] ID
        ^(ID[$id, "arguments"] $arg?)
        block?
       )
  ;

mixedEntity
  :
  m=ENTITY id=ID et=entityType? block
    ->
      ^(
        STRUCT[$m, "ME"] ID
        ^(ID[$et.tree.getToken(), "template"] $et?)
        block?
       )
  ;

mixedProcess
  :
  m=PROCESS id=ID arg=arguments1? (pt=processType par=parameters?)? block
    ->
      ^(
        STRUCT[$m, "MP"] ID
        ^(ID[$id, "arguments"] $arg?)
        ^(ID[$id, "template"] $pt?)
        (
          ^(ID[$id, "parameters"] $par))? block?
       )
  ;

templateCompartment
  :
  t=TEMPLATE COMPARTMENT ID block
    ->
      ^(STRUCT[$t, "TK"] ID block?)
  ;

instanceCompartment
  :
  i=COMPARTMENT id=ID ct=compType compBlock
    ->
      ^(
        STRUCT[$i, "IK"] ID
        ^(ID[$ct.tree.getToken(), "template"] $ct)
        compBlock?
       )
  ;

incompleteCompartment
  :
  i=COMPARTMENT id=ID ct=compType compBlockNull
    ->
      ^(
        STRUCT[$i, "IK"] ID
        ^(ID[$ct.tree.getToken(), "template"] $ct)
        compBlockNull?
       )
  ;

compBlock
  :
  l=LBRACE
  (
    ies+=instanceEntity
    | ips+=instanceProcess
    | iks+=instanceCompartment
  )*
  RBRACE
    ->
      ^(ID[$l, "ies"] $ies*)
      ^(ID[$l, "ips"] $ips*)
      ^(ID[$l, "iks"] $iks*)
  ;

compBlockNull
  :
  l=LBRACE
  (
    ies+=incompleteEntity
    | ips+=incompleteProcess
    | iks+=incompleteCompartment
  )*
  RBRACE
    ->
      ^(ID[$l, "ies"] $ies*)
      ^(ID[$l, "ips"] $ips*)
      ^(ID[$l, "iks"] $iks*)
  ;

/* Definitions */

templateDefs
  :
  (
    tes+=templateEntity
    | tps+=templateProcess
    | tks+=templateCompartment
  )*
    ->
      ^(ID["tes"] $tes*)
      ^(ID["tps"] $tps*)
      ^(ID["tks"] $tks*)
  ;

instanceDefs
  :
  (
    ies+=instanceEntity
    | ips+=instanceProcess
    | iks+=instanceCompartment
  )*
    ->
      ^(ID["ies"] $ies*)
      ^(ID["ips"] $ips*)
      ^(ID["iks"] $iks*)
  ;

incompleteDefs
  :
  (
    ies+=incompleteEntity
    | ips+=incompleteProcess
    | iks+=incompleteCompartment
  )*
    ->
      ^(ID["ies"] $ies*)
      ^(ID["ips"] $ips*)
      ^(ID["iks"] $iks*)
  ;

fullDefs
  :
  (
    fes+=fullEntity
    | fps+=fullProcess
  )*
    ->
      ^(ID["fes"] $fes*)
      ^(ID["fps"] $fps*)
  ;

mixedDefs
  :
  (
    mes+=mixedEntity
    | mps+=mixedProcess
  )*
    ->
      ^(ID["mes"] $mes*)
      ^(ID["mps"] $mps*)
  ;
/*
templateDef
	:	templateEntity
	|	templateProcess
	;


instanceDef
	:	instanceEntity
	|	instanceProcess
	;


fullDef
	:	fullEntity
	|	fullProcess
	;


mixedDef
	:	mixedEntity
	|	mixedProcess
	;
	
*/

/* Headers */


libDecl
  :
  LIBRARY ID SEMI
    -> ID
  ;

modelDecl
  :
  MODEL modelID=ID c=COLON libID=ID SEMI
    -> $modelID
      ^(ID[$c, "template"] $libID)
  ;

incompleteModelDecl
  :
  INCOMPLETE MODEL modelID=ID c=COLON libID=ID SEMI
    -> $modelID
      ^(ID[$c, "template"] $libID)
  ;

fullModelDecl
  :
  MODEL ID c=COLON UNSPECIFIED SEMI
    -> ID
      ^(ID[$c, "template"] UNSPECIFIED)
  ;

mixedModelDecl
  :
  MODEL modelID=ID c=COLON UNSPECIFIED libID=ID SEMI
    -> $modelID
      ^(ID[$c, "template"] $libID)
  ;

/* Files */

libFile
  :
  libDecl templateDefs
    ->
      ^(FILE libDecl templateDefs)
  ;

modelFile
  :
  modelDecl instanceDefs
    ->
      ^(FILE modelDecl instanceDefs)
  ;

incompleteFile
  :
  incompleteModelDecl incompleteDefs
  ;

fullFile
  :
  fullModelDecl fullDefs
    ->
      ^(FILE fullModelDecl fullDefs)
  ;

mixedFile
  :
  mixedModelDecl mixedDefs
    ->
      ^(FILE mixedModelDecl mixedDefs)
  ;

/* Top-level rules */

header //returns [Type fileType]
  :
  libDecl 
          {
           fileType = Type.LIBRARY;
          }
  | modelDecl 
              {
               fileType = Type.MODEL;
              }
  | incompleteModelDecl 
                        {
                         fileType = Type.INCOMPLETE_MODEL;
                        }
  | fullModelDecl 
                  {
                   fileType = Type.FULL_MODEL;
                  }
  | mixedModelDecl 
                   {
                    fileType = Type.MIXED_MODEL;
                   }
  ;

defs //[Type fileType]
  :
  {(fileType == Type.LIBRARY)}?=> templateDefs
  | {(fileType == Type.MODEL)}?=> instanceDefs
  | {(fileType == Type.INCOMPLETE_MODEL)}?=> incompleteDefs
  | {(fileType == Type.FULL_MODEL)}?=> fullDefs
  | {(fileType == Type.MIXED_MODEL)}?=> mixedDefs
  ;

file
  :
  header defs /*[$header.fileType]*/
  EOF
    ->
      ^(STRUCT[fileType.toString()] header defs)
  ;

/* Expressions */

expression
  :
  assignmentExpression
  ;

assignmentExpression
  :
  eqExpression (EQ^ assignmentExpression)?
  ;

eqExpression
  :
  relExpression (eqOperator^ relExpression)*
  ;

relExpression
  :
  addExpression (relOperator^ addExpression)*
  ;

addExpression
  :
  mulExpression (addOperator^ mulExpression)*
  ;

mulExpression
  :
  unaryExpression (mulOperator^ unaryExpression)*
  ;

unaryExpression
  :
  unaryOperator^ unaryExpression
  | primary
  ;

primary
  :
  parExpression
  | iterOrIDArg (DOT^ iterOrIDArg)*
  | literal
  ;

iterOrIDArg
  :
  (i=iterOrID
      -> iterOrID) (arguments
      ->
        ^(CALL[$i.tree.getToken(), "CALL"] $iterOrIDArg arguments?))?
  ;

iterOrID
  :
  ID
  | iterator
  ;

iterator
  :
  lt=LT elem=ID COLON set=ID GT
    ->
      ^(ITER[$lt, "ITER"] $elem $set)
  ;

parExpression
  :
  LPAREN! expression RPAREN!
  ;

arguments
  :
  LPAREN! valueList? RPAREN!
  ;

arguments1 // Argument list for instance processes, i.e., list of AEs
  :
  LPAREN! argumentList? RPAREN!
  ;

argumentList
  :
  argument (COMMA! argument)*
  ;

argument
  :
  instID=ref
    ->
      ^(
        STRUCT[$instID.tree.getToken(), "AE"]
        ^(ID[$instID.tree.getToken(), "arg"] $instID)
       )
  | instIDs=refList
    ->
      ^(
        STRUCT[($instIDs.tree.getToken()!=null)?$instIDs.tree.getToken():((CommonTree)$instIDs.tree.getChild(0)).getToken(), "AE"]
        ^(ID[($instIDs.tree.getToken()!=null)?$instIDs.tree.getToken():((CommonTree)$instIDs.tree.getChild(0)).getToken(), "arg"] $instIDs)
       )
  ;

refList
  :
  LBRACKET ref (COMMA ref)* RBRACKET
    -> ref*
  ;

ref
  :
  parts+=ID (parts+=DOT parts+=ID)*
    ->
      ^(ID[((CommonToken)$parts.get(0)), concat($parts)])
  ;

/* Operators */

eqOperator
  :
  EQEQ
  | BANGEQ
  ;

relOperator
  :
  LT EQ
  | GT EQ
  | LT
  | GT
  ;

addOperator
  :
  PLUS
  | MINUS
  ;

mulOperator
  :
  STAR
  | SLASH
  ;

unaryOperator
  :
  PLUS
  | MINUS
  ;

sign
  :
  PLUS
  | MINUS
  ;
