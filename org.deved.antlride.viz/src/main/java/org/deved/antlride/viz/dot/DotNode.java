/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.viz.dot;

import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.IContainer;

public class DotNode extends GraphNode {

	public DotNode(IContainer graphModel, int style, String text) {
		super(graphModel, style, text);
	}

	@Override
	public void highlight() {
		super.highlight();
	}
	
	@Override
	public void unhighlight() {
		super.unhighlight();
	}
}
