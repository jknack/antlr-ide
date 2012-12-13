grammar HT;

options {
  language = Java;
  output   = AST;
}

tokens {
  ASSERT  = 'assert';
  COMMENT = '//';
}

assertion
  :
  ^(ASSERT expression)
    ->
      ^(ASSERT<AssertionNode>[$ASSERT.token] expression)
  | COMMENT
    ->
      ^(COMMENT<AssertionNode>[$COMMENT.token])
  ;

expression
  :
  'ex'
  ;
