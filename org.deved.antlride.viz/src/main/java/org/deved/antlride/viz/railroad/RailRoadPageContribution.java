/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package org.deved.antlride.viz.railroad;

import org.deved.antlride.common.ui.AntlrImages;
import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.deved.antlride.ui.editor.AntlrEditorPageContribution;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

public class RailRoadPageContribution implements AntlrEditorPageContribution {

	public Viewer createPage(Composite composite) {
		return new RailRoadGraphViewer(composite);
	}
	
	public String getId() {
		return AntlrPreferenceConstants.RAILROAD_VIEW;
	}

	public String getImage() {
		return AntlrImages.RAILROAD;
	}

	public String getText() {
		return "Railroad View";
	}

	public int order() {
		return 2;
	}

}
