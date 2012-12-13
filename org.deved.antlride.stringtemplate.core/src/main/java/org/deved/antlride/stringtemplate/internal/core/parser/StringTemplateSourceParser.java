/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.core.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.deved.antlride.stringtemplate.core.StringTemplateCore;
import org.deved.antlride.stringtemplate.core.model.dltk.ast.DASTStringTemplateModule;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public class StringTemplateSourceParser extends AbstractSourceParser {

	private static final Pattern BRACKET = Pattern.compile("<[^>]*>");

	private static final Pattern DOLLAR = Pattern.compile("\\$[^\\$]*\\$");

	public ModuleDeclaration parse(IModuleSource moduleSource,
			IProblemReporter reporter) {
		String filename = moduleSource.getFileName();

		String source = moduleSource.getSourceContents();

		DASTStringTemplateModule module = DASTStringTemplateModule.ERROR;
		if (filename.endsWith(".stg")) {
			// parse group
			try {

				GroupLexer lexer = new GroupLexer(new ANTLRStringStream(source));

				CommonTokenStream tokens = new CommonTokenStream(lexer);

				GroupParser parser = new GroupParser(tokens);

				parser.group();

				module = new DASTStringTemplateModule(source.length());

				module.setStatements(parser.getStatements());

			} catch (RecognitionException e) {
				StringTemplateCore.error("Couldn't parse: " + filename, e);
			}
		} else {
			// parse st
			char startDelimiter = '$', endDelimiter = '$';
			if (count(DOLLAR.matcher(source)) <= 0) {
				startDelimiter = '<';
				endDelimiter = '>';
			}
			
			STLexer lexer = new STLexer(new ANTLRStringStream(source),
					startDelimiter, endDelimiter);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			STParser parser = new STParser(tokens);

			try {
				parser.templateAndEOF();
				module = new DASTStringTemplateModule(source.length());
				module.setStatements(parser.getStatements());
			} catch (RecognitionException e) {
				StringTemplateCore.error("Couldn't parse: " + filename, e);
			}
		}
		return module;
	}

	private int count(Matcher matcher) {
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}
}
