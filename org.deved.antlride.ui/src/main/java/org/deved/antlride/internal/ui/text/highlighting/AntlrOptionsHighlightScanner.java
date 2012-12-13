/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.highlighting;

import java.util.ArrayList;
import java.util.List;

import org.deved.antlride.common.ui.text.highlighting.rules.AntlrKeyWordHighlight;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class AntlrOptionsHighlightScanner extends AntlrAbstractHighlightScanner {
  private static final String[] TOKENS =
    { AntlrColorConstants.ANTLR3_DEFAULT, AntlrColorConstants.ANTLR3_KEYWORD,
        AntlrColorConstants.ANTLR3_SINGLE_LINE_COMMENT };

  public AntlrOptionsHighlightScanner(IColorManager manager, IPreferenceStore store) {
    super(manager, store);
    initialize();
  }

  @Override
  protected List<IRule> createRules() {
    List<IRule> rules = new ArrayList<IRule>();
    Token keyword = getToken(AntlrColorConstants.ANTLR3_KEYWORD);
    Token singleComment = getToken(AntlrColorConstants.ANTLR3_SINGLE_LINE_COMMENT);
    IToken defaultToken = getToken(AntlrColorConstants.ANTLR3_DEFAULT);
    rules.add(new AntlrKeyWordHighlight(keyword, "options"));
    rules.add(new EndOfLineRule("//", singleComment));
    setDefaultReturnToken(defaultToken);
    return rules;
  }

  @Override
  protected String[] getTokenProperties() {
    return TOKENS;
  }

}
