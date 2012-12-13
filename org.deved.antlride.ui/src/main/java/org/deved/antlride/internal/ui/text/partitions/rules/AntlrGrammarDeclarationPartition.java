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
/**
 * grammar structure:
 * ( cmt:DOC_COMMENT )?
 * gr:grammarType gid:id SEMI
 * optionsSpec?
 * tokensSpec?
 * scopes?
 * (actions*)?
 * rules+
 * @author edgar
 *
 */
public class AntlrGrammarDeclarationPartition extends AntlrAbstractPartition {

  public AntlrGrammarDeclarationPartition(IToken successToken) {
    super(successToken);
  }

  protected IToken doEvaluate(ICharacterScanner scanner) {
    consumeWord(scanner);
    String text = getTextReaded();
    if (text.equals("lexer") || text.equals("parser") || text.equals("tree")) {
      consumeWS(scanner);
      consumeWord(scanner);
      text = getTextReaded();
    }
    if (text.equals("grammar")) {
      consumeWS(scanner);
      consumeWord(scanner);
      consumeWS(scanner);
      consumeOne(scanner);
      text = getTextReaded();
      if (!text.equals(";")) {
        unread(scanner, getTextLengthReaded());
      }
      return fSuccessToken;
    }
    unread(scanner);
    return Token.UNDEFINED;
  }
}
