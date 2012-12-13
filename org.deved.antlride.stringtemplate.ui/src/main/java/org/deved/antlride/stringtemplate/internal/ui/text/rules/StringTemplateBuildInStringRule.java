/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.text.rules;

import org.deved.antlride.stringtemplate.internal.ui.text.partitions.StringTemplatePartitions;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class StringTemplateBuildInStringRule extends
		StringTemplateTemplateRule {

	public StringTemplateBuildInStringRule(IToken successToken) {
		super(successToken);
	}
	
	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {
		consumeOne(scanner);
		String text = getTextReaded();
		if (text.equals("\"")) {
			consumeOne(scanner);
			text = getTextReaded();
			if (text.equals("<")) {				
				unread(scanner, 1);
				IToken token = super.doEvaluate(scanner);
				if(token == fSuccessToken) {
					text = getTextReaded();
					while(!fEOF && !text.equals("\"") && !text.equals("\n")) {
						consumeOne(scanner);
						text = getTextReaded();
					}
					if(text.equals("\n")) {
						unread(scanner, 1);
					}
					return fSuccessToken;
				}
			}
		}
		unread(scanner);
		return Token.UNDEFINED;
	}
	
	@Override
	protected String[] isInIgnoreMode(String ch, ICharacterScanner scanner) {		
		return null;
	}
	
	protected String getPartitionName() {
		return StringTemplatePartitions.BUILD_IN_STRING;
	}
}
