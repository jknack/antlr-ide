/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.ScriptTemplateContextType;
import org.eclipse.jface.text.IDocument;

public class AntlrTemplateContextType extends ScriptTemplateContextType {
	public static final String CONTEXT_TYPE_ID = "org.deved.antlride.antlrTemplateContextType";


	public AntlrTemplateContextType() {
	}

	public AntlrTemplateContextType(String id) {
		super(id);
	}

	public AntlrTemplateContextType(String id, String name) {
		super(id, name);
	}

	public ScriptTemplateContext createContext(IDocument document, int offset,
			int length, ISourceModule sourceModule) {
		return new AntlrTemplateContext(this, document, offset, length, sourceModule);
	}
}
