package org.deved.antlride.internal.core.parser;
/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

import org.antlr.runtime.tree.CommonTree;

public interface CallExpressionBuilder extends StatementBuilder {
	CallExpressionBuilder name(CommonTree nameNode);
	
	CallExpressionBuilder parameters(CommonTree nameNode);
	
	CallExpressionBuilder labeled();
	
	CallExpressionBuilder option(CommonTree optionNode);
	
	CallExpressionBuilder option(CommonTree optionNode, CommonTree optionValue);
}
