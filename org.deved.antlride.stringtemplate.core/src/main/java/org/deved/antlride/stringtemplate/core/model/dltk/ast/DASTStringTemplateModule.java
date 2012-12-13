/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.core.model.dltk.ast;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;

public class DASTStringTemplateModule extends ModuleDeclaration {

	public static final DASTStringTemplateModule ERROR = new DASTStringTemplateModule(0);
	
	public DASTStringTemplateModule(int sourceLength) {
		super(sourceLength);
	}

}
