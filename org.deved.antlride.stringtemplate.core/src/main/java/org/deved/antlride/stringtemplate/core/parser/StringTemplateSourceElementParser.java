/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.core.parser;


import org.deved.antlride.stringtemplate.core.StringTemplateConstants;
import org.deved.antlride.stringtemplate.internal.core.parser.StringTemplateSourceParser;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.core.AbstractSourceElementParser;

public class StringTemplateSourceElementParser extends
		AbstractSourceElementParser {

	@Override
	protected String getNatureId() {
		return StringTemplateConstants.NATURE_ID;
	}

	@Override
	protected ModuleDeclaration parse(IModuleSource moduleSource) {
		StringTemplateSourceParser parser = new StringTemplateSourceParser();
		ModuleDeclaration moduleDeclaration = parser.parse(moduleSource, null);
		return moduleDeclaration;
	}
}
