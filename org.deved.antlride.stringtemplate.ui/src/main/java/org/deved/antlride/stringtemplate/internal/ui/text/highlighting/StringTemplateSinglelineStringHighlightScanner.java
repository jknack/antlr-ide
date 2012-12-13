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
import org.deved.antlride.common.ui.text.rules.AntlrCharacterRule;
import org.deved.antlride.stringtemplate.ui.StringTemplateUIConstants;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.SingleLineRule;

public class StringTemplateSinglelineStringHighlightScanner extends
		AntlrBaseHighlightScanner {

	private static final String[] TOKENS = {
			StringTemplateUIConstants.SH_STRING,
			StringTemplateUIConstants.SH_TEMPLATE_DELIMETERS };

	public StringTemplateSinglelineStringHighlightScanner(
			IColorManager manager, IPreferenceStore store) {
		super(manager, store);
	}

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<IRule>();
		// tokens
		IToken templateDefinitionToken = getToken(StringTemplateUIConstants.SH_TEMPLATE_DELIMETERS);
		// rules
		rules.add(new SingleLineRule("\\<", ">", getToken(StringTemplateUIConstants.SH_STRING)));
		rules.add(new AntlrCharacterRule(templateDefinitionToken,
				'<'));
		rules.add(new AntlrCharacterRule(templateDefinitionToken,
				'>'));
		// default
		setDefaultReturnToken(getToken(StringTemplateUIConstants.SH_STRING));
		return rules;
	}

	@Override
	protected String[] getTokenProperties() {
		return TOKENS;
	}

}
