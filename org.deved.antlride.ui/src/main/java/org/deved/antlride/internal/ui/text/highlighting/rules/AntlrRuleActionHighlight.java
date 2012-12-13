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
import org.eclipse.jface.text.rules.Token;

public class AntlrRuleActionHighlight extends AntlrAbstractHighlight {
  private IToken fInvalidActionToken;

  public AntlrRuleActionHighlight(IToken actionToken, IToken invalidActionToken) {
    super(actionToken);
    fInvalidActionToken = invalidActionToken;
  }

  protected IToken doEvaluate(ICharacterScanner scanner) {
    consumeOne(scanner);
    String text = getTextReaded();
    if (text.equals("@")) {
      consumeWS(scanner);
      consumeWord(scanner);// action-scope | action-name
      text = getTextReaded();
      if (text.equals("init") || text.equals("after")) {
        return fSuccessToken;
      }
      return fInvalidActionToken;
    }
    unread(scanner);
    return Token.UNDEFINED;
  }
}
