/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.views.doc;

import org.deved.antlride.core.AntlrNature;
import org.deved.antlride.internal.ui.editor.AntlrMultiPageEditor;
import org.deved.antlride.ui.AntlrUI;
import org.eclipse.dltk.ui.infoviews.AbstractDocumentationView;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPart;

public class AntlrDocumentationView extends AbstractDocumentationView {
	private static final String EMPTY_HTML = "<html><head></head><body bgcolor=\"#ffffe1\"></body></html>";//$NON-NLS-1$

	@Override
	protected String getNature() {
		return AntlrNature.NATURE_ID;
	}

	@Override
	protected boolean isValidWorkbenchPart(IWorkbenchPart part) {
		return part instanceof AntlrMultiPageEditor;
	}
	
	@Override
	protected Object computeInput(Object input) {
		Object object = super.computeInput(input);
		if (object instanceof String) {
			// i don't have whites views
			String documentation = (String) object;
			if (documentation == null || documentation.length() == 0) {
				return EMPTY_HTML;
			}
		}
		return object;
	}

	@Override
	protected IPreferenceStore getPreferenceStore() {
		return AntlrUI.getDefault().getPreferenceStore();
	}

}
