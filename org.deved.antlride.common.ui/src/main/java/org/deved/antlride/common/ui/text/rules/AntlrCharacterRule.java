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

public class AntlrCharacterRule extends AntlrAbstractRule {

	private String fCharacter;

	public AntlrCharacterRule(IToken successToken, char character) {
		super(successToken);
		fCharacter = "" + character;
	}

	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {
		consumeOne(scanner);
		String text = getTextReaded();
		if(text.equals(fCharacter)) {
			return fSuccessToken;
		}
		unread(scanner);
		return Token.UNDEFINED;
	}

}
