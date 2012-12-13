/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.templates;

import org.deved.antlride.ui.AntlrUI;
import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.jface.preference.IPreferenceStore;

public class AntlrTemplateAccess extends ScriptTemplateAccess {
	// Template
	private static final String CUSTOM_TEMPLATES_KEY = "org.deved.antlride.Templates"; //$NON-NLS-1$

	private static AntlrTemplateAccess instance;

	@Override
	protected String getContextTypeId() {	  
	  return AntlrTemplateContextType.CONTEXT_TYPE_ID;
	}
	
	@Override
	protected String getCustomTemplatesKey() {
	  return CUSTOM_TEMPLATES_KEY;
	}

	
	public static AntlrTemplateAccess getInstance() {
		if (instance == null){
			instance = new AntlrTemplateAccess();
		}

		return instance;
	}

	protected IPreferenceStore getPreferenceStore() {
		return AntlrUI.getDefault().getPreferenceStore();
	}
}
