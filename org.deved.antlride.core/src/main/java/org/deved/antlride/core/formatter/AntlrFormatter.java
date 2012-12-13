/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.core.formatter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.deved.antlride.core.formatter.AntlrFormatterOptions.BracesStyle;
import org.deved.antlride.core.formatter.AntlrFormatterPreferences.BlankLines;
import org.deved.antlride.core.formatter.AntlrFormatterPreferences.Braces;
import org.deved.antlride.core.formatter.AntlrFormatterPreferences.ControlStatements;
import org.deved.antlride.core.formatter.AntlrFormatterPreferences.Indent;
import org.deved.antlride.core.formatter.AntlrFormatterPreferences.WhiteSpaces;
import org.deved.antlride.core.integration.AntlrLanguageTargetService;
import org.deved.antlride.core.model.GrammarType;
import org.deved.antlride.core.model.IAlternative;
import org.deved.antlride.core.model.IAssign;
import org.deved.antlride.core.model.IBangOperator;
import org.deved.antlride.core.model.IBlock;
import org.deved.antlride.core.model.ICallExpression;
import org.deved.antlride.core.model.ICallExpressionOption;
import org.deved.antlride.core.model.ICompositeStatement;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IGrammarAction;
import org.deved.antlride.core.model.IGrammarScope;
import org.deved.antlride.core.model.IImport;
import org.deved.antlride.core.model.IImports;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.INotOperator;
import org.deved.antlride.core.model.IOption;
import org.deved.antlride.core.model.IOptions;
import org.deved.antlride.core.model.IParameter;
import org.deved.antlride.core.model.IParameters;
import org.deved.antlride.core.model.IRange;
import org.deved.antlride.core.model.IReturns;
import org.deved.antlride.core.model.IRootOperator;
import org.deved.antlride.core.model.IRule;
import org.deved.antlride.core.model.IRuleAction;
import org.deved.antlride.core.model.IRuleCatch;
import org.deved.antlride.core.model.IRuleFinally;
import org.deved.antlride.core.model.IRuleScope;
import org.deved.antlride.core.model.IRuleThrows;
import org.deved.antlride.core.model.IScopeReference;
import org.deved.antlride.core.model.ISemanticPredicate;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.IStatement;
import org.deved.antlride.core.model.IStatementAction;
import org.deved.antlride.core.model.ITemplate;
import org.deved.antlride.core.model.ITemplateParameter;
import org.deved.antlride.core.model.IToken;
import org.deved.antlride.core.model.ITokenName;
import org.deved.antlride.core.model.ITokenValue;
import org.deved.antlride.core.model.ITokens;
import org.deved.antlride.core.model.ITreeStatement;
import org.deved.antlride.core.model.IRule.RuleAccessModifier;
import org.deved.antlride.core.model.IStatement.EBNF;
import org.deved.antlride.core.model.ast.AAbstractModelElementVisitor;
import org.deved.antlride.core.model.ast.ModelElementQuery;
import org.deved.antlride.core.util.PerformanceMonitor;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.builder.ISourceLineTracker;
import org.eclipse.dltk.utils.TextUtils;

public class AntlrFormatter extends AAbstractModelElementVisitor {

	private static class LineTracker {

		private ISourceLineTracker lineTracker;

		private StringBuilder source;

		public LineTracker(StringBuilder buffer) {
			lineTracker = TextUtils.createLineTracker(buffer.toString());
			source = buffer;
		}

		public String previousLineAt(int offset) {
			int line = lineTracker.getLineNumberOfOffset(offset) - 1;
			line = Math.max(0, line);
			ISourceRange range = lineTracker.getLineInformation(line);
			return line(range);
		}

		public String lineAt(int offset) {
			try {
				ISourceRange range = lineTracker
						.getLineInformationOfOffset(offset);
				return line(range);
			} catch (StringIndexOutOfBoundsException ex) {
				return EMPTY;
			}
		}

		public String nextLineAt(int offset) {
			int line = lineTracker.getLineNumberOfOffset(offset) + 1;
			line = Math.min(line, lineTracker.getNumberOfLines());
			ISourceRange range = lineTracker.getLineInformation(line);
			return line(range);
		}

		private String line(int start, int end) {
			String line = source.substring(start, end);
			return line;
		}

		private String line(ISourceRange range) {
			if (range == ISourceLineTracker.NULL_RANGE) {
				return EMPTY;
			}
			int start = range.getOffset();
			int end = start + range.getLength();
			return line(start, end);
		}
	}

	private static final String BANG = "!";

	private static final String ROOT = "^";

	private static final String COMMA = ",";

	private static final String SEMANTIC_OPERATOR = "=>";

	private static final String EMPTY = "";

	private static final String AMPERSAND = "@";

	private static final String RETURNS = "returns";

	private static final String SCOPE = "scope";

	private static final String ONE_SPACE = " ";

	private static final String SEMI = ";";

	private static final String COLON = ":";

	private static final String ASSIGN = "=";

	private static final String GRAMMAR = "grammar";

	private static final String OPTIONS = "options";

	private static final String L_CURLY_BRACKET = "{";

	private static final String L2_CURLY_BRACKET = "{{";

	private static final String R_CURLY_BRACKET = "}";

	private static final String R2_CURLY_BRACKET = "}}";

	private static final String L_ANGULAR_BRACKET = "<";

	private static final String R_ANGULAR_BRACKET = ">";

	private static final String L_BRACKET = "[";

	private static final String R_BRACKET = "]";

	private static final String L_ROUND_BRACKET = "(";

	private static final String R_ROUND_BRACKET = ")";

	private static final String TOKENS = "tokens";

	private static final String RANGE = "..";

	private static final String REWRITE_OPERATOR = "->";

	private static final String ALT = "|";

	private static final String TREE_OPERATOR = "^(";

	static boolean DEBUG = false;

	private int level = 0;

	private StringBuilder target = new StringBuilder();

	private AntlrFormatterOptions options;

	private String NL;

	private int ruleOffset;

	private int maxTokenLength;

	private int maxOptionLength;

	private int startOffset;

	private int endOffset;

	private AntlrLanguageTargetService languageTarget;

	public AntlrFormatter(AntlrFormatterOptions options) {
		this.options = options;
		NL = options.getString(BlankLines.NEW_LINE);
	}

	public AntlrLanguageTargetService getLanguageTarget() {
		return languageTarget;
	}

	public AntlrFormatter withLanguageTarget(
			AntlrLanguageTargetService languageTarget) {
		this.languageTarget = languageTarget;
		return this;
	}

	@Override
	public void accept(IModelElement node) {
		IGrammar grammar = node.getAdapter(IGrammar.class);
		if (grammar.isValid()) {
			startOffset = node.sourceStart();
			endOffset = node.sourceEnd();
			PerformanceMonitor monitor = new PerformanceMonitor().start();
			target.setLength(0);
			super.accept(node);
			monitor.end();
			if (DEBUG) {
				System.out.printf(" %s- Formatted-Source took %ss, %sms\n",
						node.getElementName(), monitor.seconds(), monitor
								.milliSecongds());
			}
			// merge comments
			monitor = new PerformanceMonitor().start();
			mergeComments(node.getAdapter(IGrammar.class));
			monitor.end();
			if (DEBUG) {
				System.out.printf(" %s- Comments-Added took %ss, %sms\n", node
						.getElementName(), monitor.seconds(), monitor
						.milliSecongds());
			}
		} else {
			target.append(grammar.getSource());
		}
	}

	private void mergeComments(IGrammar grammar) {
		StringBuilder source = new StringBuilder(grammar.getSource());

		String[] comments = grammar.getComments();
		int commentStart = -1;
		int offset = 1;
		Pattern pattern = Pattern.compile("\r?\n");
		for (String comment : comments) {
			// find the comment in the original source
			commentStart = source.indexOf(comment, commentStart + offset);

			if (commentStart < startOffset || commentStart > endOffset) {
				// ignore comment, usually a single rule
				continue;
			}
			offset = comment.length();
			// avoid conflict between the end of line
			comment = pattern.matcher(comment).replaceAll(NL);

			// extract the content that preceded by the comment
			String prefix = source.substring(0, commentStart);

			// find the "prefix" ignoring whitespace chars
			int insertionPoint = findOffset(prefix, startOffset, target);

			comment = comment.trim();

			// is a single line comment? can be // or /**/
			boolean isSingleLineComment = getNumberOfLines(comment) == 0;

			LineTracker sourceLineTracker = new LineTracker(source);
			String line = sourceLineTracker.lineAt(commentStart);
			// remove the comment from the line
			line = line.replace(comment, "");

			// create the comment
			StringBuilder chunk = new StringBuilder();
			if (isSingleLineComment) {
				// is there anything else in the line?
				boolean isInAnEmptyLine = false;
				if (line.trim().length() == 0) {
					isInAnEmptyLine = true;
				} else {
					String[] blankPatterns = { ALT, REWRITE_OPERATOR,
							TREE_OPERATOR, COLON };
					String l = line.trim();
					for (int i = 0; i < blankPatterns.length; i++) {
						if (blankPatterns[i].equals(l)) {
							isInAnEmptyLine = true;
							break;
						}
					}
				}
				if (isInAnEmptyLine) {
					// the next line in the target source
					LineTracker targetLineTracker = new LineTracker(target);
					String nextLine = targetLineTracker
							.nextLineAt(insertionPoint);

					// the previous line in the source
					String previousLine = sourceLineTracker
							.previousLineAt(commentStart);

					if (previousLine.length() == 0
							|| previousLine.trim().length() == 0) {
						// the line before the comment is empty in the source
						// add an additional line in the target source
						chunk.append(NL);
					}
					chunk.append(NL);
					int firstNoneWS = firstNoneWS(nextLine, 0);
					fill(chunk, firstNoneWS);
					chunk.append(comment);
				} else {
					chunk.append(ONE_SPACE).append(comment);
				}
			} else {
				if (commentStart > 0) {
					chunk.append(NL);
				}
				chunk.append(comment).append(NL);
			}
			// ensure new line
			if (isSingleLineComment && !match(target, NL, insertionPoint)) {
				chunk.append(NL);
				if (insertionPoint > 0) {
					int start = target.lastIndexOf(NL, insertionPoint)
							+ NL.length();
					if (match(target, REWRITE_OPERATOR, start)) {
						start += REWRITE_OPERATOR.length();
					}
					String offsetText = target.substring(start, insertionPoint);
					fill(chunk, firstNoneWS(offsetText, 0));
					int i = insertionPoint;
					while (i < target.length()
							&& isWhitespace(target.charAt(i))) {
						target.deleteCharAt(i);
					}
				}
			}
			target.insert(insertionPoint, chunk);
			if (DEBUG) {
				System.out.println("inserting:");
				System.out.println(chunk);
				System.out.println("comment inserted");
				System.out.println(target);
			}
		}
	}

	private int firstNoneWS(CharSequence charSequence, int start) {
		int i = start;
		while (i < charSequence.length()
				&& isWhitespace(charSequence.charAt(i))) {
			i++;
		}
		return i;
	}

	private String braceSeperator(BracesStyle bracesStyle) {
		switch (bracesStyle) {
		case SAME_LINE:
			return ONE_SPACE;
		case NEXT_LINE:
			return NL;
		}
		throw new UnsupportedOperationException(bracesStyle.name());
	}

	private void fill(StringBuilder chunk, int length) {
		for (int i = 0; i < length; i++) {
			chunk.append(ONE_SPACE);
		}
	}

	private boolean match(CharSequence source, String string, int offset) {
		int i = 0;
		while ((offset >= 0 && (offset + i) < source.length())
				&& (i < string.length())
				&& source.charAt(offset + i) == string.charAt(i)) {
			i++;
		}
		return i == string.length();
	}

	private int getNumberOfLines(String comment) {
		int start = comment.indexOf(NL);
		int lines = 0;
		while (start != -1) {
			start = comment.indexOf(NL, start + NL.length());
			lines++;
		}
		return lines;
	}

	// private String nextLineAt(StringBuilder source, int offset) {
	// try {
	// return lineAt(source, offset
	// + (target.indexOf(NL, offset) + NL.length() - offset));
	// } catch (StringIndexOutOfBoundsException ex) {
	// return EMPTY;
	// }
	// }
	//
	// private String lineAt(StringBuilder source, int offset) {
	// try {
	// ISourceLineTracker lineTracker = TextUtils.createLineTracker(source
	// .toString());
	// ISourceRange sourceRange = lineTracker
	// .getLineInformationOfOffset(offset);
	// if (sourceRange == ISourceLineTracker.NULL_RANGE) {
	// return EMPTY;
	// }
	// int start = sourceRange.getOffset();
	// int end = start + sourceRange.getLength();
	//
	// String line = source.substring(start, end);
	//
	// return line;
	// } catch (StringIndexOutOfBoundsException ex) {
	// return EMPTY;
	// }
	// }

	private boolean isWhitespace(char ch) {
		return Character.isWhitespace(ch);
	}

	private int findOffset(CharSequence prefix, int offset, CharSequence source) {
		int j = 0;
		for (int i = offset; i < prefix.length();) {
			char ch1 = prefix.charAt(i);
			if (isWhitespace(ch1)) {
				i++;
				continue;
			}
			char ch2 = source.charAt(j);
			if (isWhitespace(ch2)) {
				j++;
				continue;
			}
			if (ch1 != ch2) {
				break;
			}
			j++;
			i++;
		}
		return j;
	}

	public String content() {
		return target.toString();
	}

	@Override
	public boolean visitGrammar(IGrammar node) {
		boolean hasDoc = node.getPlainDocumentation() != null;
		if (hasDoc) {
			target.append(node.getPlainDocumentation());
			if (options.getInt(BlankLines.LINES_BEFORE_GRAMMAR_DECLARATION) == 0) {
				// ensure one line between the doc and the grammar decl
				target.append(NL);
			}
		}
		target.append(blankLines(options
				.getInt(BlankLines.LINES_BEFORE_GRAMMAR_DECLARATION)));
		if (node.getGrammarType() != GrammarType.COMBINED) {
			target.append(node.getGrammarType().description())
					.append(ONE_SPACE);
		}
		target.append(GRAMMAR).append(ONE_SPACE).append(node.getElementName())
				.append(SEMI);
		target.append(blankLines(options
				.getInt(BlankLines.LINES_AFTER_GRAMMAR_DECLARATION)));
		return super.visitGrammar(node);
	}

	private String blankLines(int count) {
		StringBuilder buff = new StringBuilder();
		for (int i = 0; i < count; i++) {
			buff.append(NL);
		}
		return buff.toString();
	}

	@Override
	public boolean visitImports(IImports node) {
		target.append("import").append(ONE_SPACE);
		return super.visitImports(node);
	}

	@Override
	public boolean visitImport(IImport node) {
		target.append(node.getElementName()).append(COMMA).append(ONE_SPACE);
		return super.visitImport(node);
	}

	@Override
	public void endvisitImports(IImports node) {
		// eat the last ', '
		target.setLength(target.length() - 2);
		target.append(SEMI).append(NL).append(NL);
		super.endvisitImports(node);
	}

	@Override
	public boolean visitGrammarOptions(IOptions node) {
		target
				.append(blankLines(options
						.getInt(BlankLines.LINES_BEFORE_TOKENS)));
		target.append(OPTIONS);

		// braces option
		target.append(braceSeperator(options.getEnum(Braces.OPTIONS,
				BracesStyle.class)));

		target.append(L_CURLY_BRACKET).append(NL);

		if (options.getBoolean(Indent.ALIGN_OPTIONS_IN_COLUMNS)) {
			// find the largest token
			IOption[] options = node.getOptions();
			maxOptionLength = 0;
			for (IOption token : options) {
				maxOptionLength = Math.max(token.getElementName().length(),
						maxOptionLength);
			}
		}

		if (options.getBoolean(Indent.OPTIONS))
			level(1);

		return super.visitGrammarOptions(node);
	}

	@Override
	public boolean visitGrammarOption(IOption node) {
		target.append(getIndent()).append(node.getName().getElementName());

		if (options.getBoolean(Indent.ALIGN_OPTIONS_IN_COLUMNS)) {
			int size = maxOptionLength - node.getElementName().length();
			if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_OPTION)) {
				size += 1;
			}
			target.append(getIndent(1, size));
		} else {
			if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_OPTION)) {
				target.append(ONE_SPACE);
			}
		}

		target.append(ASSIGN);
		if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_OPTION)) {
			target.append(ONE_SPACE);
		}
		target.append(node.getValue().getElementName()).append(SEMI).append(NL);
		return super.visitGrammarOption(node);
	}

	@Override
	public void endvisitGrammarOptions(IOptions node) {
		target.append(R_CURLY_BRACKET);

		target
				.append(blankLines(options
						.getInt(BlankLines.LINES_AFTER_OPTIONS)));

		if (options.getBoolean(Indent.OPTIONS))
			level(-1);

		super.endvisitGrammarOptions(node);
	}

	@Override
	public boolean visitTokens(ITokens node) {
		target
				.append(blankLines(options
						.getInt(BlankLines.LINES_BEFORE_TOKENS)));
		target.append(TOKENS);
		target.append(braceSeperator(options.getEnum(Braces.TOKENS,
				BracesStyle.class)));
		target.append(L_CURLY_BRACKET).append(NL);
		if (options.getBoolean(Indent.ALIGN_TOKENS_IN_COLUMNS)) {
			// find the largest token
			IToken[] tokens = node.getTokens();
			maxTokenLength = 0;
			for (IToken token : tokens) {
				if (token.hasValue()) {
					maxTokenLength = Math.max(token.getElementName().length(),
							maxTokenLength);
				}
			}
		}

		if (options.getBoolean(Indent.TOKENS))
			level(1);
		return super.visitTokens(node);
	}

	@Override
	public boolean visitTokenName(ITokenName node) {
		target.append(getIndent()).append(node.getElementName());
		return super.visitTokenName(node);
	}

	@Override
	public boolean visitTokenValue(ITokenValue node) {
		if (options.getBoolean(Indent.ALIGN_TOKENS_IN_COLUMNS)) {
			IToken token = node.getParent().getAdapter(IToken.class);
			ITokenName tokenName = token.getName();
			int size = (maxTokenLength - tokenName.getElementName().length());
			if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_TOKEN)) {
				size += 1;
			}
			target.append(getIndent(1, size));
		} else {
			if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_TOKEN)) {
				target.append(ONE_SPACE);
			}
		}
		target.append(ASSIGN);
		if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_TOKEN)) {
			target.append(ONE_SPACE);
		}
		target.append(node.getElementName());
		return super.visitTokenValue(node);
	}

	@Override
	public void endvisitToken(IToken node) {
		target.append(SEMI).append(NL);
		super.endvisitToken(node);
	}

	@Override
	public void endvisitTokens(ITokens node) {
		target.append(R_CURLY_BRACKET);

		target
				.append(blankLines(options
						.getInt(BlankLines.LINES_AFTER_TOKENS)));

		if (options.getBoolean(Indent.TOKENS))
			level(-1);
		super.endvisitTokens(node);
	}

	@Override
	public boolean visitGrammarAction(IGrammarAction node) {
		target
				.append(blankLines(options
						.getInt(BlankLines.LINES_BEFORE_ACTION)));
		target.append(AMPERSAND).append(node.getElementName());

		target.append(braceSeperator(options.getEnum(Braces.ACTIONS,
				BracesStyle.class)));

		target.append(L_CURLY_BRACKET).append(NL);

		target.append(processActionCode(node.getAction()));
		target.append(NL).append(R_CURLY_BRACKET);
		target
				.append(blankLines(options
						.getInt(BlankLines.LINES_AFTER_ACTION)));
		return super.visitGrammarAction(node);
	}

	private String processActionCode(ISourceElement source) {
		return source == null ? EMPTY : processActionCode(source.getText());
	}

	private String processActionCode(String source) {
		StringBuilder buff = new StringBuilder();
		String[] starts = { L2_CURLY_BRACKET, L_CURLY_BRACKET, L_CURLY_BRACKET,
				L_BRACKET };
		String[] ends = { R2_CURLY_BRACKET, R_CURLY_BRACKET,
				R_CURLY_BRACKET + "?", R_BRACKET };

		boolean matches = false;
		for (int i = 0; i < starts.length; i++) {
			if (source.startsWith(starts[i]) && source.endsWith(ends[i])) {
				buff.append(source, starts[i].length(), source.length()
						- ends[i].length());
				matches = true;
				break;
			}
		}
		if (!matches) {
			// no match
			buff.append(source.trim());
		}

		String action = buff.toString().trim();
		if (action.length() > 0 && languageTarget != null) {
			action = languageTarget.format(action);
		}
		return action;
	}

	@Override
	public boolean visitGrammarScope(IGrammarScope node) {
		target
				.append(blankLines(options
						.getInt(BlankLines.LINES_BEFORE_SCOPE)));
		target.append(SCOPE).append(ONE_SPACE).append(node.getElementName());

		target.append(braceSeperator(options.getEnum(Braces.SCOPES,
				BracesStyle.class)));

		target.append(L_CURLY_BRACKET).append(NL);
		target.append(processActionCode(node.getText()));
		return true;
	}

	@Override
	public void endvisitGrammarScope(IGrammarScope node) {
		target.append(NL).append(R_CURLY_BRACKET);
		target.append(blankLines(options.getInt(BlankLines.LINES_AFTER_SCOPE)));
	}

	@Override
	public boolean visitBlockOptions(IOptions node) {
		if (endsWith(NL)) {
			target.setLength(target.length() - NL.length());
			target.append(ONE_SPACE);
		}
		target.append(OPTIONS).append(ONE_SPACE).append(L_CURLY_BRACKET);
		return true;
	}

	@Override
	public boolean visitBlockOption(IOption node) {
		if (options.getBoolean(Indent.BLOCK_OPTIONS)) {
			target.append(getIndent(1));
		}
		target.append(node.getName().getElementName()).append(ASSIGN).append(
				node.getValue().getElementName()).append(SEMI)
				.append(ONE_SPACE);
		return true;
	}

	@Override
	public void endvisitBlockOptions(IOptions node) {
		// eat the last space
		target.setLength(target.length() - 1);
		target.append(R_CURLY_BRACKET).append(COLON);
	}

	@Override
	public boolean visitRuleScope(IRuleScope node) {
		target.append(blankLines(options
				.getInt(BlankLines.LINES_BEFORE_RULE_SCOPE)));

		target.append(SCOPE);

		target.append(braceSeperator(options.getEnum(Braces.RULE_SCOPES,
				BracesStyle.class)));

		target.append(L_CURLY_BRACKET).append(NL);
		target.append(processActionCode(node.getText()));
		return true;
	}

	@Override
	public void endvisitRuleScope(IRuleScope node) {
		target.append(NL).append(R_CURLY_BRACKET);
		target.append(blankLines(options
				.getInt(BlankLines.LINES_AFTER_RULE_SCOPE)));
	}

	@Override
	public boolean visitRuleScopeReference(IScopeReference node) {
		IRule rule = (IRule) node.getParent();
		int index = 0;
		IScopeReference[] scopesReferences = rule.getScopesReferences();
		for (int i = 0; i < scopesReferences.length; i++) {
			if (scopesReferences[i] == node) {
				index = i;
				break;
			}
		}
		if (index == 0) {
			target.append(blankLines(options
					.getInt(BlankLines.LINES_BEFORE_RULE_SCOPE)));
			target.append(SCOPE).append(ONE_SPACE);
		}
		target.append(node.getElementName());
		if (index < scopesReferences.length - 1) {
			target.append(COMMA).append(ONE_SPACE);
		} else {
			target.append(SEMI);
			target.append(blankLines(options
					.getInt(BlankLines.LINES_AFTER_RULE_SCOPE)));
		}
		return true;
	}

	@Override
	public boolean visitRuleOptions(IOptions node) {
		target.append(blankLines(options
				.getInt(BlankLines.LINES_BEFORE_RULE_OPTIONS)));

		target.append(OPTIONS);
		target.append(braceSeperator(options.getEnum(Braces.RULE_OPTIONS,
				BracesStyle.class)));
		target.append(L_CURLY_BRACKET).append(NL);

		return true;
	}

	@Override
	public boolean visitRuleOption(IOption node) {
		if (options.getBoolean(Indent.RULE_OPTIONS)) {
			target.append(getIndent(1));
		}

		target.append(node.getElementName());
		if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_OPTION)) {
			target.append(ONE_SPACE);
		}
		target.append(ASSIGN);
		if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_OPTION)) {
			target.append(ONE_SPACE);
		}
		target.append(node.getValue().getElementName()).append(SEMI).append(NL);

		return true;
	}

	@Override
	public void endvisitRuleOptions(IOptions node) {
		target.append(R_CURLY_BRACKET);
		target.append(blankLines(options
				.getInt(BlankLines.LINES_AFTER_RULE_OPTIONS)));
	}

	@Override
	public boolean visitRuleAction(IRuleAction node) {
		target.append(blankLines(options
				.getInt(BlankLines.LINES_BEFORE_RULE_ACTION)));
		target.append(AMPERSAND).append(node.getElementName());

		target.append(braceSeperator(options.getEnum(Braces.RULE_ACTIONS,
				BracesStyle.class)));

		target.append(L_CURLY_BRACKET).append(NL);
		target.append(processActionCode(node.getAction().getText()));
		return true;
	}

	@Override
	public void endvisitRuleAction(IRuleAction node) {
		target.append(NL).append(R_CURLY_BRACKET);
		target.append(blankLines(options
				.getInt(BlankLines.LINES_AFTER_RULE_ACTION)));
	}

	@Override
	public boolean visitRuleReturns(IReturns node) {
		if (options.getBoolean(ControlStatements.NL_BEFORE_RULE_RETURNS)) {
			target.append(NL).append(getIndent(1));
		} else {
			target.append(ONE_SPACE);
		}
		target.append(RETURNS).append(ONE_SPACE).append(L_BRACKET).append(
				processActionCode(node.getText())).append(R_BRACKET);
		return true;
	}

	@Override
	public boolean visitRule(IRule node) {
		IGrammar grammar = node.getAdapter(IGrammar.class);
		if (grammar.firstRule() == node) {
			target.append(blankLines(options
					.getInt(BlankLines.LINES_BEFORE_FIRST_RULE)));
		} else {
			target.append(blankLines(options
					.getInt(BlankLines.LINES_BEFORE_RULE)));
		}
		if (node.getPlainDocumentation() != null) {
			target.append(node.getPlainDocumentation()).append(NL);
		}
		RuleAccessModifier accessModifier = node.getAccessModifier();
		if (accessModifier != RuleAccessModifier.PUBLIC) {
			target.append(accessModifier.description());
			if (options.getBoolean(ControlStatements.NL_AFTER_RULE_MODIFIER)) {
				target.append(NL);
			} else {
				target.append(ONE_SPACE);
			}
		}
		target.append(node.getElementName());

		target.append(node.getAstSuffix().description());

		ruleOffset = target.length();

		if (options.getBoolean(Indent.RULE)) {
			level(1);
		}

		return true;
	}

	@Override
	public boolean visitRuleThrows(IRuleThrows node) {
		if (options.getBoolean(ControlStatements.NL_BEFORE_RULE_THROWS)) {
			target.append(NL).append(getIndent(1));
		} else {
			target.append(ONE_SPACE);
		}
		target.append(node.getElementName()).append(ONE_SPACE);
		for (String exception : node.getExceptions()) {
			target.append(exception).append(COMMA).append(ONE_SPACE);
		}
		// eat the last ", "
		target.setLength(target.length() - COMMA.length() - ONE_SPACE.length());
		return true;
	}

	@Override
	public boolean visitRuleCatch(IRuleCatch node) {
		target.append(node.getElementName()).append(ONE_SPACE).append(
				node.getException()).append(ONE_SPACE).append(L_CURLY_BRACKET)
				.append(NL);
		target.append(processActionCode(node.getAction().getText()));
		target.append(NL).append(R_CURLY_BRACKET).append(NL);
		return true;
	}

	@Override
	public boolean visitRuleFinally(IRuleFinally node) {
		target.append(node.getElementName()).append(ONE_SPACE).append(
				L_CURLY_BRACKET).append(NL);
		target.append(processActionCode(node.getAction().getText()));
		target.append(NL).append(R_CURLY_BRACKET).append(NL);
		return true;
	}

	protected boolean endsWith(String suffix) {
		return endsWith(target, suffix);
	}

	protected boolean endsWith(StringBuilder builder, String suffix) {
		int k = 0;
		for (int i = builder.length() - suffix.length(); i < builder.length(); i++) {
			char ch = builder.charAt(i);
			if (suffix.charAt(k++) != ch) {
				return false;
			}
		}
		return true;
	}

	protected void ensureIndentAtBegining() {
		if (!isWhitespace(target.charAt(target.length() - 1))) {
			target.append(ONE_SPACE);
		} else {
			target.append(getIndent());
		}
	}

	@Override
	public void endvisitRule(IRule node) {
		boolean completelyEmpty = node.isEmpty()
				&& !(node.hasActions() || node.hasCatchs() || node.hasOptions()
						|| node.hasParameters() || node.hasReturns()
						|| node.hasScopes() || node.hasScopesReferences());
		if (completelyEmpty
				&& options.getBoolean(ControlStatements.EMPTY_RULE_ON_ONE_LINE)) {
			// completely empty!
			target.setLength(target.length() - (target.length() - ruleOffset));
			target.append(COLON).append(ONE_SPACE).append(SEMI);
		} else {
			if (options.getBoolean(ControlStatements.NL_BEFORE_RULE_END)) {
				if (!endsWith(NL)) {
					target.append(NL);
				}
				target.append(getIndent());
			} else {
				if (endsWith(NL)) {
					target.setLength(target.length() - NL.length());
				}
			}
			target.append(SEMI);
		}
		target.append(blankLines(options.getInt(BlankLines.LINES_AFTER_RULE)));

		if (options.getBoolean(Indent.RULE)) {
			level(-1);
		}
	}

	@Override
	public boolean visitRuleBody(IBlock node) {
		if (options.getBoolean(ControlStatements.NL_BEFORE_RULE_COLON)) {
			if (!endsWith(NL)) {
				target.append(NL);
			}
			target.append(getIndent());
		} else {
			if (endsWith(NL)) {
				target.setLength(target.length() - NL.length());
			}
			if (!endsWith(ONE_SPACE)) {
				target.append(ONE_SPACE);
			}
		}
		target.append(COLON);
		target.append(NL);
		return visitBlock(node);
	}

	@Override
	public void endvisitRuleBody(IBlock node) {
		endvisitBlock(node);
	}

	@Override
	public boolean visitSemanticPredicate(ISemanticPredicate node) {
		ensureIndentAtBegining();
		target.append(L_CURLY_BRACKET).append(
				processActionCode(node.getCondition().getText())).append(
				R_CURLY_BRACKET).append("?");
		target.append(node.getPredicateType().description());
		return true;
	}

	@Override
	public void endvisitBangOperator(IBangOperator node) {
		if (endsWith(NL)) {
			target.insert(target.length() - NL.length(), BANG);
		} else {
			target.append(BANG);
		}
		target.append(node.getEbnfOperator().description());
	}

	@Override
	public void endvisitRootOperator(IRootOperator node) {
		if (endsWith(NL)) {
			target.insert(target.length() - NL.length(), ROOT);
		} else {
			target.append(ROOT);
		}
		target.append(node.getEbnfOperator().description());
	}

	@Override
	public void endvisitSyntacticPredicateCondition(IStatement node) {
		level(1);
		ensureIndentAtBegining();
		target.append(SEMANTIC_OPERATOR);
		level(-1);
	}

	@Override
	public boolean visitAssign(IAssign node) {
		if (endsWith(L_ROUND_BRACKET)) {
			if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_BLOCK_PARENTHESIS)) {
				ensureIndentAtBegining();
			}
		} else {
			ensureIndentAtBegining();
		}
		target.append(node.getVariable().getElementName());
		if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_ASSIGN)) {
			target.append(ONE_SPACE);
		}
		target.append(node.getOperator().getText().trim());
		return super.visitAssign(node);
	}

	@Override
	public void endvisitAssign(IAssign node) {
		target.append(node.getEbnfOperator().description());
		super.endvisitAssign(node);
	}

	@Override
	public boolean visitStatementAction(IStatementAction node) {
		ensureIndentAtBegining();
		int newLineStart = target.lastIndexOf(NL);
		String lastLine = target.substring(newLineStart);
		int whiteSize = firstNoneWS(lastLine, 0) + lastLine.trim().length() - 1;
		target.append(NL);
		fill(target, whiteSize);
		String lbracket = L_CURLY_BRACKET;
		String rbracket = R_CURLY_BRACKET;
		if (node.isForced()) {
			lbracket = L2_CURLY_BRACKET;
			rbracket = R2_CURLY_BRACKET;
		}
		target.append(lbracket).append(NL);
		String[] actionCode = processActionCode(node.getAction().getText())
				.split(NL);
		for (String lineOfActionCode : actionCode) {
			fill(target, whiteSize + lbracket.length());
			target.append(lineOfActionCode).append(NL);
		}
		fill(target, whiteSize);
		target.append(rbracket);
		target.append(NL);
		return super.visitStatementAction(node);
	}

	@Override
	public void endvisitStatementAction(IStatementAction node) {
		super.endvisitStatementAction(node);
	}

	@Override
	public boolean visitTreeStatement(ITreeStatement node) {
		boolean firstBlockAfterRewriteOp = endsWith(REWRITE_OPERATOR);

		if (firstBlockAfterRewriteOp) {
			if (options.getBoolean(ControlStatements.NL_AFTER_REWRITE_OPERATOR)) {
				if (!endsWith(NL)) {
					target.append(NL);
				}
				target.append(getIndent());
			} else {
				target.append(ONE_SPACE);
			}
		} else {
			if (!endsWith(NL)) {
				target.append(NL);
			}
			target.append(getIndent());
		}
		target.append(node.getElementName());
		if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_BLOCK_PARENTHESIS)) {
			target.append(ONE_SPACE);
		}
		boolean isMultipleLineBlock = isMultipleLineBlock(node);
		if (isMultipleLineBlock) {
			target.append(NL);
		}
		level(1);
		return super.visitTreeStatement(node);
	}

	@Override
	public void endvisitTreeStatement(ITreeStatement node) {
		boolean isMultipleLineBlock = isMultipleLineBlock(node);
		level(-1);
		if (isMultipleLineBlock) {
			if (!endsWith(NL)) {
				target.append(NL);
			}
			target.append(getIndent());
		}
		if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_BLOCK_PARENTHESIS)
				|| isMultipleLineBlock) {
			target.append(ONE_SPACE);
		}
		if (!options.getBoolean(ControlStatements.NL_AFTER_REWRITE_OPERATOR)) {
			target.append(ONE_SPACE);
		}
		target.append(R_ROUND_BRACKET).append(
				node.getEbnfOperator().description());

		target.append(NL);
		super.endvisitTreeStatement(node);
	}

	@Override
	public boolean visitBlock(IBlock node) {
		if (node.getLeftParenthesis() != null) {
			boolean isMultipleLineBlock = isMultipleLineBlock(node);

			if (isMultipleLineBlock) {
				if (!endsWith(NL)) {
					target.append(NL);
				}
				target.append(getIndent());
			} else {
				ensureIndentAtBegining();
			}
			if (node.getParent() instanceof INotOperator) {
				target.append(node.getParent().getElementName());
			}
			target.append(L_ROUND_BRACKET);

			if (isMultipleLineBlock) {
				target.append(NL);
			}

			if (options.getBoolean(Indent.BLOCKS)) {
				level(1);
			}
		}
		return true;
	}

	private boolean isMultipleLineBlock(ICompositeStatement node) {
		IGrammar grammar = (IGrammar) node.getEnclosingRule().getParent();
		if (grammar.isTreeParserGrammar())
			return true;
		IModelElement[] elements = ModelElementQuery.collectBlocksOrAlts(node);
		Map<IModelElement, Integer> map = new HashMap<IModelElement, Integer>();
		for (int i = 0; i < elements.length; i++) {
			ICompositeStatement element = (ICompositeStatement) elements[i];
			IModelElement parent = element.getParent();
			int count = map.get(parent) == null ? 0 : map.get(parent);
			count++;
			if (count > 1) {
				// found!
				return true;
			}
			map.put(parent, count);
		}
		return false;
	}

	private boolean isMultipleLineBlock(ITreeStatement node) {
		IModelElement[] elements = ModelElementQuery
				.collectTreeStatements(node);
		return elements.length > 0;
	}

	@Override
	public void endvisitBlock(IBlock node) {
		boolean multipleLineBlock = isMultipleLineBlock(node);
		if (node.getRightParenthesis() != null) {
			if (!endsWith(NL)) {
				if (multipleLineBlock) {
					// alternative
					target.append(NL);
				}
			}

			if (options.getBoolean(Indent.BLOCKS)) {
				if (multipleLineBlock) {
					target.append(getIndent(level(0) - 1));
				} else {
					if (endsWith(NL)) {
						target.setLength(target.length() - NL.length());
					}
					ensureIndentAtBegining();
				}
				level(-1);
			} else {
				if (multipleLineBlock) {
					target.append(getIndent(level(0)));
				}
			}
			if (node.getParent() instanceof INotOperator) {
				target.append(ONE_SPACE);
			}
			if (!multipleLineBlock
					&& (!options
							.getBoolean(WhiteSpaces.BEFORE_AFTER_BLOCK_PARENTHESIS) && endsWith(ONE_SPACE))) {
				target.setLength(target.length() - ONE_SPACE.length());
			}
			target.append(R_ROUND_BRACKET);
		}
		if (multipleLineBlock) {
			if (endsWith(NL)) {
				// ebnf
				target.insert(target.length() - NL.length(), node
						.getEbnfOperator().description());
			} else {
				target.append(node.getEbnfOperator().description()).append(NL);
			}
		} else {
			if (endsWith(NL)) {
				target.setLength(target.length() - NL.length());
			}
			target.append(node.getEbnfOperator().description());
		}
		super.endvisitBlock(node);
	}

	@Override
	public boolean visitRewriteBlock(IBlock node) {
		if (!endsWith(NL)) {
			target.append(NL);
		}
		if (options.getBoolean(Indent.REWRITE_OPERATOR)) {
			level(1);
			target.append(getIndent());
		}
		target.append(REWRITE_OPERATOR);
		level(1);
		return true;
	}

	@Override
	public void endvisitRewriteBlock(IBlock node) {
		if (options.getBoolean(Indent.REWRITE_OPERATOR)) {
			level(-1);
		}
		level(-1);
	}

	@Override
	public void endvisitAlternative(IAlternative node) {
		ICompositeStatement cmpstt = (ICompositeStatement) node.getParent();
		if (node.getNumber() < cmpstt.size() - 1) {
			if (!endsWith(NL)) {
				target.append(NL);
			}
			if (node.getOperator().equals(REWRITE_OPERATOR)) {
				// rewrite alternative
				// reduce the indent level
				level(-1);
				target.append(getIndent());
				level(1);
			} else {
				target.append(getIndent());
			}
			target.append(node.getOperator());
		}
	}

	@Override
	public void endvisitNotOperator(INotOperator node) {
		if (node.getEbnfOperator() != EBNF.NONE) {
			if (endsWith(NL)) {
				target.insert(target.length() - NL.length(), node
						.getEbnfOperator().description());
			} else {
				target.append(node.getEbnfOperator().description());
			}
		}
	}

	@Override
	public boolean visitCallExpression(ICallExpression node) {
		int k = target.lastIndexOf(NL);
		if (k > 0 && k == target.length() - NL.length()) {
			target.append(getIndent());
		}
		if (!isWhitespace(target.charAt(target.length() - 1))) {
			if (!endsWith(RANGE)) {
				if (endsWith(ASSIGN)) {
					if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_ASSIGN)) {
						target.append(ONE_SPACE);
					}
				} else if (endsWith(L_ROUND_BRACKET)) {
					if (options
							.getBoolean(WhiteSpaces.BEFORE_AFTER_BLOCK_PARENTHESIS)) {
						target.append(ONE_SPACE);
					}
				} else {
					target.append(ONE_SPACE);
				}
			}
		}
		if (node.getParent() instanceof INotOperator) {
			target.append(node.getParent().getElementName());
		}
		if (node.hasLabel()) {
			target.append("$");
		}
		target.append(node.getElementName());
		// call expression options
		ICallExpressionOption[] options = node.getOptions();
		StringBuilder optBuff = new StringBuilder();
		for (ICallExpressionOption option : options) {
			optBuff.append(option.getOptionName().getText());
			if (option.hasOptionValue()) {
				optBuff.append(ASSIGN);
				optBuff.append(option.getOptionValue().getText());
			}
			optBuff.append(COMMA).append(ONE_SPACE);
		}
		if (optBuff.length() > 0) {
			target.append(L_ANGULAR_BRACKET);
			optBuff.setLength(optBuff.length()
					- (COMMA.length() + ONE_SPACE.length()));
			target.append(optBuff);
			target.append(R_ANGULAR_BRACKET);
		}
		// EBNF operator
		if (node.getEbnfOperator() != EBNF.NONE && !node.hasParameters()) {
			target.append(node.getEbnfOperator().description());
		}
		IRange range = node.getParent().getAdapter(IRange.class);
		if (range != null) {
			if (range.getRight() == node) {
				target.append(range.getEbnfOperator().description());
			} else {
				target.append(range.getElementName());
			}
		}
		return true;
	}

	@Override
	public boolean visitRuleParameters(IParameters node) {
		if (options.getBoolean(ControlStatements.NL_BEFORE_RULE_ARGS)) {
			target.append(NL).append(getIndent(1));
		}
		target.append(L_BRACKET);
		target.append(node.getAction().getText());
		return true;
	}

	@Override
	public void endvisitRuleParameters(IParameters node) {
		target.append(R_BRACKET);
	}

	public boolean visitCallParameters(IParameters node) {
		visitRuleParameters(node);
		return true;
	}

	@Override
	public void endvisitCallParameters(IParameters node) {
		endvisitRuleParameters(node);
		ICallExpression callExpression = node.getParent().getAdapter(
				ICallExpression.class);
		target.append(callExpression.getEbnfOperator().description());
	}

	@Override
	public boolean visitCallParameter(IParameter node) {
		visitRuleParameter(node);
		return true;
	}

	@Override
	public void endvisitCallParameter(IParameter node) {
		endvisitRuleParameter(node);
	}

	@Override
	public boolean visitTemplate(ITemplate node) {
		if (options.getBoolean(ControlStatements.NL_AFTER_REWRITE_OPERATOR)) {
			target.append(NL);
			target.append(getIndent());
		}
		if (node.isNamed()) {
			target.append(node.getName().getText());
		} else {
			if (node.isSimpleActionTemplate()) {
				target.append(node.getAction().getText());
			} else {
				target.append(L_ROUND_BRACKET);
				target.append(node.getAction().getText());
				target.append(R_ROUND_BRACKET);
			}
		}
		if (!node.isSimpleActionTemplate()) {
			target.append(L_ROUND_BRACKET);
		}
		return true;
	}

	@Override
	public boolean visitTemplateParameter(ITemplateParameter node) {
		target.append(node.getElementName());
		if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_ASSIGN)) {
			target.append(ONE_SPACE);
		}
		target.append(ASSIGN);
		if (options.getBoolean(WhiteSpaces.BEFORE_AFTER_ASSIGN)) {
			target.append(ONE_SPACE);
		}
		target.append(node.getAction().getText());
		target.append(COMMA).append(ONE_SPACE);
		return true;
	}

	@Override
	public void endvisitTemplate(ITemplate node) {
		if (node.hasParameters()) {
			// eat ", "
			target.setLength(target.length() - 2);
		}
		if (!node.isSimpleActionTemplate()) {
			target.append(R_ROUND_BRACKET);
		}
		if (node.isInline()) {
			target.append(node.getInlineTemplate().getText());
		}
		target.append(NL);
	}

	private String getIndent() {
		return getIndent(this.level);
	}

	private String getIndent(int level) {
		if (level == 0)
			return EMPTY;
		return getIndent(level, options.getInt(Indent.INDENTATION_SIZE));
	}

	private String getIndent(int level, int size) {
		if (level == 0)
			return EMPTY;
		String spaceChar = options.getString(Indent.TAB_CHAR);
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < (size * level); i++) {
			buffer.append(spaceChar);
		}
		return buffer.toString();
	}

	private int level(int level) {
		this.level += level;
		return this.level;
	}

	@Override
	public String toString() {
		return target.toString();
	}
}
