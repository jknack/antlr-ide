/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model.dltk;

import org.deved.antlride.core.model.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.SourceField;
import org.eclipse.dltk.internal.core.SourceRange;

@SuppressWarnings("restriction")
public class FakeField extends SourceField {

	private ISourceRange range;
	
	public FakeField(ISourceModule parent, IModelElement element) {
		super((ModelElement)parent, element.getElementName());
		int offset = element.sourceStart();
		int length = element.sourceEnd() - offset;
		range = new SourceRange(offset,length);
	}
	
	public FakeField(ISourceModule parent, String elementName) {
		super((ModelElement)parent, elementName);
	}

	public ISourceRange getNameRange() throws ModelException {
		return range;
	}

	public ISourceRange getSourceRange() throws ModelException {
		return range;
	}
}
