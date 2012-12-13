/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.dot;

import java.util.ArrayList;
import java.util.Collection;

public class DotAttributesBuilder {
	private Collection<DotAttribute> attributeList = new ArrayList<DotAttribute>();
	
	public DotAttributesBuilder attribute(String name, String value) {
		attributeList.add(new DotAttribute(name, value));
		return this;
	}

	public DotAttribute[] build() {
		return attributeList.toArray(new DotAttribute[attributeList.size()]);
	}
	
}
