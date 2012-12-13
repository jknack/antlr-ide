/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.breakpoints;

import org.deved.antlride.debug.AntlrDebugConstants;
import org.deved.antlride.debug.model.AntlrDebugTarget;
import org.deved.antlride.debug.model.AntlrThread;
import org.deved.antlride.debug.model.AntlrDebugEventListener;
import org.deved.antlride.debug.model.event.AntlrDebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.Breakpoint;
import org.eclipse.debug.core.model.IThread;

public abstract class AntlrBreakpoint extends Breakpoint implements AntlrDebugEventListener {

	protected AntlrDebugTarget fTarget;
	
	public String getModelIdentifier() {
		return AntlrDebugConstants.DEBUG_MODEL_ID;
	}
	
	public abstract String getId();
	
	public abstract String getMessage();
	
	public abstract String getName();

	public void install(AntlrDebugTarget target) {
		fTarget = target;
		fTarget.addEventListener(this);
	}
	
	protected void notifyThread() {
		if (fTarget != null) {
			try {
				IThread[] threads = fTarget.getThreads();
				if (threads.length == 1) {
					AntlrThread thread = (AntlrThread) threads[0];
					thread.suspendedBy(this);
				}
			} catch (DebugException e) {
			}
		}
	}
	
	protected abstract boolean match(AntlrDebugEvent event);
	
	public void remove() {
		fTarget.removeEventListener(this);
    	fTarget = null;    	
    }
	
	public boolean handleEvent(AntlrDebugEvent event) {
		if (match(event)) {
			notifyThread();
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return getMessage();
	}
}
