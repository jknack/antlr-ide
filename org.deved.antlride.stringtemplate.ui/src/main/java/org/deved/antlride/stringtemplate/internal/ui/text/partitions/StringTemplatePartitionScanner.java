/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.text.partitions;

import java.util.ArrayList;
import java.util.List;

import org.deved.antlride.common.ui.text.partitions.AntlrBasePartitionScanner;
import org.deved.antlride.stringtemplate.internal.ui.text.partitions.rules.StringTemplateBuildInStringPartition;
import org.deved.antlride.stringtemplate.internal.ui.text.partitions.rules.StringTemplateDoubleAngleBracketPartition;
import org.deved.antlride.stringtemplate.internal.ui.text.partitions.rules.StringTemplateMapPartition;
import org.deved.antlride.stringtemplate.internal.ui.text.partitions.rules.StringTemplateTemplateBodyPartition;
import org.deved.antlride.stringtemplate.internal.ui.text.partitions.rules.StringTemplateTemplatePartition;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class StringTemplatePartitionScanner extends AntlrBasePartitionScanner {

	public StringTemplatePartitionScanner() {
		// rules
		List<IRule> rules = new ArrayList<IRule>();

		// multi line comment
		IToken stComment = new Token(StringTemplatePartitions.COMMENT);
		rules.add(new MultiLineRule("<!", "!>", stComment));
		rules.add(new MultiLineRule("$!", "!$", stComment));
		// stg line comment comments
		IToken stgComment = new Token(
				StringTemplatePartitions.STG_SINGLE_LINE_COMMENT);
		rules.add(new EndOfLineRule("//", stgComment));
		//stg doc comment
//		stgComment = new Token(StringTemplatePartitions.STG_DOC_COMMENT);
//		rules.add(new MultiLineRule("/**", "*/", stgComment));
		//stg multi line comment
		stgComment = new Token(StringTemplatePartitions.STG_MULTI_LINE_COMMENT);
		rules.add(new MultiLineRule("/*", "*/", stgComment));
		//map
		rules.add(new StringTemplateMapPartition(new Token(StringTemplatePartitions.MAP)));
		// template
		rules.add(new StringTemplateTemplatePartition(new Token(
				StringTemplatePartitions.TEMPLATE)));
		// build in string
		rules.add(new StringTemplateBuildInStringPartition(new Token(
				StringTemplatePartitions.BUILD_IN_STRING)));
		// template region
		rules.add(new MultiLineRule("$", "$", new Token(
				StringTemplatePartitions.TEMPLATE_BODY)));
		rules.add(new StringTemplateTemplateBodyPartition(new Token(
				StringTemplatePartitions.TEMPLATE_BODY)));
		// String literal
		rules.add(new SingleLineRule("\"", "\"", new Token(
				StringTemplatePartitions.STRING), '\\'));
		// <<|>>
		IToken doubleAngleBracketToken = new Token(
				StringTemplatePartitions.DOUBLE_ANGLE_BRACKETS);
		rules.add(new StringTemplateDoubleAngleBracketPartition(
				doubleAngleBracketToken, "<"));
		rules.add(new StringTemplateDoubleAngleBracketPartition(
				doubleAngleBracketToken, ">"));
		rules.add(new SingleLineRule("<@", ">", new Token(
				StringTemplatePartitions.TEMPLATE_REGION)));
		rules.add(new SingleLineRule("<\\", ">", new Token(
				StringTemplatePartitions.ESPECIAL_CHARACTERS), '\\'));		
		// to array
		setPredicateRules((IPredicateRule[]) rules
				.toArray(new IPredicateRule[rules.size()]));
		rules.clear();
	}
}
