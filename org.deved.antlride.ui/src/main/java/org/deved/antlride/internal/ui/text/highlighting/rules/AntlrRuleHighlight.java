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
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class AntlrRuleHighlight extends AntlrAbstractHighlight implements IRule {

  private IToken fLexerToken;

  public AntlrRuleHighlight(IToken ruleToken, IToken lexerToken) {
    super(ruleToken);
    fLexerToken = lexerToken;
  }

  @Override
  protected IToken doEvaluate(ICharacterScanner scanner) {
    consumeWord(scanner);
    String text = getTextReaded();
    if (text.length() > 0) {
      if (Character.isLowerCase(text.charAt(0))) {
        return fSuccessToken;
      }
      return fLexerToken;
    }
    unread(scanner);
    return Token.UNDEFINED;
  }

}
