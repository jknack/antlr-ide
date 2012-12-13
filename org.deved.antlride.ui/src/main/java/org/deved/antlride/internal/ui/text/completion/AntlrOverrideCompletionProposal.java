/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.completion;

import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.deved.antlride.ui.AntlrUI;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.text.completion.ScriptTypeCompletionProposal;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformation;

public class AntlrOverrideCompletionProposal extends
		ScriptTypeCompletionProposal {
	private String fMethodName;
	
	private int length;

	public AntlrOverrideCompletionProposal(IScriptProject jproject,
			ISourceModule cu, String methodName, String[] paramTypes,
			int start, int length, String displayName, String completionProposal) {
		super(completionProposal, cu, start, length, null, displayName, 0);
		Assert.isNotNull(jproject);
		Assert.isNotNull(methodName);
		Assert.isNotNull(paramTypes);
		Assert.isNotNull(cu);

		fMethodName = methodName;

		StringBuilder buffer = new StringBuilder();
		buffer.append(completionProposal);

		setReplacementString(buffer.toString());
		
		this.length = length;
	}

	@Override
	public int getReplacementLength() {
		boolean ovewrite = !getPreferenceStore().getBoolean(AntlrPreferenceConstants.CODEASSIST_INSERT_COMPLETION);
		return ovewrite? length: 1;
	}
	
	public IPreferenceStore getPreferenceStore() {
		return AntlrUI.getDefault().getPreferenceStore();
	}
	
	public CharSequence getPrefixCompletionText(IDocument document,
			int completionOffset) {
		return fMethodName;
	}
	
	protected boolean updateReplacementString(IDocument document, char trigger,
			int offset) throws CoreException, BadLocationException {
		final IDocument buffer = new Document(document.get());
		int index = offset - 1;
		while (index >= 0
				&& Character.isJavaIdentifierPart(buffer.getChar(index)))
			index--;
		final int length = offset - index - 1;
		buffer.replace(index + 1, length, " "); //$NON-NLS-1$
		return true;
	}

	public IContextInformation getContextInformation() {
		return new ContextInformation(getDisplayString(), getDisplayString());
	}
}
