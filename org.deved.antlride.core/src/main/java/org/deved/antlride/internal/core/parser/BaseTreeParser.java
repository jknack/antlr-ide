/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.parser;

import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;

public abstract class BaseTreeParser extends TreeParser implements ANTLRTreeWalker {

	public BaseTreeParser(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
	}

	public BaseTreeParser(TreeNodeStream input) {
		super(input);
	}
}
