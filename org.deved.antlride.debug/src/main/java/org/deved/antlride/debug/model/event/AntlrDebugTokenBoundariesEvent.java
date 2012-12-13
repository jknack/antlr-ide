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

public class AntlrDebugTokenBoundariesEvent extends AntlrDebugEvent {

	private int nodeType;
	
	private int startIndex;
	
	private int stopIndex;

	public AntlrDebugTokenBoundariesEvent(AntlrDebugTarget debugTarget,AntlrDebugEventKind eventKind,
			String... properties) {
		super(debugTarget, eventKind);
		this.nodeType = Integer.parseInt(properties[1]);
		this.startIndex = Integer.parseInt(properties[2]);
		this.stopIndex = Integer.parseInt(properties[3]);
	}

	public int getNodeType() {
		return nodeType;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public int getStopIndex() {
		return stopIndex;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(nodeType);
		builder.append(' ');
		builder.append(startIndex);
		builder.append(' ');
		builder.append(stopIndex);
		return builder.toString();
	}
}
