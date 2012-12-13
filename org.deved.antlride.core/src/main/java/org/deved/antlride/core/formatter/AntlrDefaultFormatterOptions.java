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

import org.deved.antlride.core.formatter.AntlrFormatterPreferences.Indent;

public class AntlrDefaultFormatterOptions implements AntlrFormatterOptions {

	private Map<AntlrFormatterPreference, Object> preferences;

	private String name;

	public AntlrDefaultFormatterOptions(String name,
			Map<AntlrFormatterPreference, Object> preferences) {
		this.preferences = new HashMap<AntlrFormatterPreference, Object>(
				preferences);
		this.name = name;
	}

	public AntlrDefaultFormatterOptions(String name) {
		this(name, AntlrFormatterPreferences.toMap());
	}
	
	public AntlrDefaultFormatterOptions() {
		this("default", AntlrFormatterPreferences.toMap());
	}

	public boolean getBoolean(AntlrFormatterPreference preference) {
		return (Boolean) preferences.get(preference);
	}

	public int getInt(AntlrFormatterPreference preference) {
		if (preference == Indent.INDENTATION_SIZE) {
			String stringIndentStyle = (String) preferences
					.get(AntlrFormatterPreferences.Indent.TAB_CHAR);
			IndentStyle indentStyle = IndentStyle.valueOf(stringIndentStyle);
			// override the key if necessary
			if (indentStyle == IndentStyle.TAB) {
				preference = AntlrFormatterPreferences.Indent.TAB_SIZE;
			}
		}
		return (Integer) preferences.get(preference);
	}

	public String getString(AntlrFormatterPreference preference) {
		if (preference == Indent.TAB_CHAR) {
			String stringIndentStyle = (String) preferences.get(preference);
			IndentStyle indentStyle = IndentStyle.valueOf(stringIndentStyle);
			if (indentStyle != null) {
				return indentStyle.getTabChar();
			}
		}
		return (String) preferences.get(preference);
	}

	public <T extends Enum<T>> T getEnum(AntlrFormatterPreference preference,
			Class<T> enumType) {
		String name = getString(preference);
		return Enum.valueOf(enumType, name);
	}

	public AntlrDefaultFormatterOptions set(
			AntlrFormatterPreference preference, int value) {
		preferences.put(preference, value);
		return this;
	}

	public AntlrDefaultFormatterOptions set(
			AntlrFormatterPreference preference, boolean value) {
		preferences.put(preference, value);
		return this;
	}

	public AntlrDefaultFormatterOptions set(
			AntlrFormatterPreference preference, String value) {
		preferences.put(preference, value);
		return this;
	}

	public AntlrDefaultFormatterOptions set(
			AntlrFormatterPreference preference, BracesStyle value) {
		preferences.put(preference, value);
		return this;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
