/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.highlighting.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;

public abstract class AntlrTargetLanguageHighlight extends AntlrAbstractHighlight {

  public AntlrTargetLanguageHighlight(IToken successToken) {
    super(successToken);
  }

  protected String getTargetLanguage(ICharacterScanner scanner) {
    return getGrammarOption(scanner, "language", "Java"); //$NON-NLS-1$
  }
}
