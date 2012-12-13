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

public interface AntlrCodeGeneratorPreferences {
	
	PreferenceKey OUTPUT_OPTION = new PreferenceKey(
			AntlrCore.PLUGIN_ID,
			AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_OPTION);

	PreferenceKey X_DBG_ST = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_CODE_GENERATOR_X_DBG_ST);

	PreferenceKey PROFILE = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_CODE_GENERATOR_PROFILE);

	PreferenceKey DEBUG = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_CODE_GENERATOR_DEBUG);

	PreferenceKey TRACE = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_CODE_GENERATOR_TRACE);

	PreferenceKey OUTPUT_FOLDER = new PreferenceKey(
			AntlrCore.PLUGIN_ID,
			AntlrConstants.ANTLR_CODE_GENERATOR_OUTPUT_FOLDER);

	PreferenceKey MAX_MEMORY = new PreferenceKey(
			AntlrCore.PLUGIN_ID, AntlrConstants.ANTLR_CODE_GENERATOR_MAX_MEMORY);

	PreferenceKey X_MAX_SWITCH_CASE_LABELS = new PreferenceKey(
			AntlrCore.PLUGIN_ID,
			AntlrConstants.ANTLR_CODE_GENERATOR_X_MAX_SWITCH_CASE_LABELS);

	PreferenceKey X_MIN_SWITCH_ALTS = new PreferenceKey(
			AntlrCore.PLUGIN_ID,
			AntlrConstants.ANTLR_CODE_GENERATOR_X_MIN_SWITCH_ALTS);

	PreferenceKey APPEND_JAVA_PACKAGE_TO_OUTPUT_FOLDER = new PreferenceKey(
			AntlrCore.PLUGIN_ID,
			AntlrConstants.ANTLR_CODE_GENERATOR_APPEND_JAVA_PACKAGE_TO_OUTPUT_FOLDER);

	PreferenceKey[] KEYS = {
			APPEND_JAVA_PACKAGE_TO_OUTPUT_FOLDER, OUTPUT_OPTION, OUTPUT_FOLDER,
			DEBUG, PROFILE, TRACE, X_DBG_ST, MAX_MEMORY,
			X_MAX_SWITCH_CASE_LABELS, X_MIN_SWITCH_ALTS };
	
	String getString(PreferenceKey key);
	
	boolean getBoolean(PreferenceKey key);
	
	void setString(PreferenceKey key, String value);
	
	void setBoolean(PreferenceKey key, boolean value);
	
	void savePreferences();
}
