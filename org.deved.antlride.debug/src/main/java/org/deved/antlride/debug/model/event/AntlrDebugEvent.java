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

public abstract class AntlrDebugEvent implements Comparable<AntlrDebugEvent>{
	
	private AntlrDebugEventKind fEventKind;
	
	private AntlrDebugTarget fDebugTarget;	
	
	private static long nextId;
	
	private long id;
	
	public AntlrDebugEvent(AntlrDebugTarget debugTarget, AntlrDebugEventKind eventKind) {
		this.fEventKind = eventKind;
		this.fDebugTarget = debugTarget;
		id = nextId++;
	}
	
	public long getId() {
		return id;
	}
	
	public int compareTo(AntlrDebugEvent e) {
		return (int)( id - e.id);
	}
	
	public AntlrDebugTarget getDebugTarget() {
		return fDebugTarget;
	}
	
	public AntlrDebugEventKind getEventKind() {
		return fEventKind;
	}	
	
	protected String getTextFromEvent(int index, String[] properties) {
		StringBuilder builder = new StringBuilder();
		for (int i = index; i < properties.length; i++) {
			builder.append(properties[i]);
			if(i < properties.length - 1) {
				builder.append(" ");
			}
		}
		builder.deleteCharAt(0);
		return builder.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(fEventKind.description());
		builder.append(" ");
		return builder.toString();
	}
}
