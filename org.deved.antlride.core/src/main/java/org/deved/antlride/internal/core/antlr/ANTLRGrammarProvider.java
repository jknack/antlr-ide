package org.deved.antlride.internal.core.antlr;

import java.io.CharArrayReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedHashSet;
import java.util.Set;

import org.antlr.Tool;
import org.antlr.analysis.DFAOptimizer;
import org.antlr.tool.ANTLRErrorListener;
import org.antlr.tool.CompositeGrammar;
import org.antlr.tool.ErrorManager;
import org.antlr.tool.Grammar;
import org.antlr.tool.GrammarNonDeterminismMessage;
import org.antlr.tool.Message;
import org.antlr.tool.ToolMessage;
import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.build.AntlrBuildUnitRepository;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.util.AntlrCoreHelper;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import antlr.RecognitionException;

public class ANTLRGrammarProvider {
	private static final ANTLRErrorListener DO_NOT_REPORT_ERRORS = new ANTLRErrorListener() {

		public void warning(Message msg) {
		}

		public void info(String msg) {

		}

		public void error(ToolMessage msg) {
		}

		public void error(Message msg) {
		}
	};

	private class NonDeterminismListener implements ANTLRErrorListener {

		public void error(Message msg) {
			if (msg instanceof GrammarNonDeterminismMessage) {
				GrammarNonDeterminismMessage gndm = (GrammarNonDeterminismMessage) msg;
				nonDeterminismList.add(gndm);
			}
		}

		public void error(ToolMessage msg) {
			error((Message) msg);
		}

		public void info(String msg) {

		}

		public void warning(Message msg) {
			error((Message) msg);

		}

	}

	private Grammar g;

	private Grammar lg;

	private Set<GrammarNonDeterminismMessage> nonDeterminismList = new LinkedHashSet<GrammarNonDeterminismMessage>();

	public IStatus build(IProgressMonitor monitor, IGrammar grammar) {
		IStatus status = null;
		try {
			configureErrorManager(new NonDeterminismListener());

			this.g = doCreateGrammar(grammar);

			doProcessGrammar(monitor, g, grammar.getSource());
			String lexerContent = null;
			if (grammar.isCombinedGrammar()) {
				lexerContent = g.getLexerGrammar();
			} else if (grammar.isParserGrammar()) {
				IPath libraryPath = AntlrBuildUnitRepository.create(grammar)
						.getAbsoluteLibraryPath();
				libraryPath = libraryPath.append(
						grammar.getOption("tokenVocab")).addFileExtension("g");
				File lexerFile = libraryPath.toFile();
				if (lexerFile.exists()) {
					lexerContent = AntlrCoreHelper.loadFileAsString(lexerFile);
				}
			}
			if (lexerContent != null) {
				this.lg = doCreateLexerGrammar(monitor, g);
				doProcessGrammar(monitor, lg, lexerContent);
			}
//			Nondeterminism nondeterminism = new Nondeterminism();
//			for (GrammarNonDeterminismMessage message : nonDeterminismList) {
//				System.out.println(nondeterminism.findAltPaths(message));
//			}
			status = Status.OK_STATUS;
		} catch (Exception ex) {
			status = new Status(IStatus.ERROR, AntlrCore.PLUGIN_ID,
					"Could not create gramnar", ex);
			AntlrCore.error(new CoreException(status));
		}
		return status;
	}

	public Grammar getGrammar() {
		return g;
	}

	public Grammar getLexerGrammar() {
		return lg;
	}

	private Grammar doCreateGrammar(IGrammar grammar) {
		CompositeGrammar composite = new CompositeGrammar();
		String grammarFileName = grammar.getAbsoluteFile().toOSString();

		// the tool object is used for composite grammars and for parser/tree
		// grammars
		String lib = null;
		if (grammar.isParserGrammar()) {
			lib = AntlrBuildUnitRepository.create(grammar)
					.getAbsoluteLibraryPath().toOSString();
		} else {
			lib = grammar.getAbsoluteFolder().toOSString();
		}
		Tool tool = new Tool(new String[] { "-lib", lib,
				grammar.getAbsoluteFile().toOSString() });

		Grammar g = new Grammar(tool, grammarFileName, composite);
		composite.setDelegationRoot(g);

		return g;
	}

	private Grammar doCreateLexerGrammar(IProgressMonitor monitor, Grammar g)
			throws RecognitionException {
		Grammar lexerGrammar = new Grammar();
		lexerGrammar.tool = g.tool;
		lexerGrammar.implicitLexer = true;
		lexerGrammar.setFileName(g.name + "__.g");
		lexerGrammar.importTokenVocabulary(g);
		return lexerGrammar;
	}

	private void doProcessGrammar(IProgressMonitor monitor, Grammar g,
			char[] content) throws RecognitionException {
		doProcessGrammar(monitor, g, new CharArrayReader(content));
	}

	private void doProcessGrammar(IProgressMonitor monitor, Grammar g,
			String content) throws RecognitionException {
		doProcessGrammar(monitor, g, new StringReader(content));
	}

	private void doProcessGrammar(IProgressMonitor monitor, Grammar g,
			Reader reader) throws RecognitionException {
		try {
			monitor.subTask("Parsing and building AST");
			g.parseAndBuildAST(reader);
			monitor.worked(1);

			monitor.subTask("Assiging token types");
			g.composite.assignTokenTypes();
			monitor.worked(1);

			monitor.subTask("Defining symbols");
			g.composite.defineGrammarSymbols();
			monitor.worked(1);

			monitor.subTask("Creating NFAs");
			g.composite.createNFAs();
			monitor.worked(1);

			// Build NFAs from the grammar AST
			monitor.subTask("Building NFAs");
			g.buildNFA();
			monitor.worked(1);

			// Create the DFA predictors for each decision
			g.createLookaheadDFAs();
			monitor.worked(1);

			// OPTIMIZE DFA
			monitor.subTask("Building DFAs");
			DFAOptimizer optimizer = new DFAOptimizer(g);
			optimizer.optimize();
			monitor.worked(1);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void configureErrorManager() {
		configureErrorManager(DO_NOT_REPORT_ERRORS);
	}

	public static void configureErrorManager(ANTLRErrorListener errorListener) {
		try {
			ErrorManager.resetErrorState();
			ErrorManager.setErrorListener(errorListener);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
