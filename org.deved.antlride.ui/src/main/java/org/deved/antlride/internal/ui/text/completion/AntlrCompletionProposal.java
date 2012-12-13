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
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;

public class AntlrCompletionProposal extends ScriptCompletionProposal {

	private int length;
	
	public AntlrCompletionProposal(String replacementString,
			int replacementOffset, int replacementLength, Image image,
			String displayString, int relevance) {
		super(replacementString, replacementOffset, replacementLength, image,
				displayString, relevance);
		this.length = replacementLength;
	}

	@Override
	public int getReplacementLength() {
		boolean ovewrite = !getPreferenceStore().getBoolean(AntlrPreferenceConstants.CODEASSIST_INSERT_COMPLETION);
		return ovewrite? length: 1;
	}
	
	public IPreferenceStore getPreferenceStore() {
		return AntlrUI.getDefault().getPreferenceStore();
	}

	public AntlrCompletionProposal(String replacementString,
			int replacementOffset, int replacementLength, Image image,
			String displayString, int relevance, boolean isInDoc) {
		super(replacementString, replacementOffset, replacementLength, image,
				displayString, relevance, isInDoc);
	}

	protected boolean isSmartTrigger(char trigger) {
		return false;
	}

}
