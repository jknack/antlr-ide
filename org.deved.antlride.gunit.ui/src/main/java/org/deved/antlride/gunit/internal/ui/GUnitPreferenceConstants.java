/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.gunit.internal.ui;

import org.deved.antlride.gunit.ui.GUnitUIConstants;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;

public class GUnitPreferenceConstants extends PreferenceConstants {

	/**
	 * keywords
	 */
	public static final String EDITOR_KEYWORD_COLOR = GUnitUIConstants.SH_KEYWORD;

	public final static String EDITOR_KEYWORD_BOLD = GUnitUIConstants.SH_KEYWORD
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_KEYWORD_ITALIC = GUnitUIConstants.SH_KEYWORD
			+ EDITOR_ITALIC_SUFFIX;

	public static final String EDITOR_OK_KEYWORD_COLOR = GUnitUIConstants.SH_OK_KEYWORD;

	public final static String EDITOR_OK_KEYWORD_BOLD = GUnitUIConstants.SH_OK_KEYWORD
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_OK_KEYWORD_ITALIC = GUnitUIConstants.SH_OK_KEYWORD
			+ EDITOR_ITALIC_SUFFIX;

	public static final String EDITOR_FAIL_KEYWORD_COLOR = GUnitUIConstants.SH_FAIL_KEYWORD;

	public final static String EDITOR_FAIL_KEYWORD_BOLD = GUnitUIConstants.SH_FAIL_KEYWORD
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_FAIL_KEYWORD_ITALIC = GUnitUIConstants.SH_FAIL_KEYWORD
			+ EDITOR_ITALIC_SUFFIX;

	public static final String EDITOR_DIRECTIVE_KEYWORD_COLOR = GUnitUIConstants.SH_DIRECTIVE;

	public final static String EDITOR_DIRECTIVE_KEYWORD_BOLD = GUnitUIConstants.SH_DIRECTIVE
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_DIRECTIVE_KEYWORD_ITALIC = GUnitUIConstants.SH_DIRECTIVE
			+ EDITOR_ITALIC_SUFFIX;

	/**
	 * string
	 */
	public static final String EDITOR_STRING_COLOR = GUnitUIConstants.SH_STRING;

	public final static String EDITOR_STRING_BOLD = GUnitUIConstants.SH_STRING
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_STRING_ITALIC = GUnitUIConstants.SH_STRING
			+ EDITOR_ITALIC_SUFFIX;

	public static final String EDITOR_ML_STRING_COLOR = GUnitUIConstants.SH_ML_STRING;

	public final static String EDITOR_ML_STRING_BOLD = GUnitUIConstants.SH_ML_STRING
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_ML_STRING_ITALIC = GUnitUIConstants.SH_ML_STRING
			+ EDITOR_ITALIC_SUFFIX;
	/**
	 * comment
	 */
	/**
	 * single line comment
	 */
	public static final String EDITOR_SINGLE_LINE_COMMENT_COLOR = GUnitUIConstants.SH_SINGLE_LINE_COMMENT;

	public final static String EDITOR_SINGLE_LINE_COMMENT_BOLD = GUnitUIConstants.SH_SINGLE_LINE_COMMENT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_SINGLE_LINE_COMMENT_ITALIC = GUnitUIConstants.SH_SINGLE_LINE_COMMENT
			+ EDITOR_ITALIC_SUFFIX;

	/**
	 * multi line comment
	 */
	public static final String EDITOR_MULTI_LINE_COMMENT_COLOR = GUnitUIConstants.SH_MULTI_LINE_COMMENT;

	public final static String EDITOR_MULTI_LINE_COMMENT_BOLD = GUnitUIConstants.SH_MULTI_LINE_COMMENT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_MULTI_LINE_COMMENT_ITALIC = GUnitUIConstants.SH_MULTI_LINE_COMMENT
			+ EDITOR_ITALIC_SUFFIX;

	/**
	 * doc comment
	 */
	public static final String EDITOR_DOC_COMMENT_COLOR = GUnitUIConstants.SH_DOC_COMMENT;

	public final static String EDITOR_DOC_COMMENT_BOLD = GUnitUIConstants.SH_DOC_COMMENT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_DOC_COMMENT_ITALIC = GUnitUIConstants.SH_DOC_COMMENT
			+ EDITOR_ITALIC_SUFFIX;

	/**
	 */
	public static final String EDITOR_TEST_COLOR = GUnitUIConstants.SH_TEST_SUITE;

	public final static String EDITOR_TEST_BOLD = GUnitUIConstants.SH_TEST_SUITE
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_TEST_ITALIC = GUnitUIConstants.SH_TEST_SUITE
			+ EDITOR_ITALIC_SUFFIX;

	/**
	 * <<|>> operators
	 */
	public static final String EDITOR_ML_STRING_OPERATORS_COLOR = GUnitUIConstants.SH_ML_STRING_OPERATORS;

	public final static String EDITOR_ML_STRING_OPERATORS_BOLD = GUnitUIConstants.SH_ML_STRING_OPERATORS
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_ML_STRING_OPERATORS_ITALIC = GUnitUIConstants.SH_ML_STRING_OPERATORS
			+ EDITOR_ITALIC_SUFFIX;

	public static final String EDITOR_EXPECT_OPERATOR_COLOR = GUnitUIConstants.SH_EXPECT_OPERATOR;

	public final static String EDITOR_EXPECT_OPERATOR_BOLD = GUnitUIConstants.SH_EXPECT_OPERATOR
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_EXPECT_OPERATOR_ITALIC = GUnitUIConstants.SH_EXPECT_OPERATOR
			+ EDITOR_ITALIC_SUFFIX;
	/**
	 * other's
	 */

	public final static String EDITOR_OTHERS_COLOR = GUnitUIConstants.SH_DEFAULT;

	public final static String EDITOR_OTHERS_BOLD = GUnitUIConstants.SH_DEFAULT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_OTHERS_ITALIC = GUnitUIConstants.SH_DEFAULT
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_OTHERS_STRIKETHROUGH = GUnitUIConstants.SH_DEFAULT
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_OTHERS_UNDERLINE = GUnitUIConstants.SH_DEFAULT
			+ EDITOR_UNDERLINE_SUFFIX;

	/**
	 * 
	 * @param store
	 */

	public static void initializeDefaultValues(IPreferenceStore store) {
		PreferenceConstants.initializeDefaultValues(store);
		// ##################################################################
		// ####################### Syntax Coloring ##########################
		// ##################################################################
		// color's
		RGB defaultColor = new RGB(0, 0, 0);
		RGB keywordColor = new RGB(127, 0, 85);
		RGB literalColor = new RGB(42, 0, 255);
		RGB greyColor = new RGB(100, 100, 100);
		RGB testColor = new RGB(0, 64, 128);
		// keywords
		PreferenceConverter.setDefault(store, EDITOR_KEYWORD_COLOR,
				keywordColor);
		store.setDefault(EDITOR_KEYWORD_BOLD, true);
		store.setDefault(EDITOR_KEYWORD_ITALIC, false);

		PreferenceConverter.setDefault(store, EDITOR_KEYWORD_COLOR,
				keywordColor);

		PreferenceConverter.setDefault(store, EDITOR_OK_KEYWORD_COLOR,
				keywordColor);
		store.setDefault(EDITOR_OK_KEYWORD_BOLD, true);

		PreferenceConverter.setDefault(store, EDITOR_FAIL_KEYWORD_COLOR,
				keywordColor);
		store.setDefault(EDITOR_FAIL_KEYWORD_BOLD, true);

		PreferenceConverter.setDefault(store, EDITOR_DIRECTIVE_KEYWORD_COLOR,
				keywordColor);
		// template
		PreferenceConverter.setDefault(store, EDITOR_TEST_COLOR, testColor);
		// string
		PreferenceConverter
				.setDefault(store, EDITOR_STRING_COLOR, literalColor);
		PreferenceConverter.setDefault(store, EDITOR_ML_STRING_COLOR,
				literalColor);

		// <<|>>
		PreferenceConverter.setDefault(store, EDITOR_ML_STRING_OPERATORS_COLOR,
				greyColor);
		// ->
		PreferenceConverter.setDefault(store, EDITOR_EXPECT_OPERATOR_COLOR,
				greyColor);
		// comment's
		RGB commentRgb = new RGB(63, 127, 95);
		PreferenceConverter.setDefault(store, EDITOR_MULTI_LINE_COMMENT_COLOR,
				commentRgb);

		PreferenceConverter.setDefault(store, EDITOR_SINGLE_LINE_COMMENT_COLOR,
				commentRgb);

		PreferenceConverter.setDefault(store, EDITOR_DOC_COMMENT_COLOR,
				new RGB(63, 95, 191));
		// other's
		PreferenceConverter
				.setDefault(store, EDITOR_OTHERS_COLOR, defaultColor);
		// ##################################################################
		// ########################### Folding ##############################
		// ##################################################################
		store.setDefault(PreferenceConstants.EDITOR_FOLDING_ENABLED, true);
		store.setDefault(PreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED,
				true);
		store
				.setDefault(PreferenceConstants.EDITOR_FOLDING_INIT_COMMENTS,
						true);
		store.setDefault(
				PreferenceConstants.EDITOR_FOLDING_INIT_HEADER_COMMENTS, true);
		store.setDefault(PreferenceConstants.EDITOR_FOLDING_LINES_LIMIT, 2);
		// ##################################################################
		// ###################### Editor Formatter ##########################
		// ##################################################################
		store.setDefault(CodeFormatterConstants.FORMATTER_TAB_CHAR,
				CodeFormatterConstants.SPACE);
		store.setDefault(CodeFormatterConstants.FORMATTER_TAB_SIZE, "2");//$NON-NLS-1$
		store
				.setDefault(CodeFormatterConstants.FORMATTER_INDENTATION_SIZE,
						"2");//$NON-NLS-1$
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
	}
}
