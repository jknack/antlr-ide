/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package org.deved.antlride.core.formatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deved.antlride.core.formatter.AntlrFormatterOptions.BracesStyle;
import org.deved.antlride.core.formatter.AntlrFormatterOptions.IndentStyle;

public class AntlrFormatterPreferences {
	public static String FORMATTER_PROFILES = ("formatter.profiles"); //$NON-NLS-1$
	public static String FORMATTER_ACTIVE_PROFILE = ("formatter.profiles.active"); //$NON-NLS-1$	

	private static interface PreferenceValue {
		Object value();
	}

	private static abstract class AbstractFormatterPreference implements
			AntlrFormatterPreference {
		private final String optionName;

		protected final Object value;

		public AbstractFormatterPreference(String optionName, Object value) {
			this.optionName = optionName;
			this.value = value;
		}

		public boolean booleanValue() {
			throw new IllegalStateException("not a boolean!");
		}

		public int intValue() {
			throw new IllegalStateException("not a int!");
		}

		public String stringValue() {
			throw new IllegalStateException("not a string!");
		}

		public String getName() {
			return optionName;
		}

		public boolean isBoolean() {
			return false;
		}

		public boolean isInt() {
			return false;
		}

		public boolean isString() {
			return false;
		}
	}

	/** Indent Section */
	public static enum Indent implements AntlrFormatterPreference,
			PreferenceValue {
		TAB_CHAR(stringPreference(
				"formatter.tabulation.char", IndentStyle.SPACE.name())), //$NON-NLS-1$
		TAB_SIZE(intPreference("formatter.tabulation.size", 2)), //$NON-NLS-1$
		INDENTATION_SIZE(intPreference("formatter.indentation.size", 2)), //$NON-NLS-1$

		RULE(booleanPreference("indent.rule", true)), //$NON-NLS-1$
		OPTIONS(booleanPreference("indent.options", true)), //$NON-NLS-1$
		TOKENS(booleanPreference("indent.tokens", true)), //$NON-NLS-1$
		BLOCKS(booleanPreference("indent.blocks", true)), //$NON-NLS-1$
		ALIGN_TOKENS_IN_COLUMNS(booleanPreference(
				"indent.align.tokensInColumns", true)), //$NON-NLS-1$
		ALIGN_OPTIONS_IN_COLUMNS(booleanPreference(
				"indent.align.optionsInColumns", true)), //$NON-NLS-1$
		RULE_OPTIONS(booleanPreference("indent.rule.options", true)), //$NON-NLS-1$
		BLOCK_OPTIONS(booleanPreference("indent.block.options", false)), //$NON-NLS-1$
		REWRITE_OPERATOR(booleanPreference("indent.rewriteOperator", true)); //$NON-NLS-1$

		private AbstractFormatterPreference delegator;

		private Indent(AbstractFormatterPreference delegator) {
			this.delegator = delegator;
		}

		public boolean booleanValue() {
			return delegator.booleanValue();
		}

		public int intValue() {
			return delegator.intValue();
		}

		public String stringValue() {
			return delegator.stringValue();
		}

		public String getName() {
			return delegator.getName();
		}

		public boolean isBoolean() {
			return delegator.isBoolean();
		}

		public boolean isInt() {
			return delegator.isInt();
		}

		public boolean isString() {
			return delegator.isString();
		}

		public Object value() {
			return delegator.value;
		}
	}

	/** Blank Lines Section */
	public static enum BlankLines implements AntlrFormatterPreference,
			PreferenceValue {
		NEW_LINE(stringPreference("new.line", "\n")), //$NON-NLS-1$

		LINES_BEFORE_GRAMMAR_DECLARATION(intPreference(
				"line.file.before.grammar.decl", 0)), //$NON-NLS-1$
		LINES_AFTER_GRAMMAR_DECLARATION(intPreference(
				"line.file.after.grammar.decl", 1)), //$NON-NLS-1$

		LINES_BEFORE_OPTIONS(intPreference("line.before.options", 1)), //$NON-NLS-1$
		LINES_AFTER_OPTIONS(intPreference("line.after.options", 1)), //$NON-NLS-1$

		LINES_BEFORE_TOKENS(intPreference("line.before.tokens", 1)), //$NON-NLS-1$
		LINES_AFTER_TOKENS(intPreference("line.after.tokens", 1)), //$NON-NLS-1$

		LINES_BEFORE_ACTION(intPreference("line.before.action", 1)), //$NON-NLS-1$
		LINES_AFTER_ACTION(intPreference("line.after.action", 1)), //$NON-NLS-1$

		LINES_BEFORE_RULE(intPreference("line.before.rule", 1)), //$NON-NLS-1$
		LINES_BEFORE_FIRST_RULE(intPreference("line.before.first.rule", 1)), //$NON-NLS-1$
		LINES_AFTER_RULE(intPreference("line.after.rule", 1)), //$NON-NLS-1$
		LINES_BEFORE_SCOPE(intPreference("line.before.scope", 1)), //$NON-NLS-1$
		LINES_AFTER_SCOPE(intPreference("line.after.scope", 1)), //$NON-NLS-1$
		LINES_BEFORE_RULE_OPTIONS(intPreference("line.before.rule.options", 1)), //$NON-NLS-1$
		LINES_AFTER_RULE_OPTIONS(intPreference("line.after.rule.options", 0)), //$NON-NLS-1$
		LINES_BEFORE_RULE_SCOPE(intPreference("line.before.rule.scope", 1)), //$NON-NLS-1$
		LINES_AFTER_RULE_SCOPE(intPreference("line.after.rule.scope", 0)),
		LINES_BEFORE_RULE_ACTION(intPreference("line.before.rule.action", 1)), //$NON-NLS-1$;
		LINES_AFTER_RULE_ACTION(intPreference("line.after.rule.action", 0)); //$NON-NLS-1$

		private AbstractFormatterPreference delegator;

		private BlankLines(AbstractFormatterPreference delegator) {
			this.delegator = delegator;
		}

		public boolean booleanValue() {
			return delegator.booleanValue();
		}

		public int intValue() {
			return delegator.intValue();
		}

		public String stringValue() {
			return delegator.stringValue();
		}

		public String getName() {
			return delegator.getName();
		}

		public boolean isBoolean() {
			return delegator.isBoolean();
		}

		public boolean isInt() {
			return delegator.isInt();
		}

		public boolean isString() {
			return delegator.isString();
		}

		public Object value() {
			return delegator.value;
		}
	}

	/** Braces Section */
	public static enum Braces implements AntlrFormatterPreference,
			PreferenceValue {
		OPTIONS(
				stringPreference("braces.options", BracesStyle.SAME_LINE.name())), //$NON-NLS-1$
		TOKENS(stringPreference("braces.tokens", BracesStyle.SAME_LINE.name())), //$NON-NLS-1$
		ACTIONS(
				stringPreference("braces.actions", BracesStyle.SAME_LINE.name())), //$NON-NLS-1$		
		SCOPES(stringPreference("braces.scopes", BracesStyle.SAME_LINE.name())), //$NON-NLS-1$
		RULE_OPTIONS(stringPreference(
				"braces.rule.options", BracesStyle.SAME_LINE.name())), //$NON-NLS-1$
		RULE_ACTIONS(stringPreference(
				"braces.rule.actions", BracesStyle.SAME_LINE.name())), //$NON-NLS-1$
		RULE_SCOPES(stringPreference(
				"braces.rule.scopes", BracesStyle.SAME_LINE.name())); //$NON-NLS-1$

		private AbstractFormatterPreference delegator;

		private Braces(AbstractFormatterPreference delegator) {
			this.delegator = delegator;
		}

		public boolean booleanValue() {
			return delegator.booleanValue();
		}

		public int intValue() {
			return delegator.intValue();
		}

		public String stringValue() {
			return delegator.stringValue();
		}

		public String getName() {
			return delegator.getName();
		}

		public boolean isBoolean() {
			return delegator.isBoolean();
		}

		public boolean isInt() {
			return delegator.isInt();
		}

		public boolean isString() {
			return delegator.isString();
		}

		public Object value() {
			return delegator.value;
		}
	}

	/** White Spaces Section */
	public static enum WhiteSpaces implements AntlrFormatterPreference,
			PreferenceValue {
		BEFORE_AFTER_OPTION(booleanPreference("ws.before-after.option", true)), //$NON-NLS-1$

		BEFORE_AFTER_TOKEN(booleanPreference("ws.before-after.tokenName", true)), //$NON-NLS-1$

		BEFORE_AFTER_ASSIGN(booleanPreference("ws.before-after.assign", false)), //$NON-NLS-1$

		BEFORE_AFTER_BLOCK_PARENTHESIS(booleanPreference(
				"ws.before-after.blockParenthesis", false)); //$NON-NLS-1$

		private AbstractFormatterPreference delegator;

		private WhiteSpaces(AbstractFormatterPreference delegator) {
			this.delegator = delegator;
		}

		public boolean booleanValue() {
			return delegator.booleanValue();
		}

		public int intValue() {
			return delegator.intValue();
		}

		public String stringValue() {
			return delegator.stringValue();
		}

		public String getName() {
			return delegator.getName();
		}

		public boolean isBoolean() {
			return delegator.isBoolean();
		}

		public boolean isInt() {
			return delegator.isInt();
		}

		public boolean isString() {
			return delegator.isString();
		}

		public Object value() {
			return delegator.value;
		}
	}

	/** New Lines Section */
	public static enum ControlStatements implements AntlrFormatterPreference,
			PreferenceValue {
		NL_AFTER_RULE_MODIFIER(
				booleanPreference("nl.after.rule.modifier", true)), //$NON-NLS-1$
		NL_BEFORE_RULE_COLON(booleanPreference("nl.before.rule.colon", true)), //$NON-NLS-1$
		NL_BEFORE_RULE_ARGS(booleanPreference("nl.before.rule.args", false)), //$NON-NLS-1$;
		NL_BEFORE_RULE_RETURNS(booleanPreference(
				"nl.before.rule.returns", false)), //$NON-NLS-1$;
		NL_BEFORE_RULE_THROWS(booleanPreference("nl.before.rule.throws", false)), //$NON-NLS-1$;
		NL_AFTER_REWRITE_OPERATOR(booleanPreference(
				"nl.after.rewriteOperator", true)), //$NON-NLS-1$;
		NL_BEFORE_RULE_END(booleanPreference("nl.after.rule.end", true)), //$NON-NLS-1$;;
		EMPTY_RULE_ON_ONE_LINE(booleanPreference("empty.rule.onOneLine", true)); //$NON-NLS-1$;

		private AbstractFormatterPreference delegator;

		private ControlStatements(AbstractFormatterPreference delegator) {
			this.delegator = delegator;
		}

		public boolean booleanValue() {
			return delegator.booleanValue();
		}

		public int intValue() {
			return delegator.intValue();
		}

		public String stringValue() {
			return delegator.stringValue();
		}

		public String getName() {
			return delegator.getName();
		}

		public boolean isBoolean() {
			return delegator.isBoolean();
		}

		public boolean isInt() {
			return delegator.isInt();
		}

		public boolean isString() {
			return delegator.isString();
		}

		public Object value() {
			return delegator.value;
		}
	}

	public static Map<AntlrFormatterPreference, Object> toMap() {
		AntlrFormatterPreference[] preferences = toList();
		Map<AntlrFormatterPreference, Object> map = new HashMap<AntlrFormatterPreference, Object>();
		for (AntlrFormatterPreference preference : preferences) {
			map.put(preference, ((PreferenceValue) preference).value());
		}
		return map;
	}

	public static AntlrFormatterPreference[] toList() {
		List<AntlrFormatterPreference> all = new ArrayList<AntlrFormatterPreference>();
		all.addAll(Arrays.asList(Indent.values()));
		all.addAll(Arrays.asList(Braces.values()));
		all.addAll(Arrays.asList(BlankLines.values()));
		all.addAll(Arrays.asList(WhiteSpaces.values()));
		all.addAll(Arrays.asList(ControlStatements.values()));

		return all.toArray(new AntlrFormatterPreference[all.size()]);
	}

	public static String[] toStringArray() {
		AntlrFormatterPreference[] all = toList();

		String[] names = new String[all.length];
		for (int i = 0; i < all.length; i++) {
			names[i] = all[i].getName();
		}
		return names;
	}

	private static AbstractFormatterPreference intPreference(String name,
			int value) {
		return new AbstractFormatterPreference(name, value) {
			@Override
			public boolean isInt() {
				return true;
			}

			@Override
			public int intValue() {
				return (Integer) value;
			}
		};
	}

	private static AbstractFormatterPreference booleanPreference(String name,
			boolean value) {
		return new AbstractFormatterPreference(name, value) {
			@Override
			public boolean isBoolean() {
				return true;
			}

			@Override
			public boolean booleanValue() {
				return (Boolean) value;
			}
		};
	}

	private static AbstractFormatterPreference stringPreference(String name,
			String value) {
		return new AbstractFormatterPreference(name, value) {
			@Override
			public boolean isString() {
				return true;
			}

			@Override
			public String stringValue() {
				return (String) value;
			}
		};
	}
}
