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

public class AntlrDebugRewindEvent extends AntlrDebugEvent {

	private int index = -1;

	public AntlrDebugRewindEvent(AntlrDebugTarget debugTarget,AntlrDebugEventKind eventKind,
			String... properties) {
		super(debugTarget, eventKind);
		if (properties.length > 1) {
			this.index = Integer.parseInt(properties[1]);
		}
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(index);
		return builder.toString();
	}
}
