/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.internal.ui.text;

import org.deved.antlride.common.ui.SingleProjectProblem;
import org.deved.antlride.gunit.ui.GUnitUI;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.ui.text.ScriptOutlineInformationControl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.texteditor.ITextEditor;

public class GUnitOutlineInformationControl extends
		ScriptOutlineInformationControl {

	private ITextEditor fEditor;

	public GUnitOutlineInformationControl(ITextEditor editor, Shell parent,
			int shellStyle, int treeStyle, String commandId) {
		super(parent, shellStyle, treeStyle, commandId);
		fEditor = editor;
	}

	@SuppressWarnings("restriction")
	@Override
	@SingleProjectProblem
	protected Object getSelectedElement() {
		Object selectedElement = super.getSelectedElement();
		dispose();		
		if (fEditor != null && selectedElement != null) {			
			EditorUtility.revealInEditor(fEditor,
					(IModelElement) selectedElement);
		}
		return null;
	}

	@Override
	protected IPreferenceStore getPreferenceStore() {
		return GUnitUI.getDefault().getPreferenceStore();
	}

}
