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

public class AntlrDebugBacktrackEvent extends AntlrDebugEvent {

	private int level;
	
	private Boolean successful;
	
	public AntlrDebugBacktrackEvent(AntlrDebugTarget debugTarget,AntlrDebugEventKind eventKind, String...properties) {
		super(debugTarget, eventKind);
		this.level = Integer.parseInt( properties[1] );
		if(properties.length > 2) {
			successful = Boolean.valueOf(properties[2]);
		}
	}

	public int getNodeLevel() {
		return level;
	}
	
	public boolean isSuccessful() {
		return successful == null? true: successful.booleanValue();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(level);
		if(successful != null) {
			builder.append(' ');
			builder.append(successful);
		}
		return builder.toString();
	}
}
