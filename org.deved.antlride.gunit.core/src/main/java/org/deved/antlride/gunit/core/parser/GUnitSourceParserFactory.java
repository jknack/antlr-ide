/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.core.parser;

import org.deved.antlride.gunit.internal.core.parser.GUnitSourceParser;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;

public class GUnitSourceParserFactory implements ISourceParserFactory {

	public ISourceParser createSourceParser() {
		return create();
	}

	public static ISourceParser create() {
		return new GUnitSourceParser();
	}

}
