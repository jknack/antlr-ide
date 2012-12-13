grammar SimpleDot;

options {
  language = Java;
}

@header {
  package org.deved.antlride.internal.core.dot;
  
  import org.deved.antlride.core.dot.*;
}

@lexer::header {
  package org.deved.antlride.internal.core.dot;
}

@members {
   DotGraph graph = null; 
   DotAttributesBuilder attrBuilder;
}

graph[DotGraph graph]
@init {
	this.graph = graph;
}
  :
    'strict'? 
    ('graph' | 'digraph') 
    ID? 
    body
  ;

body:
  '{' stmt_list*  '}'
  ;

stmt_list
  :   
    stmt ';'?
  ;

stmt
  :
      node_stmt
  |   edge_stmt
  |   attr_stmt
  |   ID '=' ID
  |   subgraph
  ;

attr_stmt
  :
    ('graph' | 'node' | 'edge') attr_list;

attr_list
@init {
  attrBuilder = new DotAttributesBuilder();
}
  :   
    ('[' a_list? ']')+
  ;

a_list
  :
    name=ID ( '=' value=ID )? ','? a_list?
    {
      attrBuilder.attribute($name.text, $value.text);
    }
   ;

edge_stmt
  :   
    (node_id /*| subgraph*/) to=edgeRHS attr_list?
     {if(to!=null)graph.edge($node_id.text, to, attrBuilder);}
  ;
  
edgeRHS returns[String nodeId]
  :
  EDGEOP_LITERAL  (node_id{nodeId=$node_id.text;} | subgraph)
  ;
  
node_stmt 
  :   node_id attr_list?
    {graph.node($node_id.text, attrBuilder);}
  ;

node_id
  :
    ID port?
  ;

port
  :   
    ':' ID ( ':' COMPASS_PT )?
  | ':' COMPASS_PT
  ;

subgraph
  :
    ( 'subgraph' ID? )? body
  ;

EDGEOP_LITERAL
    :  (  '->'
        | '--'
       )
    ;

ID
    :  (  VALIDSTR
        | NUMBER
        | STRING_LITERAL
        | HTMLSTR
       );

fragment COMPASS_PT
    :  (  'ne'
        | 'nw'
        | 'node'
        | 'n'
        | 'e'
        | 'se'
        | 'sw'
        | 's'
        | 'w'
       );

fragment ALPHACHAR
    :  (   'a'..'z'
        |  'A'..'Z'
        |  '_'
       );

fragment VALIDSTR
    :  ALPHACHAR
        (  ALPHACHAR
         |  '0'..'9'
        )*
    ;

fragment NUMBER
    :  ('-' | '.')? ('0'..'9')+ ('.' ('0'..'9')+)?
    ;

fragment
STRING_LITERAL
  : '"' LITERAL_CHAR LITERAL_CHAR* '"'
  ;

fragment
LITERAL_CHAR
  : ESC
  | ~('"'|'\\')
  ;

fragment
ESC : '\\'
    ( 'n'
    | 'r'
    | 't'
    | 'b'
    | 'f'
    | '"'
    | '\''
    | '\\'
    | '>'
    //| 'u' XDIGIT XDIGIT XDIGIT XDIGIT
    )
  ;  

fragment HTMLSTR
    :  '<' (~'>')* '>'
    ;

WS  : ( ' '
    | '\t'
    | '\r'? '\n'
    )+
    {$channel=HIDDEN;}
  ;

// Single-line comments
SL_COMMENT
  : (  '/*'
     | '//'
    )
    ~('\r'|'\n')*
    '\r'? '\n'
    {$channel=HIDDEN;} 
  ;

// multiple-line comments
ML_COMMENT
  : '/*' {$channel=HIDDEN;} .* '*/'
  ;
