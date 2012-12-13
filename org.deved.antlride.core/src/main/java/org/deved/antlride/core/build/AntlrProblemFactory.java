/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.build;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.tool.ErrorManager;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IImport;
import org.deved.antlride.core.model.IOption;
import org.deved.antlride.core.model.IOptionValue;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.ISourceElement;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.compiler.problem.ProblemSeverity;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.builder.ISourceLineTracker;
import org.eclipse.dltk.utils.TextUtils;

public class AntlrProblemFactory {
	/**
	 * Extract the raw message error reported by ANTLR from the message package
	 * 
	 */
	private static interface RawMessageStrategy {
		String getRawMessage(String[] message);
	}

	private static class DefaultRawMessageStrategy implements
			RawMessageStrategy {

		public String getRawMessage(String[] message) {
			return message[MESSAGE].replace("\r", "").replace("\n\n", "\n");
		}

	}

	private static interface RelativePathStrategy {
		String getRelativePath(AntlrBuildUnit unit);
	}

	private static class DefaultRelativePathStrategy implements
			RelativePathStrategy {
		public String getRelativePath(AntlrBuildUnit unit) {
			return unit.getFolderPath().toString();
		}
	}

	private static class CannotFindTokenFileRelativePathStrategy implements
			RelativePathStrategy {
		public String getRelativePath(AntlrBuildUnit unit) {
			return unit.getLibraryPath().toString();
		}
	}

	private static interface ShortMessageStrategy {
		String getShortMessage(String rawMessage);
	}

	private static class DefaultShortMessageStrategy implements
			ShortMessageStrategy {

		private int id;

		public DefaultShortMessageStrategy(int id) {
			this.id = id;
		}

		public String getShortMessage(String rawMessage) {
			String shortMessage = null;
			try {
				Matcher matcher = SHORT_MESSAGE_PATTERN.matcher(rawMessage);
				// match for .g:d:d:
				if (matcher.find()) {
					StringBuilder builder = new StringBuilder();
					builder.append("(").append(id).append("): ");
					builder.append(rawMessage.substring(matcher.end()).trim());
					shortMessage = builder.toString();
				} else {
					// the file name is not reported
					// remove the error type prefix
					if (rawMessage.startsWith("error")) {
						shortMessage = rawMessage.substring("error".length());
					} else if (rawMessage.startsWith("warning")) {
						shortMessage = rawMessage.substring("warning".length());
					} else {
						shortMessage = rawMessage;
					}
				}
			} catch (Exception ex) {
				// if any problem happen, use the raw message
				shortMessage = rawMessage;
			}
			return shortMessage.trim();
		}
	}

	private static interface ProblemFileStrategy {
		IPath getOriginatingFilePath(String rawMessage, AntlrBuildUnit unit);
	}

	private static class DefaultProblemFileStrategy implements
			ProblemFileStrategy {

		public IPath getOriginatingFilePath(String rawMessage,
				AntlrBuildUnit unit) {
			IPath originatingFilePath = unit.getPath();
			try {
				Matcher matcher = PATH_PATTERN.matcher(rawMessage);
				String path1 = originatingFilePath.toString();
				if (matcher.find()) {
					String path2 = matcher.group();
					path2 = path2.replaceAll(":\\d+:\\d+:", "");
					if (!path1.equals(path2)) {
						originatingFilePath = new Path(path2);
					}
				} else {
					Matcher smm = SHORT_MESSAGE_PATTERN.matcher(rawMessage);
					if (smm.find()) {
						Matcher mpm = MSG_PREFIX_PATTERN.matcher(rawMessage);
						if (mpm.find()) {
							int start = mpm.start() + mpm.group().length();
							int end = smm.start();
							String path2 = rawMessage.substring(start, end)
									.trim()
									+ ".g";
							if (!path1.equals(path2)) {
								originatingFilePath = new Path(path2);
								if (originatingFilePath.segmentCount() == 1) {
									originatingFilePath = unit.getLibraryPath()
											.append(originatingFilePath);
								}
							}
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return originatingFilePath;
		}
	}

	private static interface ProblemPostProcessor {

		int start();

		int end();

		int line();

		void process(AntlrBuildUnit unit, char[] source,
				ISourceLineTracker lineTracker, String rawMessage, int line,
				int start, int end);
	}

	private static class DefaultProblemPostProcessor implements
			ProblemPostProcessor {

		private final Collection<Character> IGNORED_CHARS = Arrays.asList('{',
				'}', '?', ';', '*', '+', ':', '(', ')', '[', ']', '|', '^',
				'.', '!');

		private int line;

		private int start;

		private int end;

		public final void process(AntlrBuildUnit unit, char[] source,
				ISourceLineTracker lineTracker, String rawMessage, int line,
				int start, int end) {
			this.start = next(source, start);
			this.end = prev(source, end) + 1;
			if (this.end < this.start) {
				// restore the previous offsets
				this.start = start;
				this.end = end;
				if (Character.isWhitespace(source[end])) {
					this.end--;
				}
			}
			this.line = line;
			doProcess(unit, source, lineTracker, rawMessage, this.line,
					this.start, this.end);
		}

		protected void doProcess(AntlrBuildUnit unit, char[] source,
				ISourceLineTracker lineTracker, String rawMessage, int line,
				int start, int end) {

		}

		private int next(char[] source, int offset) {
			int offs = offset;
			try {
				while (offs < source.length && ignoreChar(source[offs])) {
					offs++;
				}
				return offs;
			} catch (Exception ex) {
				ex.printStackTrace();
				return offset;
			}
		}

		private int prev(char[] source, int offset) {
			int offs = offset;
			try {
				while ((offs >= 0 && offs < source.length)
						&& ignoreChar(source[offs])) {
					offs--;
				}
				return offs;
			} catch (Exception ex) {
				ex.printStackTrace();
				return offset;
			}
		}

		private boolean ignoreChar(char ch) {
			if (Character.isWhitespace(ch))
				return true;
			return IGNORED_CHARS.contains(ch);
		}

		public int start() {
			return start;
		}

		public int end() {
			return end;
		}

		public int line() {
			return line;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public void setLine(int line) {
			this.line = line;
		}

		public void setStart(int start) {
			this.start = start;
		}
	}

	private static class LeftRecursionCyclesPostProcessor extends
			DefaultProblemPostProcessor {
		@Override
		public void doProcess(AntlrBuildUnit unit, char[] source,
				ISourceLineTracker lineTracker, String rawMessage, int line,
				int start, int end) {
			try {
				// The following sets of rules are mutually left-recursive
				// [rulename]
				int bracketIndex = rawMessage.lastIndexOf('[');
				String ruleName = rawMessage.substring(bracketIndex + 1,
						rawMessage.indexOf(']', bracketIndex));
				IGrammar grammar = unit.getGrammar();
				IRule rule = grammar.findRule(ruleName);
				setStart(rule.getName().sourceStart());
				setEnd(rule.getName().sourceEnd());
				setLine(lineTracker.getLineNumberOfOffset(start()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private static class CannotFindTokenFilePostProcessor extends
			DefaultProblemPostProcessor {
		@Override
		public void doProcess(AntlrBuildUnit unit, char[] source,
				ISourceLineTracker lineTracker, String rawMessage, int line,
				int start, int end) {
			try {
				// the *.tokens reference in a parser/tree grammar doesn't
				// exists. adjust the offset to the declaration
				IGrammar grammar = unit.getGrammar();
				IOption tokenVocab = grammar.findOption("tokenVocab");
				IOptionValue value = tokenVocab.getValue();
				setStart(value.sourceStart());
				setEnd(value.sourceEnd());
				setLine(lineTracker.getLineNumberOfOffset(start()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private static class FileAndGrammarNameDifferPostProcessor extends
			DefaultProblemPostProcessor {
		@Override
		public void doProcess(AntlrBuildUnit unit, char[] source,
				ISourceLineTracker lineTracker, String rawMessage, int line,
				int start, int end) {
			try {
				IGrammar grammar = unit.getGrammar();
				ISourceElement name = grammar.getName();
				setStart(name.sourceStart());
				setEnd(name.sourceEnd());
				setLine(lineTracker.getLineNumberOfOffset(start()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private static class MissingCodeGenPostProcessor extends
			DefaultProblemPostProcessor {
		@Override
		public void doProcess(AntlrBuildUnit unit, char[] source,
				ISourceLineTracker lineTracker, String rawMessage, int line,
				int start, int end) {
			try {
				IGrammar grammar = unit.getGrammar();
				IOption language = grammar.findOption("language");
				IOptionValue value = language.getValue();
				setStart(value.sourceStart());
				setEnd(value.sourceEnd());
				setLine(lineTracker.getLineNumberOfOffset(start()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private static class NonRegularDecisionPostProcessor extends
			DefaultProblemPostProcessor {
		private static final String pattern = "[fatal] rule ";

		@Override
		public void doProcess(AntlrBuildUnit unit, char[] source,
				ISourceLineTracker lineTracker, String rawMessage, int line,
				int start, int end) {
			try {
				int istart = rawMessage.indexOf(pattern) + pattern.length();
				int iend = rawMessage.indexOf(" ", istart);
				String ruleName = rawMessage.substring(istart, iend);
				IGrammar grammar = unit.getGrammar();
				IRule rule = grammar.findRule(ruleName);
				ISourceElement name = rule.getName();
				setStart(name.sourceStart());
				setEnd(name.sourceEnd());
				setLine(lineTracker.getLineNumberOfOffset(start()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private static class TokenNonDeterminismPostProcessor extends
			DefaultProblemPostProcessor {
		@Override
		public void doProcess(AntlrBuildUnit unit, char[] source,
				ISourceLineTracker lineTracker, String rawMessage, int line,
				int start, int end) {
			try {
				String ruleName = new String(source, start, end - start);
				int colonIdx = ruleName.indexOf(':');
				if (colonIdx > 0) {
					ruleName = ruleName.substring(0, colonIdx);
				}
				IGrammar grammar = unit.getGrammar();
				IRule rule = grammar.findRule(ruleName.trim());
				ISourceElement name = rule.getName();
				setStart(name.sourceStart());
				setEnd(name.sourceEnd());
				setLine(lineTracker.getLineNumberOfOffset(start()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private static class CannotOpenFilePostProcessor extends
			DefaultProblemPostProcessor {
		private static final String pattern = "cannot find or open file: ";

		@Override
		public void doProcess(AntlrBuildUnit unit, char[] source,
				ISourceLineTracker lineTracker, String rawMessage, int line,
				int start, int end) {
			try {
				int istart = rawMessage.indexOf(pattern) + pattern.length();
				int iend = rawMessage.indexOf(';', istart);
				if (iend == -1) {
					iend = rawMessage.length();
				}
				String filename = rawMessage.substring(istart, iend).trim()
						.replace(".g", "");
				IGrammar grammar = unit.getGrammar();
				IImport imp = grammar.findImport(filename);
				ISourceElement name = imp.getImportedGrammar();
				setStart(name.sourceStart());
				setEnd(name.sourceEnd());
				setLine(lineTracker.getLineNumberOfOffset(start()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// private static class NoTokenDefinitionPostProcessor extends
	// DefaultProblemPostProcessor {
	// @Override
	// public void doProcess(AntlrBuildUnit unit, char[] source,
	// ISourceLineTracker lineTracker, String rawMessage, int line,
	// int start, int end) {
	// try {
	// //"no lexer rule corresponding to token: <arg>"
	// String tokenName = rawMessage.substring(rawMessage.lastIndexOf(':') +
	// 1).trim();
	// String content = new String(source, 0, source.length);
	// setStart( content.indexOf(tokenName, start) );
	// setEnd(start() + tokenName.length());
	// setLine(lineTracker.getLineNumberOfOffset(start()));
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// }
	// }

	private static Map<Integer, RawMessageStrategy> rawMessageMap;

	private static Map<Integer, RelativePathStrategy> relativePathMap;

	private static Map<Integer, ProblemPostProcessor> problemPostProcessors;

	private static final int SEVERITY = 1;

	private static final int ID = 2;

	private static final int LINE = 3;

	private static final int COLUMN = 4;

	private static final int START = 5;

	private static final int END = 6;

	private static final int OFF_SET = 7;

	private static final int MESSAGE = 8;

	private static final Pattern SHORT_MESSAGE_PATTERN = Pattern
			.compile("\\.g:\\d+:\\d+:");

	private static final Pattern PATH_PATTERN = Pattern
			.compile("/.*(/.*)*\\.g:\\d+:\\d+:");

	private static final Pattern MSG_PREFIX_PATTERN = Pattern
			.compile("(warning|error)\\(\\d+\\):");

	private static final Integer DEFAULT_STRATEGY = new Integer(
			Integer.MIN_VALUE);

	private static Map<Integer, RawMessageStrategy> getRawMessageMap() {
		if (rawMessageMap == null) {
			rawMessageMap = new HashMap<Integer, RawMessageStrategy>() {
				@Override
				public RawMessageStrategy get(Object key) {
					RawMessageStrategy rawMessageStrategy = super.get(key);
					return rawMessageStrategy == null ? super
							.get(DEFAULT_STRATEGY) : rawMessageStrategy;
				}

				private static final long serialVersionUID = 1L;
				{
					put(DEFAULT_STRATEGY, new DefaultRawMessageStrategy());
				}
			};
		}
		return rawMessageMap;
	}

	private static Map<Integer, ProblemPostProcessor> getProblemPostProcessors() {
		if (problemPostProcessors == null) {
			problemPostProcessors = new HashMap<Integer, ProblemPostProcessor>() {
				@Override
				public ProblemPostProcessor get(Object key) {
					ProblemPostProcessor strategy = super.get(key);
					return strategy == null ? super.get(DEFAULT_STRATEGY)
							: strategy;
				}

				private static final long serialVersionUID = 1L;
				{
					put(DEFAULT_STRATEGY, new DefaultProblemPostProcessor());
					put(ErrorManager.MSG_LEFT_RECURSION_CYCLES,
							new LeftRecursionCyclesPostProcessor());
					put(ErrorManager.MSG_CANNOT_FIND_TOKENS_FILE,
							new CannotFindTokenFilePostProcessor());
					put(ErrorManager.MSG_FILE_AND_GRAMMAR_NAME_DIFFER,
							new FileAndGrammarNameDifferPostProcessor());
					put(ErrorManager.MSG_MISSING_CODE_GEN_TEMPLATES,
							new MissingCodeGenPostProcessor());
					put(ErrorManager.MSG_NONREGULAR_DECISION,
							new NonRegularDecisionPostProcessor());
					put(ErrorManager.MSG_TOKEN_NONDETERMINISM,
							new TokenNonDeterminismPostProcessor());
					put(ErrorManager.MSG_CANNOT_OPEN_FILE,
							new CannotOpenFilePostProcessor());
					// put(ErrorManager.MSG_NO_TOKEN_DEFINITION,
					// new NoTokenDefinitionPostProcessor());
				}
			};
		}
		return problemPostProcessors;
	}

	private static Map<Integer, RelativePathStrategy> getRelativePathMap() {
		if (relativePathMap == null) {
			relativePathMap = new HashMap<Integer, RelativePathStrategy>() {
				@Override
				public RelativePathStrategy get(Object key) {
					RelativePathStrategy strategy = super.get(key);
					return strategy == null ? super.get(DEFAULT_STRATEGY)
							: strategy;
				}

				private static final long serialVersionUID = 1L;
				{
					put(ErrorManager.MSG_CANNOT_FIND_TOKENS_FILE,
							new CannotFindTokenFileRelativePathStrategy());

					put(DEFAULT_STRATEGY, new DefaultRelativePathStrategy());
				}
			};
		}
		return relativePathMap;
	}

	public static AntlrProblem create(AntlrBuildUnit unit, String[] message) {
		ProblemSeverity severity = ProblemSeverity.values()[Integer.parseInt(message[SEVERITY])];
		int id = Integer.parseInt(message[ID]);
		int line = Integer.parseInt(message[LINE]);
		int column = Integer.parseInt(message[COLUMN]);
		int start = Integer.parseInt(message[START]);
		int end = Integer.parseInt(message[END]);
		int offset = Integer.parseInt(message[OFF_SET]);

		// extract & process the raw message form package(message[])
		String rawMessage = getRawMessageMap().get(id).getRawMessage(message);

		// replace the full location for a relative location
		String containerLocation = unit.getAbsoluteFolderPath().toOSString()
				+ File.separator;
		String relativePath = getRelativePathMap().get(id)
				.getRelativePath(unit);

		rawMessage = rawMessage.replace(containerLocation, relativePath + "/");

		// try to get a short version of the raw message (without the line
		// information, absolute file location, etc..)
		ShortMessageStrategy shortMessageStrategy = new DefaultShortMessageStrategy(
				id);
		String shortMessage = shortMessageStrategy.getShortMessage(rawMessage);

		char[] source = unit.getContents();
		ISourceLineTracker lineTracker = unit.getLineTracker();

		IPath filepath = unit.getPath();

		IPath originatingFilepath = new DefaultProblemFileStrategy()
				.getOriginatingFilePath(rawMessage, unit);

		if (!filepath.equals(originatingFilepath)) {
			// used for composite grammars
			try {
				source = getSource(originatingFilepath);
				lineTracker = TextUtils.createLineTracker(source);
			} catch (CoreException ex) {
				ex.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (line > 0) {
			// adjust positions: start, end and offset
			try {
				start = column + lineTracker.getLineOffset(line - 1) - 1;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (offset == 0) {
				try {
					if (line == lineTracker.getNumberOfLines()) {
						offset = lineTracker.getLength() - 1;
					} else {
						offset = lineTracker.getLineOffset(line) - 1;
					}
					if (offset > start) {
						String pattern = new String(source, start, offset
								- start);
						int commentOffset = pattern.indexOf("//");
						if (commentOffset == -1) {
							commentOffset = pattern.indexOf("/*");
						}
						if (commentOffset >= 0) {
							offset -= (pattern.length() - commentOffset + 1);
						}
					}
					end = offset;
				} catch (Exception ex) {
					ex.printStackTrace();

				}
			} else {
				end = start + offset;
			}
		}
		String lineWithProblem = getLineWithProblem(start, source, lineTracker);
		// handle error where ANTLR doesn't report the problem location
		ProblemPostProcessor problemPostProcessor = getProblemPostProcessors()
				.get(id);
		problemPostProcessor.process(unit, source, lineTracker, rawMessage,
				line, start, end);

		return new AntlrProblem(originatingFilepath, severity, id,
				problemPostProcessor.line() == 0 ? 1 : problemPostProcessor
						.line(), column, problemPostProcessor.start(),
				problemPostProcessor.end(), rawMessage, shortMessage,
				lineWithProblem);
	}

	private static String getLineWithProblem(int start, char[] source,
			ISourceLineTracker lineTracker) {
		String lineWithProblem = "";
		try {
			if (start > 0) {
				ISourceRange sourceRange = lineTracker
						.getLineInformationOfOffset(start);
				if (sourceRange != ISourceLineTracker.NULL_RANGE) {
					lineWithProblem = new String(source, sourceRange
							.getOffset(), sourceRange.getLength());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return lineWithProblem;
	}

	private static char[] getSource(IPath path) throws CoreException,
			IOException {
		InputStream in = null;
		try {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IFile originatingFile = root.getFile(path);
			in = new BufferedInputStream(originatingFile.getContents(true));
			StringBuilder buffer = new StringBuilder();
			int ch = in.read();
			while (ch != -1) {
				buffer.append((char) ch);
				ch = in.read();
			}
			char[] source = new char[buffer.length()];
			buffer.getChars(0, source.length, source, 0);
			return source;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
