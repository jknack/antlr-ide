/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.stringtemplate.internal.ui;

import org.deved.antlride.stringtemplate.ui.StringTemplateUIConstants;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;

public class StringTemplatePreferenceConstants extends PreferenceConstants {

	/**
	 * keywords
	 */
	public static final String EDITOR_KEYWORD_COLOR = StringTemplateUIConstants.SH_KEYWORD;

	public final static String EDITOR_KEYWORD_BOLD = StringTemplateUIConstants.SH_KEYWORD
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_KEYWORD_ITALIC = StringTemplateUIConstants.SH_KEYWORD
			+ EDITOR_ITALIC_SUFFIX;

	/**
	 * string
	 */
	public static final String EDITOR_STRING_COLOR = StringTemplateUIConstants.SH_STRING;

	public final static String EDITOR_STRING_BOLD = StringTemplateUIConstants.SH_STRING
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_STRING_ITALIC = StringTemplateUIConstants.SH_STRING
			+ EDITOR_ITALIC_SUFFIX;
	/**
	 * comment
	 */
	public static final String EDITOR_COMMENT_COLOR = StringTemplateUIConstants.SH_COMMENT;

	public final static String EDITOR_COMMENT_BOLD = StringTemplateUIConstants.SH_COMMENT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_COMMENT_ITALIC = StringTemplateUIConstants.SH_COMMENT
			+ EDITOR_ITALIC_SUFFIX;

	/**
	 * stg single line comment
	 */
	public static final String EDITOR_STG_SINGLE_LINE_COMMENT_COLOR = StringTemplateUIConstants.SH_STG_SINGLE_LINE_COMMENT;

	public final static String EDITOR_STG_SINGLE_LINE_COMMENT_BOLD = StringTemplateUIConstants.SH_STG_SINGLE_LINE_COMMENT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_STG_SINGLE_LINE_COMMENT_ITALIC = StringTemplateUIConstants.SH_STG_SINGLE_LINE_COMMENT
			+ EDITOR_ITALIC_SUFFIX;

	/**
	 * stg multi line comment
	 */
	public static final String EDITOR_STG_MULTI_LINE_COMMENT_COLOR = StringTemplateUIConstants.SH_STG_MULTI_LINE_COMMENT;

	public final static String EDITOR_STG_MULTI_LINE_COMMENT_BOLD = StringTemplateUIConstants.SH_STG_MULTI_LINE_COMMENT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_STG_MULTI_LINE_COMMENT_ITALIC = StringTemplateUIConstants.SH_STG_MULTI_LINE_COMMENT
			+ EDITOR_ITALIC_SUFFIX;

	/**
	 * stg document comment
	 */
	public static final String EDITOR_STG_DOCUMENT_COMMENT_COLOR = StringTemplateUIConstants.SH_STG_DOC_COMMENT;

	public final static String EDITOR_STG_DOCUMENT_COMMENT_BOLD = StringTemplateUIConstants.SH_STG_DOC_COMMENT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_STG_DOCUMENT_COMMENT_ITALIC = StringTemplateUIConstants.SH_STG_DOC_COMMENT
			+ EDITOR_ITALIC_SUFFIX;

	/**
	 * <<|>>
	 */
	public static final String EDITOR_DOUBLE_ANGLE_BRACKETS_COLOR = StringTemplateUIConstants.SH_DOUBLE_ANGLE_BRACKETS;

	public final static String EDITOR_DOUBLE_ANGLE_BRACKETS_BOLD = StringTemplateUIConstants.SH_DOUBLE_ANGLE_BRACKETS
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_DOUBLE_ANGLE_BRACKETS_ITALIC = StringTemplateUIConstants.SH_DOUBLE_ANGLE_BRACKETS
			+ EDITOR_ITALIC_SUFFIX;
	/**
	 * <@...>
	 */
	public static final String EDITOR_TEMPLATE_REGION_COLOR = StringTemplateUIConstants.SH_TEMPLATE_REGION;

	public final static String EDITOR_TEMPLATE_REGION_BOLD = StringTemplateUIConstants.SH_TEMPLATE_REGION
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_TEMPLATE_REGION_ITALIC = StringTemplateUIConstants.SH_TEMPLATE_REGION
			+ EDITOR_ITALIC_SUFFIX;
	/**
	 * template name => template()::=
	 */
	public static final String EDITOR_TEMPLATE_COLOR = StringTemplateUIConstants.SH_TEMPLATE;

	public final static String EDITOR_TEMPLATE_BOLD = StringTemplateUIConstants.SH_TEMPLATE
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_TEMPLATE_ITALIC = StringTemplateUIConstants.SH_TEMPLATE
			+ EDITOR_ITALIC_SUFFIX;

	/**
	 * assign template => ::=
	 */
	public static final String EDITOR_ASSIGN_TEMPLATE_COLOR = StringTemplateUIConstants.SH_ASSIGN_TEMPLATE;

	public final static String EDITOR_ASSIGN_TEMPLATE_BOLD = StringTemplateUIConstants.SH_ASSIGN_TEMPLATE
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_ASSIGN_TEMPLATE_ITALIC = StringTemplateUIConstants.SH_ASSIGN_TEMPLATE
			+ EDITOR_ITALIC_SUFFIX;
	/**
	 * <|> operators
	 */
	public static final String EDITOR_TEMPLATE_DELIMETERS_COLOR = StringTemplateUIConstants.SH_TEMPLATE_DELIMETERS;

	public final static String EDITOR_TEMPLATE_DELIMETERS_BOLD = StringTemplateUIConstants.SH_TEMPLATE_DELIMETERS
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_TEMPLATE_DELIMETERS_ITALIC = StringTemplateUIConstants.SH_TEMPLATE_DELIMETERS
			+ EDITOR_ITALIC_SUFFIX;
	/**
	 * <<|>> operators
	 */
	public static final String EDITOR_DOUBLE_ANGLE_BRACKET_COLOR = StringTemplateUIConstants.SH_DOUBLE_ANGLE_BRACKETS;

	public final static String EDITOR_DOUBLE_ANGLE_BRACKET_BOLD = StringTemplateUIConstants.SH_DOUBLE_ANGLE_BRACKETS
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_DOUBLE_ANGLE_BRACKET_ITALIC = StringTemplateUIConstants.SH_DOUBLE_ANGLE_BRACKETS
			+ EDITOR_ITALIC_SUFFIX;
	/**
	 * <\...>
	 */
	public static final String EDITOR_ESPECIAL_CHARACTERS_COLOR = StringTemplateUIConstants.SH_ESPECIAL_CHARACTERS;

	public final static String EDITOR_ESPECIAL_CHARACTERS_BOLD = StringTemplateUIConstants.SH_ESPECIAL_CHARACTERS
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_ESPECIAL_CHARACTERS_ITALIC = StringTemplateUIConstants.SH_ESPECIAL_CHARACTERS
			+ EDITOR_ITALIC_SUFFIX;
	/**
	 * other's
	 */

	public final static String EDITOR_OTHERS_COLOR = StringTemplateUIConstants.SH_DEFAULT;

	public final static String EDITOR_OTHERS_BOLD = StringTemplateUIConstants.SH_DEFAULT
			+ EDITOR_BOLD_SUFFIX;

	public final static String EDITOR_OTHERS_ITALIC = StringTemplateUIConstants.SH_DEFAULT
			+ EDITOR_ITALIC_SUFFIX;

	public final static String EDITOR_OTHERS_STRIKETHROUGH = StringTemplateUIConstants.SH_DEFAULT
			+ EDITOR_STRIKETHROUGH_SUFFIX;

	public final static String EDITOR_OTHERS_UNDERLINE = StringTemplateUIConstants.SH_DEFAULT
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
		RGB templateColor = new RGB(0, 64, 128);
		// keywords
		PreferenceConverter.setDefault(store, EDITOR_KEYWORD_COLOR,
				keywordColor);
		store.setDefault(EDITOR_KEYWORD_BOLD, true);
		store.setDefault(EDITOR_KEYWORD_ITALIC, false);
		// template
		PreferenceConverter.setDefault(store, EDITOR_TEMPLATE_COLOR,
				templateColor);
		// string
		PreferenceConverter
				.setDefault(store, EDITOR_STRING_COLOR, literalColor);
		// <@...>
		PreferenceConverter.setDefault(store, EDITOR_TEMPLATE_REGION_COLOR,
				greyColor);
		// <\...>
		PreferenceConverter.setDefault(store, EDITOR_ESPECIAL_CHARACTERS_COLOR,
				literalColor);
		// ::=
		PreferenceConverter.setDefault(store, EDITOR_ASSIGN_TEMPLATE_COLOR,
				greyColor);
		// <|>
		PreferenceConverter.setDefault(store, EDITOR_TEMPLATE_DELIMETERS_COLOR,
				greyColor);
		// store.setDefault(EDITOR_SINGLE_ANGLE_BRACKET_BOLD, true);
		// <<|>>
		PreferenceConverter.setDefault(store,
				EDITOR_DOUBLE_ANGLE_BRACKET_COLOR, greyColor);
		// store.setDefault(EDITOR_DOUBLE_ANGLE_BRACKET_BOLD, true);
		// comment's
		RGB commentRgb = new RGB(63, 127, 95);
		PreferenceConverter.setDefault(store, EDITOR_COMMENT_COLOR, commentRgb);

		PreferenceConverter.setDefault(store,
				EDITOR_STG_MULTI_LINE_COMMENT_COLOR, commentRgb);

		PreferenceConverter.setDefault(store,
				EDITOR_STG_SINGLE_LINE_COMMENT_COLOR, commentRgb);
		PreferenceConverter.setDefault(store,
				EDITOR_STG_DOCUMENT_COMMENT_COLOR, new RGB(63, 95, 191));
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
