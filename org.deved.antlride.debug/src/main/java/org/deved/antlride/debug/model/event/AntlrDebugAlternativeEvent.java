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

public class AntlrDebugAlternativeEvent extends AntlrDebugEvent {

	private int number;
	
	public AntlrDebugAlternativeEvent(AntlrDebugTarget debugTarget, AntlrDebugEventKind eventKind, String...properties) {
		super(debugTarget, eventKind);
		this.number = Integer.parseInt( properties[properties.length-1] );
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(number);
		return builder.toString();
	}
}
