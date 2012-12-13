/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.core.AntlrCore;
import org.eclipse.dltk.ui.preferences.PreferenceKey;

public interface AntlrSaveActionsPreferences {
	
	PreferenceKey SAVE_ACTIONS_ENABLED = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_SAVE_ACTIONS_ENABLED);

	PreferenceKey SAVE_ACTIONS_GENERATE_RESOURCES_ENABLED = new PreferenceKey(
			AntlrCore.PLUGIN_ID,
			AntlrConstants.ANTLR_SAVE_ACTIONS_GENERATE_RESOURCES_ENABLED);

	PreferenceKey SAVE_ACTIONS_FORMAT_CODE_ENABLED = new PreferenceKey(
			AntlrCore.PLUGIN_ID,
			AntlrConstants.ANTLR_SAVE_ACTIONS_FORMAT_CODE_ENABLED);

	PreferenceKey[] KEYS = { SAVE_ACTIONS_ENABLED,
			SAVE_ACTIONS_GENERATE_RESOURCES_ENABLED,
			SAVE_ACTIONS_FORMAT_CODE_ENABLED };

	String[] LABELS = {
			AntlrPreferenceMessages.SaveActions_text,
			AntlrPreferenceMessages.SaveActions_generate_resources,
			AntlrPreferenceMessages.SaveActions_format_code };
	
	boolean getBoolean(PreferenceKey key);
	
	void setBoolean(PreferenceKey key, boolean value);
}
