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

public class DotNode {

	private DotAttribute[] attributes;
	public final String name;

	public DotNode(String name, DotAttribute[] attributes) {
		this.name = name;
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
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		if (attributes.length > 0) {
			builder.append(" ");
			builder.append(Arrays.toString(attributes));
		}
		return builder.toString();
	}
}
