/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.internal.core.parser;

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.TokenStream;
import org.deved.antlride.gunit.core.model.dltk.ast.DASTGUnitModule;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public class GUnitSourceParser extends AbstractSourceParser {

	@SuppressWarnings("unchecked")
	public ModuleDeclaration parse(IModuleSource moduleSource,
			IProblemReporter reporter) {

		String source = moduleSource.getSourceContents();
		ModuleDeclaration module = new DASTGUnitModule(source.length());
		try {
			gUnitLexer lexer = new gUnitLexer(new ANTLRStringStream(source));
			TokenStream stream = new CommonTokenStream(lexer);
			gUnitParser parser = new gUnitParser(stream);
			List statements = module.getStatements();
			parser.gUnitDef(statements);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return module;
	}
}
