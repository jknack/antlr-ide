/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model.dltk.ast;

import java.util.List;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IGrammarAction;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IScope;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;

public class DASTGrammar extends ModuleDeclaration {

	private IGrammar grammar;

	public static final DASTGrammar ERROR_AST = new DASTGrammar();
	
	public DASTGrammar(IGrammar grammar) {
		super(grammar.getSource().length(), true);
		this.grammar = grammar;
	}
	
	private DASTGrammar() {
		super(0, false);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	protected void doRebuild() {
		// grammar declaration
//		ISourceElement name = grammar.getName();
//		String grammarName;
//		int sourceStart = 0;
//		int sourceEnd = 0;
//		if (name != null) {
//			grammarName = name.getText();
//			sourceStart = name.sourceStart();
//			sourceEnd = name.sourceEnd();
//		} else {
//			grammarName = grammar.getElementName();
//		}

//		GrammarDeclarationNode grammarDecl = new GrammarDeclarationNode(
//				grammarName, sourceStart,
//				sourceEnd);
//		addStatement(grammarDecl);
		
		@SuppressWarnings("rawtypes")
		List statements = getStatements();//grammarDecl.getStatements();
		
		if (grammar.hasOptions())
			statements.add(grammar.getOptions().getAdapter(ASTNode.class));

		if (grammar.hasImports())
			statements.add(grammar.getImports().getAdapter(ASTNode.class));
		
		if (grammar.hasTokens())
			statements.add(grammar.getTokens().getAdapter(ASTNode.class));

		if (grammar.hasActions()) {
			IGrammarAction[] actions = grammar.getActions();
			for (int i = 0; i < actions.length; i++) {
				statements.add(actions[i].getAdapter(ASTNode.class));
			}
		}

		if (grammar.hasScopes()) {
			IScope[] scopes = grammar.getScopes();
			for (int i = 0; i < scopes.length; i++) {
				statements.add(scopes[i].getAdapter(ASTNode.class));
			}
		}

		// rules
		if (grammar.hasRules()) {
			IRule[] rules = grammar.getRules();
			for (IRule rule : rules) {
				statements.add(rule.getAdapter(ASTNode.class));
			}
		}
	}

	public IGrammar getGrammar() {
		return grammar;
	}
}
