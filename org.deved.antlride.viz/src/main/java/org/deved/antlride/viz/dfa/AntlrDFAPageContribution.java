/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.viz.dfa;

import org.deved.antlride.common.ui.AntlrImages;
import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.deved.antlride.ui.editor.AntlrEditorPageContribution;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

public class AntlrDFAPageContribution implements AntlrEditorPageContribution {

	public Viewer createPage(Composite composite) {
		return new AntlrDFAViewer(composite);
	}
	
	public String getId() {
		return AntlrPreferenceConstants.DFA_VIEW;
	}

	public int order() {
		return 3;
	}

	public String getText() {
		return "Decision Points";
	}
	
	public String getImage() {
		return AntlrImages.DECISION;
	}
}
