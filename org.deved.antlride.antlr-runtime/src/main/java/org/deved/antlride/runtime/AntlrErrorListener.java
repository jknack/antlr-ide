package org.deved.antlride.runtime;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import antlr.FileLineFormatter;
import antlr.MismatchedTokenException;
import antlr.NoViableAltException;
import antlr.RecognitionException;
import antlr.Token;

import antlr.TokenStreamRecognitionException;

import org.antlr.tool.ANTLRErrorListener;
import org.antlr.tool.ErrorManager;
import org.antlr.tool.GrammarSemanticsMessage;
import org.antlr.tool.GrammarSyntaxMessage;
import org.antlr.tool.Message;
import org.antlr.tool.ToolMessage;

public class AntlrErrorListener extends FileLineFormatter implements
		ANTLRErrorListener {

	private static final int ERROR = Integer.parseInt(System.getProperty(
			"gERROR", "0"));

	private static final int WARNING = Integer.parseInt(System.getProperty(
			"gWARNING", "1"));

	private static final String PATTERN = System.getProperty("gPATTERN");

	private static final String LINE_ESCAPE = System
			.getProperty("gLINE_ESCAPE");

	private static final String CRETURNS_ESCAPE = System
			.getProperty("gCREUTRNS_ESCAPE");

	private static final boolean includeStacktrace = Boolean
			.parseBoolean(System.getProperty("gINCLUDE_STACKTRACE", "false"));

	private static final String FILE = System.getProperty("gFILE");

	/**
	 * Since 3.4 ANTLR use org.antlr.runtime.Token instead of
	 * antlr.Token. This class acts like a dynamic binding between the two.
	 */
	private static class DynamicToken {
		private Object token;

		public DynamicToken(Object token) {
			this.token = token;
		}

		public Object invokeMethod(String name) {
			try {
				Method method = token.getClass().getMethod(name, new Class[0]);
				return method.invoke(token, new Object[0]);
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}

		public int getLine() {
			return ((Integer) invokeMethod("getLine")).intValue();
		}

		public int getColumn() {
			try {
				return ((Integer) invokeMethod("getColumn")).intValue();
			} catch (IllegalStateException ex) {
				return ((Integer) invokeMethod("getCharPositionInLine"))
						.intValue();
			}
		}

		public String getText() {
			return ((String) invokeMethod("getText"));
		}

		public static DynamicToken offendingToken(Object source) {
			try {
				Field field = source.getClass().getDeclaredField(
						"offendingToken");
				return new DynamicToken(field.get(source));
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
	}

	public AntlrErrorListener() {
	}

	public void info(String message) {
		System.out.println(escapeNL(message));
	}

	public void warning(Message re) {
		message(re);
	}

	public void error(Message re) {
		message(re);
	}

	public void error(ToolMessage message) {
		message(message);
	}

	private void message(Message re) {
		String message = ErrorManager.getMessageType(re.msgID);
		if (message.startsWith("error")) {
			report(ERROR, re);
		} else {
			report(WARNING, re);
		}
	}

	private void report(int severity, Message message) {
		// first call Message.toString, toString method update location info
		String strMessage = getMessage(message, message.msgID);
		// retrive location
		int line = message.line > 0 ? message.line : -1;
		int column = message.column > 0 ? message.column : 0;
		int start = 0;
		int end = 0;
		int offset = 0;
		int id = message.msgID;
		DynamicToken token = extractToken(message);
		if (token != null) {
			line = token.getLine();
			column = token.getColumn();
			if (token.getText() != null)
				offset = token.getText().length();
		}
		report(severity, id, line, column, start, end, offset, strMessage);
	}

	private String escapeNL(String s) {
		return s == null ? "" : s.replaceAll("\n", LINE_ESCAPE).replaceAll(
				"\r", CRETURNS_ESCAPE);
	}

	private void report(int severity, int id, int line, int column, int start,
			int end, int offset, String strMessage) {
		// encode new line
		System.out.println(new StringBuffer().append(PATTERN).append(severity)
				.append(PATTERN).append(id).append(PATTERN).append(line)
				.append(PATTERN).append(column).append(PATTERN).append(start)
				.append(PATTERN).append(end).append(PATTERN).append(offset)
				.append(PATTERN).append(escapeNL(strMessage)).toString());
	}

	private DynamicToken extractToken(Message message) {
		if (message.e instanceof TokenStreamRecognitionException) {
			return extractToken(((TokenStreamRecognitionException) message.e).recog);
		} else if (message instanceof GrammarSyntaxMessage) {
			return DynamicToken.offendingToken(message);
		} else if (message instanceof GrammarSemanticsMessage) {
			return DynamicToken.offendingToken(message);
		}
		return null;
	}

	private DynamicToken extractToken(RecognitionException rex) {
		if (rex == null)
			return null;
		if (rex instanceof NoViableAltException) {
			return new DynamicToken(((NoViableAltException) rex).token);
		}
		if (rex instanceof MismatchedTokenException) {
			return new DynamicToken(((MismatchedTokenException) rex).token);
		}
		final int line = rex.line;
		if (line > 0) {
			int column = rex.column;
			Token t = new Token();
			t.setColumn(column);
			t.setLine(line);
			return new DynamicToken(t);
		}
		return null;
	}

	private String getMessage(Message message, int msgID) {
		String strMessage = message.toString();

//		if (message instanceof ToolMessage) {
//			ToolMessage toolMessage = (ToolMessage) message;
//			if (toolMessage.e != null) {
//				StringTemplate messageTemplate = toolMessage
//						.getMessageTemplate();
//				if (toolMessage.arg != null) {
//					messageTemplate.setAttribute("arg", toolMessage.arg);
//				}
//				if (toolMessage.arg2 != null) {
//					messageTemplate.setAttribute("arg2", toolMessage.arg2);
//				}
//				messageTemplate.setAttribute("exception", toolMessage.e);
//				if (includeStacktrace) {
//					messageTemplate.setAttribute("stackTrace",
//							toolMessage.e.getStackTrace());
//				}
//				strMessage = toolMessage.toString(messageTemplate);
//			} else {
//				if (msgID == ErrorManager.MSG_FILE_AND_GRAMMAR_NAME_DIFFER) {
//					toolMessage.arg2 = FILE;
//				} else if (msgID == ErrorManager.MSG_NO_RULES) {
//					toolMessage.arg = FILE;
//				}
//				strMessage = message.toString();
//			}
//		} else {
//			strMessage = message.toString();
//		}
//
//		System.out.println(strMessage);
		return strMessage.trim();
	}

	public String getFormatString(String fileName, int line, int column) {
		return "";
	}
}
