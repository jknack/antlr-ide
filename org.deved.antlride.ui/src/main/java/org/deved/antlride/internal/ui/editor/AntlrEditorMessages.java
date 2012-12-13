/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.editor;

import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

public final class AntlrEditorMessages extends NLS {

	private AntlrEditorMessages() {
		// Do not instantiate
	}

  private static ResourceBundle fResourceBundle ;

	public static ResourceBundle getResourceBundle() {
	  return fResourceBundle;
	}

	public static String MemberFilterActionGroup_hide_rules_label;
	public static String MemberFilterActionGroup_hide_rules_tooltip;
	public static String MemberFilterActionGroup_hide_rules_description;
	public static String MemberFilterActionGroup_hide_scopes_label;
	public static String MemberFilterActionGroup_hide_scopes_tooltip;
	public static String MemberFilterActionGroup_hide_scopes_description;
	public static String GenerateCodeAction_label;

	static {
		NLS.initializeMessages(AntlrEditorMessages.class.getName(), AntlrEditorMessages.class);
		fResourceBundle = ResourceBundle.getBundle(AntlrEditorMessages.class.getName());
	}

}
