/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.highlighting.rules;

import java.util.HashMap;
import java.util.Map;

import org.deved.antlride.core.AntlrLanguageTargetName;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class AntlrTargetLanguageLiteralHighlight extends
		AntlrTargetLanguageHighlight {

	private Map<String, IRule[]> fLiterals;

	private static final IRule[] NO_RULE = new IRule[0];

	public AntlrTargetLanguageLiteralHighlight(IToken successToken) {
		super(successToken);
		initLiterals(successToken);
	}

	private void initLiterals(IToken successToken) {
		final IRule[] clikeLiterals = new IRule[] {
				new SingleLineRule("\'", "\'", successToken, '\\'),
				new SingleLineRule("\"", "\"", successToken, '\\') };

		final IRule[] pythonLiterals = new IRule[] {
				new MultiLineRule("\"\"\"", "\"\"\"", successToken, '\\'),
				new MultiLineRule("\'\'\'", "\'\'\'", successToken, '\\'),
				new MultiLineRule("\'", "\'", successToken, '\\'),
				new MultiLineRule("\"", "\"", successToken, '\\') };

		fLiterals = new HashMap<String, IRule[]>() {
			private static final long serialVersionUID = 1L;

			{
				// c like literals
				put(AntlrLanguageTargetName.C.name(), clikeLiterals);
				put(AntlrLanguageTargetName.CPP.name(), clikeLiterals);
				put(AntlrLanguageTargetName.CSharp.name(), clikeLiterals);
				put(AntlrLanguageTargetName.CSharp2.name(), clikeLiterals);
				put(AntlrLanguageTargetName.CSharp3.name(), clikeLiterals);
				put(AntlrLanguageTargetName.Java.name(), clikeLiterals);
				put(AntlrLanguageTargetName.ObjC.name(), clikeLiterals);
				put(AntlrLanguageTargetName.ActionScript.name(), clikeLiterals);
				put(AntlrLanguageTargetName.Ruby.name(), clikeLiterals);
				put(AntlrLanguageTargetName.JavaScript.name(), clikeLiterals);
				// python literals
				put(AntlrLanguageTargetName.Python.name(), pythonLiterals);
			}

			@Override
			public IRule[] get(Object key) {
				IRule[] rules = super.get(key);
				return rules == null ? NO_RULE : rules;
			}
		};
	}

	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {
		String language = getTargetLanguage(scanner);
		try {
			IRule[] rules = fLiterals.get(language);
			for (IRule rule : rules) {
				IToken token = rule.evaluate(scanner);
				if (!token.isUndefined()) {
					return fSuccessToken;
				}
			}
		} catch (Throwable t) {
			// shhh
		}
		return Token.UNDEFINED;
	}

}
