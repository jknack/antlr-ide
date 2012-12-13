/**
* @autor Edgar Espina
* @since 2.8.3
* @since WLS-4973
*/
grammar Calculator;

options {
  language = Java;
}

@header {
package com.twg.wls.util;

import java.math.BigDecimal;
}

@lexer::header {
package com.twg.wls.util;

import java.math.BigDecimal;
}

@members {
  private boolean eval(String op, BigDecimal a, BigDecimal b) {
    if("L".equals(op)) {
      return a.compareTo(b) <= 0;
    }
    else if("<".equals(op)) {
      return a.compareTo(b) < 0;
    }
    else if("G".equals(op)) {
      return a.compareTo(b) >= 0;
    }
    else if(">".equals(op)) {
      return a.compareTo(b) > 0;
    }
    else if("=".equals(op)) {
      return a.compareTo(b) == 0;
    }
    else if("!".equals(op)) {
      return a.compareTo(b) != 0;
    }
    throw new IllegalArgumentException("Unknow operator: " + op);
  }
  
  private boolean match(char ch) {
   return input.LA(1) == ch;
  }
}

eval returns [BigDecimal value]
  :
  stat {$value=$stat.value;} EOF {System.out.println($value);}
  ;
 
stat returns [BigDecimal value]
  :
  (
    /*
    {match('(')}?=> '(' e=simpleStat ')'
    |*/ e=simpleStat
  )
  {$value = $e.value;}
  ;

simpleStat returns [BigDecimal value]
  :
  (
    {match('F')}?=> e=ifStat
    | e=expr
  )
  {$value = $e.value;}
  ;

ifStat returns [BigDecimal value]
  :
  ('F' c=conditionalOrExpr '?' thenExpr=stat ':' elseExpr=stat) {$value=($c.value)?$thenExpr.value: $elseExpr.value;}
  ;

expr returns [BigDecimal value]
  :
  e=multExpr {$value = $e.value;}
  (
    '+' e=multExpr {$value = $value.add($e.value);}
    | '-' e=multExpr {$value = $value.subtract($e.value);}
  )*
  ;

multExpr returns [BigDecimal value]
  :
  e=unaryExpr {$value = $e.value;}
  (
    '*' e=unaryExpr {$value = $value.multiply($e.value);}
    | '/' e=unaryExpr {$value = $value.divide($e.value);}
  )*
  ;

unaryExpr returns [BigDecimal value]
  :
  '+' e=atom {$value = $e.value;}
  | '-' e=atom {$value = $e.value.negate();}
  | e=atom {$value=$e.value;}
  ;

atom returns [BigDecimal value]
  :
  NUMBER {$value = new BigDecimal($NUMBER.text);}
  | '(' expr ')' {$value = $expr.value;}
  | ifStat {$value = $ifStat.value;}
  ;

conditionalOrExpr returns [boolean value]
  :
  e1=conditionalAndExpr {$value = $e1.value;} ('|' e2=conditionalAndExpr {$value = value || $e2.value;})*
  ;

conditionalAndExpr returns [boolean value]
  :
  e1=relationalExpr {$value = $e1.value;} ('&' e2=relationalExpr {$value = value && $e2.value;})*
  ;

relationalExpr returns [boolean value]
  :
  e1=expr RELATIONAL_OP e2=expr {$value = eval($RELATIONAL_OP.text, $e1.value, $e2.value);}
  ;

RELATIONAL_OP
  :
  (
    'L'
    | 'G'
    | '<'
    | '>'
    | '='
    | '!'
  )
  ;

NUMBER
  :
  DECIMAL
  ;

fragment
DECIMAL
  :
  ('0'..'9')+ '.' ('0'..'9')*
  | '.' ('0'..'9')+
  | ('0'..'9')+
  ;

WS
  :
  (
    ' '
    | '\t'
  )+
  {$channel=HIDDEN;}
  ;
