/******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.debug.ui;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.debug.model.AntlrDebugTarget;
import org.deved.antlride.debug.model.event.AntlrDebugTokenEvent;
import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.deved.antlride.debug.model.event.AntlrDebugEventKind;
import org.deved.antlride.ui.AntlrUIHelper;
import org.deved.antlride.ui.text.AntlrInputSourceViewer;
import org.deved.antlride.ui.text.AntlrTextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

public class AntlrDebugInputView extends AntlrDebugView {

	private AntlrInputSourceViewer fSourceViewer;
	
	public static final String ID = "org.deved.antlride.debug.ui.AntlrDebugInputView";

	public AntlrDebugInputView() {
	}

	@Override
	protected void onTerminate(AntlrDebugTarget debugTarget) {
		super.onTerminate(debugTarget);
	}
	
	public void setSelection(ISelection selection) {
		AntlrUIHelper.select(getTextWidget(), (AntlrTextSelection) selection);
	}

	@Override
	protected void onStart(AntlrDebugTarget debugTarget) {
		StyledText styledText = getTextWidget();
		styledText.setText("");
	}

	private void scrollText() {
		AntlrUIHelper.select(getTextWidget(), getTextWidget().getCharCount());
	}

	protected StyledText getTextWidget() {
		return fSourceViewer.getTextWidget();
	}

	@Override
	protected void onDebugEvents(AntlrDebugEvent[] events) {
		processEvents(events);
	}

	private void processEvents(AntlrDebugEvent[] events) {
		StringBuilder buffer = new StringBuilder();
		for (AntlrDebugEvent event : events) {
			buffer.append(getTextFromEvent(event));
			IGrammar grammar = event.getDebugTarget().getGrammar();
			fSourceViewer.setGrammar(grammar);
		}
		StyledText styledText = getTextWidget();
		styledText.setText(buffer.toString());
		buffer.delete(0, buffer.length());
		buffer = null;
		scrollText();
	}

	@Override
	protected boolean accept(AntlrDebugEvent e) {
		AntlrDebugEventKind kind = e.getEventKind();
		switch (kind) {
		case CONSUME_HIDDEN_TOKEN:
		case CONSUME_TOKEN:
			return !isInDecision();
		}
		return false;
	}

	private String getTextFromEvent(AntlrDebugEvent e) {
		return getTextFromEvent((AntlrDebugTokenEvent) e);
	}

	private String getTextFromEvent(AntlrDebugTokenEvent e) {
		return e.getText();
	}

	@Override
	protected Viewer internalCreateViewer(Composite parent) {
		fSourceViewer = new AntlrInputSourceViewer(parent, false);
		return fSourceViewer;
	}

}
