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
import org.deved.antlride.common.ui.text.rules.AntlrStringRule;
import org.deved.antlride.stringtemplate.core.StringTemplateConstants;
import org.deved.antlride.stringtemplate.internal.ui.text.highlighting.rules.StringTemplateRegionHighlight;
import org.deved.antlride.stringtemplate.ui.StringTemplateUIConstants;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;

public class StringTemplateCodeHighlightScanner extends AntlrBaseHighlightScanner {

	public StringTemplateCodeHighlightScanner(IColorManager manager,
			IPreferenceStore store) {
		super(manager, store);
	}	

	private static final String[] TOKENS = {
			StringTemplateUIConstants.SH_DEFAULT,
			StringTemplateUIConstants.SH_KEYWORD,
			StringTemplateUIConstants.SH_TEMPLATE_REGION,
			StringTemplateUIConstants.SH_ASSIGN_TEMPLATE};

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<IRule>();
		// tokens
		IToken keywordToken = getToken(StringTemplateUIConstants.SH_KEYWORD);
		IToken defaultToken = getToken(StringTemplateUIConstants.SH_DEFAULT);
		// highlight's
		rules.add(new AntlrKeyWordHighlight(keywordToken, StringTemplateConstants.KEYWORDS));
		rules.add(new AntlrStringRule(getToken(StringTemplateUIConstants.SH_ASSIGN_TEMPLATE), "::="));
		rules.add(new StringTemplateRegionHighlight(getToken(StringTemplateUIConstants.SH_TEMPLATE_REGION)));
		
		setDefaultReturnToken(defaultToken);
		return rules;
	}

	@Override
	protected String[] getTokenProperties() {
		return TOKENS;
	}
}
