/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.debug.ui;

import org.deved.antlride.debug.breakpoints.AntlrBreakpoint;
import org.deved.antlride.debug.model.AntlrDebugElement;
import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.deved.antlride.ui.AntlrUIConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

public class AntlrDebugModelPresentation extends LabelProvider implements
		IDebugModelPresentation {

	public void setAttribute(String attribute, Object value) {
	}

	public String getText(Object element) {
		if (element instanceof AntlrDebugElement) {
			return element.toString();
		} else if (element instanceof AntlrBreakpoint) {
			return element.toString();
		} else if (element instanceof AntlrDebugEvent) {
			return element.toString();
		}
		return null;
	}

	public void computeDetail(IValue value, IValueDetailListener listener) {
		String detail = "";
		try {
			detail = value.getValueString();
		} catch (DebugException e) {
		}
		listener.detailComputed(value, detail);
	}

	public IEditorInput getEditorInput(Object element) {
		if (element instanceof IFile) {
			return new FileEditorInput((IFile) element);
		}
		if (element instanceof ILineBreakpoint) {
			return new FileEditorInput((IFile) ((ILineBreakpoint) element)
					.getMarker().getResource());
		}
		return null;
	}

	public String getEditorId(IEditorInput input, Object element) {
		return AntlrUIConstants.EDITOR_ID;
	}

}
