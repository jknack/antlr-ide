/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.parser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;
import org.antlr.tool.ANTLRErrorListener;
import org.antlr.tool.Message;
import org.antlr.tool.ToolMessage;
import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IImport;
import org.deved.antlride.internal.core.model.AAbstractGrammarBuilder;
import org.deved.antlride.internal.core.model.AGrammar;
import org.deved.antlride.internal.core.parser.ANTLRParser.grammarDef_return;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;

public class AntlrGrammarBuilder extends AAbstractGrammarBuilder {

	private static class ErrorCounter implements ANTLRErrorListener {

		private int errors;

		public void error(Message msg) {
			errors++;

		}

		public void error(ToolMessage msg) {
			error((Message) msg);
		}

		public void info(String msg) {
		}

		public void warning(Message msg) {
		}

	}

	@Override
	public IGrammar process(final String content) {
		final AGrammar grammar = isBasicMode() ? new AGrammar(content)
				: new AGrammar(filename, content);
		final ErrorCounter counter = new ErrorCounter();
		try {
			ANTLRLexer lexer = new ANTLRLexer(new ANTLRStringStream(content)) {
				@Override
				public void emitErrorMessage(String msg) {
					counter.errors++;
					super.emitErrorMessage(msg);
				}
			};

			CommonTokenStream tokens = new CommonTokenStream(lexer);

			ANTLRParser parser = new ANTLRParser(tokens) {
				@Override
				public void emitErrorMessage(String msg) {
					counter.errors++;
					super.emitErrorMessage(msg);
				}
			};

			grammarDef_return grammarDef = parser.grammarDef();

			CommonTreeNodeStream nodes = new CommonTreeNodeStream(
					(Tree) grammarDef.tree);

			nodes.setTokenStream(tokens);

			ANTLRTreeWalker walker = new ANTLRWalker(nodes);

			DefaultGrammarBuilder builder = new DefaultGrammarBuilder(grammar);

			walker.grammarDef(builder);

			builder.build();

			@SuppressWarnings("unchecked")
			List<CommonToken> tokenList = tokens.getTokens();

			// int k = 0;
			for (CommonToken token : tokenList) {
				if (token.getType() == ANTLRLexer.SL_COMMENT
						|| token.getType() == ANTLRLexer.ML_COMMENT) {
					grammar.addComment(token.getText());
				}
			}

			// load dependent grammars
			if (!isBasicMode() && grammar.hasImports()) {
				IImport[] imports = grammar.getImports().getImports();
				Set<String> gnames = new HashSet<String>();
				for (IImport imp : imports) {
					gnames.add(imp.getElementName());
				}
				gnames.remove(grammar.getElementName());
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				for (String gname : gnames) {
					IPath dependentPath = grammar.getFolder().append(gname)
							.addFileExtension("g");
					IFile dependentFile = root.getFile(dependentPath);
					if (dependentFile.exists()) {
						IGrammar dependentGrammar = AntlrSourceParserRepository
								.getGrammar(dependentPath);
						grammar.addDependent(dependentGrammar);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		grammar.setValid(counter.errors == 0);
		return grammar;
	}

	private boolean isBasicMode() {
		return filename == null;
	}
}
