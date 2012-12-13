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


public class AntlrDebugErrorNodeEvent extends AntlrDebugEvent {

	private int nodeId;
	
	private int tokenType;
	
	private String text;
	
	public AntlrDebugErrorNodeEvent(AntlrDebugTarget debugTarget,AntlrDebugEventKind eventKind, String...properties) {
		super(debugTarget, eventKind);
		this.nodeId = Integer.parseInt( properties[1] );
		this.tokenType = Integer.parseInt( properties[2] );
		this.text = getTextFromEvent(3, properties);
	}

	public int getNodeId() {
		return nodeId;
	}
	
	public int getTokenType() {
		return tokenType;
	}
	
	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(nodeId);
		builder.append(' ');
		builder.append(tokenType);
		builder.append(' ');
		builder.append(text);
		return builder.toString();
	}
}
