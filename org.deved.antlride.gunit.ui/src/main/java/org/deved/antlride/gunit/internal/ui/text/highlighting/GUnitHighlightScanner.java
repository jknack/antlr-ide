/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.gunit.internal.ui.text.highlighting;

import java.util.ArrayList;
import java.util.List;

import org.deved.antlride.common.ui.text.highlighting.AntlrBaseHighlightScanner;
import org.deved.antlride.common.ui.text.highlighting.rules.AntlrKeyWordHighlight;
import org.deved.antlride.common.ui.text.rules.AntlrStringRule;
import org.deved.antlride.gunit.core.GUnitConstants;
import org.deved.antlride.gunit.internal.ui.text.highlighting.rules.GUnitDirectiveHighlight;
import org.deved.antlride.gunit.internal.ui.text.highlighting.rules.GUnitTestSuiteHighlight;
import org.deved.antlride.gunit.ui.GUnitUIConstants;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;

public class GUnitHighlightScanner extends AntlrBaseHighlightScanner {

	public GUnitHighlightScanner(IColorManager manager,
			IPreferenceStore store) {
		super(manager, store);
	}	

	private static final String[] TOKENS = {
			GUnitUIConstants.SH_DEFAULT,
			GUnitUIConstants.SH_TEST_SUITE,
			GUnitUIConstants.SH_DIRECTIVE,
			GUnitUIConstants.SH_KEYWORD, 
			GUnitUIConstants.SH_OK_KEYWORD,
			GUnitUIConstants.SH_EXPECT_OPERATOR,
			GUnitUIConstants.SH_FAIL_KEYWORD};

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<IRule>();
		// tokens
		IToken keywordToken = getToken(GUnitUIConstants.SH_KEYWORD);
		IToken okKeywordToken = getToken(GUnitUIConstants.SH_OK_KEYWORD);
		IToken failKeywordToken = getToken(GUnitUIConstants.SH_FAIL_KEYWORD);
		IToken testToken = getToken(GUnitUIConstants.SH_TEST_SUITE);
		IToken directiveToken = getToken(GUnitUIConstants.SH_DIRECTIVE);
		IToken defaultToken = getToken(GUnitUIConstants.SH_DEFAULT);
		IToken expectOperatorToken = getToken(GUnitUIConstants.SH_EXPECT_OPERATOR);
		// highlight's
		rules.add(new AntlrStringRule(expectOperatorToken, "->"));
		rules.add(new AntlrKeyWordHighlight(keywordToken, GUnitConstants.KEYWORDS));				
		
		rules.add(new GUnitDirectiveHighlight(directiveToken));
		
		rules.add(new AntlrStringRule(okKeywordToken, "OK"));
		rules.add(new AntlrStringRule(failKeywordToken, "FAIL"));
		
		rules.add(new GUnitTestSuiteHighlight(testToken));
		
		setDefaultReturnToken(defaultToken);
		return rules;
	}

	@Override
	protected String[] getTokenProperties() {
		return TOKENS;
	}
}
