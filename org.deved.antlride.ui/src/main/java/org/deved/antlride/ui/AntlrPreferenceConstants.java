/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.ui;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.internal.ui.text.highlighting.AntlrColorConstants;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.RGB;

/**
 * UI Preferences
 * 
 * @author edgar
 * 
 */

public class AntlrPreferenceConstants extends PreferenceConstants {
	
	public static final String DFA_VIEW = "show.dfa";
	
	public static final String RAILROAD_VIEW = "show.rr";
	
	public static final String FORMATTER_ID = "formatterId"; //$NON-NLS-1$
	/**
	 * Grammar declaration
	 */
	public final static String EDITOR_GRAMMAR_NAME_COLOR = AntlrColorConstants.ANTLR3_GRAMMAR_NAME;

	public final static String EDITOR_GRAMMAR_NAME_BOLD = AntlrColorConstants.ANTLR3_GRAMMAR_NAME
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_GRAMMAR_NAME_ITALIC = AntlrColorConstants.ANTLR3_GRAMMAR_NAME
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_GRAMMAR_NAME_STRIKETHROUGH = AntlrColorConstants.ANTLR3_GRAMMAR_NAME
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_GRAMMAR_NAME_UNDERLINE = AntlrColorConstants.ANTLR3_GRAMMAR_NAME
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * ANTLR options
	 */
	public static final String EDITOR_OPTIONS_FOLDING_ENABLED = "editor_options_folding_enabled";

	/**
	 * ANTLR Grammar Action
	 */
	public final static String EDITOR_GRAMMAR_ACTION_COLOR = AntlrColorConstants.ANTLR3_GRAMMAR_ACTION;

	public final static String EDITOR_GRAMMAR_ACTION_BOLD = AntlrColorConstants.ANTLR3_GRAMMAR_ACTION
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_GRAMMAR_ACTION_ITALIC = AntlrColorConstants.ANTLR3_GRAMMAR_ACTION
			+ EDITOR_ITALIC_SUFFIX;

	public static final String EDITOR_GRAMMAR_ACTION_FOLDING_ENABLED = "editor_grammar_action_folding_enabled";

	public final static String EDITOR_UNKNOW_GRAMMAR_ACTION_COLOR = AntlrColorConstants.ANTLR3_UNKNOW_GRAMMAR_ACTION;

	public final static String EDITOR_UNKNOW_GRAMMAR_ACTION_BOLD = AntlrColorConstants.ANTLR3_UNKNOW_GRAMMAR_ACTION
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_UNKNOW_GRAMMAR_ACTION_ITALIC = AntlrColorConstants.ANTLR3_UNKNOW_GRAMMAR_ACTION
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_UNKNOW_GRAMMAR_ACTION_UNDERLINE = AntlrColorConstants.ANTLR3_UNKNOW_GRAMMAR_ACTION
			+ EDITOR_UNDERLINE_SUFFIX;

	public final static String EDITOR_UNKNOW_GRAMMAR_ACTION_STRIKETHROUGH = AntlrColorConstants.ANTLR3_UNKNOW_GRAMMAR_ACTION
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	/**
	 * Single line comments
	 */
	public final static String EDITOR_SINGLE_LINE_COMMENT_COLOR = AntlrColorConstants.ANTLR3_SINGLE_LINE_COMMENT;

	public final static String EDITOR_SINGLE_LINE_COMMENT_BOLD = AntlrColorConstants.ANTLR3_SINGLE_LINE_COMMENT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_SINGLE_LINE_COMMENT_ITALIC = AntlrColorConstants.ANTLR3_SINGLE_LINE_COMMENT
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_SINGLE_LINE_COMMENT_STRIKETHROUGH = AntlrColorConstants.ANTLR3_SINGLE_LINE_COMMENT
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_SINGLE_LINE_COMMENT_UNDERLINE = AntlrColorConstants.ANTLR3_SINGLE_LINE_COMMENT
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * Doc comments
	 */

	public final static String EDITOR_DOC_COMMENT_COLOR = AntlrColorConstants.ANTLR3_DOC_COMMENT;

	public final static String EDITOR_DOC_COMMENT_BOLD = AntlrColorConstants.ANTLR3_DOC_COMMENT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_DOC_COMMENT_ITALIC = AntlrColorConstants.ANTLR3_DOC_COMMENT
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_DOC_COMMENT_STRIKETHROUGH = AntlrColorConstants.ANTLR3_DOC_COMMENT
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_DOC_COMMENT_UNDERLINE = AntlrColorConstants.ANTLR3_DOC_COMMENT
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * target language comments
	 */

	public final static String EDITOR_TARGET_LANGUAGE_COMMENT_COLOR = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_COMMENT;

	public final static String EDITOR_TARGET_LANGUAGE_COMMENT_BOLD = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_COMMENT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_TARGET_LANGUAGE_COMMENT_ITALIC = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_COMMENT
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_TARGET_LANGUAGE_COMMENT_STRIKETHROUGH = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_COMMENT
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_TARGET_LANGUAGE_COMMENT_UNDERLINE = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_COMMENT
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * Multiple line comments
	 */

	public final static String EDITOR_MULTI_LINE_COMMENT_COLOR = AntlrColorConstants.ANTLR3_MULTI_LINE_COMMENT;

	public final static String EDITOR_MULTI_LINE_COMMENT_BOLD = AntlrColorConstants.ANTLR3_MULTI_LINE_COMMENT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_MULTI_LINE_COMMENT_ITALIC = AntlrColorConstants.ANTLR3_MULTI_LINE_COMMENT
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_MULTI_LINE_COMMENT_STRIKETHROUGH = AntlrColorConstants.ANTLR3_MULTI_LINE_COMMENT
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_MULTI_LINE_COMMENT_UNDERLINE = AntlrColorConstants.ANTLR3_MULTI_LINE_COMMENT
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * Keywords
	 */

	public final static String EDITOR_KEYWORD_COLOR = AntlrColorConstants.ANTLR3_KEYWORD;

	public final static String EDITOR_KEYWORD_BOLD = AntlrColorConstants.ANTLR3_KEYWORD
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_KEYWORD_ITALIC = AntlrColorConstants.ANTLR3_KEYWORD
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_KEYWORD_STRIKETHROUGH = AntlrColorConstants.ANTLR3_KEYWORD
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_KEYWORD_UNDERLINE = AntlrColorConstants.ANTLR3_KEYWORD
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * Keyword return
	 */

	public final static String EDITOR_KEYWORD_RETURNS_COLOR = AntlrColorConstants.ANTLR3_KEYWORD_RETURNS;

	public final static String EDITOR_KEYWORD_RETURNS_BOLD = AntlrColorConstants.ANTLR3_KEYWORD_RETURNS
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_KEYWORD_RETURNS_ITALIC = AntlrColorConstants.ANTLR3_KEYWORD_RETURNS
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_KEYWORD_RETURNS_STRIKETHROUGH = AntlrColorConstants.ANTLR3_KEYWORD_RETURNS
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_KEYWORD_RETURNS_UNDERLINE = AntlrColorConstants.ANTLR3_KEYWORD_RETURNS
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * Target language keywords
	 */

	public final static String EDITOR_TARGET_LANGUAGE_KEYWORD_COLOR = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_KEYWORD;

	public final static String EDITOR_TARGET_LANGUAGE_KEYWORD_BOLD = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_KEYWORD
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_TARGET_LANGUAGE_KEYWORD_ITALIC = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_KEYWORD
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_TARGET_LANGUAGE_KEYWORD_STRIKETHROUGH = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_KEYWORD
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_TARGET_LANGUAGE_KEYWORD_UNDERLINE = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_KEYWORD
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * Target language literals
	 */

	public final static String EDITOR_TARGET_LANGUAGE_LITERAL_COLOR = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_LITERAL;

	public final static String EDITOR_TARGET_LANGUAGE_LITERAL_BOLD = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_LITERAL
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_TARGET_LANGUAGE_LITERAL_ITALIC = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_LITERAL
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_TARGET_LANGUAGE_LITERAL_STRIKETHROUGH = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_LITERAL
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_TARGET_LANGUAGE_LITERAL_UNDERLINE = AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_LITERAL
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * EBNF operators
	 */

	public final static String EDITOR_EBNF_OPERATORS_COLOR = AntlrColorConstants.ANTLR3_EBNF_OPERATORS;

	public final static String EDITOR_EBNF_OPERATORS_BOLD = AntlrColorConstants.ANTLR3_EBNF_OPERATORS
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_EBNF_OPERATORS_ITALIC = AntlrColorConstants.ANTLR3_EBNF_OPERATORS
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_EBNF_OPERATORS_STRIKETHROUGH = AntlrColorConstants.ANTLR3_EBNF_OPERATORS
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_EBNF_OPERATORS_UNDERLINE = AntlrColorConstants.ANTLR3_EBNF_OPERATORS
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * Tree operators
	 */

	public final static String EDITOR_TREE_OPERATORS_COLOR = AntlrColorConstants.ANTLR3_TREE_OPERATORS;

	public final static String EDITOR_TREE_OPERATORS_BOLD = AntlrColorConstants.ANTLR3_TREE_OPERATORS
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_TREE_OPERATORS_ITALIC = AntlrColorConstants.ANTLR3_TREE_OPERATORS
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_TREE_OPERATORS_STRIKETHROUGH = AntlrColorConstants.ANTLR3_TREE_OPERATORS
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_TREE_OPERATORS_UNDERLINE = AntlrColorConstants.ANTLR3_TREE_OPERATORS
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * rEWRITE operator
	 */

	public final static String EDITOR_REWRITE_OPERATOR_COLOR = AntlrColorConstants.ANTLR3_REWRITE_OPERATOR;

	public final static String EDITOR_REWRITE_OPERATOR_BOLD = AntlrColorConstants.ANTLR3_REWRITE_OPERATOR
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_REWRITE_OPERATOR_ITALIC = AntlrColorConstants.ANTLR3_REWRITE_OPERATOR
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_REWRITE_OPERATOR_STRIKETHROUGH = AntlrColorConstants.ANTLR3_REWRITE_OPERATOR
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_REWRITE_OPERATOR_UNDERLINE = AntlrColorConstants.ANTLR3_REWRITE_OPERATOR
			+ EDITOR_UNDERLINE_SUFFIX;

	public final static String EDITOR_OTHERS_COLOR = AntlrColorConstants.ANTLR3_DEFAULT;

	public final static String EDITOR_OTHERS_BOLD = EDITOR_OTHERS_COLOR
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_OTHERS_ITALIC = EDITOR_OTHERS_COLOR
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_OTHERS_STRIKETHROUGH = EDITOR_OTHERS_COLOR
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_OTHERS_UNDERLINE = EDITOR_OTHERS_COLOR
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * Strings
	 */

	public final static String EDITOR_STRING_COLOR = AntlrColorConstants.ANTLR3_STRING_LITERAL;

	public final static String EDITOR_STRING_BOLD = AntlrColorConstants.ANTLR3_STRING_LITERAL
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_STRING_ITALIC = AntlrColorConstants.ANTLR3_STRING_LITERAL
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_STRING_STRIKETHROUGH = AntlrColorConstants.ANTLR3_STRING_LITERAL
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_STRING_UNDERLINE = AntlrColorConstants.ANTLR3_STRING_LITERAL
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * local var reference
	 */

	public final static String EDITOR_LOCAL_VAR_REFERENCE_COLOR = AntlrColorConstants.ANTLR3_LOCAL_VAR_REFERENCE;

	public final static String EDITOR_LOCAL_VAR_REFERENCE_BOLD = AntlrColorConstants.ANTLR3_LOCAL_VAR_REFERENCE
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_LOCAL_VAR_REFERENCE_ITALIC = AntlrColorConstants.ANTLR3_LOCAL_VAR_REFERENCE
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_LOCAL_VAR_REFERENCE_STRIKETHROUGH = AntlrColorConstants.ANTLR3_LOCAL_VAR_REFERENCE
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	/**
	 * local var declaration
	 */

	public final static String EDITOR_LOCAL_VAR_DECLARATION_COLOR = AntlrColorConstants.ANTLR3_LOCAL_VAR_DECLARATION;

	public final static String EDITOR_LOCAL_VAR_DECLARATION_BOLD = AntlrColorConstants.ANTLR3_LOCAL_VAR_DECLARATION
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_LOCAL_VAR_DECLARATION_ITALIC = AntlrColorConstants.ANTLR3_LOCAL_VAR_DECLARATION
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_LOCAL_VAR_DECLARATION_STRIKETHROUGH = AntlrColorConstants.ANTLR3_LOCAL_VAR_DECLARATION
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	/**
	 * ANTLR Rules
	 */
	public final static String EDITOR_RULE_COLOR = AntlrColorConstants.ANTLR3_RULE;

	public final static String EDITOR_RULE_BOLD = AntlrColorConstants.ANTLR3_RULE
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_RULE_ITALIC = AntlrColorConstants.ANTLR3_RULE
			+ EDITOR_ITALIC_SUFFIX;

	public static final String EDITOR_RULE_FOLDING_ENABLED = "editor_rule_folding_enabled";

	/**
	 * ANTLR Lexer Rules
	 */
	public final static String EDITOR_LEXER_RULE_COLOR = AntlrColorConstants.ANTLR3_LEXER_RULE;

	public final static String EDITOR_LEXER_RULE_BOLD = AntlrColorConstants.ANTLR3_LEXER_RULE
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_LEXER_RULE_ITALIC = AntlrColorConstants.ANTLR3_LEXER_RULE
			+ EDITOR_ITALIC_SUFFIX;

	public static final String EDITOR_TOKENS_SPECIFICATION_FOLDING_ENABLED = "editor_tokens_specification_folding_enabled";

	/**
	 * ANTLR Scopes
	 */
	public static final String EDITOR_SCOPES_FOLDING_ENABLED = "editor_scopes_folding_enabled";

	public static final String EDITOR_BACKGROUND_COLOR = "editor_bg_color";

	public static Collection<String> getColorKeys() {
		//FIXME: Find a better way of get the color's keywords.
		Field[] fields = AntlrPreferenceConstants.class.getFields();
		Collection<String> keys = new ArrayList<String>();
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers())) {
				if (field.getName().contains("COLOR")) {
					try {
						field.setAccessible(true);
						keys.add(field.get(null).toString());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		return keys;
	}

	public static void initializeDefaultValues(IPreferenceStore store) {
		PreferenceConstants.initializeDefaultValues(store);
		// Comment's
		RGB commentRgb = new RGB(63, 127, 95);
		PreferenceConverter.setDefault(store, EDITOR_SINGLE_LINE_COMMENT_COLOR,
				commentRgb);
		store.setDefault(EDITOR_SINGLE_LINE_COMMENT_BOLD, false);
		store.setDefault(EDITOR_SINGLE_LINE_COMMENT_ITALIC, false);
		store.setDefault(EDITOR_COMMENTS_FOLDING_ENABLED, true);
		store
				.setDefault(PreferenceConstants.EDITOR_FOLDING_INIT_COMMENTS,
						true);
		store.setDefault(
				PreferenceConstants.EDITOR_FOLDING_INIT_HEADER_COMMENTS, true);

		PreferenceConverter.setDefault(store, EDITOR_MULTI_LINE_COMMENT_COLOR,
				commentRgb);
		store.setDefault(EDITOR_MULTI_LINE_COMMENT_BOLD, false);
		store.setDefault(EDITOR_MULTI_LINE_COMMENT_ITALIC, false);

		PreferenceConverter.setDefault(store, EDITOR_DOC_COMMENT_COLOR,
				new RGB(63, 95, 191));
		store.setDefault(EDITOR_DOC_COMMENT_BOLD, false);
		store.setDefault(EDITOR_DOC_COMMENT_ITALIC, false);

		PreferenceConverter.setDefault(store,
				EDITOR_TARGET_LANGUAGE_COMMENT_COLOR, new RGB(128, 128, 128));
		store.setDefault(EDITOR_TARGET_LANGUAGE_COMMENT_BOLD, false);
		store.setDefault(EDITOR_TARGET_LANGUAGE_COMMENT_ITALIC, false);

		// keyword's
		RGB keywordRgb = new RGB(127, 0, 85);
		PreferenceConverter.setDefault(store, EDITOR_KEYWORD_COLOR, keywordRgb);
		store.setDefault(EDITOR_KEYWORD_BOLD, true);
		store.setDefault(EDITOR_KEYWORD_ITALIC, false);

		PreferenceConverter.setDefault(store, EDITOR_KEYWORD_RETURNS_COLOR,
				keywordRgb);
		store.setDefault(EDITOR_KEYWORD_RETURNS_BOLD, true);
		store.setDefault(EDITOR_KEYWORD_RETURNS_ITALIC, false);

		PreferenceConverter.setDefault(store,
				EDITOR_TARGET_LANGUAGE_KEYWORD_COLOR, new RGB(0, 0, 128));
		store.setDefault(EDITOR_TARGET_LANGUAGE_KEYWORD_BOLD, true);
		store.setDefault(EDITOR_TARGET_LANGUAGE_KEYWORD_ITALIC, false);
		// Literal's
		RGB literalRgb = new RGB(42, 0, 255);
		PreferenceConverter.setDefault(store, EDITOR_STRING_COLOR, literalRgb);
		PreferenceConverter.setDefault(store,
				EDITOR_TARGET_LANGUAGE_LITERAL_COLOR, new RGB(0, 128, 0));

		store.setDefault(EDITOR_TARGET_LANGUAGE_LITERAL_BOLD, true);
		// Rule's
		RGB ruleRgb = new RGB(0, 64, 128);
		PreferenceConverter.setDefault(store, EDITOR_RULE_COLOR, ruleRgb);
		store.setDefault(EDITOR_RULE_BOLD, false);

		PreferenceConverter.setDefault(store, EDITOR_LOCAL_VAR_REFERENCE_COLOR,
				new RGB(0, 0, 192));
		store.setDefault(EDITOR_LOCAL_VAR_REFERENCE_BOLD, false);

		RGB blackRgb = new RGB(0, 0, 0);
		PreferenceConverter.setDefault(store,
				EDITOR_LOCAL_VAR_DECLARATION_COLOR, blackRgb);
		store.setDefault(EDITOR_LOCAL_VAR_DECLARATION_BOLD, false);

		PreferenceConverter.setDefault(store, EDITOR_OTHERS_COLOR, blackRgb);

		PreferenceConverter
				.setDefault(store, EDITOR_LEXER_RULE_COLOR, blackRgb);
		store.setDefault(EDITOR_LEXER_RULE_ITALIC, true);
		// @ grammar actions
		PreferenceConverter.setDefault(store, EDITOR_GRAMMAR_ACTION_COLOR,
				keywordRgb);
		store.setDefault(EDITOR_GRAMMAR_ACTION_BOLD, false);

		PreferenceConverter.setDefault(store,
				EDITOR_UNKNOW_GRAMMAR_ACTION_COLOR, keywordRgb);
		store.setDefault(EDITOR_UNKNOW_GRAMMAR_ACTION_BOLD, false);
		store.setDefault(EDITOR_UNKNOW_GRAMMAR_ACTION_UNDERLINE, false);

		RGB red = new RGB(255, 0, 0);
		PreferenceConverter.setDefault(store, EDITOR_REWRITE_OPERATOR_COLOR,
				red);
		store.setDefault(EDITOR_REWRITE_OPERATOR_BOLD, false);
		store.setDefault(EDITOR_REWRITE_OPERATOR_UNDERLINE, false);

		// editor
		store.setDefault(EDITOR_SMART_INDENT, true);
		store.setDefault(EDITOR_CLOSE_STRINGS, true);
		store.setDefault(EDITOR_CLOSE_BRACKETS, true);
		store.setDefault(EDITOR_CLOSE_BRACES, true);
		store.setDefault(EDITOR_SMART_TAB, true);
		store.setDefault(EDITOR_SMART_PASTE, true);
		store.setDefault(EDITOR_SMART_HOME_END, true);
		store.setDefault(EDITOR_SUB_WORD_NAVIGATION, true);
		store.setDefault(EDITOR_TAB_WIDTH, 2);
		store.setDefault(EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE, true);

		// folding
		store.setDefault(PreferenceConstants.EDITOR_FOLDING_ENABLED, true);
		store.setDefault(EDITOR_OPTIONS_FOLDING_ENABLED, true);
		store.setDefault(EDITOR_TOKENS_SPECIFICATION_FOLDING_ENABLED, true);
		store.setDefault(EDITOR_SCOPES_FOLDING_ENABLED, true);
		store.setDefault(EDITOR_GRAMMAR_ACTION_FOLDING_ENABLED, true);
		store.setDefault(EDITOR_RULE_FOLDING_ENABLED, false);
		store
				.setDefault(
						AntlrPreferenceConstants.EDITOR_FOLDING_LINES_LIMIT, 2);

		store.setDefault(CodeFormatterConstants.FORMATTER_TAB_CHAR,
				CodeFormatterConstants.SPACE);
		store.setDefault(CodeFormatterConstants.FORMATTER_TAB_SIZE, "2");//$NON-NLS-1$
		store
				.setDefault(CodeFormatterConstants.FORMATTER_INDENTATION_SIZE,
						"2");//$NON-NLS-1$

		// NewScriptProjectPreferencePage.initDefaults(store);

		store.setDefault(PreferenceConstants.APPEARANCE_COMPRESS_PACKAGE_NAMES,
				false);
		store.setDefault(PreferenceConstants.APPEARANCE_METHOD_RETURNTYPE,
				false);
		store.setDefault(PreferenceConstants.APPEARANCE_METHOD_TYPEPARAMETERS,
				true);
		store.setDefault(
				PreferenceConstants.APPEARANCE_PKG_NAME_PATTERN_FOR_PKG_VIEW,
				""); //$NON-NLS-1$

		store.setDefault(PreferenceConstants.SHOW_SOURCE_MODULE_CHILDREN, true);

		// code assist
		store.setDefault(PreferenceConstants.CODEASSIST_AUTOACTIVATION, true);
		store.setDefault(
				PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS, ".$:");//$NON-NLS-1$
		store.setDefault(PreferenceConstants.CODEASSIST_AUTOACTIVATION_DELAY,
				300);
		store.setDefault(PreferenceConstants.CODEASSIST_INSERT_COMPLETION,
				false);
		// ######################################################################
		// ################
		// core options
		// ######################################################################
		// ################
		// code generator
		store.setDefault(AntlrConstants.ANTLR_CODE_GENERATOR_PROFILE, false);
		store.setDefault(AntlrConstants.ANTLR_CODE_GENERATOR_TRACE, false);
		store.setDefault(AntlrConstants.ANTLR_CODE_GENERATOR_X_DBG_ST, false);
		store.setDefault(AntlrConstants.ANTLR_CODE_GENERATOR_MAX_MEMORY, 0);
		store.setDefault(
				AntlrConstants.ANTLR_CODE_GENERATOR_X_MAX_SWITCH_CASE_LABELS,
				300);
		store.setDefault(AntlrConstants.ANTLR_CODE_GENERATOR_X_MIN_SWITCH_ALTS,
				3);
		store.setDefault(AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_FOLDER, "");
		store
				.setDefault(
						AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION,
						AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION_SAME_AS_GRAMMAR);
		store.setDefault(AntlrConstants.ANTLR_CODE_GENERATOR_DEBUG, false);
		store
				.setDefault(
						AntlrConstants.ANTLR_CODE_GENERATOR_APPEND_JAVA_PACKAGE_TO_OUTPUT_FOLDER,
						false);
		// save actions
		store.setDefault(AntlrConstants.ANTLR_SAVE_ACTIONS_ENABLED, true);
		store.setDefault(
				AntlrConstants.ANTLR_SAVE_ACTIONS_GENERATE_RESOURCES_ENABLED,
				true);
		store.setDefault(AntlrConstants.ANTLR_SAVE_ACTIONS_FORMAT_CODE_ENABLED,
				false);
		// builder
		store
				.setDefault(
						AntlrConstants.ANTLR_BUILDER_MAX_NUMBER_OF_PROBLEMS_REPORTED_PER_GRAMMAR,
						25);
		store
				.setDefault(
						AntlrConstants.ANTLR_BUILDER_INCLUDE_STACK_TRACE_ON_INTERNAL_ERRORS,
						false);
		store.setDefault(AntlrConstants.ANTLR_BUILDER_REPORT, false);
		store.setDefault(AntlrConstants.ANTLR_BUILDER_NFA, false);
		store.setDefault(AntlrConstants.ANTLR_BUILDER_DFA, false);
		store.setDefault(AntlrConstants.ANTLR_BUILDER_X_DFA, false);
		store.setDefault(AntlrConstants.ANTLR_BUILDER_X_NO_PRUNE, false);
		store.setDefault(AntlrConstants.ANTLR_BUILDER_X_NO_COLLAPSE, false);
		store.setDefault(AntlrConstants.ANTLR_BUILDER_X_DBG_CONVERSION, false);
		store.setDefault(AntlrConstants.ANTLR_BUILDER_X_NO_MERGE_STOP_STATES,
				false);
		store.setDefault(AntlrConstants.ANTLR_BUILDER_X_DFA_VERBOSE, false);
		store.setDefault(AntlrConstants.ANTLR_BUILDER_X_M, 4);
		store.setDefault(AntlrConstants.ANTLR_BUILDER_X_MAX_DFA_EDGES, 65534);
		store.setDefault(AntlrConstants.ANTLR_BUILDER_X_CONVERSION_TIME_OUT,
				1000);
		// general
		store
				.setDefault(
						AntlrConstants.ANTLR_GENERAL_MARK_GENERATED_RESOURCES_AS_DERIVED,
						true);
		
		//views
		store.setDefault(DFA_VIEW, false);
		
		store.setDefault(RAILROAD_VIEW, true);

		//color sync
		Collection<String> colorKeys = getColorKeys();
		for (String colorKey : colorKeys) {
			JFaceResources.getColorRegistry().put(colorKey,
					PreferenceConverter.getColor(store, colorKey));
		}
	}
}
