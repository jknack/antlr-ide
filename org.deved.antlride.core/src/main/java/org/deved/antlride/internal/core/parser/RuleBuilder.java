/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.parser;

import java.util.List;

import org.antlr.runtime.tree.CommonTree;
import org.deved.antlride.core.model.ASTSuffix;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IRule.RuleAccessModifier;

public interface RuleBuilder extends OptionsAware {
	RuleBuilder documentation(CommonTree documentationNode);

	RuleBuilder name(CommonTree nameNode);

	IRule build();

	RuleBuilder accessModifier(RuleAccessModifier modifier);

	RuleBuilder astSuffix(ASTSuffix suffix);

	RuleBuilder parameters(CommonTree paramNode);

	RuleBuilder returns(CommonTree returnsNode);

	RuleBuilder ruleStart(CommonTree startNode);

	RuleBuilder ruleEnd(CommonTree endNode);

	RuleBuilder body(BlockBuilder block);

	RuleBuilder scope(CommonTree scopeNode, CommonTree bodyNode);

	RuleBuilder scopeReference(CommonTree scopeNameNode);

	RuleBuilder action(CommonTree nameNode, CommonTree bodyNode);

	RuleBuilder ruleCatch(CommonTree catchNode, CommonTree argNode,
			CommonTree actionNode);

	RuleBuilder ruleFinally(CommonTree finallyNode, CommonTree actionNode);
	
	RuleBuilder ruleThrows(List<CommonTree> exceptions);

	RuleBuilder bodyStart(CommonTree node);
	
	RuleBuilder bodyEnd(CommonTree node);
}
