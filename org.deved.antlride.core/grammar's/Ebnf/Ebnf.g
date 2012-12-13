/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
grammar Ebnf;

options {
  language = Java;
}

@lexer::header {
/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package net.hydromatic.clapham.parser.ebnf;
}

@header {
/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package net.hydromatic.clapham.parser.ebnf;

import net.hydromatic.clapham.parser.*;
import java.util.Arrays;
}

@members {
protected void mismatch(IntStream input, int ttype, BitSet follow)
    throws RecognitionException
{
    throw new MismatchedTokenException(ttype, input);
}
public Object recoverFromMismatchedSet(IntStream input,
                                     RecognitionException e,
                                     BitSet follow)
    throws RecognitionException
{
    throw e;
}

public void emitErrorMessage(String msg) {
 //do not print anything, handle by the exception mechanism
}

private String removeQuotes(String text) {
  return text.substring(1, text.length()-1).replace("\\'", "'");
}

}
// Alter code generation so catch-clauses get replace with
// this action.
@rulecatch {
catch (RecognitionException rexc) {
    throw rexc;
}
}


rule returns [List<ProductionNode> productionNodes = new ArrayList<ProductionNode>()]
  :
  (node=production {productionNodes.add(node);})* EOF
  ;

production returns [ProductionNode productionNode]
  :
  ID '::=' expr=expression {
    productionNode = new ProductionNode(new IdentifierNode($ID.text), expr);
  }
  ;

expression returns [EbnfNode node]
@init {
  List<EbnfNode> list = new ArrayList<EbnfNode>();
}
  :
  t=term { list.add(t); } ('|' t=term { list.add(t);})* {
    if (list.size() == 1) {
     node = list.get(0);
    } else {
     node = new AlternateNode(list);
    }
  }
  ;

term returns [EbnfNode node]
@init {
  List<EbnfNode> list = new ArrayList<EbnfNode>();
}
  :
  (e=ebnf {list.add(e);})* {
  switch (list.size()) {
        case 0:
            node = new EmptyNode();
            break;
        case 1:
            node = list.get(0);
            break;
        default:
            node = new SequenceNode(list);
            break;
        }
  }
  ;

ebnf returns [EbnfNode node]
  :
  n=factor
  (
    '+' {node = new SequenceNode(Arrays.asList(n, new MandatoryRepeatNode(n)));}
    | '?' {node = new OptionNode(n);}
    | '*' {node = new RepeatNode(n);}
    | {node = n;}
  )
  ; 

factor returns [EbnfNode node]
  :  
    ID {node = new IdentifierNode($ID.text);}
  | STRING_LITERAL {node = new LiteralNode($STRING_LITERAL.text);}
  | any='.' {node = new AnyCharacterNode($any.text);}
  | '(' expr=expression ')' {node = expr;}
  |  '~' '(' expr=expression ')' {node = new ExceptionNode(expr, "~");}
  | '??' STRING_LITERAL '(' expr=expression ')' {node = new PredicateNode(removeQuotes($STRING_LITERAL.text), expr);}
  ;

STRING_LITERAL
  :
  '\'' LITERAL_CHAR LITERAL_CHAR* '\''
  ;

fragment
LITERAL_CHAR
  :
  ESC
  |
  ~(
    '\''
    | '\\'
   )
  ;

fragment
ESC
  :
  '\\'
  (
    'n'
    | 'r'
    | 't'
    | 'b'
    | 'f'
    | '"'
    | '\''
    | '\\'
    | '>'
    | 'u' XDIGIT XDIGIT XDIGIT XDIGIT
    | . // unknown, leave as it is
  )
  ;

ID
  :
  (
    'a'..'z'
    | 'A'..'Z'
    | '_'
  )
  (
    'a'..'z'
    | 'A'..'Z'
    | '0'..'9'
    | '-'
    | '_'
  )*
  ;

fragment
XDIGIT
  :
  '0'..'9'
  | 'a'..'f'
  | 'A'..'F'
  ;

WS
  :
  (
    ' '
    | '\t'
    | '\r'? '\n'
  )+
  {$channel=HIDDEN;}
  ;
