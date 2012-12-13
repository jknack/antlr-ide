/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.common.ui.text.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class AntlrStringRule extends AntlrAbstractRule {

	private String fCharacters;

	public AntlrStringRule(IToken successToken, String characters) {
		super(successToken);
		fCharacters = characters;
	}

	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {		
		for (int i = 0; i < fCharacters.length(); i++) {
			consumeOne(scanner);
			String text = getTextReaded();
			if(text.length() == 1) {
				if(fCharacters.charAt(i) != text.charAt(0)) {
					unread(scanner);
					return Token.UNDEFINED;
				}
			}
		}
		return fSuccessToken;
	}

}
