/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model;

public interface IStatement extends IModelElement {

	public enum EBNF {
		NONE(""), OPTIONAL("?"), CLOSURE("*"), POSITIVE_CLOSURE("+");

		private final String description;

		EBNF(String description) {
			this.description = description;
		}

		public static EBNF fromString(String value) {
			return OPTIONAL.description.equals(value) ? OPTIONAL
					: CLOSURE.description.equals(value) ? CLOSURE
							: POSITIVE_CLOSURE.description.equals(value) ? POSITIVE_CLOSURE
									: NONE;
		}

		public String description() {
			return description;
		}
	}

	EBNF getEbnfOperator();

	IRule getEnclosingRule();
}
