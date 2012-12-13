/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.jdt.sourcelookup;

import org.deved.antlride.debug.sourcelookup.AntlrSourceLookupParticipant;
import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;
import org.eclipse.jdt.internal.launching.JavaSourceLookupDirector;

@SuppressWarnings("restriction")
public class AntlrJavaSourceLookupDirector extends JavaSourceLookupDirector {
	@Override
	public void initializeParticipants() {
		super.initializeParticipants();
		addParticipants(new ISourceLookupParticipant[]{new AntlrSourceLookupParticipant()});
	}
}
