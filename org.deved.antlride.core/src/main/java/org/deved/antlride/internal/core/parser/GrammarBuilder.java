/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.parser;

import org.antlr.runtime.tree.CommonTree;
import org.deved.antlride.core.model.GrammarType;
import org.deved.antlride.core.model.IGrammar;

public interface GrammarBuilder extends OptionsAware {

	IGrammar build();
	
	GrammarBuilderFactory imports(CommonTree startNode, CommonTree Endnode);
	
	GrammarBuilderFactory importGrammar(CommonTree name, CommonTree alias);
	
	GrammarBuilderFactory importGrammar(CommonTree name);
	
	GrammarBuilderFactory scope(CommonTree nameNode, CommonTree actionNode);

	GrammarBuilder option(CommonTree name, CommonTree value);

	GrammarBuilderFactory type(GrammarType grammarType);

	GrammarBuilderFactory name(CommonTree node);

	GrammarBuilder options(CommonTree optionStart, CommonTree optionEnd);

	GrammarBuilderFactory documentation(CommonTree node);

	GrammarBuilderFactory token(CommonTree nameNode, CommonTree valueNode);

	GrammarBuilderFactory token(CommonTree nameNode);

	GrammarBuilderFactory tokens(CommonTree startNode, CommonTree endNode);

	GrammarBuilderFactory scopes();

	GrammarBuilderFactory action(CommonTree ampersandNode, CommonTree scopeNode,
			CommonTree nameNode, CommonTree actionNode);

	GrammarBuilderFactory action(CommonTree ampersandNode, CommonTree nameName,
			CommonTree actionNode);

	GrammarBuilderFactory actions();

	GrammarBuilderFactory beginRule();

	GrammarBuilderFactory endRule();

	RuleBuilder rule();

	GrammarBuilderFactory rules();	
}