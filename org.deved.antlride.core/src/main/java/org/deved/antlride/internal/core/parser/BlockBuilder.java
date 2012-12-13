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
import org.deved.antlride.core.model.IStatement;

public interface BlockBuilder extends StatementBuilder, OptionsAware {

	IStatement build();
	
	BlockBuilder statement(StatementBuilder statement);
	
	BlockBuilder lp(CommonTree node);
	
	BlockBuilder rp(CommonTree node);
	
	BlockBuilder body();

	BlockBuilder start(CommonTree node);

	BlockBuilder end(CommonTree node);
}
