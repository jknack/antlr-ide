/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *******************************************************************************/
package org.deved.antlride.core.formatter;

/**
 * Represent a formatter preference
 * 
 * @author Edgar Espina
 * 
 */
public interface AntlrFormatterPreference {

	/**
	 * Return the preference name
	 * 
	 * @return An string representation of the preference name
	 */
	String getName();

	/**
	 * Returns true if it's a boolean preference
	 * 
	 * @return True if it's a boolean preference
	 */
	boolean isBoolean();

	/**
	 * Returns true if it's a int preference
	 * 
	 * @return True if it's a int preference
	 */
	boolean isInt();

	/**
	 * Returns true if it's a string preference
	 * 
	 * @return True if it's a string preference
	 */
	boolean isString();

	/**
	 * The boolean preference value
	 * 
	 * @return The boolean preference value
	 */
	boolean booleanValue();

	/**
	 * The int preference value
	 * 
	 * @return The int preference value
	 */
	int intValue();

	/**
	 * The string preference value
	 * 
	 * @return The string preference value
	 */
	String stringValue();
}
