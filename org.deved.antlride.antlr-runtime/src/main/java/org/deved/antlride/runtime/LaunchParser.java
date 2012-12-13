package org.deved.antlride.runtime;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.debug.BlankDebugEventListener;
import org.antlr.runtime.debug.DebugEventListener;
import org.antlr.runtime.debug.DebugEventSocketProxy;
import org.antlr.runtime.debug.DebugParser;
import org.antlr.runtime.debug.DebugTokenStream;
import org.antlr.runtime.debug.RemoteDebugEventSocketListener;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

public class LaunchParser implements Runnable {

	private class Debugger extends DebugEventSocketProxy {
		private BaseRecognizer fRecognizer;

		private int fDecision = 0;

		private java.util.Collection msgs = new java.util.ArrayList();

		public Debugger(BaseRecognizer recognizer, int port, TreeAdaptor adaptor) {
			super(recognizer, port, adaptor);
			this.fRecognizer = recognizer;
		}

		public void enterDecision(int decisionNumber) {
			fDecision++;
			super.enterDecision(decisionNumber);
		}

		public void exitDecision(int decisionNumber) {
			fDecision--;
			super.exitDecision(decisionNumber);
		}

		public void recognitionException(RecognitionException e) {
			StringBuffer buf = new StringBuffer(50);
			buf.append("exception\t");
			String className = e.getClass().getName();
			className = className.substring(className.lastIndexOf('.') + 1);
			buf.append(className);
			// dump only the data common to all exceptions for now
			buf.append("\t");
			buf.append(e.index);
			buf.append("\t");
			buf.append(e.line);
			buf.append("\t");
			buf.append(e.charPositionInLine);
			buf.append("\t\"");
			String msg = getErrMessage(e);
			if (fDecision == 0 && !msgs.contains(msg)) {
				System.err.println(msg);
				msgs.add(msg);
			}
			buf.append(msg);
			transmit(buf.toString());
		}
		
		protected String getErrMessage(RecognitionException e) {
			String hdr = fRecognizer.getErrorHeader(e);
			String msg = fRecognizer.getErrorMessage(e, fRecognizer
					.getTokenNames());
			String errmsg = hdr + " " + msg;
			return escapeNewlines(errmsg);
		}

		public void setTreeAdaptorHook(TreeAdaptor adaptor) {
			// ANTLR 3.0.x compatible
			this.adaptor = adaptor;
		}
	}

	private Map args = new HashMap();

	public LaunchParser(String[] args) {
		for (int i = 0; i < args.length; i = i + 2) {
			String key = args[i];
			String val = args[i + 1];
			this.args.put(key, val);
		}
	}

	public void launch() throws Exception {
		// ++++++++++++++++++++++++++
		// create lexer
		// ++++++++++++++++++++++++++
		String testCase = (String) args.get("-testCase");
		ANTLRFileStream stream = new ANTLRFileStream(testCase);
		String lexerClassName = (String) args.get("-lexer");
		Class lexerClass = Class.forName(lexerClassName);
		Constructor lexerConstructor = lexerClass
				.getDeclaredConstructor(new Class[] { CharStream.class });
		TokenSource lexer = (TokenSource) lexerConstructor
				.newInstance(new Object[] { stream });
		// ++++++++++++++++++++++++++
		// create token stream
		// ++++++++++++++++++++++++++
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		// ++++++++++++++++++++++++++
		// create parser
		// ++++++++++++++++++++++++++
		String parserClassName = (String) args.get("-parser");
		Class parserClass = Class.forName(parserClassName);
		Constructor parserConstructor = parserClass
				.getDeclaredConstructor(new Class[] { TokenStream.class,
						DebugEventListener.class });
		Object parser = parserConstructor.newInstance(new Object[] { tokens,
				null });
		// start the debugger
		startDebugger(tokens, parser);
		// ++++++++++++++++++++++++++
		// rule name
		// ++++++++++++++++++++++++++
		String ruleName = (String) args.get("-ruleName");

		// invoke rule
		invokeRule(ruleName, lexer, parser);
	}

	private Debugger startDebugger(TokenStream input, Object parser) {
		int port = Integer.parseInt((String) args.get("-port"));
		DebugParser debugParser = (DebugParser) parser;
		Debugger debugger = new Debugger(debugParser, port, null);
		TreeAdaptor adap = new CommonTreeAdaptor();
		debugParser.setDebugListener(debugger);
		debugParser.setTokenStream(new DebugTokenStream(input, debugger));
		try {
			debugger.handshake();
		} catch (IOException ioe) {
			debugParser.reportError(ioe);
		}
		// ++++++ parser deps
		setTreeAdaptor(parser, adap);
		Field[] fields = parser.getClass().getDeclaredFields();
		if(fields != null) {
			for (int i = 0; i < fields.length; i++) {
				Class parserClass = fields[i].getType();
				if(DebugParser.class.isAssignableFrom(parserClass)) {
					try {
						DebugParser delegate = (DebugParser) fields[i].get(parser);
						delegate.setDebugListener(debugger);
						setTreeAdaptor(delegate, adap);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		// ++++++ debugger deps
		try {
			// ANTLR 3.0.x compatible
			java.lang.reflect.Method setTreeAdaptor = findMethod(debugger
					.getClass(), "setTreeAdaptor",
					new Class[] { TreeAdaptor.class });
			setTreeAdaptor.invoke(debugger, new Object[] { adap });
		} catch (Exception ex) {
			debugger.setTreeAdaptorHook(adap);
		}
		return debugger;
	}

	private void setTreeAdaptor(Object parser, TreeAdaptor adap) {
		try {
			// ANTLR 3.0.x compatible
			java.lang.reflect.Method setTreeAdaptor = findMethod(parser
					.getClass(), "setTreeAdaptor",
					new Class[] { TreeAdaptor.class });
			setTreeAdaptor.invoke(parser, new Object[] { adap });
		} catch (Exception ex) {
		}
	}

	private void invokeRule(String ruleName, Object lexer, Object parser)
			throws Exception {
		Class clazz = null;
		Object instance = null;
		if (isLexerRule(ruleName)) {
			instance = lexer;
			clazz = lexer.getClass();
			ruleName = "m" + ruleName;
		} else {
			instance = parser;
			clazz = parser.getClass();
		}
		java.lang.reflect.Method rule = findMethod(clazz, ruleName,
				new Class[0]);
		rule.invoke(instance, null);
	}

	private boolean isLexerRule(String ruleName) {
		return Character.isUpperCase(ruleName.charAt(0));
	}

	private java.lang.reflect.Method findMethod(Class clazz, String methodName,
			Class[] params) {
		try {
			while (clazz != null) {
				java.lang.reflect.Method m = clazz.getDeclaredMethod(
						methodName, params);
				if (m != null)
					return m;
				clazz = clazz.getSuperclass();
			}
		} catch (Exception ex) {
			// shhhhh
		}
		throw new IllegalArgumentException("Invalid method name: \""
				+ methodName + "\". Execution was cancelled");
	}

	public static void main(String[] args) throws Exception {
		LaunchParser parser = new LaunchParser(args);
		parser.launch();
	}

	public void run() {
		try {
			Thread.sleep(1000);
			DebugEventListener del = new BlankDebugEventListener();
			int port = Integer.parseInt((String) args.get("-port"));
			RemoteDebugEventSocketListener rdesl = new RemoteDebugEventSocketListener(
					del, "localhost", port);
			rdesl.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
