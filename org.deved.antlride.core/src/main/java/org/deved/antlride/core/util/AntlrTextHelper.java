/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.util;

import java.util.regex.Matcher;

import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IGrammarAction;

public final class AntlrTextHelper {
	public static final char NL = '\n';

	public static final String EMPTY_STRING = "";

	public static String getJavaPackage(IGrammar grammar) {
		String packageName = null;
		if (grammar.isCombinedGrammar() || grammar.isParserGrammar()
				|| grammar.isTreeParserGrammar()) {
			packageName = extractJavaPackage(grammar, "header");
			if (packageName == null) {
				String scope = grammar.isTreeParserGrammar() ? "treeparser"
						: "parser";
				packageName = extractJavaPackage(grammar, scope + "::header");
			}
		}
		return packageName;
	}
	
	public static String parseDoc(String text) {
		if (text != null) {
			text = text.replace("/**", "").replace("*/", "").replace("*", "");
		}
		return text;
	}

	public static String trimBracket(String text) {
		try {
			String s = text;
			if (s.startsWith("[")) {
				s = s.substring(1);
			}
			if (s.endsWith("]")) {
				s = s.substring(0, s.length() - 1);
			}
			return s;
		} catch (StringIndexOutOfBoundsException sio) {
			return text;
		}
	}

	public static String unEscapeNewlines(String txt) {
		// this unescape is slow but easy to understand
		txt = txt.replaceAll("%0A", "\n") // unescape \n
				.replaceAll("%0D", "\r") // unescape \r
				.replaceAll("%25", "%"); // undo escaped escape chars
		return txt;
	}

	public static String getLexerJavaPackage(IGrammar grammar) {
		String packageName = null;
		if (grammar.isCombinedGrammar()) {
			return extractJavaPackage(grammar, "lexer::header");
		} else if (grammar.isParserGrammar() || grammar.isTreeParserGrammar()) {
			IGrammar tokenGrammar = grammar.getTokenVocab();
			if (tokenGrammar != null) {
				packageName = extractJavaPackage(tokenGrammar, "header");
				if (packageName == null) {
					packageName = extractJavaPackage(tokenGrammar,
							"lexer::header");
				}
			}
		} else if (grammar.isLexerGrammar()) {
			packageName = extractJavaPackage(grammar, "header");
			if (packageName == null) {
				packageName = extractJavaPackage(grammar, "lexer::header");
			}
		}
		return packageName;
	}

	private static String extractJavaPackage(IGrammar grammar, String action) {
		IGrammarAction header = grammar.findAction(action);
		String packageName = null;
		if (header != null) {
			String headerText = header.toString();
			Matcher matcher = AntlrConstants.ANTLR_JAVA_PACKAGE_PATTERN
					.matcher(headerText);
			if (matcher.find()) {
				packageName = matcher.group(1);
			}
		}
		return packageName;
	}

	public static String removeComments(String input) {
		int start = input.indexOf("/*");
		String pattern = null;
		int end = -1;
		if (start == -1) {
			start = input.indexOf("//", start);
			pattern = "\n";
		} else {
			pattern = "*/";
		}
		if (start != -1 && pattern != null) {
			end = input.indexOf(pattern, start);
		}
		if (end != -1) {
			StringBuilder buffer = new StringBuilder(input.length());
			int i = 0;
			end += pattern.length();
			while (i < input.length()) {
				if (i >= start && i < end) {
					buffer.append(' ');
				} else {
					buffer.append(input.charAt(i));
				}
				i++;
			}
			return buffer.toString();
		}
		return input;
	}

	public static int getOffsetAtLine(String text, int line) {
		return getOffsetAtLine(text.toCharArray(), line);
	}

	public static int getLineAtOffset(String text, int offset) {
//		ISourceLineTracker lineTracker = TextUtils.createLineTracker(text);
//		return lineTracker.getLineNumberOfOffset(offset) + 1;
		 int line = 1;
		 for (int i = 0; i < offset; i++) {
		 if (text.charAt(i) == '\n') {
		 line++;
		 }
		 }
		 return line;
	}

	public static int getOffsetAtLine(char[] text, int line) {
//		ISourceLineTracker lineTracker = TextUtils.createLineTracker(text);
//		return lineTracker.getLineInformation(line).getOffset();
		int offset = 0;
		if (line == 1)
			return offset;
		int lineIndex = 0;
		line--;
		for (int i = 0; i < text.length; i++) {
			char ch = text[i];
			offset++;
			if (ch == '\n') {
				lineIndex++;
				if (lineIndex == line)
					break;
			}
		}
		return offset;
	}

	public static String findWord(char[] text, int offset, boolean completeWord) {
		try {
			int start = -2;
			int end = -1;

			int pos = offset;
			char c;

			while (pos >= 0) {
				c = text[pos];
				if (!isAntlrIdentifierPart(c))
					break;
				--pos;
			}
			start = pos;

			pos = offset;
			if (completeWord)
				while (pos < text.length) {
					c = text[pos];
					if (!isAntlrIdentifierPart(c))
						break;
					++pos;
				}
			end = pos;

			if (start >= -1 && end > -1) {
				if (start == offset && end == offset) {
					return "";
				} else if (start == offset) {
					return new String(text, start, end - start);
				} else {
					int from = start + 1;
					int to = end - start;
					if (completeWord)
						to--;
					if (to == 0)
						to++;
					return new String(text, from, to);
				}
			}
		} catch (StringIndexOutOfBoundsException ex) {

		}
		return null;
	}

	public static boolean isGrammarName(String grammarName) {
		if (grammarName == null || grammarName.length() == 0)
			return false;
		for (int i = 0; i < grammarName.length(); i++) {
			char ch = grammarName.charAt(i);
			if (i == 0) {
				if (!isLetter(ch)) {
					return false;
				}
				continue;
			}
			if (!isLetterOrDigit(ch) && ch != '_') {
				return false;
			}
		}
		return true;
	}

	public static boolean isAntlrIdentifierStart(char ch) {
		return isLetter(ch);
	}

	public static boolean isAntlrIdentifierPart(char ch) {
		return isLetterOrDigit(ch) || ch == '_';
	}

	public static boolean isLetterOrDigit(int ch) {
		return isLetter(ch) || isDigit(ch);
	}

	public static boolean isLetter(int ch) {
		return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
	}

	public static boolean isDigit(int ch) {
		return (ch >= '0' && ch <= '9');
	}
}
