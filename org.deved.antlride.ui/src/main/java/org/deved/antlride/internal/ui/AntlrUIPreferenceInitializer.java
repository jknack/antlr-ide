/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui;

import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.deved.antlride.ui.AntlrUI;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.ui.editor.highlighting.SemanticHighlightingUtils;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.editors.text.EditorsUI;


public class AntlrUIPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = AntlrUI.getDefault().getPreferenceStore();

		EditorsUI.useAnnotationsPreferencePage(store);
		EditorsUI.useQuickDiffPreferencePage(store);
		AntlrPreferenceConstants.initializeDefaultValues(store);
		SemanticHighlightingUtils.initializeDefaultValues(store, AntlrUI.getDefault()
				.getTextTools().getSemanticHighlightings());
		store.setDefault(AntlrPreferenceConstants.FORMATTER_ID, "");
	}

}
