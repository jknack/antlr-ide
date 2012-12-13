/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.text.partitions.rules;

import org.deved.antlride.common.ui.text.partitions.AntlrBasePartitionScanner;
import org.deved.antlride.common.ui.text.partitions.rules.AntlrAbstractPartition;
import org.deved.antlride.common.ui.text.rules.AntlrMultilineRule;
import org.deved.antlride.common.ui.text.rules.AntlrStringRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class StringTemplateTemplatePartition extends AntlrAbstractPartition {

	public StringTemplateTemplatePartition(IToken successToken) {
		super(successToken);
	}

	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {
		consumeWord(scanner);
		String text = getTextReaded();
		AntlrBasePartitionScanner scanner2 = (AntlrBasePartitionScanner) scanner;
		int mark = scanner2.getOffset();
		//template(....)::=
		if(text.length() > 0) {
			int templateNameLength = scanner2.getOffset();
			consumeWS(scanner);
			consumeOne(scanner);
			text = getTextReaded();
			if(text.equals("(")) {
				unread(scanner, 1);
				IRule rule = new AntlrMultilineRule(fSuccessToken, '(', ')');
				IToken result = rule.evaluate(scanner);
				if(result == fSuccessToken) {
					//find ::=
					consumeWS(scanner);
					rule = new AntlrStringRule(fSuccessToken, "::=");
					result = rule.evaluate(scanner);
					if(result == fSuccessToken) {
						unread(scanner, scanner2.getOffset() - templateNameLength);
						return fSuccessToken;
					}
				}
			}
		}
		unread(scanner, scanner2.getOffset() - mark);
		return Token.UNDEFINED;
	}

}
