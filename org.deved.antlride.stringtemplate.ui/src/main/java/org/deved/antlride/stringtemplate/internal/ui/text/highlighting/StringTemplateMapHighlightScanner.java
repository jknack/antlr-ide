/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.text.highlighting;

import java.util.ArrayList;
import java.util.List;

import org.deved.antlride.common.ui.text.highlighting.AntlrBaseHighlightScanner;
import org.deved.antlride.common.ui.text.highlighting.rules.AntlrKeyWordHighlight;
import org.deved.antlride.stringtemplate.core.StringTemplateConstants;
import org.deved.antlride.stringtemplate.ui.StringTemplateUIConstants;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.SingleLineRule;

public class StringTemplateMapHighlightScanner extends
		AntlrBaseHighlightScanner {

	private static final String[] TOKENS = {
			StringTemplateUIConstants.SH_DEFAULT,
			StringTemplateUIConstants.SH_KEYWORD,
			StringTemplateUIConstants.SH_STRING,
			StringTemplateUIConstants.SH_COMMENT,
			StringTemplateUIConstants.SH_STG_SINGLE_LINE_COMMENT,
			StringTemplateUIConstants.SH_STG_MULTI_LINE_COMMENT};

	public StringTemplateMapHighlightScanner(IColorManager manager,
			IPreferenceStore store) {
		super(manager, store);
	}

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<IRule>();
		// tokens
		IToken stringToken = getToken(StringTemplateUIConstants.SH_STRING);
		IToken keywordToken = getToken(StringTemplateUIConstants.SH_KEYWORD);
		IToken commentToken = getToken(StringTemplateUIConstants.SH_COMMENT);
		// rules
		rules.add(new MultiLineRule("<!", "!>", commentToken));
		rules.add(new MultiLineRule("$!", "!$", commentToken));
		rules.add(new EndOfLineRule("//", getToken(StringTemplateUIConstants.SH_STG_SINGLE_LINE_COMMENT)));
		rules.add(new MultiLineRule("/*", "*/", getToken(StringTemplateUIConstants.SH_STG_MULTI_LINE_COMMENT)));
		rules.add(new AntlrKeyWordHighlight(keywordToken,
				StringTemplateConstants.KEYWORDS_IN_MAP));
		rules.add(new SingleLineRule("\"", "\"", stringToken));
		// default token
		setDefaultReturnToken(getToken(StringTemplateUIConstants.SH_DEFAULT));
		return rules;
	}

	@Override
	protected String[] getTokenProperties() {
		return TOKENS;
	}

}
