/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.model.event;

import org.deved.antlride.debug.model.AntlrDebugTarget;

public class AntlrDebugExceptionEvent extends AntlrDebugEvent {

	private int line;

	private int column;

	private String exception;
	
	private String message;

	public AntlrDebugExceptionEvent(AntlrDebugTarget debugTarget,
			AntlrDebugEventKind eventKind, String event, String... properties) {
		super(debugTarget, eventKind);
		this.line = Integer.parseInt(properties[3]);
		this.column = Integer.parseInt(properties[4]);
		this.exception = properties[1];
		this.message = event.substring( event.indexOf('\"') + 1 );
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public String getException() {
		return exception;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(exception);
		builder.append(' ');
		builder.append(line);
		builder.append(':');
		builder.append(column);
		return builder.toString();
	}
}
