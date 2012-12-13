/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model;

import org.eclipse.core.runtime.IPath;

public interface IGrammar extends IModelElement {
	
	IGrammarAction findAction(String name);
	
	IOption findOption(String name);
	
	IImport findImport(String name);
	
	IRule findRule(String name);
	
	IGrammarScope findScope(String name);
	
	IToken findToken(String name);	
	
	IGrammarAction[] getActions();
	
	String getPlainDocumentation();
	
	String getDocumentation();
	
	IModelElement getElementAt(int position);		
	
	IPath getAbsoluteFile();
	
	IPath getAbsoluteFolder();
	
	IImports getImports();
	
	IPath getFile();
	
	IPath getFolder();
	
	ISourceElement getName();
	
	String getOption(String optionName);
	
	IOptions getOptions();
	
	IRule[] getRules();	
	
	String getSource();
	
	IGrammarScope[] getScopes();
	
	ITokens getTokens();
	
	GrammarType getGrammarType();
	
	IGrammar[] getDependents();
	
	IGrammar getTokenVocab();
	
	IRule firstRule();
	
	boolean hasActions();
	
	boolean hasOptions();
	
	boolean hasOption(String name);
	
	boolean hasRules();
	
	boolean hasRule(String name);
	
	boolean hasScope(String name);
	
	boolean hasScopes();
	
	boolean hasToken(String name);
	
	boolean hasTokens();
	
	boolean isValid();
	
	boolean isCombinedGrammar();
	
	boolean isCompositeGrammar();
	
	boolean isImplicitLexerGrammar();
	
	boolean isPrototypeGrammar();
	
	boolean isParserGrammar();
	
	boolean isLexerGrammar();
	
	boolean isTreeParserGrammar();
	
	boolean hasImports();
	
	String[] getComments();
	
	void addComment(String comment);

	String toEbnf();
}
