//
// ANTLR Tree for shader files
//
// (C) 2009 Christian Schladetsch
// (C) 2009 Blue Lion Software
//
// Permission to use for any purpose is given, as long as this copyright
// information is included in any derived works.

tree grammar ShaderTree;

options 
{
	tokenVocab = Shader;				// reuse token types
	ASTLabelType = CommonTree;
	language = CSharp;
	output = template;
}

@header
{
	using System.Collections.Generic;
	using System.Diagnostics;
}

@members
{
	class Label
	{
	public Label()
		{
		}
	public Label(string s)
		{
		}
	}
	enum Operation
	{
		Plus,
		Minus,
		Multiply,
		Divide,
		Assign,
		Equiv,
		NotEquiv,
		LogicalOr,
		LogicalAnd,
		LogicalNot,
		
		PlusAssign,
		MultiplyAssign,
		
		MethodCall,
		FunctionCall,
		
		Less,
		Enter,
		Leave,
	};
	
	void AppendOp(Operation op)
	{
	}
	
	void Append(object q)
	{
	}
	public bool trace = false;
	void Trace(string s)
	{
		if (!trace)
			return;
		s = "tree: " + s;
		Debug.WriteLine(s);
		Console.WriteLine(s);
	}
}

//------------------------------------------------------------------------------------------------

entry
	:	a+=toplevel* 
		-> file(contents={$a})
	;
	
toplevel
	:	function -> {$function.st}
	|	uniform -> {$uniform.st}
	|	texture -> {$texture.st}
	|	sampler -> {$sampler.st}
	|	structure -> {$structure.st}
	;
	
function
	:	^(FUNCTION r=ident n=ident a+=formal_arg* semantic? block) 
		-> function(ret={$r.st}, name={$n.st}, args={$a}, semantic={$semantic.st}, body={$block.st})
	;
	
uniform
	:	^(UNIFORM ty=ident name=ident sub=subscript? sem=semantic?)
		-> uniform(type={$ty.st}, name={$name.st}, subscript={$sub.st}, semantic={$sem.st})
	;
	
texture
	:	^('texture' ident) -> texture(tex_name={$ident.st})
	;
	
structure
	:	^(STRUCT ident fields+=struct_field*)
		-> structure(name={$ident.st}, fields={$fields})
	;
	
struct_field
	:	u=uniform -> {$u.st}
	;
	
sampler
	:	^(SAMPLER ident f+=sampler_field*)
		-> sampler(name={$ident.st}, fields={$f})
	;
	
sampler_field
	:	^('Texture' ident) {Trace("sampler_field_texture");} 
		-> sampler_field_texture(tex_name={$ident.st})
	|	^('=' ident expression) 
		-> sampler_field_assign(ident={$ident.st}, value={$expression.st})
	;
	
block
	:	^(BLOCK s+=statement*) -> block(statements={$s})
	;
	
ident
	:	id=Identifier -> {%{id.Text}}
	|	id=UserSemantic -> {%{id.Text}}
	;
	
formal_arg
	:	^(FORMAL_ARG ty=type_name name=ident dir=direction? sub=subscript? sem=semantic?)
		-> formal_arg(type={$ty.st}, name={$name.st}, dir={$dir.st}, subscript={$sub.st}, semantic={$sem.st})
	;
	
direction
	:	'in' -> {%{"in"}}
	|	'out' -> {%{"out"}}
	;
	
type_name
	:	id=ident -> {$id.st}
	|	'sampler2D' -> {%{"sampler2D"}}
	;
	
	
subscript
	:	^(SUBSCRIPT e=expression) -> subscript(index={$e.st})
	;
	
semantic
	:	^(SEMANTIC n=ident) -> semantic(name={$n.st})
	|	^(USER_SEMANTIC r=ident) -> user_semantic(name={$r.st})
	;
	
statement
	:	expression 
		-> statement(expr={$expression.st})
	|	local_var
		-> statement(expr={$local_var.st})
	;

local_var
	:	^(LOCAL_VAR ty=ident id=ident expression?)
		-> local_var(type={$ty.st}, name={$id.st}, init={$expression.st})
	;
	
expression
	:	^('=' l=expression r=expression)		
		{ AppendOp(Operation.Assign); } 
		-> assign(left={$l.st}, right={$r.st})
	
	|	^(CAST ty=expression e=expression)
		-> cast(type={$ty.st}, expr={$e.st})
		
	|	^('return' e=expression?)
		-> return(expr={$e.st})

	|	^(IF statement expression)			
//	|	^(IF_ELSE statement statement expression)
	//|	^('==' a=expression b=expression)		{ AppendOp(Operation.Equiv); }
	//	-> equiv(left={$a.st}, right={$b.st})
	
	|	^('!=' a=expression b=expression)		
		{ AppendOp(Operation.NotEquiv); }
		-> bin_op(op={"!="}, left={$a.st}, right={$b.st})
		
	|	^('<' a=expression b=expression)		
		{ AppendOp(Operation.Less); }
		-> bin_op(op={"<"}, left={$a.st}, right={$b.st})
	
	|	^('-' a=expression b=expression)		
		{ AppendOp(Operation.Minus); }
		-> bin_op(op={"-"}, left={$a.st}, right={$b.st})
	
	|	^('+' a=expression b=expression)		
		{ AppendOp(Operation.Plus); }
		-> bin_op(op={"+"}, left={$a.st}, right={$b.st})
	
	|	^('+=' a=expression b=expression)		
		{ AppendOp(Operation.PlusAssign); }
		-> bin_op(op={"+="}, left={$a.st}, right={$b.st})
	
	|	^('*' a=expression b=expression)		
		{ AppendOp(Operation.Multiply); }
		-> bin_op(op={"*"}, left={$a.st}, right={$b.st})
		
	|	^('/' a=expression b=expression)		
		{ AppendOp(Operation.Divide); }
		-> bin_op(op={"/"}, left={$a.st}, right={$b.st})
		
	|	^('||' expression expression)		
		{ AppendOp(Operation.LogicalOr); }
		
	|	^('&&' expression expression)		
		{ AppendOp(Operation.LogicalAnd); }
		
	|	^('!' expression)					
		{ AppendOp(Operation.LogicalNot); }
		
	|	literal 
		-> literal(val={$literal.st})
		
	|	ident 
		-> ident(name={$ident.st})
		
	|	^(FUNCTION_CALL args=arguments n=expression)		
		{ AppendOp(Operation.FunctionCall); } 
		-> function_call(name={$n.st}, args={$args.st})
		
	|	^('.' obj=expression field=expression)				
		-> field_access(object={$obj.st}, field={$field.st})
		
	//|	^(METHOD_CALL arguments expression)			{ AppendOp(Operation.MethodCall); }
	//|	^('->' expression id=Identifier)			{ AppendOp(Operation.GetProperty); }
	//|	^('->' expression QuotedIdentifier)			{ AppendOp(Operation.GetProperty); }
	;

arguments
	:	^(ARG_LIST e+=expression*)
		-> arg_list(args={$e})
	;
	
literal	
	:	n=integerLiteral			//{ Append(atoi((const char *)$n.text->chars)); }
		-> {$n.st}
	|	f=FloatingPointLiteral		//{ Append((float)atof((const char *)$f.text->chars)); }
		-> {%{$f.Text}}
	|	CharacterLiteral
	|	s=StringLiteral				//{ Append(StripQuotes((const char *)$s.text->chars)); }
	|	b=booleanLiteral			//{ Append(String((const char *)$b.text->chars) == "true"); }
	|	'null'
	;

integerLiteral
	:	a=HexLiteral -> {%{$a.Text}}
	|	b=OctalLiteral -> {%{$b.Text}}
	|	c=DecimalLiteral -> {%{$c.Text}}
	;                    

booleanLiteral
	:	'true'
	|	'false'
	;

//EOF
