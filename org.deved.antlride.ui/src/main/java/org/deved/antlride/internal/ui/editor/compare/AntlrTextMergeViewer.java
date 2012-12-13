/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.editor.compare;

import org.deved.antlride.core.AntlrNature;
import org.deved.antlride.ui.AntlrUI;
import org.eclipse.compare.CompareConfiguration;
import org.eclipse.dltk.ui.compare.ScriptMergeViewer;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;

public class AntlrTextMergeViewer extends ScriptMergeViewer {

	public AntlrTextMergeViewer(Composite parent,
			CompareConfiguration configuration) {
		super(parent, configuration, AntlrNature.NATURE_ID);
	}

	@Override
	protected IPreferenceStore getPreferenceStore() {
		return AntlrUI.getDefault().getPreferenceStore();
	}

	@Override
	protected ScriptTextTools getTextTools() {
		return AntlrUI.getDefault().getTextTools();
	}

	@Override
	public String getTitle() {
		return "ANTLR Compare";
	}

}
