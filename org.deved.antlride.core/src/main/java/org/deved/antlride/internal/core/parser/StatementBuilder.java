/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.parser;

import org.antlr.runtime.tree.CommonTree;
import org.deved.antlride.core.model.IStatement;

public interface StatementBuilder {
	IStatement build();
	
	StatementBuilder not(CommonTree notNode);
	
	StatementBuilder root(CommonTree rootNode);
	
	StatementBuilder bang(CommonTree bangNode);
	
	StatementBuilder treeOp(CommonTree treeOpNode);
	
	StatementBuilder ebnf(CommonTree ebnfNode);	
}
