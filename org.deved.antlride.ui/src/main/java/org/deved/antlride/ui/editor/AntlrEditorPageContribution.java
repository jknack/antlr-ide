package org.deved.antlride.ui.editor;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

public interface AntlrEditorPageContribution {
	Viewer createPage(Composite composite);
	
	int order();
	
	String getText();
	
	String getId();

	String getImage();
}
