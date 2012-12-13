/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.highlighting.rules;

import org.deved.antlride.common.ui.text.rules.AntlrAbstractRule;
import org.deved.antlride.internal.ui.text.highlighting.AntlrAbstractHighlightScanner;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;

public abstract class AntlrAbstractHighlight extends AntlrAbstractRule {

  protected AntlrAbstractHighlight(IToken successToken) {
    super(successToken);
  }

  protected String getGrammarOption(ICharacterScanner scanner, String optionName, String defaultValue) {
    AntlrAbstractHighlightScanner scanner2 =
        (AntlrAbstractHighlightScanner) scanner;
    return scanner2.getGrammarOption(optionName, defaultValue); //$NON-NLS-1$
  }
}
