/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.internal.ui.text.partitions;

import java.util.ArrayList;
import java.util.List;

import org.deved.antlride.common.ui.text.partitions.AntlrBasePartitionScanner;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class GUnitPartitionScanner extends AntlrBasePartitionScanner {

	public GUnitPartitionScanner() {
		// rules
		List<IRule> rules = new ArrayList<IRule>();

		// line comment comments
		IToken slcomment = new Token(GUnitPartitions.SINGLE_LINE_COMMENT);
		rules.add(new EndOfLineRule("//", slcomment));
		// multi line comment
		IToken mlcomment = new Token(GUnitPartitions.MULTI_LINE_COMMENT);
		rules.add(new MultiLineRule("/*", "*/", mlcomment));
		// String literal
		rules.add(new SingleLineRule("\"", "\"", new Token(
				GUnitPartitions.STRING), '\\'));
		// <<|>>
		IToken mlstring = new Token(GUnitPartitions.MULTI_LINE_STRING);
		rules.add(new MultiLineRule("<<", ">>", mlstring));
		// to array
		setPredicateRules((IPredicateRule[]) rules
				.toArray(new IPredicateRule[rules.size()]));
		rules.clear();
	}
}
