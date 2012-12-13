/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.text.partitions.rules;

import org.deved.antlride.common.ui.text.partitions.rules.AntlrAbstractPartition;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class StringTemplateDoubleAngleBracketPartition extends
		AntlrAbstractPartition {

	private String fPattern;
	
	public StringTemplateDoubleAngleBracketPartition(IToken successToken, String pattern) {
		super(successToken);
		fPattern = pattern;
	}

	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {
		consumeOne(scanner);
		String text = getTextReaded();
		if(text.equals(fPattern)) {
			consumeOne(scanner);
			text = getTextReaded();
			if(text.equals(fPattern)) {
				return fSuccessToken;
			}
		}
		unread(scanner);
		return Token.UNDEFINED;
	}

}
