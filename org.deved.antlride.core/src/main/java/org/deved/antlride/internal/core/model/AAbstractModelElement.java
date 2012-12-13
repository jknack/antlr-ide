/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.IModelElement;

public abstract class AAbstractModelElement implements
		IModelElement {

	protected IModelElement parent;

	protected int sourceEnd;

	protected int sourceStart;
			
	protected AAbstractModelElement(IModelElement parent, int sourceStart,
			int sourceEnd) {
		this.parent = parent;
		this.sourceStart = sourceStart;
		this.sourceEnd = sourceEnd;
	}
	
	protected AAbstractModelElement(int sourceStart, int sourceEnd) {
		this(null, sourceStart, sourceEnd);
	}

	protected AAbstractModelElement(IModelElement parent) {
		this(parent, 0, 0);
	}

	protected AAbstractModelElement() {
	}
	
	public boolean isIn(int position) {
		int start = sourceStart();
		int end = sourceEnd();
		return position >= start && position <= end;
	}	

	public IModelElement getParent() {
		return parent;
	}

	public int sourceEnd() {
		return sourceEnd;
	}

	public int sourceStart() {
		return sourceStart;
	}

	public void setParent(IModelElement parent) {
		this.parent = parent;
	}

	public void setSourceStart(int sourceStart) {
		this.sourceStart = sourceStart;
	}

	public void setSourceEnd(int sourceEnd) {
		this.sourceEnd = sourceEnd;
	}
}
