/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.ui;

import org.deved.antlride.core.model.IModelElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;

public class AntlrWorkbenchAdapter implements IWorkbenchAdapter {
	private final IModelElement element;
	
	private static final Object[] NO_CHILDREN = new Object[0];
	
	public AntlrWorkbenchAdapter(IModelElement element) {
		this.element = element;
	}
	
	public IModelElement getElement() {
		return element;
	}
	
	public Object[] getChildren(Object o) {
		return NO_CHILDREN;
	}

	public ImageDescriptor getImageDescriptor(Object object) {
		return null;
	}

	public String getLabel(Object o) {
		IModelElement element = null;
		if(o == this) {
			element = this.element;
		}
		else if(o instanceof IModelElement) {
			element = (IModelElement) o;
		}
		else if(o instanceof AntlrWorkbenchAdapter) {
			AntlrWorkbenchAdapter adapter = (AntlrWorkbenchAdapter) o;
			element = adapter.element;
		}
		return element == null?"": "'" + element.getElementName() + "'";
	}

	public Object getParent(Object o) {
		return null;
	}
}
