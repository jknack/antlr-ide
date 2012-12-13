/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.parser;

import java.util.concurrent.TimeUnit;

import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IGrammarBuilder;
import org.deved.antlride.core.model.dltk.ast.DASTGrammar;
import org.deved.antlride.internal.core.model.GrammarRepository;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public class AntlrSourceParser extends AbstractSourceParser {

	private static boolean DEBUG = false;

	public AntlrSourceParser() {
	}

	public ModuleDeclaration parse(IModuleSource moduleSource,
			IProblemReporter reporter) {
		long start = System.currentTimeMillis();
		long end = 0;
		DASTGrammar ast = DASTGrammar.ERROR_AST;
		IGrammarBuilder grammarBuilder = null;
		String filename = moduleSource.getFileName().startsWith("|") ? moduleSource
				.getFileName().substring(1)
				: moduleSource.getFileName();
		IPath file = new Path(filename);
		try {
			// java.library.path
			GrammarRepository builderFactory = GrammarRepository.getInstance();
			grammarBuilder = builderFactory
					.createGrammarBuilder(file, reporter);
			IGrammar grammar = grammarBuilder.process(moduleSource
					.getSourceContents());
			ast = (DASTGrammar) grammar.getAdapter(ASTNode.class);
			ast.rebuild();
		} catch (CoreException ex) {
			AntlrCore.error(ex);
		} catch (Throwable t) {
			AntlrCore.error((new StringBuilder("Error building ")).append(
					moduleSource.getFileName()).toString(), t);
		} finally {
			end = System.currentTimeMillis();
			long parseTime = end - start;
			if (DEBUG) {
				System.out.printf("%s took : %sms %ssec\n", file.lastSegment(),
						TimeUnit.MILLISECONDS.toMillis(parseTime),
						TimeUnit.MILLISECONDS.toSeconds(parseTime));
			}
		}
		return ast;
	}

	public static void debug(String message) {
		if (DEBUG) {
			System.out.println(message);
		}
	}

}
