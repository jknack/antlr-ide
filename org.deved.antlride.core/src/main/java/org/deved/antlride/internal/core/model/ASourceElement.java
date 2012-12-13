/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.ISourceElement;

public class ASourceElement implements ISourceElement {

	private String text;

	private int sourceEnd;

	private int sourceStart;


	public ASourceElement(String text, int sourceStart, int sourceEnd
			) {
		this.text = text;
		this.sourceStart = sourceStart;
		this.sourceEnd = sourceEnd;
	}

	public String getText() {
		return text;
	}

	public boolean isIn(int position) {
		int start = sourceStart();
		int end = sourceEnd();
		return position >= start && position < end;
	}

	public int sourceEnd() {
		return sourceEnd;
	}

	public int sourceStart() {
		return sourceStart;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(text);
		builder.append(" [");
		builder.append(sourceStart);
		builder.append(":");
		builder.append(sourceEnd);
		builder.append("]");
		return builder.toString();
	}
}
