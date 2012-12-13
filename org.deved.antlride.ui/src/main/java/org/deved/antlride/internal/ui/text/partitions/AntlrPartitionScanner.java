/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.partitions;

import java.util.ArrayList;
import java.util.List;

import org.deved.antlride.common.ui.text.partitions.AntlrBasePartitionScanner;
import org.deved.antlride.internal.ui.text.partitions.rules.AntlrActionPartition;
import org.deved.antlride.internal.ui.text.partitions.rules.AntlrGlobalScopePartition;
import org.deved.antlride.internal.ui.text.partitions.rules.AntlrGrammarActionPartition;
import org.deved.antlride.internal.ui.text.partitions.rules.AntlrGrammarDeclarationPartition;
import org.deved.antlride.internal.ui.text.partitions.rules.AntlrImportPartition;
import org.deved.antlride.internal.ui.text.partitions.rules.AntlrOptionsPartition;
import org.deved.antlride.internal.ui.text.partitions.rules.AntlrRuleActionPartition;
import org.deved.antlride.internal.ui.text.partitions.rules.AntlrRuleRefScopePartition;
import org.deved.antlride.internal.ui.text.partitions.rules.AntlrRuleScopePartition;
import org.deved.antlride.internal.ui.text.partitions.rules.AntlrTokensPartition;
import org.deved.antlride.ui.text.AntlrTextPartitions;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

public class AntlrPartitionScanner extends AntlrBasePartitionScanner {

	public AntlrPartitionScanner() {
		// rules
		List<IPredicateRule> rules = new ArrayList<IPredicateRule>();

		// multi line comment
		IToken multiLineComment = new Token(
				AntlrTextPartitions.ANTLR_MULTI_LINE_COMMENT);
		rules.add(new MultiLineRule("/*", "*/", multiLineComment));
		// Single line comment
		IToken singleLineComment = new Token(
				AntlrTextPartitions.ANTLR_SINGLE_LINE_COMMENT);
		rules.add(new EndOfLineRule("//", singleLineComment));
		// String
		rules.add(new MultiLineRule("\'", "\'", new Token(
				AntlrTextPartitions.ANTLR_STRING), '\\'));

		// (lexer | parser | tree) grammar Name;
		rules.add(new AntlrGrammarDeclarationPartition(new Token(
				AntlrTextPartitions.ANTLR_GRAMMAR_DECLARATION)));
		// options {optionName=optionValue;}
		rules.add(new AntlrOptionsPartition(new Token(
				AntlrTextPartitions.ANTLR_OPTIONS)));
		// import G1, G2, ..., G3;
		rules.add(new AntlrImportPartition(new Token(
				AntlrTextPartitions.ANTLR_IMPORT)));
		// tokens {tokenName=tokenValue;}
		rules.add(new AntlrTokensPartition(new Token(
				AntlrTextPartitions.ANTLR_TOKENS)));
		// scope Name {...} | scope {...} | scope Scope1 Scope2...ScopeN;
		IToken scope = new Token(AntlrTextPartitions.ANTLR_SCOPE);
		rules.add(new AntlrRuleRefScopePartition(scope));
		rules.add(new AntlrRuleScopePartition(scope));
		rules.add(new AntlrGlobalScopePartition(scope));
		// @scopeName?::?actionName {...}
		rules.add(new AntlrGrammarActionPartition(new Token(
				AntlrTextPartitions.ANTLR_GRAMMAR_ACTION)));
		// @init{...} | @after{...}
		rules.add(new AntlrRuleActionPartition(new Token(
				AntlrTextPartitions.ANTLR_RULE_ACTION)));
		// [...]
		rules.add(new MultiLineRule("[", "]", new Token(
				AntlrTextPartitions.ANTLR_BRACKET)));
		// {}
		rules.add(new AntlrActionPartition(new Token(
				AntlrTextPartitions.ANTLR_TARGET_ACTION)));

		setPredicateRules(rules.toArray(new IPredicateRule[rules.size()]));
	}
}
