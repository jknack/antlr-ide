/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.core.model;

import org.eclipse.dltk.ast.Modifiers;

public enum StringTemplateKind {
	ST, ST_ANGLE_BRACKET, ST_IN_GROUP;
	
	public int kind() {
		return 2 << ( Modifiers.USER_MODIFIER + (ordinal() + 1));
	}
	
}
