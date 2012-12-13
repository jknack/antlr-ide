/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class AntlrGrammarSaveActionsPage extends AntlrGrammarPropertyPage implements
		AntlrSaveActionsPreferences {

	private AntlrSaveActionsPageBuilder saveActionsPageBuilder;

	public AntlrGrammarSaveActionsPage() {
		saveActionsPageBuilder = new AntlrSaveActionsPageBuilder(this);
	}
	
	@Override
	protected PreferenceKey[] getKeys() {
		return KEYS;
	}
	
	@Override
	public boolean performOk() {
		savePreferences();
		return super.performOk();
	}

	@Override
	protected Control createContents(Composite parent) {
		return saveActionsPageBuilder.build(parent);
	}

	public boolean getBoolean(PreferenceKey key) {
		return Boolean.valueOf(getPreferences().get(key));
	}

	public void setBoolean(PreferenceKey key, boolean value) {
		getPreferences().put(key, Boolean.toString(value));
	}
	
}
