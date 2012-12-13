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
import org.deved.antlride.core.model.ISourceElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.SourceRange;
import org.eclipse.dltk.internal.core.SourceType;

@SuppressWarnings("restriction")
public class FakeType extends SourceType {

	private int offset;
	
	private int length;
	
	public FakeType(ISourceModule parent, IModelElement element) {
		super((ModelElement) parent, element.getElementName());
		this.offset = element.sourceStart();
		this.length = element.sourceEnd() - offset;
	}
	
	public FakeType(ISourceModule parent, ISourceElement element) {
		super((ModelElement) parent, element.getText());
		this.offset = element.sourceStart();
		this.length = element.sourceEnd() - offset;
	}
	
	public FakeType(ISourceModule parent, String text, int sourceStart, int sourceEnd) {
		super((ModelElement) parent, text);
		this.offset = sourceStart;
		this.length = sourceEnd - offset;
	}

	public ISourceRange getNameRange() throws ModelException {
		return new SourceRange(offset,length);
	}

	public ISourceRange getSourceRange() throws ModelException {
		return new SourceRange(offset,length);
	}
}
