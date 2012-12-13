/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import org.deved.antlride.ui.AntlrUI;
import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.PreferencesAdapter;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class AntlrSaveActionsPage extends
		AbstractConfigurationBlockPropertyAndPreferencePage {

	public static final String PROPERTY_PAGE_ID = "org.deved.antlride.propertyPage.saveActions";//$NON-NLS-1$

	public static final String PREFERENCE_PAGE_ID = "org.deved.antlride.ui.preferences.editor.saveActions";//$NON-NLS-1$

	@Override
	protected AbstractOptionsBlock createOptionsBlock(
			IStatusChangeListener newStatusChangedListener, IProject project,
			IWorkbenchPreferenceContainer container) {
		return new AntlrSaveActionsBlock(newStatusChangedListener, project,
				container);
	}

	@Override
	protected String getHelpId() {
		return null;
	}

	@Override
	protected String getProjectHelpId() {
		return null;
	}

	@Override	
	protected void setDescription() {
	}

	@Override
	@SuppressWarnings("deprecation")
	protected void setPreferenceStore() {
		PreferencesAdapter preferences = new PreferencesAdapter(AntlrUI
				.getDefault().getPluginPreferences());
		setPreferenceStore(preferences);
	}

	@Override
	protected String getPreferencePageId() {
		return PREFERENCE_PAGE_ID;
	}

	@Override
	protected String getPropertyPageId() {
		return PROPERTY_PAGE_ID;
	}
}
