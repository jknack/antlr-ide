/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.core.formatter;

public interface AntlrFormatterOptions {

	public static final AntlrFormatterOptions DEFAULT = new AntlrDefaultFormatterOptions();

	public static enum IndentStyle {
		SPACE {
			@Override
			public String getTabChar() {
				return " ";
			}
		},
		TAB {
			@Override
			public String getTabChar() {
				return "\t";
			}
		};

		public static String[] toStringArray() {
			IndentStyle[] values = values();
			String[] array = new String[values.length];
			for (int i = 0; i < values.length; i++) {
				array[i] = values[i].name();
			}
			return array;
		}

		public abstract String getTabChar();
	}

	public static enum BracesStyle {
		SAME_LINE, NEXT_LINE;

		public static String[] toStringArray() {
			return new String[] { SAME_LINE.name(), NEXT_LINE.name() };
		}
	}

	int getInt(AntlrFormatterPreference preference);

	String getString(AntlrFormatterPreference preference);

	boolean getBoolean(AntlrFormatterPreference preference);

	<T extends Enum<T>> T getEnum(AntlrFormatterPreference preference,
			Class<T> enumType);

}