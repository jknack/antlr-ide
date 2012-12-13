/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.partitions.rules;

import org.deved.antlride.common.ui.text.partitions.rules.AntlrBlockPartition;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class AntlrRuleActionPartition extends AntlrBlockPartition {

  public AntlrRuleActionPartition(IToken successToken) {
    super(successToken);
  }

  @Override
  protected IToken doEvaluate(ICharacterScanner scanner) {
    try {
      consumeOne(scanner);
      String text = getTextReaded();
      if (text.equals("@")) {
        consumeWS(scanner);
        consumeWord(scanner);
        text = getTextReaded();
        if (text.equals("init") || text.equals("after")) {
          consumeWS(scanner);
          consumeOne(scanner);
          text = getTextReaded();
          int l1 = fCharsReaded;
          if (text.equals("{")) {
            return doBlock(scanner);
          } else {
            unread(scanner, fCharsReaded - l1);
            return fSuccessToken;
          }
        }
      }
      unread(scanner);
      return Token.UNDEFINED;
    } finally {
    }
  }

}
