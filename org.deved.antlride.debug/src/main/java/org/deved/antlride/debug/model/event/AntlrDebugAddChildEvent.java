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

public class AntlrDebugAddChildEvent extends AntlrDebugEvent {

	private int parentNodeId;
	
	private int childNodeId;
	
	public AntlrDebugAddChildEvent(AntlrDebugTarget debugTarget, AntlrDebugEventKind eventKind, String...properties) {
		super(debugTarget, eventKind);
		this.parentNodeId = Integer.parseInt( properties[1] );
		this.childNodeId = Integer.parseInt( properties[2] );
	}

	public int getParentNodeId() {
		return parentNodeId;
	}
	
	public int getChildNodeId() {
		return childNodeId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(parentNodeId);
		builder.append(' ');
		builder.append(childNodeId);
		return builder.toString();
	}
}
