/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.text.rules;

import org.deved.antlride.common.ui.text.partitions.rules.AntlrBlockPartition;
import org.deved.antlride.stringtemplate.internal.ui.text.partitions.StringTemplatePartitions;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class StringTemplateTemplateRule extends AntlrBlockPartition {
	
	public StringTemplateTemplateRule(IToken successToken) {
		super(successToken, '<', '>');
	}

	@Override
	protected IToken doEvaluate(ICharacterScanner scanner) {
		consumeOne(scanner);
		String text = getTextReaded();
	    if (text.equals("<")) {
	    	//setDebug(true);
	    	consumeWS(scanner);
	    	consumeOne(scanner);
	    	text = getTextReaded();
	    	if(!text.equals("\\") && !text.equals("<") && !text.equals("@")) {
	    		IToken block = doBlock(scanner);	    			    		
	    		return block;
	    	}
	    }
	    unread(scanner);
		return Token.UNDEFINED;
	}

	@Override
	protected String[] isInIgnoreMode(String ch, ICharacterScanner scanner) {
		if (ch.equals("\"")) {
			//this rule take precedence over the super rule
			consumeOne(scanner);
			String nextChar = getTextReaded();
			unread(scanner, 1);
			if (nextChar.equals("<")) {
				//build in string
				return new String[] { "\"<", ">\"", null };
			}
		}
		String[] pattern = super.isInIgnoreMode(ch, scanner);
		if(pattern != null)
			return pattern;		
		if (ch.equals("<")) {
			consumeOne(scanner);
			String nextChar = getTextReaded();
			unread(scanner, 1);
			if (nextChar.equals("!")) {
				return new String[] { "<!", "!>", null };
			}
		}		
		if (ch.equals("$")) {
			consumeOne(scanner);
			String nextChar = getTextReaded();
			unread(scanner, 1);
			if (nextChar.equals("!")) {
				return new String[] { "$!", "!$", null };
			}
		}
		return null;
	}
	
	protected String getPartitionName() {
		return StringTemplatePartitions.TEMPLATE_BODY;
	}
}
