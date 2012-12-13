/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.formatter.internal;

import java.util.Map;

import org.deved.antlride.core.AntlrLanguageToolkit;
import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.formatter.AntlrFormatterOptions;
import org.deved.antlride.core.formatter.AntlrFormatterPreference;
import org.deved.antlride.core.formatter.AntlrFormatterPreferences;
import org.deved.antlride.core.formatter.AntlrFormatterPreferences.BlankLines;
import org.deved.antlride.core.formatter.AntlrFormatterPreferences.Indent;
import org.deved.antlride.core.integration.AntlrLanguageTargetService;
import org.deved.antlride.core.model.IGrammar;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.formatter.AbstractScriptFormatter;
import org.eclipse.dltk.ui.formatter.FormatterException;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;

public class AntlrFormatter extends AbstractScriptFormatter implements
		AntlrFormatterOptions {

	private final String lineDelimiter;

	@SuppressWarnings("unchecked")
	public AntlrFormatter(String lineDelimiter, Map preferences) {
		super(preferences);
		this.lineDelimiter = lineDelimiter;
	}

	public TextEdit format(String source, int offset, int length,
			int indentationLevel) throws FormatterException {
		try {
			IGrammar grammar = AntlrSourceParserRepository.parse(source);
			if (grammar.isValid()) {
				AntlrLanguageTargetService languageTarget = AntlrLanguageToolkit.getDefault().getLanguageTargetService( grammar.getOption("language") );
				org.deved.antlride.core.formatter.AntlrFormatter formatter = new org.deved.antlride.core.formatter.AntlrFormatter(
						this).withLanguageTarget(languageTarget);
				formatter.accept(grammar);
				String formattedSource = formatter.content();
				if (!source.equals(formattedSource)) {
					return new ReplaceEdit(offset, length, formattedSource);
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean getBoolean(AntlrFormatterPreference option) {
		return super.getBoolean(option.getName());
	}

	public int getInt(AntlrFormatterPreference preference) {
		if (preference == Indent.INDENTATION_SIZE) {
			String stringIndentStyle = super
					.getString(AntlrFormatterPreferences.Indent.TAB_CHAR
							.getName());
			IndentStyle indentStyle = IndentStyle.valueOf(stringIndentStyle);
			// override the key if necessary
			if (indentStyle == IndentStyle.TAB) {
				preference = AntlrFormatterPreferences.Indent.TAB_SIZE;
			}
		}
		return super.getInt(preference.getName());
	}

	public String getString(AntlrFormatterPreference preference) {
		if (preference == BlankLines.NEW_LINE) {
			return lineDelimiter;
		} else if (preference == Indent.TAB_CHAR) {
			String stringIndentStyle = super.getString(preference.getName());
			IndentStyle indentStyle = IndentStyle.valueOf(stringIndentStyle);
			return indentStyle.getTabChar();
		}
		return super.getString(preference.getName());
	}

	public <T extends Enum<T>> T getEnum(AntlrFormatterPreference preference,
			Class<T> enumType) {
		String name = getString(preference);
		return Enum.valueOf(enumType, name);
	}
}
