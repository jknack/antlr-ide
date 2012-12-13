/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.model.event;

import org.deved.antlride.core.util.AntlrTextHelper;
import org.deved.antlride.debug.model.AntlrDebugTarget;

public class AntlrDebugLTEvent extends AntlrDebugEvent {

	private int line;
	
	private int column;
	
	private String text;
	
	private String tokenName;

	private int type;
	
	public AntlrDebugLTEvent(AntlrDebugTarget debugTarget,AntlrDebugEventKind eventKind, String...properties) {
		super(debugTarget, eventKind);
		this.type = Integer.parseInt(properties[3]);
		this.line = Integer.parseInt(properties[5]);		
		this.column = Integer.parseInt(properties[6]);
		this.text = AntlrTextHelper.unEscapeNewlines( getTextFromEvent(7, properties) );
		if(eventKind == AntlrDebugEventKind.CONSUME_HIDDEN_TOKEN && text.length() == 0) {
			//bug when i use String.split(" ")
			text = " ";
		}
	}

	public int getType() {
		return type;
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
	
	public String getTokenName() {
		return tokenName;
	}
	
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(tokenName);
		builder.append(' ');
		builder.append(line);
		builder.append(':');
		builder.append(column);
		builder.append(" \"");
		builder.append(text);
		builder.append("\"");
		return builder.toString();
	}
}
