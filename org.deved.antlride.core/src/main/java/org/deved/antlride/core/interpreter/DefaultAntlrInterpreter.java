/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.interpreter;

import java.lang.reflect.Field;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.IntStream;
import org.antlr.tool.Grammar;
import org.antlr.tool.Interpreter;
import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.build.AntlrBuildUnitRepository;
import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ast.ModelElementQuery;
import org.deved.antlride.core.model.evaluation.IResultEvalElement;
import org.deved.antlride.core.model.test.AntlrTestSuite;
import org.deved.antlride.core.model.test.DefaultAntlrTestService;
import org.deved.antlride.internal.core.antlr.ANTLRGrammarProvider;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class DefaultAntlrInterpreter implements AntlrInterpreter {

	private static class InternalInterpreter extends Interpreter {

		public InternalInterpreter(Grammar grammar, IntStream input) {
			super(grammar, input);
		}

		@Override
		public void reportScanError(org.antlr.runtime.RecognitionException re) {
			// avoid print to the console
		}
	}

	private static Field ALL_DECISION_DFA_CREATED;

	private Grammar g;

	private Grammar lg;

	private IGrammar grammar;

	private AntlrTestSuite testSuite;

	public IResultEvalElement interpret(IRule rule, String text) {
		AntlrEvalElementBuilder builder = new AntlrEvalElementBuilder(grammar);
		try {
			ANTLRGrammarProvider.configureErrorManager();
			Grammar lexerGrammar = lg;
			if (grammar.isLexerGrammar()) {
				lexerGrammar = g;
			} else {
				if (lexerGrammar != null)
					resetDFAs(lexerGrammar);
			}
			if (lexerGrammar != null) {
				text = text.replace("\r", "");
				CharStream input = new ANTLRStringStream(text);
				Grammar parser = g;
				resetDFAs(parser);

				Interpreter lexEngine = new InternalInterpreter(lexerGrammar,
						input);
				CommonTokenStream tokens = new CommonTokenStream(lexEngine);

				
				IGrammar lexicalGramar = grammar;
				
				if(grammar.isParserGrammar()) {
					IPath libraryPath = AntlrBuildUnitRepository.create(grammar).getLibraryPath();
					libraryPath = libraryPath.append(grammar.getOption("tokenVocab")).addFileExtension("g");
					lexicalGramar = AntlrSourceParserRepository.getGrammar(libraryPath);					
				}
				IModelElement[] elements = ModelElementQuery
						.findIgnoredRules(lexicalGramar);
				for (IModelElement element : elements) {
					IRule ignoredRule = (IRule) element;
					String ignoredRuleName = ignoredRule.getElementName();
					tokens.setTokenTypeChannel(lexerGrammar
							.getTokenType(ignoredRuleName), 99);
				}
				builder.commence(lexerGrammar);
				if (rule.isLexerRule()) {
					lexEngine.parse(rule.getElementName(), builder, null);
				} else {
					Interpreter parseEngine = new InternalInterpreter(parser,
							tokens);
					parseEngine.parse(rule.getElementName(), builder, null);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			builder.terminate();
		}
		return builder.getResult();
	}

	public IStatus beginSession(IProgressMonitor monitor, IGrammar grammar) {		
		this.grammar = grammar;
		int work = 8;
		monitor.beginTask("Initializing Interpreter", work);
		IStatus status = null;
		try {
			ANTLRGrammarProvider grammarProvider = new ANTLRGrammarProvider();
			status = grammarProvider.build(monitor, grammar);
			if(status.getCode() == IStatus.OK) {
				g = grammarProvider.getGrammar();
				lg = grammarProvider.getLexerGrammar();
				testSuite = new DefaultAntlrTestService().get(grammar);
			}			
		} catch (Exception ex) {
			status = new Status(IStatus.ERROR, AntlrCore.PLUGIN_ID,
					"Could not create the interpreter", ex);
			AntlrCore.error(new CoreException(status));
		} finally {
			monitor.done();
		}
		return status;
	}

	public AntlrTestSuite getTestSuite() {
		return testSuite;
	}

	private void resetDFAs(Grammar g) {
		try {
			if (ALL_DECISION_DFA_CREATED == null) {
				ALL_DECISION_DFA_CREATED = Grammar.class
						.getDeclaredField("allDecisionDFACreated");
				ALL_DECISION_DFA_CREATED.setAccessible(true);
			}
			ALL_DECISION_DFA_CREATED.set(g, Boolean.FALSE);
			g.nfa = null;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void endSession() {
		testSuite.destroy();
		g = null;
		lg = null;
		grammar = null;
		testSuite = null;
	}
}
