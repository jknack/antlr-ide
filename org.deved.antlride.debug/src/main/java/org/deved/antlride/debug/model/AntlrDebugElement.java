/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug.model;

import org.deved.antlride.debug.AntlrDebugConstants;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.DebugElement;
import org.eclipse.debug.core.model.IDebugTarget;

public class AntlrDebugElement extends DebugElement {

	public AntlrDebugElement(IDebugTarget target) {
		super(target);
	}

	public String getModelIdentifier() {
		return AntlrDebugConstants.DEBUG_MODEL_ID;
	}

	@Override
	public AntlrDebugTarget getDebugTarget() {
		return (AntlrDebugTarget) super.getDebugTarget();
	}
	
	protected IBreakpointManager getBreakpointManager() {
        return DebugPlugin.getDefault().getBreakpointManager();
    }
}
