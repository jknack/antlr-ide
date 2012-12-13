/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package org.deved.antlride.formatter;

import java.net.URL;
import java.util.Map;

import org.deved.antlride.core.formatter.AntlrFormatterPreferences;
import org.deved.antlride.formatter.internal.AntlrFormatter;
import org.deved.antlride.formatter.preferences.AntlrFormatterModifyDialog;
import org.deved.antlride.ui.AntlrUI;
import org.eclipse.dltk.formatter.AbstractScriptFormatterFactory;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialogOwner;
import org.eclipse.dltk.ui.formatter.IScriptFormatter;
import org.eclipse.dltk.ui.preferences.PreferenceKey;

public class AntlrFormatterFactory extends AbstractScriptFormatterFactory {

	private static final String[] KEYS = AntlrFormatterPreferences.toStringArray();

	private static final URL PREVIEW = AntlrFormatterFactory.class
			.getResource("formatterPreview"); //$NON-NLS-1$;

	public PreferenceKey getProfilesKey() {
		return new PreferenceKey(AntlrFormatterPlugin.PLUGIN_ID,
				AntlrFormatterPreferences.FORMATTER_PROFILES);
	}

	public PreferenceKey getActiveProfileKey() {
		return new PreferenceKey(AntlrFormatterPlugin.PLUGIN_ID,
				AntlrFormatterPreferences.FORMATTER_ACTIVE_PROFILE);
	}

	public PreferenceKey[] getPreferenceKeys() {
		final PreferenceKey[] result = new PreferenceKey[KEYS.length];
		for (int i = 0; i < KEYS.length; ++i) {
			final String key = KEYS[i];
			final String qualifier;
			if (AntlrFormatterPreferences.Indent.TAB_CHAR.equals(key)
					|| AntlrFormatterPreferences.Indent.INDENTATION_SIZE
							.equals(key)
					|| AntlrFormatterPreferences.Indent.TAB_SIZE.equals(key)) {
				qualifier = AntlrUI.PLUGIN_ID;
			} else {
				qualifier = AntlrFormatterPlugin.PLUGIN_ID;
			}
			result[i] = new PreferenceKey(qualifier, key);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public IScriptFormatter createFormatter(String lineDelimiter,
			Map preferences) {
		return new AntlrFormatter(lineDelimiter, preferences);
	}

	public URL getPreviewContent() {
		return PREVIEW;
	}

	public IFormatterModifyDialog createDialog(
			IFormatterModifyDialogOwner dialogOwner) {
		return new AntlrFormatterModifyDialog(dialogOwner, this);
	}

}
