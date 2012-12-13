/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class AntlrCodeGeneratorBlock extends AbstractOptionsBlock implements
		AntlrCodeGeneratorPreferences {

	private AntlrCodeGeneratorPageBuilder codeGeneratorPageBuilder;

	public AntlrCodeGeneratorBlock(IStatusChangeListener context,
			IProject project, IWorkbenchPreferenceContainer container) {
		super(context, project, AntlrCodeGeneratorPreferences.KEYS,
				container);
		codeGeneratorPageBuilder = new AntlrCodeGeneratorPageBuilder(getShell(), this);
	}

	@Override
	protected boolean processChanges(IWorkbenchPreferenceContainer container) {
		return codeGeneratorPageBuilder.processChanges();
	}

	@Override
	protected Control createOptionsBlock(Composite parent) {
		return codeGeneratorPageBuilder.createContents(parent);
	}

	public boolean getBoolean(PreferenceKey key) {
		return super.getBoolean(key);
	}

	public String getString(PreferenceKey key) {
		return super.getString(key);
	}

	public void savePreferences() {
	}

	public void setBoolean(PreferenceKey key, boolean value) {
		super.setBoolean(key, value);
	}

	public void setString(PreferenceKey key, String value) {
		super.setString(key, value);
	}
}
