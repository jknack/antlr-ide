/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.common.ui.text.highlighting;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public abstract class AntlrBaseHighlightScanner extends AbstractScriptScanner {

	private IRule fResumeRule;
	
	public AntlrBaseHighlightScanner(IColorManager manager,
			IPreferenceStore store) {
		super(manager, store);
		initialize();
	}
	
	@Override
	public IToken nextToken() {
		fTokenOffset= fOffset;
		fColumn= UNDEFINED;

		if (fRules != null) {
			if(fResumeRule != null) {
				//take precedence
				fResumeRule.evaluate(this);
			}
			for (int i= 0; i < fRules.length; i++) {
				IToken token= (fRules[i].evaluate(this));
				if (!token.isUndefined())
					return token;
			}
		}

		if (read() == EOF)
			return Token.EOF;
		return fDefaultReturnToken;
	}

	public int getOffset() {
		return fOffset;
	}
	
	public void setResumeRule(IRule resumeRule) {
		fResumeRule = resumeRule;
	}
	
	public IRule getResumeRule() {
		return fResumeRule;
	}
}
