/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.ast.ModelElementQuery;
import org.deved.antlride.debug.breakpoints.AntlrBreakpoint;
import org.deved.antlride.debug.breakpoints.AntlrLocationBreakpoint;
import org.deved.antlride.debug.breakpoints.AntlrTokenBreakpoint;
import org.deved.antlride.debug.model.event.AntlrDebugTokenEvent;
import org.deved.antlride.debug.model.event.AntlrDebugLTEvent;
import org.deved.antlride.debug.model.event.AntlrDebugRuleEvent;
import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.deved.antlride.debug.model.event.AntlrDebugEventFactory;
import org.deved.antlride.debug.model.event.AntlrDebugEventKind;
import org.deved.antlride.debug.model.event.AntlrDebugLocationEvent;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.IBreakpointManagerListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;

public class AntlrDebugTarget extends AntlrDebugElement implements
		IDebugTarget, IBreakpointManagerListener {

	private ILaunch fLaunch;

	private IProcess fProcess;

	private IThread[] fThreads;

	private AntlrThread fThread;

	private boolean fTerminated;

	private PrintWriter fRequestWriter;

	private Socket fSocket;

	private BufferedReader fRequestReader;

	private EventDispatchJob fEventDispatch;

	private Vector<AntlrDebugEventListener> fEventListeners = new Vector<AntlrDebugEventListener>();

	private List<AntlrDebugEvent> fDebugEvents = new Vector<AntlrDebugEvent>();

	private IGrammar fGrammar;

	private int fDecision;

	private int fPort;

	public AntlrDebugTarget(IGrammar grammar, ILaunch launch, IProcess process,
			int port) throws CoreException {
		super(null);
		this.fGrammar = grammar;
		fLaunch = launch;
		fPort = port;
		fProcess = process;
		// fEventListeners.add(this);
		try {
			// give interpreter a chance to start
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			fSocket = new Socket("localhost", port);
			fSocket.setTcpNoDelay(true);
			fRequestWriter = createWriter(fSocket);
			fRequestReader = createReader(fSocket);
		} catch (UnknownHostException e) {
			requestFailed("Unable to connect to ANTLR Debugger", e);
		} catch (IOException e) {
			requestFailed("Unable to connect to ANTLR Debugger", e);
		}
		fThread = new AntlrThread(this);
		fThreads = new IThread[] { fThread };
		fEventDispatch = new EventDispatchJob();
		fEventDispatch.schedule();
		IBreakpointManager breakpointManager = getBreakpointManager();
		breakpointManager.addBreakpointListener(this);
		breakpointManager.addBreakpointManagerListener(this);
	}

	private PrintWriter createWriter(Socket socket) throws IOException {
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF8");
		return new PrintWriter(new BufferedWriter(osw));
	}

	private BufferedReader createReader(Socket socket) throws IOException {
		return new BufferedReader(new InputStreamReader(
				socket.getInputStream(), "UTF8"));
	}

	public String getName() throws DebugException {
		return "ANTLR";// $-NON-NLS-1$;
	}

	private void started() {
		fireCreationEvent();
		installDeferredBreakpoints();
		// try {
		// resume();
		// } catch (DebugException e) {
		// }
	}

	private IBreakpoint[] getBreakpoints() {
		return getBreakpointManager().getBreakpoints(getModelIdentifier());
	}

	private void installDeferredBreakpoints() {
		IBreakpoint[] breakpoints = getBreakpoints();
		for (int i = 0; i < breakpoints.length; i++) {
			breakpointAdded(breakpoints[i]);
		}
	}

	public boolean hasBreakpointsEnabled() {
		IBreakpoint[] breakpoints = getBreakpoints();
		for (IBreakpoint breakpoint : breakpoints) {
			try {
				if (breakpoint.isEnabled()) {
					return true;
				}
			} catch (Exception ex) {

			}
		}
		return false;
	}

	public boolean addEventListener(AntlrDebugEventListener listener) {
		if (!fEventListeners.contains(listener)) {
			fEventListeners.add(listener);
			return true;
		}
		return false;
	}

	public IProcess getProcess() {
		return fProcess;
	}

	public synchronized IThread getThread() {
		return fThread;
	}

	public AntlrDebugEvent getEvent(int index) {
		AntlrDebugEvent event = fDebugEvents.get(index);
		if(event == null) {
//			System.out.println("NULL at " + index + " " + getEventCount());
			event = fDebugEvents.get(index);
		}
		return event;
	}

	public int getEventCount() {
		return fDebugEvents.size();
	}

	public AntlrDebugEvent[] getEvents() {
		return fDebugEvents.toArray(new AntlrDebugEvent[fDebugEvents.size()]);
	}

	public List<AntlrDebugEvent> getEventList() {
		return new ArrayList<AntlrDebugEvent>(fDebugEvents);
	}

	public IThread[] getThreads() throws DebugException {
		return fThreads;
	}

	public int getPort() {
		return fPort;
	}

	public boolean hasThreads() throws DebugException {
		return true;
	}

	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		return breakpoint instanceof AntlrBreakpoint;
	}

	public ILaunch getLaunch() {
		return fLaunch;
	}

	public IGrammar getGrammar() {
		return fGrammar;
	}

	public boolean canTerminate() {
		return getProcess().canTerminate();
	}

	public boolean isTerminated() {
		return fTerminated || getProcess().isTerminated();
	}

	public void terminate() throws DebugException {
		terminated();
	}

	public boolean canResume() {
		return !isTerminated() && isSuspended();
	}

	public boolean canSuspend() {
		return !isTerminated() && !isSuspended();
	}

	public boolean isSuspended() {
		return !isTerminated()
				&& (getThread() == null || getThread().isSuspended());
	}

	public void resume() throws DebugException {
		getThread().resume();
	}

	public void removeEventListener(AntlrDebugEventListener listener) {
		fEventListeners.remove(listener);
	}

	public void ack() throws DebugException {
		fRequestWriter.println("ack");
		fRequestWriter.flush();
		// System.out.println("sending signal!!");
	}

	public void suspend() throws DebugException {
	}

	@Override
	public AntlrDebugTarget getDebugTarget() {
		return this;
	}

	public void breakpointAdded(IBreakpoint breakpoint) {
		if (supportsBreakpoint(breakpoint)) {
			try {
				if (isBreakpointEnabled(breakpoint)
						|| !breakpoint.isRegistered()) {
					AntlrLocationBreakpoint antlrBreakpoint = (AntlrLocationBreakpoint) breakpoint;
					antlrBreakpoint.install(this);
				}
			} catch (CoreException e) {
			}
		}
	}

	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		if (supportsBreakpoint(breakpoint)) {
			if (isBreakpointEnabled(breakpoint)) {
				breakpointAdded(breakpoint);
			} else {
				breakpointRemoved(breakpoint, null);
			}
		}
	}

	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		if (supportsBreakpoint(breakpoint)) {
			AntlrBreakpoint antlrBreakpoint = (AntlrBreakpoint) breakpoint;
			antlrBreakpoint.remove();
		}
	}

	public boolean canDisconnect() {
		return false;
	}

	public void disconnect() throws DebugException {
	}

	public boolean isDisconnected() {
		return false;
	}

	public IMemoryBlock getMemoryBlock(long startAddress, long length)
			throws DebugException {
		return null;
	}

	public boolean supportsStorageRetrieval() {
		return false;
	}

	public void breakpointManagerEnablementChanged(boolean enabled) {
		IBreakpoint[] breakpoints = getBreakpointManager().getBreakpoints(
				getModelIdentifier());
		for (int i = 0; i < breakpoints.length; i++) {
			if (enabled) {
				breakpointAdded(breakpoints[i]);
			} else {
				breakpointRemoved(breakpoints[i], null);
			}
		}
	}

	private boolean isBreakpointEnabled(IBreakpoint breakpoint) {
		try {
			return breakpoint.isEnabled() && getBreakpointManager().isEnabled();
		} catch (CoreException e) {
			return false;
		}
	}

	private void closeReader() {
		try {
			fRequestReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fRequestReader = null;
	}

	private void closeWriter() {
		fRequestWriter.close();
		fRequestWriter = null;
	}

	private void closeSocket() {
		try {
			fSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fSocket = null;
	}

	private synchronized void terminated() {
		if (!isTerminated()) {
			try {
				fProcess.terminate();
			} catch (DebugException e) {
				e.printStackTrace();
			}
			closeReader();
			closeWriter();
			closeSocket();
			fTerminated = true;
			fThread = null;
			fThreads = new IThread[0];
			fEventListeners.clear();			
			// fEventListeners = null;
			// fGrammar = null;
			IBreakpointManager breakpointManager = getBreakpointManager();
			breakpointManager.removeBreakpointListener(this);
			breakpointManager.removeBreakpointManagerListener(this);
			fireTerminateEvent();
		}
	}

	class EventDispatchJob extends Job {

		public EventDispatchJob() {
			super("ANTLR Event Dispatch");
			setSystem(true);
		}

		protected IStatus run(IProgressMonitor monitor) {
			String event = "";
			String lastEvent = "";
			int eventCount = 0;
			started();
			while (!isTerminated() && event != null) {
				try {
					event = fRequestReader.readLine();
					if (event != null) {
						if (event.equals(lastEvent)) {
							ack();
						} else {
							boolean handle = false;
							AntlrDebugEvent debugEvent = createDebugEvent(event);
							fDebugEvents.add(debugEvent);
							preHandleEvent(debugEvent);
//							if(debugEvent.getEventKind() == AntlrDebugEventKind.EXCEPTION) {
//							System.out.println(event);
//							}
							for (int i = 0; i < fEventListeners.size(); i++) {
								AntlrDebugEventListener listener = fEventListeners
										.get(i);
								boolean h = listener.handleEvent(debugEvent);
								if (!handle)
									handle = h;
							}
							if (!handle && eventCount > 0) {
								/**
								 * eventCount > 0. eat the first two events.
								 * this events don't require ack
								 */
								postHandleEvent(debugEvent);
							}
							lastEvent = event;
						}
					}
					eventCount++;
				} catch (Exception e) {
					e.printStackTrace();
					if (!isTerminated()) {
						terminated();
					}
				}
			}
			return Status.OK_STATUS;
		}

	}

	private AntlrDebugEvent createDebugEvent(String event) {
		return AntlrDebugEventFactory.createFromString(this, event);
	}

	private Map<String, Object> createStackFrameContext(int stackFrameId,
			String ruleName, AntlrDebugLocationEvent locationEvent) {
		return createStackFrameContext(stackFrameId, ruleName, locationEvent
				.getLine(), locationEvent.getColumn());
	}

	private Map<String, Object> createStackFrameContext(int stackFrameId,
			String ruleName, int line, int column) {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put(AntlrStackFrame.ATTR_GRAMMAR_FILE, fGrammar.getFile().removeFirstSegments(1).toString());
		context.put(AntlrStackFrame.ATTR_ID, stackFrameId);
		context.put(AntlrStackFrame.ATTR_LINE_NUMBER, line);
		context.put(AntlrStackFrame.ATTR_COLUMN_NUMBER, column);
		context.put(AntlrStackFrame.ATTR_STACK_FRAME, ruleName);
		return context;
	}

	private List<AntlrDebugEvent> getRuleInvocationStack(
			boolean isTokenBreakpoint) {
		AntlrDebugEvent[] events = getEvents();
		List<AntlrDebugEvent> eRules = new ArrayList<AntlrDebugEvent>();
		for (int i = 0; i < events.length; i++) {
			AntlrDebugEvent e = events[i];
			if (e.getEventKind() == AntlrDebugEventKind.ENTER_RULE) {
				eRules.add(0, e);
			} else if (e.getEventKind() == AntlrDebugEventKind.EXIT_RULE) {
				eRules.remove(0);
			} else if (isTokenBreakpoint && i == events.length - 1) {
				AntlrDebugEventKind kind = events[i].getEventKind();
				if (kind == AntlrDebugEventKind.CONSUME_TOKEN
						|| kind == AntlrDebugEventKind.LT) {
					eRules.add(0, e);
				}
				// events[len - 1] consumeToken
				// events[len - 2] LT
				// events[len - 3] location
			}
		}
		List<AntlrDebugEvent> stack = new ArrayList<AntlrDebugEvent>();
		int rIndex = 0;
		AntlrDebugEvent de = null;
		AntlrDebugEvent location = null;
		for (int i = 0; i < eRules.size() - 1; i++) {
			de = eRules.get(i);
			rIndex = indexOf(events, de);

			int offset = 1;
			int j = rIndex - 1;
			while (j >= 0
					&& events[j].getEventKind() != AntlrDebugEventKind.LOCATION) {
				j--;
				offset++;
			}
			location = events[rIndex - offset];
			stack.add(location);
			stack.add(de);
		}
		// report the first rule declaration
		de = eRules.get(eRules.size() - 1);
		rIndex = indexOf(events, de);
		location = events[rIndex + 1];
		stack.add(location);
		stack.add(de);
		eRules.clear();
		eRules = null;
		events = null;
		return stack;
	}

	private int indexOf(AntlrDebugEvent[] events, AntlrDebugEvent e) {
		for (int i = 0; i < events.length; i++) {
			if (events[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}

	public IStackFrame[] getStackFrames() {
		List<IStackFrame> stackFrameList = new ArrayList<IStackFrame>();
		IBreakpoint[] breakpoints = fThread.getBreakpoints();
		AntlrLocationBreakpoint locationBreakpoint = null;
		boolean isTokenBreakpoint = false;
		if (breakpoints.length > 0) {
			locationBreakpoint = (AntlrLocationBreakpoint) breakpoints[0];
			isTokenBreakpoint = locationBreakpoint instanceof AntlrTokenBreakpoint;
		}
		List<AntlrDebugEvent> stack = getRuleInvocationStack(isTokenBreakpoint);
		Map<String, Object> stackFrameContext = null;
		for (int i = 0; i < stack.size();) {
			AntlrDebugLocationEvent locationEvent = (AntlrDebugLocationEvent) stack
					.get(i++);
			String elementName;
			AntlrDebugEvent e = stack.get(i++);
			if (e.getEventKind() == AntlrDebugEventKind.ENTER_RULE) {
				elementName = ((AntlrDebugRuleEvent) e).getRule();
			} else if (e.getEventKind() == AntlrDebugEventKind.CONSUME_TOKEN) {
				// Token
				elementName = ((AntlrDebugTokenEvent) e).getTokenName();
			} else {
				elementName = ((AntlrDebugLTEvent) e).getTokenName();
			}
			stackFrameContext = createStackFrameContext(stackFrameList.size(),
					elementName, locationEvent);
			stackFrameList.add(new AntlrStackFrame(fThread, stackFrameContext));
		}
		if (!isTokenBreakpoint) {
			int line = -1;
			int column = -1;
			try {
				line = locationBreakpoint.getLineNumber();
				column = locationBreakpoint.getColumnNumber();
			} catch (CoreException ex) {
			}
			// add the element at breakpoint location
			IModelElement elementAt = ModelElementQuery.getElementAt(fGrammar,
					line, column);
			stackFrameContext = createStackFrameContext(stackFrameList.size(),
					elementAt.getElementName(), line, column);
			stackFrameList.add(0, new AntlrStackFrame(fThread,
					stackFrameContext));
		}
		IStackFrame[] stackFrames = stackFrameList
				.toArray(new IStackFrame[stackFrameList.size()]);
		stackFrameList.clear();
		stackFrameList = null;
		return stackFrames;
	}

	public boolean isInDecision() {
		return fDecision != 0;
	}

	private void preHandleEvent(AntlrDebugEvent event) {
		// LT: ?-1 index-2 type-3 channel-4 line-5 column-6 text-7
		// ex: exception index line column
		switch (event.getEventKind()) {
		case ENTER_DECISION:
			fDecision++;
			break;
		case EXIT_DECISION:
			fDecision--;
			break;
		}
	}

	private void postHandleEvent(AntlrDebugEvent event) {
		// LT: ?-1 index-2 type-3 channel-4 line-5 column-6 text-7
		// ex: exception index line column
		switch (event.getEventKind()) {
		case TERMINATE: {
			terminated();
		}
			break;
		default: {
			try {
				ack();
			} catch (DebugException ex) {
				ex.printStackTrace();
			}
		}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (isTerminated()) {
			builder.append("<terminated>");
		}
		builder.append("ANTLR Debugger at localhost: ");
		builder.append(fPort);
		return builder.toString();
	}
}
