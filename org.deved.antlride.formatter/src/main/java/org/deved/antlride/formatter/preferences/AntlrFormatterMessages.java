/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.formatter.preferences;

import org.eclipse.osgi.util.NLS;

public class AntlrFormatterMessages extends NLS {
	private static final String BUNDLE_NAME = "org.deved.antlride.formatter.preferences.messages"; //$NON-NLS-1$	
	/** Indentation Section */
	public static String Indentation_generalSettings;
	public static String Indentation_tab_policy;
	public static String Indentation_tab_policy_SPACE;
	public static String Indentation_tab_policy_TAB;
	public static String Indentation_indent_size;
	public static String Indentation_tab_size;
	public static String Indentation_indentSections;
	public static String Indentation_options;
	public static String Indentation_ruleOptions;
	public static String Indentation_tokens;
	public static String Indentation_ruleBody;
	public static String Indentation_blocks;
	public static String Indentation_alignSections;
	public static String Indentation_alignTokensInColumns;
	public static String Indentation_alignOptionsInColumns;
	public static String Indentation_indentRules;
	public static String Indentation_indentBlocks;
	public static String Indentation;
	public static String Indentation_blockOptions;
	public static String Indentation_rewriteOperator;

	/**Blank Lines Section*/
	public static String BlankLines;
	public static String BlankLines_beforeOptions;
	public static String BlankLines_afterOptions;
	public static String BlankLines_beforeGrammarDeclaration;
	public static String BlankLines_afterGrammarDeclaration;
	public static String BlankLines_blankLinesInSourceFile;
	public static String BlankLines_inRules;
	public static String BlankLines_beforeTokens;
	public static String BlankLines_afterTokens;
	public static String BlankLines_beforeAction;
	public static String BlankLines_afterAction;
	public static String BlankLines_beforeRule;
	public static String BlankLines_afterRule;
	public static String BlankLines_beforeFirstRule;
	public static String BlankLines_beforeScope;
	public static String BlankLines_afterScope;
	
	/**Braces*/
	public static String Braces;
	public static String BracesPositionsInGrammar;
	public static String Braces_sameLine;
	public static String Braces_nextLine;
	public static String Braces_Options;
	public static String Braces_Actions;
	public static String Braces_Tokens;
	public static String Braces_Scopes;
	public static String BracesPositionsInRule;
	
	/**White spaces*/
	public static String WhiteSpaces;
	public static String WhiteSpaces_beforeAfterOption;
	public static String WhiteSpaces_beforeAfterToken;
	public static String WhiteSpaces_beforeAfterAssign;
	public static String WhiteSpaces_beforeAfterBlockParenthesis;
	public static String WhiteSpacesInSections;
	public static String WhiteSpacesInStatements;
	
	/**Control Statements*/
	public static String ControlStatements;
	public static String ControlStatements_General;
	public static String ControlStatements_afterRuleModifier;
	public static String ControlStatements_beforeRuleColon;
	public static String ControlStatements_beforeRuleArgs;
	public static String ControlStatements_beforeRuleReturns;
	public static String ControlStatements_beforeRuleThrows;
	public static String ControlStatements_afterRewriteOperator;
	public static String ControlStatements_beforeRuleEnd;
	public static String ControlStatements_ruleEmpty;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, AntlrFormatterMessages.class);
	}

	private AntlrFormatterMessages() {
	}
}
