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


public class AntlrDebugCreateNodeEvent extends AntlrDebugEvent {

	private int nodeType;
	
	private int tokenIndex;
	
	public AntlrDebugCreateNodeEvent(AntlrDebugTarget debugTarget,AntlrDebugEventKind eventKind, String...properties) {
		super(debugTarget, eventKind);
		this.nodeType = Integer.parseInt( properties[1] );
		this.tokenIndex = Integer.parseInt( properties[2] );
	}

	public int getNodeType() {
		return nodeType;
	}
	
	public int getTokenIndex() {
		return tokenIndex;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(nodeType);
		builder.append(' ');
		builder.append(tokenIndex);
		return builder.toString();
	}
}
