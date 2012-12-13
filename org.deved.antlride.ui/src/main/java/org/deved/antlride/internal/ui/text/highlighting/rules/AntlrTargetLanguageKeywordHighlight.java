/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.highlighting.rules;

import org.deved.antlride.core.resources.AntlrLanguageTargetRepository;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class AntlrTargetLanguageKeywordHighlight extends
		AntlrTargetLanguageHighlight {

	private IToken fDefaultToken;

	public AntlrTargetLanguageKeywordHighlight(IToken successToken,
			IToken defaultToken) {
		super(successToken);
		fDefaultToken = defaultToken;
	}

	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {
		String language = getTargetLanguage(scanner);
		try {
			consumeWord(scanner);
			String text = getTextReaded();
			if (text.length() == 0) {
				return Token.UNDEFINED;
			}

			boolean isKeyword = AntlrLanguageTargetRepository.matchKeyword(
					language, text);
			if (isKeyword) {
				return fSuccessToken;
			}
			// unread(scanner);
		} catch (Throwable t) {
			// shhh
		}
		return fDefaultToken;// Token.UNDEFINED;
	}
}
