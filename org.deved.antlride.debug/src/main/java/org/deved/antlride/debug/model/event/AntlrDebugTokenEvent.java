/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.model.event;

import org.deved.antlride.core.util.AntlrTextHelper;
import org.deved.antlride.debug.model.AntlrDebugTarget;

public class AntlrDebugTokenEvent extends AntlrDebugEvent {

	private int line;

	private int column;

	private String text;

	private int type;

	private String tokenName;

	public AntlrDebugTokenEvent(AntlrDebugTarget debugTarget,
			AntlrDebugEventKind eventKind, String event, String... properties) {
		super(debugTarget, eventKind);
		this.line = Integer.parseInt(properties[4]);
		this.column = Integer.parseInt(properties[5]);
		this.type = Integer.parseInt(properties[2]);
		if (eventKind == AntlrDebugEventKind.CONSUME_HIDDEN_TOKEN) {
			int i = event.lastIndexOf('\"');
			this.text =  AntlrTextHelper.unEscapeNewlines(event.substring(i + 1));
		} else {
			this.text = AntlrTextHelper.unEscapeNewlines(getTextFromEvent(6, properties));
		}
	}

	public int getLine() {
		return line;
	}

	public boolean isHidden() {
		return getEventKind() == AntlrDebugEventKind.CONSUME_HIDDEN_TOKEN;
	}

	public int getColumn() {
		return column;
	}

	public String getText() {
		return text;
	}

	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(tokenName);
		builder.append(' ');
		builder.append(line);
		builder.append(':');
		builder.append(column);
		builder.append(" \"");
		builder.append(text);
		builder.append("\"");
		return builder.toString();
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public String getTokenName() {
		return tokenName;
	}
}
