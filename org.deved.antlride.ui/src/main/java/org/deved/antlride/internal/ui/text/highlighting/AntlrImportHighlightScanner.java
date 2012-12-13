/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.highlighting;

import java.util.ArrayList;
import java.util.List;

import org.deved.antlride.common.ui.text.highlighting.rules.AntlrKeyWordHighlight;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.ui.texteditor.ITextEditor;

public class AntlrImportHighlightScanner extends AntlrAbstractHighlightScanner {
	private static final String[] TOKENS = {
			AntlrColorConstants.ANTLR3_GRAMMAR_NAME,
			AntlrColorConstants.ANTLR3_DEFAULT,
			AntlrColorConstants.ANTLR3_KEYWORD };

	public AntlrImportHighlightScanner(IColorManager manager,
			IPreferenceStore store, ITextEditor editor) {
		super(manager, store, editor);
		initialize();
	}

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<IRule>(1);
		IToken keywordToken = getToken(AntlrColorConstants.ANTLR3_KEYWORD);
		IToken grammarName = getToken(AntlrColorConstants.ANTLR3_GRAMMAR_NAME);
		rules
				.add(new AntlrKeyWordHighlight(keywordToken, grammarName,
						"import"));
		setDefaultReturnToken(getToken(AntlrColorConstants.ANTLR3_DEFAULT));
		return rules;
	}

	@Override
	protected String[] getTokenProperties() {
		return TOKENS;
	}

}
