/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.ui.views.interpreter;

import org.deved.antlride.ui.views.interpreter.EvalElementViewer;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class EvalElementViewerDialog extends Dialog {

	private EvalElementViewer fViewer;
	
	public EvalElementViewerDialog(EvalElementViewer viewer) {
		super(Display.getCurrent().getActiveShell());
		fViewer = viewer;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		fViewer.getControl().setParent(parent);
		return fViewer.getControl();
	}

}
