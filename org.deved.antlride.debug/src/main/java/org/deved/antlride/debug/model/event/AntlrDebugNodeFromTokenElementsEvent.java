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

public class AntlrDebugNodeFromTokenElementsEvent extends AntlrDebugEvent {

	private int nodeId;
	
	private int nodeType;

	private String nodeName;
	
	public AntlrDebugNodeFromTokenElementsEvent(AntlrDebugTarget debugTarget,AntlrDebugEventKind eventKind, String...properties) {
		super(debugTarget, eventKind);
		this.nodeId = Integer.parseInt( properties[1] );
		this.nodeType = Integer.parseInt( properties[2] );
		this.nodeName = getTextFromEvent(3, properties);
	}

	public int getNodeId() {
		return nodeId;
	}
	
	public int getNodeType() {
		return nodeType;
	}
	
	public String getNodeName() {
		return nodeName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(nodeId);
		builder.append(' ');
		builder.append(nodeType);
		builder.append(' ');
		builder.append(nodeName);
		return builder.toString();
	}
}
