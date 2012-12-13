/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import org.deved.antlride.internal.ui.text.folding.AntlrEditorFoldingPreferenceBlock;
import org.eclipse.dltk.ui.preferences.FoldingConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.text.folding.IFoldingPreferenceBlock;
import org.eclipse.jface.preference.PreferencePage;

public class AntlrEditorFoldingBlock extends FoldingConfigurationBlock {

	public AntlrEditorFoldingBlock(OverlayPreferenceStore store, PreferencePage p) {
		super(store, p);
	}

	protected IFoldingPreferenceBlock createFoldingPreferenceBlock () {
		return new AntlrEditorFoldingPreferenceBlock(fStore);
	}
}
