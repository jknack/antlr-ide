/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.partitions.rules;

import org.deved.antlride.common.ui.text.partitions.rules.AntlrBlockPartition;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * grammar structure: ( cmt:DOC_COMMENT )? gr:grammarType gid:id SEMI
 * optionsSpec? tokensSpec? scopes? (actions*)? rules+
 * 
 * @author edgar
 * 
 */
public class AntlrGrammarActionPartition extends AntlrBlockPartition {

	public AntlrGrammarActionPartition(IToken successToken) {
		super(successToken);
		addExclusionPattern("@");
	}

	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {
		try {
			consumeOne(scanner);
			String text = getTextReaded();
			if (text.equals("@")) {
				//debug = true;
				consumeWS(scanner);
				consumeWord(scanner);
				text = getTextReaded();
				if (text.equals("init") || text.equals("after")) {
					// rule action
					unread(scanner);
					return Token.UNDEFINED;
				}
				consumeWS(scanner);
				consumeOne(scanner);
				text = getTextReaded();
				int l1 = fCharsReaded;
				if (text.equals(":")) {
					consumeWS(scanner);
					consumeOne(scanner);
					text = getTextReaded();
					if (!text.equals(":")) {
						unread(scanner, fCharsReaded - l1);
						return fSuccessToken;
					}
					consumeWS(scanner);
					consumeWord(scanner);
					consumeWS(scanner);
					consumeOne(scanner);
					text = getTextReaded();
					l1 = fCharsReaded;
				}
				if (text.equals("{")) {
					return doBlock(scanner);
				} else {
					unread(scanner, fCharsReaded - l1);
					return fSuccessToken;
				}
			}
			unread(scanner);
			return Token.UNDEFINED;
		} finally {
			//debugPartition();
		}
	}

}
