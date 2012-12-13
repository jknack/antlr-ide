/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.completion;

import org.deved.antlride.core.AntlrNature;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;

public class AntlrContentAssistInvocationContext extends
		ScriptContentAssistInvocationContext {
	
	private AntlrCompletionProcessor fCompletionProcessor;

	public AntlrContentAssistInvocationContext(ISourceModule sourceModule) {
		super(sourceModule, AntlrNature.NATURE_ID);
	}
	
	public AntlrContentAssistInvocationContext(AntlrCompletionProcessor completionProcessor, ITextViewer viewer, int offset,
			IEditorPart editor, String natureId) {
		super(viewer, offset, editor, natureId);
		this.fCompletionProcessor = completionProcessor;
	}

	public AntlrCompletionProcessor getCompletionProcessor() {
		return fCompletionProcessor;
	}

}
