/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.formatter.preferences;

import java.net.URL;

import org.eclipse.dltk.ui.formatter.FormatterModifyTabPage;
import org.eclipse.dltk.ui.formatter.IFormatterControlManager;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialog;
import org.eclipse.swt.widgets.Composite;

public class AntlrFormatterCommentsPage extends FormatterModifyTabPage {

	/**
	 * @param dialog
	 */
	public AntlrFormatterCommentsPage(IFormatterModifyDialog dialog) {
		super(dialog);
	}

	protected void createOptions(IFormatterControlManager manager,
			Composite parent) {
		
		manager = new AntlrFormatterControlManager(manager);
		
//		Group commentWrappingGroup = SWTFactory.createGroup(parent,
//				AntlrFormatterMessages.RubyFormatterCommentsPage_commentFormatting, 2, 1, GridData.FILL_HORIZONTAL);
//		manager.createCheckbox(commentWrappingGroup,
//				AntlrFormatterConstants.WRAP_COMMENTS,
//				AntlrFormatterMessages.RubyFormatterCommentsPage_enableCommentWrapping, 2);
//		manager.createNumber(commentWrappingGroup,
//				AntlrFormatterConstants.WRAP_COMMENTS_LENGTH,
//				AntlrFormatterMessages.RubyFormatterCommentsPage_maximumLineWidthForComments);
	}

	protected URL getPreviewContent() {
		return getClass().getResource("wrapping-preview"); //$NON-NLS-1$
	}

}
