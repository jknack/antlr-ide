/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.text.highlighting.rules;

import org.deved.antlride.common.ui.text.rules.AntlrAbstractRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class StringTemplateRegionHighlight extends AntlrAbstractRule {

	public StringTemplateRegionHighlight(IToken successToken) {
		super(successToken);
	}

	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {
		consumeOne(scanner);
		String text = getTextReaded();
		if (text.equals("@")) {
			consumeWord(scanner);
			text = getTextReaded();
			if (text.length() > 0) {
				//@identifier
				return fSuccessToken;
			}
		}
		unread(scanner);
		return Token.UNDEFINED;
	}

}
