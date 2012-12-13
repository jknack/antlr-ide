/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.common.ui.text.highlighting.rules;

import org.deved.antlride.common.ui.text.rules.AntlrAbstractRule;
import org.deved.antlride.common.ui.text.rules.AntlrAnyDetector;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;

public class AntlrKeyWordHighlight extends AntlrAbstractRule {
	private String[] fKeywords;
	private IToken fDefaultToken;

	public AntlrKeyWordHighlight(IToken successToken, IToken defaultToken,
			String... keywords) {
		super(successToken);
		fKeywords = keywords;
		fDefaultToken = defaultToken;
	}

	public AntlrKeyWordHighlight(IToken successToken, IToken defaultToken,
			IWordDetector wordDetector, IWhitespaceDetector whitespaceDetector,
			String... keywords) {
		super(successToken, wordDetector, whitespaceDetector);
		fKeywords = keywords;
		fDefaultToken = defaultToken;
	}

	public AntlrKeyWordHighlight(IToken successToken, IToken defaultToken,
			IWhitespaceDetector whitespaceDetector, String... keywords) {
		super(successToken, AntlrAnyDetector.instance(), whitespaceDetector);
		fKeywords = keywords;
		fDefaultToken = defaultToken;
	}

	public AntlrKeyWordHighlight(IToken successToken,
			IWhitespaceDetector whitespaceDetector, String... keywords) {
		super(successToken, AntlrAnyDetector.instance(), whitespaceDetector);
		fKeywords = keywords;
		fDefaultToken = Token.UNDEFINED;
	}

	public AntlrKeyWordHighlight(IToken successToken, String... keywords) {
		this(successToken, Token.UNDEFINED, keywords);
	}

	protected IToken doEvaluate(ICharacterScanner scanner) {
		consumeWord(scanner);
		String text = getTextReaded();
		if (isKeyword(text)) {
			return fSuccessToken;
		} else if (text.length() > 0) {
			if (fDefaultToken.isUndefined()) {
				unread(scanner);
			}
			return fDefaultToken;
		}
		unread(scanner);
		return Token.UNDEFINED;
	}

	private boolean isKeyword(String keyword) {
		for (String k : fKeywords) {
			if (k.equals(keyword)) {
				return true;
			}
		}
		return false;
	}
}
