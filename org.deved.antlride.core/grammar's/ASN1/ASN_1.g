/**
 * ASN.1 Parsing
 */
grammar ASN_1;

options {
	language = Java;
	output = AST;
	ASTLabelType=CommonTree;
	memoize = true;
}

tokens {
  // virtual tokens
  ENCREF;
  MODULE;
  BODY;
  DEFID;
  NAMENUM;
  EXTIMPLIED;
  EXTEXPLICIT;
  MODSYMB;
  EXTTREF;
  EXTVREF;
  TASS;
  VASS;
  XVASS;
  SYMBOLICS;
  VSTASS;
  ITEMS;
  BITSTRING;
  SETBITS;
  BITSTRINGV;
  VALUE;
  OCTSTRING;
  MODULES;
  SEQVAL;
  VLIST;
  OBJID;
  IDS;
  VERSION;
  MEMBER;
  HERITCOMPS;
  EXTADD;
  ROOTCOMPS;
  EXTCOMPS;
  ENCPRFTYPE;
  SELTYPE;
  TAGTYPE;
  SELTYPE;
  EXTADDS;
  SEQOF;
  EMPTYLIST;
  SETOF;
  ROOTALTS;
  EXTADDALTS;
  VEREXT;
  CHOICEVAL;
  TAG;
  ENCPRF;
  OBJID;
  OBJIDT;
  EMBPDV;
  UCSTRING;
  GENTIME;
  UTCTIME;
  OBJDESC;
  ENCINS;
  CONSTRAINT;
  ELEMENTSET;
  ENTIRE;
  OPENEND;
  CLOSEDEND;
  ENCSECS;
  ENCASSLIST;
  CONTEXT_SPECIFIC;
  EXCLUDE;
  ACREF;
  COMPOUND;
}

scope manageSetHandling {
  boolean inObjectSet;
}

@header {
  package com.gobito.asn_1.parse;
  import static com.gobito.asn_1.parse.ParseUtil.*;
}
@lexer::header {
  package com.gobito.asn_1.parse;
  import static com.gobito.asn_1.parse.ParseUtil.*;
}

@members {
  private boolean doMacros = false;
  
  public boolean isProcessingMacros() {
    return doMacros;
  }
  
  public void setProcessingMacros(boolean doMacros) {
    this.doMacros = doMacros;
  }
}

@lexer::members {
  private static final Map<String,Integer> kwMap = new HashMap<String,Integer>();

  static {
    kwMap.put("ABSENT",ABSENT);
    kwMap.put("ABSTRACT-SYNTAX",ABSTRACT_SYNTAX);
    kwMap.put("ALL",ALL);
    kwMap.put("APPLICATION",APPLICATION);
    kwMap.put("AUTOMATIC",AUTOMATIC);
    kwMap.put("BEGIN",BEGIN);
    kwMap.put("BIT",BIT);
    kwMap.put("BMPString",BMPString);
    kwMap.put("BOOLEAN",BOOLEAN);
    kwMap.put("BY",BY);
    kwMap.put("CHARACTER",CHARACTER);
    kwMap.put("CHOICE",CHOICE);
    kwMap.put("CLASS",CLASS);
    kwMap.put("COMPONENT",COMPONENT);
    kwMap.put("COMPONENTS",COMPONENTS);
    kwMap.put("CONSTRAINED",CONSTRAINED);
    kwMap.put("CONTAINING",CONTAINING);
    kwMap.put("DEFAULT",DEFAULT);
    kwMap.put("DEFINITIONS",DEFINITIONS);
    kwMap.put("EMBEDDED",EMBEDDED);
    kwMap.put("ENCODED",ENCODED);
    kwMap.put("ENCODING-CONTROL",ENCODING_CONTROL);
    kwMap.put("END",END);
    kwMap.put("ENUMERATED",ENUMERATED);
    kwMap.put("EXCEPT",EXCEPT);
    kwMap.put("EXPLICIT",EXPLICIT);
    kwMap.put("EXPORTS",EXPORTS);
    kwMap.put("EXTENSIBILITY",EXTENSIBILITY);
    kwMap.put("EXTERNAL",EXTERNAL);
    kwMap.put("FALSE",FALSE);
    kwMap.put("FROM",FROM);
    kwMap.put("GeneralizedTime",GeneralizedTime);
    kwMap.put("GeneralString",GeneralString);
    kwMap.put("GraphicString",GraphicString);
    kwMap.put("IA5String",IA5String);
    kwMap.put("IDENTIFIER",IDENTIFIER); 
    kwMap.put("IMPLICIT",IMPLICIT);
    kwMap.put("IMPLIED",IMPLIED);
    kwMap.put("IMPORTS",IMPORTS);
    kwMap.put("INCLUDES",INCLUDES);
    kwMap.put("INSTANCE",INSTANCE);
    kwMap.put("INSTRUCTIONS",INSTRUCTIONS);
    kwMap.put("INTEGER",INTEGER);
    kwMap.put("INTERSECTION",INTERSECTION);
    kwMap.put("ISO646String",ISO646String);
    kwMap.put("MAX",MAX);
    kwMap.put("MIN",MIN);
    kwMap.put("MINUS-INFINITY",MINUS_INFINITY);
    kwMap.put("NOT-A-NUMBER",NOT_A_NUMBER);
    kwMap.put("NULL",NULL);
    kwMap.put("NumericString",NumericString);
    kwMap.put("OBJECT",OBJECT);
    kwMap.put("ObjectDescriptor",ObjectDescriptor);
    kwMap.put("OCTET",OCTET);
    kwMap.put("OF",OF);
    kwMap.put("OPTIONAL",OPTIONAL);
    kwMap.put("PATTERN",PATTERN);
    kwMap.put("PDV",PDV);
    kwMap.put("PLUS-INFINITY",PLUS_INFINITY);
    kwMap.put("PRESENT",PRESENT);
    kwMap.put("PrintableString",PrintableString);
    kwMap.put("PRIVATE",PRIVATE);
    kwMap.put("REAL",REAL);
    kwMap.put("RELATIVE-OID",RELATIVE_OID);
    kwMap.put("SEQUENCE",SEQUENCE);
    kwMap.put("SET",SET);
    kwMap.put("SIZE",SIZE);
    kwMap.put("STRING",STRING);
    kwMap.put("SYNTAX",SYNTAX);
    kwMap.put("T61String",T61String);
    kwMap.put("TAGS",TAGS);
    kwMap.put("TeletexString",TeletexString);
    kwMap.put("TRUE",TRUE);
    kwMap.put("TYPE-IDENTIFIER",TYPE_IDENTIFIER);
    kwMap.put("UNION",UNION);
    kwMap.put("UNIQUE",UNIQUE);
    kwMap.put("UNIVERSAL",UNIVERSAL);
    kwMap.put("UniversalString",UniversalString);
    kwMap.put("UTCTime",UTCTime);
    kwMap.put("UTF8String",UTF8String);
    kwMap.put("VideotexString",VideotexString);
    kwMap.put("VisibleString",VisibleString);
    kwMap.put("WITH",WITH);
  }
  
  private static int kwLookup(String text) {
      if (kwMap.containsKey(text)) {
        return kwMap.get(text);
      } else {
        return CAPID;
      }
  }
  
  private boolean doMacros = false;
  
  public boolean isProcessingMacros() {
    return doMacros;
  }
  
  public void setProcessingMacros(boolean doMacros) {
    this.doMacros = doMacros;
  }
}

// Lexical elements

// literals
/** test doc-comment */
fragment ABSENT : ;
fragment ABSTRACT_SYNTAX : ;
fragment ALL : ;
fragment APPLICATION : ;
fragment AUTOMATIC : ;
fragment BEGIN : ;
fragment BIT : ;
fragment BMPString : ;
fragment BOOLEAN : ;
fragment BY : ;
fragment CHARACTER : ;
fragment CHOICE : ;
fragment CLASS : ;
fragment COMPONENT : ;
fragment COMPONENTS : ;
fragment CONSTRAINED : ;
fragment CONTAINING : ;
fragment DEFAULT : ;
fragment DEFINITIONS : ;
fragment EMBEDDED : ;
fragment ENCODED : ;
fragment ENCODING_CONTROL : ;
fragment END : ;
fragment ENUMERATED : ;
fragment EXCEPT : ;
fragment EXPLICIT : ;
fragment EXPORTS : ;
fragment EXTENSIBILITY : ;
fragment EXTERNAL : ;
fragment FALSE : ;
fragment FROM : ;
fragment GeneralizedTime : ;
fragment GeneralString : ;
fragment GraphicString : ;
fragment IA5String : ;
fragment IDENTIFIER : ; 
fragment IMPLICIT : ;
fragment IMPLIED : ;
fragment IMPORTS : ;
fragment INCLUDES : ;
fragment INSTANCE : ;
fragment INSTRUCTIONS : ;
fragment INTEGER : ;
fragment INTERSECTION : ;
fragment ISO646String : ;
fragment MAX : ;
fragment MIN : ;
fragment MINUS_INFINITY : ;
fragment NOT_A_NUMBER : ;
fragment NULL : ;
fragment NumericString : ;
fragment OBJECT : ;
fragment ObjectDescriptor : ;
fragment OCTET : ;
fragment OF : ;
fragment OPTIONAL : ;
fragment PATTERN : ;
fragment PDV : ;
fragment PLUS_INFINITY : ;
fragment PRESENT : ;
fragment PrintableString : ;
fragment PRIVATE : ;
fragment REAL : ;
fragment RELATIVE_OID : ;
fragment SEQUENCE : ;
fragment SET : ;
fragment SIZE : ;
fragment STRING : ;
fragment SYNTAX : ;
fragment T61String : ;
fragment TAGS : ;
fragment TeletexString : ;
fragment TRUE : ;
fragment TYPE_IDENTIFIER : ;
fragment UNION : ;
fragment UNIQUE : ;
fragment UNIVERSAL : ;
fragment UniversalString : ;
fragment UTCTime : ;
fragment UTF8String : ;
fragment VideotexString : ;
fragment VisibleString : ;
fragment WITH : ;

MACRO : {doMacros}?=>'MACRO';
  
//"real" stuff

/**
 * Matches whitespace and causes resultant token to be hidden
 */
WS : (WSNONL | NL) {$channel=HIDDEN;};

fragment
NL : ('\n' | '\r' | '\u000B' | '\f');

fragment
WSNONL : (' ' | '\t');

fragment
LETTER: 'a'..'z' | 'A'..'Z';

fragment
DIGIT: '0'..'9';

fragment
IDHYPHEN: '-' (LETTER|DIGIT);

fragment
IDBODY : (LETTER|DIGIT|IDHYPHEN);

CAPID : 'A'..'Z' IDBODY* {$type = kwLookup($text);};

LCID : 'a'..'z' IDBODY*;

fragment
MLCOMMENTf : '/*' .* (MLCOMMENTf .*)* '*/'; 

MLCOMMENT : MLCOMMENTf {$channel=HIDDEN;};

SLCOMMENT : ('--' .*  ('--'|NL)) {$channel=HIDDEN;};

fragment
NUMBERf : '0' | '1'..'9' DIGIT*;

fragment
NUMBER : ;

REALNUMBER
  : (NUMBERf DOT DOT)=> NUMBERf {$type=NUMBER;}
  | (NUMBERf (DOT|'e'|'E'))=> NUMBERf (DOT(DIGIT+)?)? (('e'|'E')('+'|'-')? NUMBERf)?
  | NUMBERf {$type=NUMBER;};

fragment
HSTRINGCONT : ('0'..'9' | 'A'..'F' | WS)*;

HorBSTRING : '\'' cont=HSTRINGCONT '\'' ('H' {$type=HSTRING;}|'B' {$type=BSTRING;}) {($type == HSTRING) || isValidBSTRING($cont.text)}?;

fragment HSTRING : ;
fragment BSTRING : ;

/**
 * Matches a newline and surrounding non-newline whitespace
 */
fragment
CSTRINGNL : WSNONL* NL WSNONL*; 

CSTRING : '"' 
              (
                (CSTRINGNL)=>CSTRINGNL
                | ('""')=>'""'
                | ~'"'
              )*
          '"'
        {setText(parseCString($text));};

EXTMARK : '...' ;

COMMA : ',' ;

SEMI : ';' ;

LCURL : '{' ;

RCURL : '}' ;

DOT : '.' ;

ASSG : '::=' ;

LPAREN : '(' ;

RPAREN : ')' ;

DASH : '-' ;

L_VER : '[[' ;

R_VER : ']]' ;

LSQR : '[' ;

RSQR : ']' ;

COLON : ':' ;

LT: '<' ;

GT: '>' ;

PIPE : '|' ;

CARET : '^' ;

RANGE : '..' ;

EXMARK : '!' ;

// extras for information object stuff

IOLCID : '&' LCID ;

IOCAPID : '&' CAPID ;

// handling xml

fragment
XMLNAMECHAR : 'A'..'Z' | 'a'..'z' | '0'..'9' | DASH | COLON | '_' | DOT ;

fragment
XMLNAMESTART: 'A'..'Z' | 'a'..'z' | '_' | COLON ;

fragment
INVALIDINXML : LT | '&' ;

fragment
XMLENTREF : '&' XMLNAME SEMI ;

fragment
XMLNAME : XMLNAMESTART XMLNAMECHAR+;

fragment
XMLSATTVAL : '\'' ( ('&')=>XMLENTREF | (~('\''|INVALIDINXML) ) )* '\'';

fragment
XMLDATTVAL : '"' ( ('&')=>XMLENTREF | (~('"'|INVALIDINXML) ) )* '"';

fragment
XMLATTVAL : XMLDATTVAL | XMLSATTVAL ;

fragment
XMLATTRIB : XMLNAME '=' XMLATTVAL ;

fragment
WSBLOCK : WS+;

fragment
XMLATTRIBS
      : XMLATTRIB 
      | (XMLATTRIB WS)=>XMLATTRIB WSBLOCK XMLATTRIBS;

fragment
XMLTAGATTS
      : WSBLOCK XMLATTRIBS ;

fragment
XMLOPENTAG : LT XMLNAME XMLTAGATTS? WS* GT;

fragment
XMLCLOSETAG : '</' XMLNAME GT;

fragment
XMLSCLOSETAG : LT XMLNAME XMLTAGATTS? WS* '/>';

fragment
XMLNONEMPTYELEMENT : XMLOPENTAG XMLCONTENT XMLCLOSETAG;

fragment
XMLEMPTYELEMENT : XMLSCLOSETAG;

fragment
XMLELEMENT options {
  backtrack=true;
}
 
      : XMLEMPTYELEMENT | XMLNONEMPTYELEMENT ;

fragment
XMLCONTENT : (XMLELEMENT | XMLENTREF | ~INVALIDINXML) *;

XMLFRAG : XMLELEMENT;

// Parser rules

allCapReference : {ucidIsAllUC(input.LT(1).getText())}? CAPID -> ACREF[$CAPID];

moduleDefinition
  scope {
    TaggingForm tagging;
  }
  scope manageSetHandling;
  @init {
    $manageSetHandling::inObjectSet = false;
  }
  : id=moduleIdentifier DEFINITIONS enc=encodingReferenceDefault? td=tagDefault ext=extensionDefault ASSG BEGIN body=moduleBody encCon=encodingControlSections END
  ->  ^(MODULE[$DEFINITIONS,"MODULE"] $id $enc? $td $ext $body $encCon)
  ;

encodingReferenceDefault : (allCapReference INSTRUCTIONS) -> ^(INSTRUCTIONS[] allCapReference);
  
moduleIdentifier : CAPID definitiveIdentifier? -> ^(CAPID definitiveIdentifier?);

definitiveIdentifier 
 : o=LCURL definitiveObjIdComponent+ RCURL -> ^(OBJID[$o,"OBJID"] definitiveObjIdComponent+) ; 

definitiveObjIdComponent
  : nameAndNumberForm
  | numberForm
  | {isStandardOIDName(input.LT(1).getText())}? nameForm ;
    
numberForm : NUMBER ;

nameAndNumberForm : id=LCID o=LPAREN num=numberForm RPAREN -> ^(NAMENUM[$o,"NAMENUM"] $id $num); 

nameForm : LCID ;

tagDefault
  : (val=EXPLICIT | val=IMPLICIT | val=AUTOMATIC) TAGS
    {$moduleDefinition::tagging = TaggingForm.getByToken($val.type);} 
  -> ^(TAGS $val)
  | {$moduleDefinition::tagging = TaggingForm.EXPLICIT;} -> ^(TAGS EXPLICIT);

extensionDefault
  : EXTENSIBILITY IMPLIED -> EXTIMPLIED[$EXTENSIBILITY,"EXTIMPLIED"]
  | -> EXTEXPLICIT ;

moduleBody
  : (exports imports assignmentList) -> ^(BODY exports imports assignmentList) 
  | -> ^(BODY)
  ;

exports
    : EXPORTS symbolsExported SEMI -> ^(EXPORTS symbolsExported)
    | EXPORTS ALL SEMI -> ^(EXPORTS ALL)
    | -> ^(EXPORTS);

symbolsExported : symbolList?;

imports
  : IMPORTS symbolsImported SEMI -> ^(IMPORTS symbolsImported) 
  | -> ^(IMPORTS);

symbolsImported : symbolsFromModule+ ;

symbolsFromModule : symbolList FROM g=globalModuleReference -> ^(MODSYMB[$FROM,"MODSYMB"] globalModuleReference symbolList);

globalModuleReference
  : CAPID
    (
        objectIdentifierValue -> ^(CAPID objectIdentifierValue)
      | (LCID)=>definedValue -> ^(CAPID definedValue)
    )?
  -> ^(CAPID);

symbolList : symbol (COMMA symbol)* -> symbol+;

symbol : reference /*| parameterizedReference*/ ;

reference
    : CAPID
    | LCID
   /* | objectclassreference
    | objectReference
    | objectSetReference */;

assignmentList : assignment+ ;

assignment
    : typeOrValueSetTypeAssignment
    | valueAssignment
    | xmlValueAssignment
    | {doMacros}? macro
    /*| objectClassAssignment
    | objectAssignment
    | objectSetAssignment
    | parameterizedAssignment*/ ;

definedType
    : externalTypeReference
    | {!(input.LT(2).getText().equals("."))}? CAPID
    /*| parameterizedType
    | parameterizedValueSetType*/ ;

definedValue
    : externalValueReference
    | LCID
    /*| parameterizedValue*/ ;

externalTypeReference : {input.LT(3).getType() == CAPID}? CAPID d=DOT CAPID -> ^(EXTTREF[$d,"EXTTREF"] CAPID CAPID);

externalValueReference : {input.LT(3).getType() == LCID}? CAPID d=DOT LCID -> ^(EXTVREF[$d,"EXTVREF"] CAPID LCID);

typeOrValueSetTypeAssignment
scope manageSetHandling;
@init{
  $manageSetHandling::inObjectSet = false;
}
  : c=CAPID a=ASSG typeornull 
    (
      LCURL elementSetSpecs RCURL -> ^(VSTASS[$c]  typeornull elementSetSpecs)
    )?
  -> ^(TASS[$c]  typeornull)
  | c=CAPID typeornull a=ASSG LCURL elementSetSpecs RCURL
  -> ^(VSTASS[$c]  typeornull elementSetSpecs)
  ;

// typeAssignment : typereference a=ASSG type -> ^(TASS[$a] typereference type);

valueAssignment : c=LCID typeornull a=ASSG valornull -> ^(VASS[$c] typeornull valornull);

xmlValueAssignment : c=LCID a=ASSG xmlTypedValue -> ^(XVASS[$c] xmlTypedValue);

xmlTypedValue : XMLFRAG ; // needs some clever semantic predicating, probably

/*valueSetTypeAssignment
  : typereference (type a=ASSG|a=ASSG type) LCURL elementSetSpecs RCURL
  -> ^(VSTASS[$a] typereference type elementSetSpecs); */

type : (typeWithConstraint | builtinType | referencedType)^ ((LPAREN)=>constraint)* ;

typeornull : type | nullr ;

builtinType
    : bitStringType
    | booleanType
    | characterStringType
    | choiceType
    | embeddedPDVType
    | enumeratedType
    | externalType
    /*| instanceOfType*/
    | integerType
    /*| objectClassFieldType*/
    | objectIdentifierType
    | octetStringType
    | realType
    | relativeOIDType
    | sequenceType
    | sequenceOfType
    | setType
    | setOfType
    | prefixedType ;

referencedType
    : usefulType
    | definedType
    | selectionType
    /*| typeFromObject */
    /*| valueSetFromObjects*/ ;
    
namedType returns [Token name]
  : n=LCID typeornull {$name = $n;} -> ^(LCID typeornull)
  ;

value : builtinValue | referencedValue /*| objectClassFieldValue*/ ;

valornull : value | nullr;

builtinValue
    : (LCURL)=>(
        (LCURL valornull (RCURL|COMMA))=>listValue
      | objectIdentifierValue
      )
    | bitStringValue
    | booleanValue
    | characterStringValue
    | choiceValue
    /*| instanceOfValue*/
    | signedNumber
    | realValue ;
    
referencedValue : definedValue /*| valueFromObject */;

namedValue : LCID valornull -> ^(LCID VALUE);

booleanType : BOOLEAN ;

booleanValue : TRUE | FALSE ;

integerType
  : INTEGER 
    (
      (LCURL namedNumber)=>
        (LCURL namedNumberList RCURL -> ^(INTEGER namedNumberList))
    |
    ) -> INTEGER;

namedNumberList : namedNumber (COMMA namedNumber)* -> ^(SYMBOLICS namedNumber+);

namedNumber 
  : LCID LPAREN 
    (
        signedNumber -> ^(LCID signedNumber)
      | definedValue -> ^(LCID definedValue)
    ) RPAREN;

signedNumber
  : NUMBER
  | m=DASH n=NUMBER {$n.text != "0"}? -> NUMBER[$m,$m.text+$n.text]
  ;

integerValue : signedNumber | LCID ;

enumeratedType : ENUMERATED LCURL enumerations RCURL -> ^(ENUMERATED enumerations);

enumerations
  : enumerationItem (COMMA enumerationItem)* (COMMA EXTMARK exceptionSpec? (COMMA enumerationItem (COMMA enumerationItem)*)?)?
  -> ^(ITEMS enumerationItem+) exceptionSpec?;

enumerationItem : LCID | namedNumber ;

enumeratedValue : LCID ;

realType : REAL ;

realValue : numericRealValue | specialRealValue ;

numericRealValue
    : REALNUMBER
    | m=DASH r=REALNUMBER {Double.parseDouble($r.text) != 0.0}? -> REALNUMBER[$m,$m.text+$r.text]
    ;
    
specialRealValue : PLUS_INFINITY | MINUS_INFINITY | NOT_A_NUMBER;

bitStringType
  : BIT STRING 
    ( (LCURL namedBit)=>
        (LCURL namedBit (COMMA namedBit)* RCURL -> ^(BITSTRING namedBit+))
    |    
    ) -> BITSTRING;

namedBit
  : LCID LPAREN 
    (
        NUMBER -> ^(LCID NUMBER)
      | definedValue -> ^(LCID definedValue)
    ) RPAREN
  ;

bitStringValue
    : BSTRING -> ^(BITSTRINGV BSTRING)
    | HSTRING -> ^(BITSTRINGV HSTRING)
    | CONTAINING valornull -> ^(BITSTRINGV ^(CONTAINING valornull));

identifierList : LCID (COMMA LCID)* -> ^(IDS LCID+);

octetStringType : OCTET STRING -> OCTSTRING;

nullr : NULL;

sequenceType : SEQUENCE LCURL componentTypeLists RCURL -> ^(SEQUENCE componentTypeLists);

extensionAndException : EXTMARK exceptionSpec? ;

optionalExtensionMarker : (COMMA! EXTMARK)? ;

ctlExtensionStuff
    : extensionAndException extensionAdditions
      ( optionalExtensionMarker
      | extensionEndMarker COMMA! extComponentTypeList
      ) ;

componentTypeLists
    : ctlExtensionStuff
    | rootComponentTypeList (COMMA! ctlExtensionStuff)? 
    |
    ;

rootComponentTypeList : componentTypeList ;

extComponentTypeList : componentTypeList ;

extensionEndMarker : COMMA! EXTMARK;

extensionAdditions
  : COMMA! extensionAdditionList 
  |  
  ;

extensionAdditionList : extensionAddition (COMMA extensionAddition)* -> ^(EXTADDS extensionAddition+);

extensionAddition : componentType | extensionAdditionGroup ;

extensionAdditionGroup
  : L_VER versionNumber componentTypeList R_VER
  -> ^(EXTADD versionNumber componentTypeList)
  ;

componentTypeList : componentType (COMMA componentType)* -> componentType+;

componentType
    : n=namedType (OPTIONAL | DEFAULT valornull)? -> ^(MEMBER namedType OPTIONAL? ^(DEFAULT valornull)?)
    | COMPONENTS OF type -> ^(HERITCOMPS[$COMPONENTS] type);

sequenceOfType
  : SEQUENCE OF t=typeornull -> ^(SEQOF[$SEQUENCE,"SEQOF"] $t)
  | SEQUENCE OF n=namedType -> ^(SEQOF[$SEQUENCE,"SEQOF"] $n) ;

sequenceValue
  : LCURL RCURL -> EMPTYLIST[$LCURL,"EMPTYLIST"]
  | LCURL namedValueList RCURL -> ^(SEQVAL[$LCURL,"SEQVAL"] namedValueList)
  ;

valueListValue : LCURL valueList RCURL -> ^(VLIST[$LCURL,"VLIST"] valueList);

listValue : (LCURL RCURL)=>sequenceValue | valueListValue ;

valueList : valueInValueList (COMMA valueInValueList)* -> valueInValueList+ ;

valueInValueList : valornull ;

namedValueList : namedValue (COMMA namedValue)* -> namedValue+ ;

setType : SET LCURL componentTypeLists RCURL -> ^(SET componentTypeLists) ;

setOfType
  : SET OF t=typeornull -> ^(SETOF[$SET,"SETOF"] $t)
  | SET OF n=namedType -> ^(SETOF[$SET,"SETOF"] $n);

choiceType : CHOICE LCURL alternativeTypeLists RCURL -> ^(CHOICE alternativeTypeLists);

alternativeTypeLists : rootAlternativeTypeList
      (COMMA! extensionAndException extensionAdditionAlternatives optionalExtensionMarker)? ;

rootAlternativeTypeList : alternativeTypeList -> alternativeTypeList;

extensionAdditionAlternatives : (COMMA! extensionAdditionAlternativesList)? ;

extensionAdditionAlternativesList : extensionAdditionAlternative (COMMA! extensionAdditionAlternative)* ;

extensionAdditionAlternative : extensionAdditionAlternativesGroup | namedType ;

extensionAdditionAlternativesGroup : L_VER versionNumber alternativeTypeList R_VER -> ^(VEREXT[$L_VER,"VEREXT"] versionNumber alternativeTypeList);

choiceValue : {input.LT(2).getType() == COLON}? LCID COLON valornull -> ^(CHOICEVAL[$LCID,"CHOICEVAL"] LCID valornull);

alternativeTypeList : namedType (COMMA! namedType)* ;

selectionType : {input.LT(2).getType() == LT}? LCID LT type -> ^(SELTYPE[$LCID,"SELTYPE"] LCID type);

prefixedType : (tag)=>taggedType | encodingPrefixedType ;

taggedType : tag typeornull -> ^(TAGTYPE tag typeornull);

tag
  : LSQR encodingReference? clazz classNumber RSQR
    (
      ( 
        d=IMPLICIT
      | d=EXPLICIT
      )
    -> ^(TAG[$LSQR,"TAG"] clazz classNumber $d encodingReference?)
    |
    -> {$moduleDefinition::tagging == TaggingForm.IMPLICIT}?
      ^(TAG[$LSQR,"TAG"] clazz classNumber IMPLICIT[] encodingReference?)
    -> {$moduleDefinition::tagging == TaggingForm.AUTOMATIC}?
      ^(TAG[$LSQR,"TAG"] clazz classNumber AUTOMATIC[] encodingReference?)
    -> ^(TAG[$LSQR,"TAG"] clazz classNumber EXPLICIT[] encodingReference?)
    )
  
  ;

encodingReference
  : allCapReference COLON -> ^(ENCREF allCapReference) ;

encodingPrefixedType : encodingPrefix typeornull -> ^(ENCPRFTYPE encodingPrefix typeornull);

encodingPrefix : LSQR encodingReference? encodingInstruction RSQR -> ^(ENCPRF encodingReference? encodingInstruction);

encodingInstruction : (term+=encodingInstructionInvalid)* -> ^(ENCINS $term*);

encodingInstructionInvalid : ~(RSQR|LSQR) ;

classNumber : NUMBER | definedValue ;

clazz
  : (
      (c=UNIVERSAL | c=APPLICATION | c=PRIVATE) 
      -> CLASS[$c]
    )? 
    -> CLASS["CONTEXT_SPECIFIC"];

objectIdentifierType : OBJECT IDENTIFIER -> OBJIDT;

objectIdentifierValue : LCURL objIdComponents RCURL -> ^(OBJID objIdComponents);

objIdComponents 
  : objIdComponent (c=objIdComponent)+ -> objIdComponent+;

objIdComponent returns [boolean nAndN]
  : nameAndNumberForm {$nAndN = true;}
  | numberForm {$nAndN = false;}
  | definedValue {$nAndN = false;} ;

relativeOIDType : RELATIVE_OID ;

embeddedPDVType : EMBEDDED PDV -> EMBPDV;

externalType : EXTERNAL ;

characterStringType : restrictedCharacterStringType | unrestrictedCharacterStringType ;

characterStringValue : restrictedCharacterStringValue ;

restrictedCharacterStringType
    : BMPString
    | GeneralString
    | GraphicString
    | IA5String
    | ISO646String
    | NumericString
    | PrintableString
    | TeletexString
    | T61String
    | UniversalString
    | UTF8String
    | VideotexString
    | VisibleString ;
    
restrictedCharacterStringValue : CSTRING  ;

unrestrictedCharacterStringType : CHARACTER STRING -> UCSTRING ;

usefulType
  : {input.LT(1).getText().equals("GeneralizedTime")
    || 
    input.LT(1).getText().equals("UTCTime")
    ||
    input.LT(1).getText().equals("ObjectDescriptor") }?
    CAPID 
    (
      {input.LT(1).getText().equals("GeneralizedTime")}? -> GENTIME
    | {input.LT(1).getText().equals("UTCTime")}? -> UTCTIME
    | {input.LT(1).getText().equals("ObjectDescriptor")}? -> OBJDESC
    ) ; 

typeWithConstraint
  : (
      s=SET -> ^(SETOF[$s,"SETOF"] typeornull anyConstraint)
    | s=SEQUENCE -> ^(SEQOF[$s,"SEQOF"] typeornull anyConstraint)
    ) anyConstraint OF typeornull ;

anyConstraint : (constraint | sizeConstraint) ;

constraint
  : LPAREN constraintSpec exceptionSpec? RPAREN
  -> ^(CONSTRAINT constraintSpec exceptionSpec?)
  ;

constraintSpec : subtypeConstraint /*| generalConstraint*/ ;

subtypeConstraint
scope manageSetHandling;
@init{
  $manageSetHandling::inObjectSet = false;
}
  : elementSetSpecs
  ;

elementSetSpecs : rootElementSetSpec (COMMA EXTMARK (COMMA additionalElementSetSpec)?)? 
  -> ^(ELEMENTSET rootElementSetSpec EXTMARK? additionalElementSetSpec?)
  ;

rootElementSetSpec : elementSetSpec;

additionalElementSetSpec : elementSetSpec;

elementSetSpec
  : unions
  | ALL exclusions -> ^(EXCLUDE ALL exclusions) 
  ;

unions
  : intersections
    ( unionMark intersections 
      -> ^(unionMark intersections+)
    )* -> intersections 
  ;

//uElems : unions ;

intersections
  : intersectionElements 
    ( intersectionMark intersectionElements
      -> ^(intersectionMark intersectionElements+)
    )* -> intersectionElements 
  ;

//iElems : intersections ;

intersectionElements
  : elements
    ( exclusions -> ^(EXCLUDE elements exclusions)
    | -> elements 
    )
  ;

exclusions : EXCEPT elements -> ^(EXCEPT elements);

unionMark : (t=PIPE | t=UNION) -> UNION[$t] ;

intersectionMark : (t=CARET | t=INTERSECTION) -> INTERSECTION[t] ;

elements
  : {!$manageSetHandling::inObjectSet}?=>subtypeElements 
  | {$manageSetHandling::inObjectSet}?=>objectSetElements
  | LPAREN! elementSetSpec RPAREN! 
  ;

subtypeElements
    : (valueRange)=>valueRange
    | valornull
    | containedSubtype
    | permittedAlphabet
    | sizeConstraint
    | innerTypeConstraints
    | patternConstraint;

containedSubtype : INCLUDES? type;

valueRange
  : lowerEndpoint RANGE upperEndpoint
  -> ^(RANGE lowerEndpoint upperEndpoint)
  ;

lowerEndpoint
  : lowerEndValue 
    ( LT -> ^(OPENEND lowerEndValue)
    | -> ^(CLOSEDEND lowerEndValue)
    )
  ;

upperEndpoint
@init {
  boolean open = false;
}
  : 
    ( LT {open = true;} 
    )? upperEndValue
    -> {open}? ^(OPENEND upperEndValue)
    -> ^(CLOSEDEND upperEndValue)
  ;

lowerEndValue : value | MIN ;
upperEndValue : value | MAX ;

sizeConstraint : SIZE constraint ;

permittedAlphabet : FROM constraint;

innerTypeConstraints : WITH (COMPONENT singleTypeConstraint | COMPONENTS multipleTypeConstraints) ;

singleTypeConstraint : constraint ;

multipleTypeConstraints : fullSpecification | partialSpecification ;

fullSpecification : LCURL typeConstraints RCURL ;

partialSpecification : LCURL EXTMARK COMMA typeConstraints RCURL ;

typeConstraints : namedConstraint (COMMA namedConstraint)* ;

namedConstraint : LCID componentConstraint ;

componentConstraint : valueConstraint presenceConstraint ;

valueConstraint : constraint? ;

presenceConstraint : (PRESENT|ABSENT|OPTIONAL)? ;

patternConstraint : PATTERN value ;

exceptionSpec : EXMARK exceptionIdentification -> ^(EXCEPT[$EXMARK,"EXCEPT"] exceptionIdentification);

exceptionIdentification
  : signedNumber
  | definedValue
  | typeornull COLON valornull -> ^(VALUE typeornull valornull);

versionNumber : (NUMBER COLON)? -> ^(VERSION NUMBER) ;

encodingControlSections 
  : encodingControlSection* 
  -> ^(ENCSECS encodingControlSection*)
  ;

encodingControlSection 
  : ENCODING_CONTROL allCapReference encodingInstructionAssignmentList
  -> ^(ENCODING_CONTROL allCapReference encodingInstructionAssignmentList)
  ;

encodingInstructionAssignmentList 
  : notEIALTerminator* 
  -> ^(ENCASSLIST notEIALTerminator*)
  ;
  
notEIALTerminator : ~(END|ENCODING_CONTROL) ;

/**
 * Included for some semblance of backward compatibility. Won't
 * understand macros, but won't barf on them. Fill in macroBody better,
 * and it'll be more support
 */
macro
  : {doMacros}?=>CAPID MACRO ASSG BEGIN macroBody END -> ^(MACRO[$CAPID] macroBody) ;

macroBody
  : {doMacros}?=>e+=macroElement+ ;

macroElement
  : {doMacros}?=>~END;

file: moduleDefinition+ EOF -> ^(MODULES moduleDefinition+);

// Information Object Classes and Information Objects

objectClassDefn 
  : CLASS LCURL fieldSpec (COMMA fieldSpec)* RCURL withSyntaxSpec
  ;

primitiveFieldName : IOLCID | IOCAPID ;

fieldName : primitiveFieldName (DOT primitiveFieldName)* ;

fieldSpec
  : typeOrObjectSetFieldSpec
  | fixedTypeValueFieldSpec
  | variableTypeValueFieldSpec
  | variableTypeValueSetFieldSpec
  ;

typeOrObjectSetFieldSpec
@init {
  boolean type = true;
  boolean objset = false;
} 
  : IOCAPID
    ( t=typeOrClass {type = false; objset = $t.obj; })?
    ( {type}? typeOptionalSpec
    | {!type && !objset}? valueSetOptionalSpec
    | {!type && objset}? objectSetOptionalSpec
    )?;

typeOptionalSpec
  : OPTIONAL
  | DEFAULT type
  ;

valueSetOptionalSpec
  : OPTIONAL
  | DEFAULT valueSet
  ;

typeOrClass returns [boolean obj]
  : type {$obj = false;}
  | definedObjectClass {$obj = true;}
  ;

/*valueOrObject[boolean obj]
  : {!obj}? value
  | {obj}? object
  ;*/

instanceReference
  : LCID
  | externalValueReference
  ;

fixedTypeValueFieldSpec
scope {
  boolean torc;
}
  : IOLCID t=typeOrClass
    {$fixedTypeValueFieldSpec::torc = $t.obj;} 
    ({!($fixedTypeValueFieldSpec::torc)}? UNIQUE
    |)
    /*valueOrObjectOptionalSpec[$t.obj]?*/
    ( {!($fixedTypeValueFieldSpec::torc)}? valueOptionalSpec
    | {$fixedTypeValueFieldSpec::torc}? objectOptionalSpec
    ) ;

variableTypeValueFieldSpec : IOLCID fieldName valueOptionalSpec? ;

/*valueOrObjectOptionalSpec[boolean obj]
  : OPTIONAL
  | DEFAULT valueOrObject[obj]
  ;*/
  
valueOptionalSpec
  : OPTIONAL
  | DEFAULT value
  ;
  
objectOptionalSpec
  : OPTIONAL
  | DEFAULT object
  ;

variableTypeValueSetFieldSpec : IOCAPID fieldName valueSetOptionalSpec? ;

valueSet
scope manageSetHandling;
@init{
  $manageSetHandling::inObjectSet = false;
}
  : LCURL elementSetSpecs RCURL ;

objectSetOptionalSpec
  : OPTIONAL
  | DEFAULT objectSet
  ;

definedObjectClass
  : externalObjectClassReference
  | allCapReference
  | usefulObjectClassReference
  ;

definedObject
  : externalValueReference
  | LCID
  ;
  
definedObjectSet
  : externalTypeReference
  | CAPID
  ;

externalObjectClassReference : CAPID DOT allCapReference ;

usefulObjectClassReference : TYPE_IDENTIFIER | ABSTRACT_SYNTAX ;

object
  : definedObject
  | objectDefn
  /*| objectFromObject
  | parameterizedObject*/
  ;

objectSet
scope manageSetHandling;
@init{
  $manageSetHandling::inObjectSet = true;
}
  : LCURL objectSetSpec RCURL ;
  
objectSetSpec
  : rootElementSetSpec (COMMA EXTMARK additionalElementSetSpec?)?
  | EXTMARK (COMMA additionalElementSetSpec)?
  ;

objectSetElements
  : object
  | definedObjectSet
  /*| objectSetFromObjects
  | parameterizedObjectSet*/
  ;

objectDefn
  : LCURL
    ( (IOCAPID|IOLCID)=>defaultSyntax
    /*| definedSyntax*/
    ) RCURL
  ;
  
objectDefnDefault
  : LCURL defaultSyntax RCURL
  ;
  
objectDefnDefined
  : LCURL definedSyntax RCURL
  ;

defaultSyntax
  : (fieldSetting (COMMA fieldSetting)*)?
  ;

fieldSetting
  : primitiveFieldName setting
  ;

definedSyntax : definedSyntaxToken* ;

definedSyntaxToken
  : allCapReference
  | COMMA
  | setting
  ;

setting
  : (LCURL)=>compound
  | (LCID|externalValueReference)=>referencedValue
  | typeornull
  | value
  ;

compound
  : compoundInner
  -> ^(COMPOUND compoundInner)
  ;

compoundInner
  : LCURL (compoundInner|(~(LCURL|RCURL)))* RCURL 
  ;
  
 
/**
 * Some serious lexer work will be going into this...
 */
withSyntaxSpec : WITH SYNTAX ;
