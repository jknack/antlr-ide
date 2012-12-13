/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.text;

import org.deved.antlride.stringtemplate.ui.StringTemplateUI;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

public class StringTemplateContentAssistPreference extends ContentAssistPreference {

	private static StringTemplateContentAssistPreference sDefault;

	public static ContentAssistPreference getDefault() {
	    if (sDefault == null) {
	      sDefault = new StringTemplateContentAssistPreference();
	    }
	    return sDefault;
	  }
	
	@Override
	protected ScriptTextTools getTextTools() {
		return StringTemplateUI.getDefault().getTextTools();
	}

}
