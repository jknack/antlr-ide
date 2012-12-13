grammar Simply;

rule
  :
  DOC_COMMENT?
    ->
      ^(
        RULE id {modifier!=null?adaptor.create(modifier):null}
        ^(ARG $arg )? rule
        )
  ;