/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.partitions.rules;

import org.deved.antlride.common.ui.text.partitions.rules.AntlrAbstractPartition;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class AntlrRuleRefScopePartition extends AntlrAbstractPartition {

  public AntlrRuleRefScopePartition(IToken successToken) {
    super(successToken);
  }

  @Override
  protected IToken doEvaluate(ICharacterScanner scanner) {
    consumeWord(scanner);
    String text = getTextReaded();
    if (text.equals("scope")) {
      consumeWS(scanner);
      consumeWord(scanner);
      text = getTextReaded();
      //scope ScopeRef1 ScopeRef2 ScopeRefN;
      while(!fEOF) {
        if(text.length() == 0) {
          break;
        }
        consumeWS(scanner);
        consumeOne(scanner);
        text = getTextReaded();
        if(text.equals(";")) {
          return fSuccessToken;
        } else {
          unread(scanner, 1);
        }
        consumeWS(scanner);
        consumeWord(scanner);
        text = getTextReaded();
      }
    }
    unread(scanner);
    return Token.UNDEFINED;
  }

}
