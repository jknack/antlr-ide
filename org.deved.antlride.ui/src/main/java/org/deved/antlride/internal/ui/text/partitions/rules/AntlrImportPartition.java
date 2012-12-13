/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.partitions.rules;

import org.deved.antlride.common.ui.text.partitions.rules.AntlrAbstractPartition;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class AntlrImportPartition extends AntlrAbstractPartition {

	public AntlrImportPartition(IToken successToken) {
		super(successToken);
	}

	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {
		consumeWord(scanner);
		String text = getTextReaded();
		if (text.equals("import")) {
			consumeWS(scanner);
			int len = fCharsReaded;
			boolean notFound = true;
			while (!fEOF) {
				consumeWord(scanner);
				text = getTextReaded();
				len = fCharsReaded;
				consumeWS(scanner);
				consumeOne(scanner);
				text = getTextReaded();
				if (text.equals(";")) {
					notFound = false;
					break;
				}
				if (!text.equals(",")) {
					break;
				}
				consumeWS(scanner);
			}
			if (notFound) {
				unread(scanner, fCharsReaded - len);
			}
			return fSuccessToken;
		}
		unread(scanner);
		return Token.UNDEFINED;
	}

}
