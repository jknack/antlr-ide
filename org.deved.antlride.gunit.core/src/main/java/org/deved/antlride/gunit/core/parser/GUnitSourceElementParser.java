/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.core.parser;

import org.deved.antlride.gunit.core.GUnitConstants;
import org.deved.antlride.gunit.internal.core.parser.GUnitSourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.core.AbstractSourceElementParser;

public class GUnitSourceElementParser extends AbstractSourceElementParser {

	@Override
	protected String getNatureId() {
		return GUnitConstants.NATURE_ID;
	}

	@Override
	protected ModuleDeclaration parse(IModuleSource moduleSource) {
		GUnitSourceParser parser = new GUnitSourceParser();
		ModuleDeclaration moduleDeclaration = parser.parse(moduleSource, null);
		return moduleDeclaration;
	}
}
