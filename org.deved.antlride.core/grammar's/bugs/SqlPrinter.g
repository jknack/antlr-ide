tree grammar SqlPrinter;

options {
  output       = template;
  ASTLabelType = CommonTree;
}

select_command
  :
  select_statement
    ->
      {$select_statement.st}
  |
  ^(UNION c=select_command s=select_statement)
    ->
      union(left={$c.st}, right={$s.st})
  |
  ^(UNION_ALL c=select_command s=select_statement)
    ->
      union_all(left={$c.st}, right={$s.st})
  ;

select_statement: ;
