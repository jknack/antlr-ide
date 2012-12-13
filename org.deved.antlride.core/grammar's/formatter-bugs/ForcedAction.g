grammar ForcedAction;

options {
  language = Java;
}

assertion
  :
  {{System.out.println("Forced Action");}}
  expression
  ;

expression
  :
  'ex'
  ;
