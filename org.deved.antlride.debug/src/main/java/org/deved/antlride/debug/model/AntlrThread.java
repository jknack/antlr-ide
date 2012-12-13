/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.model;

import org.deved.antlride.debug.breakpoints.AntlrBreakpoint;
import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;

public class AntlrThread extends AntlrDebugElement implements IThread, AntlrDebugEventListener {

	private static final IStackFrame[] NO_FRAMES = new IStackFrame[0];
	
	private static final IBreakpoint[] NO_BREAKPOINT = new IBreakpoint[0];
	
	private IBreakpoint fBreakpoint;
	
	private boolean fStepping;
	
	private boolean fSuspended;
	
	public AntlrThread(IDebugTarget target) {
		super(target);
	}	

	public IBreakpoint[] getBreakpoints() {
		if (fBreakpoint == null) {
			return NO_BREAKPOINT;
		}
		return new IBreakpoint[]{fBreakpoint};
	}

	public String getName() {
		return "ANTLR Thread";//$NON-NLS-1$
	}

	public int getPriority() throws DebugException {
		return 0;
	}

	public IStackFrame[] getStackFrames() throws DebugException {
		if (isSuspended()) {
			return getDebugTarget().getStackFrames();
		}
		return NO_FRAMES;
	}

	public IStackFrame getTopStackFrame() throws DebugException {
		IStackFrame[] frames = getStackFrames();
		if (frames.length > 0) {
			return frames[0];
		}
		return null;
	}

	public boolean hasStackFrames() throws DebugException {
		return isSuspended();
	}

	public boolean canResume() {
		return isSuspended();
	}

	public boolean canSuspend() {
		return !isSuspended();
	}

	public boolean isSuspended() {
		return fSuspended && !isTerminated();
	}

	public void resume() throws DebugException {		
		fireResumeEvent(DebugEvent.CLIENT_REQUEST);
		fireChangeEvent(DebugEvent.UNSPECIFIED);
		getDebugTarget().ack();
		fSuspended =false;
	}

	public void suspend() throws DebugException {
		System.out.println("thread suspend");
	}
	
	public void suspendedBy(IBreakpoint breakpoint) {
		fBreakpoint = breakpoint;
		fSuspended = true;
		suspended(DebugEvent.BREAKPOINT);		
	}
	
	private void suspended(int detail) {
		fireSuspendEvent(detail);
	}

	public boolean canStepInto() {
		return false;
	}

	public boolean canStepOver() {
		return false;//isSuspended();
	}

	public boolean canStepReturn() {
		return false;
	}

	public boolean isStepping() {
		return fStepping;
	}

	public void stepInto() throws DebugException {
		System.out.println("thread stepInto");
	}

	public void stepOver() throws DebugException {
		System.out.println("thread stepOver");
	}

	public void stepReturn() throws DebugException {
		System.out.println("thread stepReturn");
	}

	public boolean canTerminate() {
		return !isTerminated();
	}

	public boolean isTerminated() {
		return getDebugTarget().isTerminated();
	}

	public void terminate() throws DebugException {
		getDebugTarget().terminate();
	}

	public boolean handleEvent(AntlrDebugEvent event) {
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (isStepping()) {
			builder.append(getName());
			builder.append(" (stepping)");
		} else if (isSuspended()) {
			builder.append(getName());
			IBreakpoint[] breakpoints = getBreakpoints();
			if (breakpoints.length == 0) {
				builder.append(" (suspended)");
			} else {
				IBreakpoint breakpoint = breakpoints[0];
				if (breakpoint instanceof AntlrBreakpoint) {
					builder.append(" (suspended at ");
					builder.append(((AntlrBreakpoint)breakpoint).getName());
					builder.append(" breakpoint)");
				}
			}
		} else if (isTerminated()) {
			builder.append("<terminated> ");
			builder.append(getName());
		} else {
			builder.append(getName());
			builder.append(" (running)");
		}
		return builder.toString();
	}
}
