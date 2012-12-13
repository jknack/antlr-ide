/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model;

public enum GrammarType {
	COMBINED(""), LEXER("lexer"), PARSER("parser"), TREE_PARSER("tree");
	
	private final String description;

	GrammarType(String description) {
		this.description = description;
	}
	
	public String description() {
		return description;
	}
}
