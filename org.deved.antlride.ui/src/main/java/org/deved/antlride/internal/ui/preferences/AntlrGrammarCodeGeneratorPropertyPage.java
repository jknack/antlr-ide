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

public class AntlrGrammarCodeGeneratorPropertyPage extends AntlrGrammarPropertyPage
		implements AntlrCodeGeneratorPreferences {

	private AntlrCodeGeneratorPageBuilder codeGeneratorPageBuilder;

	public AntlrGrammarCodeGeneratorPropertyPage() {
	}
	
	@Override
	protected PreferenceKey[] getKeys() {
		return KEYS;
	}

	@Override
	public boolean performOk() {
		return codeGeneratorPageBuilder.processChanges();
	}

	@Override
	protected Control createContents(Composite parent) {
		codeGeneratorPageBuilder = new AntlrCodeGeneratorPageBuilder(
				getShell(), this);

		Control form = codeGeneratorPageBuilder.createContents(parent);

		form.setEnabled(isEnabled());

		return form;
	}

	public void setString(PreferenceKey key, String value) {
		getPreferences().put(key, value);
	}

	public void setBoolean(PreferenceKey key, boolean value) {
		setString(key, Boolean.toString(value));
	}

	public boolean getBoolean(PreferenceKey key) {
		return Boolean.valueOf(getString(key));
	}

	public String getString(PreferenceKey key) {
		return getPreferences().get(key);
	}
}
