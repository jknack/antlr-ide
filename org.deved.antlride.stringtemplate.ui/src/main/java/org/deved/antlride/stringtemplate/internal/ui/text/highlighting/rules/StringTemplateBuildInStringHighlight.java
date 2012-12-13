/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.text.highlighting.rules;

import org.deved.antlride.stringtemplate.internal.ui.text.rules.StringTemplateBuildInStringRule;
import org.eclipse.jface.text.rules.IToken;

public class StringTemplateBuildInStringHighlight extends
		StringTemplateBuildInStringRule {

	public StringTemplateBuildInStringHighlight(IToken successToken) {
		super(successToken);
	}
	
}
