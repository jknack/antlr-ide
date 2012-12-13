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

public class AntlrDebugLNEvent extends AntlrDebugEvent {

	private int line;
	
	private int column;
	
	private String text;
	
	public AntlrDebugLNEvent(AntlrDebugTarget debugTarget,AntlrDebugEventKind eventKind, String...properties) {
		super(debugTarget, eventKind);
		this.line = Integer.parseInt(properties[4]);
		this.column = Integer.parseInt(properties[5]);		
		this.text = getTextFromEvent(7, properties);
	}

	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}

	public String getText() {
		return text;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(line);
		builder.append(':');
		builder.append(column);
		builder.append(" \"");
		builder.append(text);
		builder.append("\"");
		return builder.toString();
	}
}
