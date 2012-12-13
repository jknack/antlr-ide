/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.formatter.preferences;

import org.eclipse.dltk.ui.formatter.FormatterModifyDialog;
import org.eclipse.dltk.ui.formatter.IFormatterModifyDialogOwner;
import org.eclipse.dltk.ui.formatter.IScriptFormatterFactory;

public class AntlrFormatterModifyDialog extends FormatterModifyDialog {

	/**
	 * @param parent
	 */
	public AntlrFormatterModifyDialog(IFormatterModifyDialogOwner dialogOwner,
			IScriptFormatterFactory formatterFactory) {
		super(dialogOwner, formatterFactory);
	}

	protected void addPages() {
		addTabPage(AntlrFormatterMessages.Indentation,
				new AntlrFormatterIndentationPage(this));
		
		addTabPage(AntlrFormatterMessages.Braces,
				new AntlrFormatterBracesPage(this));
		
		addTabPage(AntlrFormatterMessages.WhiteSpaces,
				new AntlrFormatterWhiteSpacesPage(this));
		
		addTabPage(AntlrFormatterMessages.BlankLines,
				new AntlrFormatterBlankLinesPage(this));
		
		addTabPage(AntlrFormatterMessages.ControlStatements,
				new AntlrFormatterControlStatementsPage(this));
	}

}
