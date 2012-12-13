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
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

public class AntlrTargetLanguageCommentHighlight extends
		AntlrTargetLanguageHighlight {

	private Map<String, IRule[]> fComments;

	private final static IRule[] NO_RULE = new IRule[0];

	public AntlrTargetLanguageCommentHighlight(IToken successToken) {
		super(successToken);
		initComments(successToken);
	}

	private void initComments(IToken successToken) {
		final IRule[] clikeComments = new IRule[] {
				new MultiLineRule("/*", "*/", successToken),
				new EndOfLineRule("//", successToken) };

		final IRule[] sharpComments = new IRule[] { new EndOfLineRule("#",
				successToken) };

		fComments = new HashMap<String, IRule[]>() {
			private static final long serialVersionUID = 1L;

			{
				// c like
				put(AntlrLanguageTargetName.C.name(), clikeComments);
				put(AntlrLanguageTargetName.CPP.name(), clikeComments);
				put(AntlrLanguageTargetName.CSharp.name(), clikeComments);
				put(AntlrLanguageTargetName.CSharp2.name(), clikeComments);
				put(AntlrLanguageTargetName.CSharp3.name(), clikeComments);
				put(AntlrLanguageTargetName.ObjC.name(), clikeComments);
				put(AntlrLanguageTargetName.Java.name(), clikeComments);
				put(AntlrLanguageTargetName.ActionScript.name(), clikeComments);
				put(AntlrLanguageTargetName.JavaScript.name(), clikeComments);
				// # comment
				put(AntlrLanguageTargetName.Python.name(), sharpComments);
				put(AntlrLanguageTargetName.Ruby.name(), sharpComments);
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
			IRule[] rules = fComments.get(language);
			for (IRule rule : rules) {
				IToken token = rule.evaluate(scanner);
				if (!token.isUndefined()) {
					return fSuccessToken;
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return Token.UNDEFINED;
	}

}
