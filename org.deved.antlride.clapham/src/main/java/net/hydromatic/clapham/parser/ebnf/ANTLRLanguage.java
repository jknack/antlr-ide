/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package net.hydromatic.clapham.parser.ebnf;

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import net.hydromatic.clapham.parser.ProductionNode;
import net.hydromatic.clapham.parser.Language;
import net.hydromatic.clapham.parser.LanguageParserException;

/**
 * 
 * @author Edgar Espina
 * 
 */
public enum ANTLRLanguage implements Language {

	SINGLETON;

	public List<ProductionNode> parse(String content)
			throws LanguageParserException {
		try {
			boolean TRACE = false;
			if (TRACE) {
				System.err.println(content);
			}
			CharStream input = new ANTLRStringStream(content);
			EbnfLexer lexer = new EbnfLexer(input);
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			EbnfParser parser = new EbnfParser(tokenStream);
			List<ProductionNode> nodes = parser.rule();
			if (TRACE) {
				for (ProductionNode productionNode : nodes) {
					System.err.println(productionNode);
				}
			}
			return nodes;
		} catch (RecognitionException e) {
			e.printStackTrace();
			throw new LanguageParserException(e.toString());
		}
	}

}
