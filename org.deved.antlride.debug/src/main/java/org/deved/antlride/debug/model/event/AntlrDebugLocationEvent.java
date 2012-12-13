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

public class AntlrDebugLocationEvent extends AntlrDebugEvent {

	private int line;

	private int column;

	public AntlrDebugLocationEvent(AntlrDebugTarget debugTarget,AntlrDebugEventKind eventKind,
			String... properties) {
		super(debugTarget, eventKind);
		this.line = Integer.parseInt(properties[properties.length - 2]);
		this.column = Integer.parseInt(properties[properties.length - 1]);
	}

	public int getLine() {
		return line;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AntlrDebugLocationEvent) {
			AntlrDebugLocationEvent event = (AntlrDebugLocationEvent) obj;
			return getEventKind() == event.getEventKind() && line == event.line
					&& column == event.column;
		}
		return false;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(line);
		builder.append(':');
		builder.append(column);
		return builder.toString();
	}
}
