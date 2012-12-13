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
import org.eclipse.dltk.ui.text.completion.ContentAssistInvocationContext;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;

public class AntlrCompletionProcessor extends ScriptCompletionProcessor {

	public AntlrCompletionProcessor(IEditorPart editor,
			ContentAssistant assistant, String partition) {
		super(editor, assistant, partition);
	}

	protected ContentAssistInvocationContext createContext(ITextViewer viewer,
			int offset) {
		return new AntlrContentAssistInvocationContext(this, viewer, offset,
				fEditor, getNatureId());
	}

	@Override
	protected String getNatureId() {
		return AntlrNature.NATURE_ID;
	}
}
