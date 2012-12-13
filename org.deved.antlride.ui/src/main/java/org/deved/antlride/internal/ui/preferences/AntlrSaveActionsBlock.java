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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class AntlrSaveActionsBlock extends AbstractOptionsBlock implements
		AntlrSaveActionsPreferences {

	public AntlrSaveActionsBlock(IStatusChangeListener context,
			IProject project, IWorkbenchPreferenceContainer container) {
		super(context, project, KEYS, container);
	}

	public boolean getBoolean(PreferenceKey key) {
		return super.getBoolean(key);
	}

	public void setBoolean(PreferenceKey key, boolean value) {
		super.setBoolean(key, value);
	}

	@Override
	protected Control createOptionsBlock(Composite parent) {		
		return new AntlrSaveActionsPageBuilder(this).build(parent);
	}

	public void widgetDefaultSelected(SelectionEvent event) {

	}

	public void widgetSelected(SelectionEvent event) {
		Button button = (Button) event.widget;
		PreferenceKey key = (PreferenceKey) button.getData();
		setBoolean(key, button.getSelection());
	}

}
