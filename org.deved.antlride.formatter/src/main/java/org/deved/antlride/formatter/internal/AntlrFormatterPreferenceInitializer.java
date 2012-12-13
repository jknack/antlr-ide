/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.formatter.internal;

import org.deved.antlride.core.formatter.AntlrFormatterPreference;
import org.deved.antlride.core.formatter.AntlrFormatterPreferences;
import org.deved.antlride.formatter.AntlrFormatterPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class AntlrFormatterPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = AntlrFormatterPlugin.getDefault()
				.getPreferenceStore();

		AntlrFormatterPreference[] preferences = AntlrFormatterPreferences
				.toList();

		for (AntlrFormatterPreference preference : preferences) {
			if (preference.isBoolean()) {
				store.setDefault(preference.getName(), preference
						.booleanValue());
			} else if (preference.isInt()) {
				store.setDefault(preference.getName(), preference.intValue());
			} else if (preference.isString()) {
				store
						.setDefault(preference.getName(), preference
								.stringValue());
			} else {
				throw new IllegalStateException("Missing strategy for: "
						+ preference);
			}
		}
	}

}
