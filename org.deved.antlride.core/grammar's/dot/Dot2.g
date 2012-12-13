grammar Dot2;

options {
  language = Java;
}

@header {
  import org.antlr.grammars.dot.*;
}

@members {
   DotAttributeBuilder builder;
}

graph
  :
    'strict'? 
    ('graph' | 'digraph') 
    ID? 
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
//  |   subgraph
  ;

attr_stmt
@init {
  builder = new DotAttributeBuilder();
}
  :
    ('graph' | 'node' | 'edge') attr_list;

attr_list
  :   
    ('[' a_list? ']')+
  ;

a_list
  :
    name=ID ( '=' value=ID )? ','? a_list?
    {
      builder.attribute($name.text, $value.text);
    }
   ;

edge_stmt
  :   
    (node_id /*| subgraph*/) edgeRHS attr_list?
  ;
  
edgeRHS
  :
  EDGEOP_LITERAL (node_id /*| subgraph*/) edgeRHS?
  ;
  
node_stmt
  :   node_id attr_list?
    {System.out.println("Node def: " + $node_id.text + " " + $attr_list.text);}
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

//subgraph
//  :
//    ( subgraph ID? )? '{' stmt_list '}'
//  ;

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
