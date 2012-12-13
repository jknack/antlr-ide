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

public class AntlrDebugConsumeNodeEvent extends AntlrDebugEvent {

	private int nodeId;
	
	private int nodeType;
	
	private int line;
	
	private int column;
	
	private int tokenIndex;
	
	private String text;
	
	public AntlrDebugConsumeNodeEvent(AntlrDebugTarget debugTarget,AntlrDebugEventKind eventKind, String...properties) {
		super(debugTarget, eventKind);
		this.nodeId = Integer.parseInt( properties[1] );
		this.nodeType = Integer.parseInt( properties[2] );
		this.line = Integer.parseInt( properties[3] );
		this.column = Integer.parseInt( properties[4] );
		this.tokenIndex = Integer.parseInt( properties[5] );
		this.text = getTextFromEvent(6, properties);
	}

	public int getNodeId() {
		return nodeId;
	}
	
	public int getNodeType() {
		return nodeType;
	}
	
	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getTokenIndex() {
		return tokenIndex;
	}
	
	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(nodeId);
		builder.append(' ');
		builder.append(nodeType);
		builder.append(' ');
		builder.append(line);
		builder.append(' ');
		builder.append(column);
		builder.append(' ');
		builder.append(tokenIndex);
		builder.append(' ');
		builder.append(text);
		return builder.toString();
	}
}
