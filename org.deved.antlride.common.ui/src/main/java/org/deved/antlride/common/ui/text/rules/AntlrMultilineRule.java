/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.common.ui.text.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class AntlrMultilineRule implements IPredicateRule {
  private IToken fSuccesToken;
  private int fCount;
  private char fStartChar;
  private char fEndChar;
  //private StringBuilder builder;

  public AntlrMultilineRule(IToken succesToken, char startChar, char endChar) {
    fSuccesToken = succesToken;
    fStartChar = startChar;
    fEndChar = endChar;
    //builder = new StringBuilder();
  }

  public IToken evaluate(ICharacterScanner scanner) {
    int ch = scanner.read();
    //builder.setLength(0);
    if (ch != ICharacterScanner.EOF && ch == fStartChar) {
      boolean end = false;
      fCount = -1;
      do {
        //builder.append((char)ch);
        if (ch == fStartChar) {
          fCount++;
        } else if (ch == fEndChar) {
          fCount--;
          end = fCount < 0;
        }
        if (end) {
          //System.out.println(builder);
          return fSuccesToken;
        }
        ch = scanner.read();
      } while (ch != ICharacterScanner.EOF);
      //System.out.println(builder);
      return fSuccesToken;
    }
    scanner.unread();
    return Token.UNDEFINED;
  }

  public IToken evaluate(ICharacterScanner scanner, boolean resume) {
    return evaluate(scanner);
  }

  public IToken getSuccessToken() {
    return fSuccesToken;
  }

}
