/******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.debug.ui;

import java.util.ArrayList;
import java.util.List;

import org.deved.antlride.debug.AntlrDebugConstants;
import org.deved.antlride.debug.model.AntlrDebugTarget;
import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.deved.antlride.debug.model.event.AntlrDebugEventKind;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.ui.AbstractDebugView;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.contexts.DebugContextEvent;
import org.eclipse.debug.ui.contexts.IDebugContextListener;
import org.eclipse.debug.ui.contexts.IDebugContextManager;
import org.eclipse.debug.ui.contexts.IDebugContextService;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

public abstract class AntlrDebugView extends AbstractDebugView implements
		ISelectionListener, ISelectionChangedListener, IDebugContextListener,
		IDebugEventSetListener {

	private final Object lock = new Object();

	private boolean fTerminated;
	
	private int fDecision;
	
	private AntlrDebugEventFilter activeFilter = new AntlrDebugEventActiveFilter();
	
	private AntlrDebugEventFilter inactiveFilter = new AntlrDebugEventInactiveFilter();

	protected AntlrDebugTarget debugTarget;

	private class OnStartTask implements Runnable {
		private AntlrDebugTarget debugTarget;

		public OnStartTask(AntlrDebugTarget debugTarget) {
			this.debugTarget = debugTarget;
		}

		public void run() {
			onStart(debugTarget);
		}

	}

	private class OnTerminateTask implements Runnable {
		private AntlrDebugTarget debugTarget;

		public OnTerminateTask(AntlrDebugTarget debugTarget) {
			this.debugTarget = debugTarget;
		}

		public void run() {
			onTerminate(debugTarget);
		}

	}

	private class SyncDebugEventTask implements Runnable {
		private AntlrDebugEvent[] events;

		public SyncDebugEventTask(AntlrDebugEvent[] events) {
			this.events = events;
		}

		public void run() {
			onDebugEvents(events);
		}
	}

	private interface AntlrDebugEventFilter {
		public boolean acceptEvent(AntlrDebugEvent event);
	}
	
	private class AntlrDebugEventActiveFilter implements AntlrDebugEventFilter{

		public boolean acceptEvent(AntlrDebugEvent event) {
			return canDispatchEvent(event);
		}		
	}
	
	private class AntlrDebugEventInactiveFilter implements AntlrDebugEventFilter{

		public boolean acceptEvent(AntlrDebugEvent event) {
			return accept(event);
		}
		
	}
	
	public AntlrDebugView() {
	}

	private boolean canDispatchEvent(AntlrDebugEvent event) {
		AntlrDebugTarget debugTarget = event.getDebugTarget();
		if (!debugTarget.hasBreakpointsEnabled()) {
			return false;
		}
		if (!debugTarget.isSuspended()) {
			return false;
		}
		return accept(event);
	}

	private void dispatchEvents(AntlrDebugEvent[] events) {
		syncExec(new SyncDebugEventTask(events));
	}

	protected abstract void onDebugEvents(AntlrDebugEvent[] events);

	private void computeInput(ISelection selection) {
		AntlrDebugTarget target = findDebugTarget(selection);
		computeInput(target);
	}

	private void fireEvents(AntlrDebugTarget debugTarget, AntlrDebugEventFilter filter) {
		List<AntlrDebugEvent> events = new ArrayList<AntlrDebugEvent>();
		fDecision = 0;
		for (int i = 0; i < debugTarget.getEventCount(); i++) {
			AntlrDebugEvent e = debugTarget.getEvent(i);
			AntlrDebugEventKind kind = e.getEventKind();
			switch (kind) {
			case ENTER_DECISION:
				fDecision++;
				break;
			case EXIT_DECISION:
				fDecision--;
				break;
			}
			if (filter.acceptEvent(e)) {
				events.add(e);
			}
		}
		if (events.size() > 0) {
			dispatchEvents(events.toArray(new AntlrDebugEvent[events.size()]));
		}
		events.clear();
		events = null;
	}

	private void computeInput(AntlrDebugTarget debugTarget) {
		synchronized (lock) {
			if (debugTarget != null) {
				fireEvents(debugTarget, activeFilter);
			}
		}
	}

	protected boolean accept(AntlrDebugEvent e) {
		return true;
	}
	
	protected boolean isInDecision() {		
		return fDecision != 0;
	}

	private AntlrDebugTarget findDebugTarget(ISelection selection) {
		Object object = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection sSelection = (IStructuredSelection) selection;
			object = sSelection.getFirstElement();
		}
		return findDebugTarget(object);
	}

	private AntlrDebugTarget findDebugTarget(IAdaptable adaptable) {
		AntlrDebugTarget debugTarget = null;
		IDebugElement element = (IDebugElement) adaptable
				.getAdapter(IDebugElement.class);
		if (element != null) {
			if (element.getModelIdentifier().equals(
					AntlrDebugConstants.DEBUG_MODEL_ID)) {
				debugTarget = (AntlrDebugTarget) element.getDebugTarget();
			}
		} else {
			ILaunch launch = (ILaunch) adaptable.getAdapter(ILaunch.class);
			if (launch != null) {
				IDebugTarget[] debugTargets = launch.getDebugTargets();
				for (IDebugTarget dt : debugTargets) {
					if (dt instanceof AntlrDebugTarget) {
						debugTarget = (AntlrDebugTarget) dt;
						break;
					}
				}
			}
		}
		return debugTarget;
	}

	private AntlrDebugTarget findDebugTarget(Object object) {
		AntlrDebugTarget debugTarget = null;
		if (object instanceof AntlrDebugTarget) {
			debugTarget = (AntlrDebugTarget) object;
		} else if (object instanceof AntlrDebugEvent) {
			AntlrDebugEvent e = (AntlrDebugEvent) object;
			debugTarget = e.getDebugTarget();
		}
		if (debugTarget == null) {
			if (object instanceof IAdaptable) {
				debugTarget = findDebugTarget((IAdaptable) object);
			} else if (object == null) {
				IAdaptable adaptable = DebugUITools.getDebugContext();
				if (adaptable != null)
					debugTarget = findDebugTarget(adaptable);
			}
		}
		return debugTarget;
	}

	private boolean isAntlrDebugElement(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object o = ((IStructuredSelection) selection).getFirstElement();
			if (o instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable) o;
				IDebugElement element = (IDebugElement) adaptable
						.getAdapter(IDebugElement.class);
				if (element != null) {
					return (element.getModelIdentifier()
							.equals(AntlrDebugConstants.DEBUG_MODEL_ID));
				}
			}
		}
		return true;
	}

	@Override
	protected void configureToolBar(IToolBarManager tbm) {
	}

	@Override
	protected void createActions() {
	}

	protected Viewer createViewer(Composite parent) {
		Viewer viewer = internalCreateViewer(parent);
		fillActionBars(getViewSite().getActionBars());
		// viewer.addSelectionChangedListener(this);
		ISelectionService selectionService = getSelectionService();
		selectionService.addSelectionListener(IDebugUIConstants.ID_DEBUG_VIEW,
				this);
		getSite().setSelectionProvider(viewer);
		// getContextService().addDebugContextListener(this);
		DebugPlugin.getDefault().addDebugEventListener(this);
		return viewer;
	}

	protected void fillActionBars(IActionBars actionBars) {
		IToolBarManager toolBar = actionBars.getToolBarManager();
		fillToolBar(toolBar);
	}

	protected void fillToolBar(IToolBarManager toolBar) {
	}

	protected IDebugContextManager getDebugContextManager() {
		return DebugUITools.getDebugContextManager();
	}

	protected IDebugContextService getContextService() {
		return getDebugContextManager().getContextService(getWorkbenchWindow());
	}

	protected abstract Viewer internalCreateViewer(Composite parent);

	@Override
	protected void fillContextMenu(IMenuManager menu) {
	}

	@Override
	protected String getHelpContextId() {
		return null;
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		contextActivated(selection);
	}

	public void selectionChanged(SelectionChangedEvent event) {
		contextActivated(event.getSelection());
	}

	public void debugContextChanged(DebugContextEvent event) {
		if ((event.getFlags() & DebugContextEvent.ACTIVATED) > 0) {
			contextActivated(event.getContext());
		}
	}

	protected void contextActivated(ISelection selection) {
		if (!isAvailable() || !isAntlrDebugElement(selection)) {
			return;
		}

		computeInput(selection);
	}

	public void handleDebugEvents(DebugEvent[] events) {
		if (events != null && events.length > 0) {
			DebugEvent event = events[0];
			Object source = event.getSource();
			if (source instanceof IDebugElement) {
				IDebugElement element = (IDebugElement) event.getSource();
				if (element.getModelIdentifier().equals(
						AntlrDebugConstants.DEBUG_MODEL_ID)) {
					AntlrDebugTarget debugTarget = (AntlrDebugTarget) element
							.getDebugTarget();
					if (event.getKind() == DebugEvent.CREATE) {
						fTerminated = false;
						syncExec(new OnStartTask(debugTarget));
						computeInput(debugTarget);
					} else if (event.getKind() == DebugEvent.TERMINATE) {
						if (!fTerminated) {
							fTerminated = true;
							syncExec(new OnTerminateTask(debugTarget));
							computeInput((AntlrDebugTarget) null);
						}
					}
				}
			}
		}
	}

	protected void onStart(AntlrDebugTarget debugTarget) {
		this.debugTarget = debugTarget;
	}

	protected void onTerminate(AntlrDebugTarget debugTarget) {
		fireEvents(debugTarget, inactiveFilter);
	}

	protected IWorkbenchWindow getWorkbenchWindow() {
		return getSite().getWorkbenchWindow();
	}

	protected ISelectionService getSelectionService() {
		return getWorkbenchWindow().getSelectionService();
	}

	@Override
	public void dispose() {
		DebugUITools.getDebugContextManager().getContextService(
				getWorkbenchWindow()).removeDebugContextListener(this);
		ISelectionService selectionService = getSelectionService();
		selectionService.removeSelectionListener(
				IDebugUIConstants.ID_DEBUG_VIEW, this);
		DebugPlugin.getDefault().removeDebugEventListener(this);
		super.dispose();
	}
}
