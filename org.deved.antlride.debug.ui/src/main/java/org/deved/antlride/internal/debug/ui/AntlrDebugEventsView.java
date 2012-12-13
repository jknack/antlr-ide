/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.debug.ui;

import org.deved.antlride.debug.model.AntlrDebugTarget;
import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class AntlrDebugEventsView extends AntlrDebugView {

	private IBaseLabelProvider labelProvider = new LabelProvider();

	private int fMaxEvents = 25;

	private class AntlrDebugEventSorter extends ViewerSorter {
		@Override
		public int compare(Viewer viewer, Object o1, Object o2) {
			AntlrDebugEvent e1 = (AntlrDebugEvent) o1;
			AntlrDebugEvent e2 = (AntlrDebugEvent) o2;
			return e1.compareTo(e2) * -1;
		}
	}

	private class AntlrEventContentProvider implements
			IStructuredContentProvider {

		public Object[] getElements(Object object) {
			return (Object[]) object;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	@Override
	protected void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);
	}

	@Override
	protected void onTerminate(AntlrDebugTarget debugTarget) {
		super.onTerminate(debugTarget);
	}

	@Override
	protected void onDebugEvents(AntlrDebugEvent[] events) {
		int eventCount = events.length;
		if (eventCount > 0) {
			int size = Math.min(fMaxEvents, eventCount);
			AntlrDebugEvent[] maxevents = new AntlrDebugEvent[size];
			int j = 0;
			for (int i = eventCount - 1; i >= 0 && j < size; i--) {
				maxevents[j++] = events[i];
			}
			getViewer().setInput(maxevents);
			if (events.length > 0) {
				getViewer().setSelection(new StructuredSelection(events[0]));
			}
		}
	}

	@Override
	public void dispose() {
		labelProvider.dispose();
		labelProvider = null;
		super.dispose();
	}

	@Override
	protected Viewer internalCreateViewer(Composite parent) {
		TableViewer viewer = new TableViewer(parent);
		Table table = viewer.getTable();
		// TableColumn column = new TableColumn(table, SWT.BORDER);
		// column.setText("Events");
		table.setLinesVisible(true);
		viewer.setSorter(new AntlrDebugEventSorter());
		// viewer.setComparator(new ViewerComparator(new
		// InverseOrderCompartor()));
		viewer.setLabelProvider(labelProvider);
		viewer.setContentProvider(new AntlrEventContentProvider());
		return viewer;
	}
}
