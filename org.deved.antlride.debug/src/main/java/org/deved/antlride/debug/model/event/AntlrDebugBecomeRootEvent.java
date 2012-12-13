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

public class AntlrDebugBecomeRootEvent extends AntlrDebugEvent {

	private int newNodeId;
	
	private int oldNodeId;
	
	public AntlrDebugBecomeRootEvent(AntlrDebugTarget debugTarget,AntlrDebugEventKind eventKind, String...properties) {
		super(debugTarget, eventKind);
		this.newNodeId = Integer.parseInt( properties[1] );
		this.oldNodeId = Integer.parseInt( properties[2] );
	}

	public int getNewNodeId() {
		return newNodeId;
	}
	
	public int getOldNodeId() {
		return oldNodeId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(newNodeId);
		builder.append(' ');
		builder.append(oldNodeId);
		return builder.toString();
	}
}
