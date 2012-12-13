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
import org.deved.antlride.common.ui.text.rules.AntlrCharacterRule;
import org.deved.antlride.stringtemplate.ui.StringTemplateUIConstants;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;

public class StringTemplateTemplateRegionHighlightScanner extends
		AntlrBaseHighlightScanner {

	private static final String[] TOKENS = {
			StringTemplateUIConstants.SH_DEFAULT,
			StringTemplateUIConstants.SH_KEYWORD,
			StringTemplateUIConstants.SH_TEMPLATE_DELIMETERS, 
			StringTemplateUIConstants.SH_TEMPLATE_REGION };

	public StringTemplateTemplateRegionHighlightScanner(IColorManager manager,
			IPreferenceStore store) {
		super(manager, store);
	}

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<IRule>();
		rules.add(new AntlrCharacterRule(
				getToken(StringTemplateUIConstants.SH_TEMPLATE_DELIMETERS), '<'));
		rules.add(new AntlrCharacterRule(
				getToken(StringTemplateUIConstants.SH_TEMPLATE_DELIMETERS), '>'));
		rules.add(new AntlrCharacterRule(
				getToken(StringTemplateUIConstants.SH_DEFAULT), '('));
		rules.add(new AntlrCharacterRule(
				getToken(StringTemplateUIConstants.SH_DEFAULT), ')'));
		rules.add(new AntlrKeyWordHighlight(
				getToken(StringTemplateUIConstants.SH_KEYWORD), "super"));
		setDefaultReturnToken(getToken(StringTemplateUIConstants.SH_TEMPLATE_REGION));
		return rules;
	}

	@Override
	protected String[] getTokenProperties() {
		return TOKENS;
	}

}
