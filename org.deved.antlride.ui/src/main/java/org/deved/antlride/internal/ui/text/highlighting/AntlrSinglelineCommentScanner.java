/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.highlighting;

import java.util.List;

import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.Token;

public class AntlrSinglelineCommentScanner extends
		AntlrAbstractHighlightScanner {
	private static final String[] TOKENS = { AntlrColorConstants.ANTLR3_SINGLE_LINE_COMMENT };

	public AntlrSinglelineCommentScanner(IColorManager manager,
			IPreferenceStore store) {
		super(manager, store);
		initialize();
	}

	@Override
	protected List<IRule> createRules() {
		Token token = getToken(AntlrColorConstants.ANTLR3_SINGLE_LINE_COMMENT);
		setDefaultReturnToken(token);
		return null;
	}

	@Override
	protected String[] getTokenProperties() {
		return TOKENS;
	}

}
