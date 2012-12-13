/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.text.highlighting.rules;

import org.deved.antlride.common.ui.text.highlighting.rules.AntlrKeyWordHighlight;
import org.deved.antlride.stringtemplate.core.StringTemplateConstants;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class StringTemplateKeywordInTemplateExpressionHighlight extends
		AntlrKeyWordHighlight {

	public StringTemplateKeywordInTemplateExpressionHighlight(
			IToken successToken) {
		super(successToken, StringTemplateConstants.KEYWORDS_IN_TEMPLATE_EXPR);
	}

	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {
		IToken token = super.doEvaluate(scanner);
		if(token == fSuccessToken) {
			consumeWS(scanner);
			consumeOne(scanner);
			String text = getTextReaded();
			if(text.equals("=")) {
				unread(scanner, 1);
				return fSuccessToken;
			}
		}
		unread(scanner);
		return Token.UNDEFINED;
	}
}
