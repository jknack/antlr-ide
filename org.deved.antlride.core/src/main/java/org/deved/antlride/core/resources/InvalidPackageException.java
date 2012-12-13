package org.deved.antlride.core.resources;

/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
public class InvalidPackageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4966328557325803699L;


	public InvalidPackageException(String message) {
		super(message);
	}

	public InvalidPackageException(Throwable cause) {
		super(cause);
	}

	public InvalidPackageException(String message, Throwable cause) {
		super(message, cause);
	}

}
