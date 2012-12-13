/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.dot;

import java.util.Arrays;

public class DotEdge {
	public final DotNode from;
	
	public final DotNode to;
	
	public final DotAttribute[] attributes;

	public DotEdge(DotNode from, DotNode to,
			DotAttributesBuilder attributesBuilder) {
		this.from = from;
		this.to = to;
		this.attributes = attributesBuilder == null ? new DotAttribute[0]
				: attributesBuilder.build();
	}
	
	public DotEdge(DotNode from, DotNode to,
			DotAttribute[] attributes) {
		this.from = from;
		this.to = to;
		this.attributes = attributes;
	}
	
	public String getAttribute(String name) {
		for (DotAttribute attr : attributes) {
			if (attr.name.equals(name))
				return attr.value;
		}
		return null;
	}

	@Override
	public String toString() {
		return from.name + "->" + to.name + Arrays.toString(attributes);
	}
}
