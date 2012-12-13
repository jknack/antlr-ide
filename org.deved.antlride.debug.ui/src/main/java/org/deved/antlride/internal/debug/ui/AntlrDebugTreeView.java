/******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.debug.ui;

import org.deved.antlride.core.model.evaluation.IEvalElement;
import org.deved.antlride.debug.model.AntlrDebugTarget;
import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.deved.antlride.debug.model.event.AntlrDebugEventKind;
import org.deved.antlride.debug.ui.AntlrDebugParser;
import org.deved.antlride.ui.views.interpreter.EvalElementViewer;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;

public class AntlrDebugTreeView extends AntlrDebugView {

	private EvalElementViewer viewer;

	@Override
	protected boolean accept(AntlrDebugEvent e) {
		AntlrDebugEventKind kind = e.getEventKind();
		return !isInDecision()
				&& (kind == AntlrDebugEventKind.CONSUME_TOKEN
						|| kind == AntlrDebugEventKind.ENTER_RULE
						|| kind == AntlrDebugEventKind.EXIT_RULE || kind == AntlrDebugEventKind.EXCEPTION);
	}

	@Override
	protected void onStart(AntlrDebugTarget debugTarget) {
		viewer.clear();
		super.onStart(debugTarget);		
	}

	@Override
	protected void fillToolBar(IToolBarManager toolBar) {
		toolBar.add(viewer.getAction(EvalElementViewer.CLEAR_DIAGRAM_ACTION));
		toolBar.add(viewer.getAction(EvalElementViewer.ZOOM_IN_ACTION));
		toolBar.add(viewer.getAction(EvalElementViewer.ZOOM_OUT_ACTION));
		super.fillToolBar(toolBar);
	}

	@Override
	protected void fillContextMenu(IMenuManager menu) {
		menu.add(viewer.getAction(EvalElementViewer.CLEAR_DIAGRAM_ACTION));
		menu.add(viewer.getAction(EvalElementViewer.ZOOM_IN_ACTION));
		menu.add(viewer.getAction(EvalElementViewer.ZOOM_OUT_ACTION));
		super.fillContextMenu(menu);
	}

	@Override
	protected void onDebugEvents(AntlrDebugEvent[] events) {
		IEvalElement element = AntlrDebugParser.evaluate(debugTarget.getGrammar(), events);
		viewer.setInput(element);
	}

	@Override
	protected Viewer internalCreateViewer(Composite parent) {
		viewer = new EvalElementViewer(parent);
		viewer.addSelectionChangedListener(new EvalElementSelectionListener());
		return viewer;
	}

	@Override
	public void dispose() {
		viewer.dispose();
		super.dispose();
	}

	private class EvalElementSelectionListener implements
			ISelectionChangedListener {

		public void selectionChanged(SelectionChangedEvent event) {
			IViewPart view = findView(AntlrDebugInputView.ID);
			if (view != null) {
				((AntlrDebugInputView) view).setSelection(event.getSelection());
			}
		}

	}
}
