/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.internal.ui.preferences;

import org.deved.antlride.gunit.ui.GUnitUI;
import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ui.PreferencesAdapter;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class GUnitPreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage {
	private static final String PREFERENCE_PAGE_ID = "org.deved.antlride.gunit.ui.preferences";
	@Override
	protected AbstractOptionsBlock createOptionsBlock(
			IStatusChangeListener context, IProject project,
			IWorkbenchPreferenceContainer container) {
		return new GUnitBlock(context, project, container);
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
	protected void setPreferenceStore() {
		PreferencesAdapter preferences = new PreferencesAdapter(GUnitUI.getDefault().getPluginPreferences());
		setPreferenceStore(preferences);
	}

	@Override
	protected String getPreferencePageId() {
		return PREFERENCE_PAGE_ID;
	}

	@Override
	protected String getPropertyPageId() {
		return null;
	}
}
