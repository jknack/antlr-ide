/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.editor;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ILabelDecorator;

public class StringTemplateOutlinePage extends ScriptOutlinePage {

	public StringTemplateOutlinePage(ScriptEditor editor, IPreferenceStore store) {
		super(editor, store);
	}

	@Override
	public void setInput(IModelElement inputElement) {
		super.setInput(inputElement);
	}

	@Override
	protected ILabelDecorator getLabelDecorator() {
		return new StringTemplateOutlineLabelDecorator();
	}
}
