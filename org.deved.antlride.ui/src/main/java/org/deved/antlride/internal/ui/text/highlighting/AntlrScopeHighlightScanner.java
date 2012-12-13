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
import org.deved.antlride.internal.ui.text.highlighting.rules.AntlrTargetLanguageCommentHighlight;
import org.deved.antlride.internal.ui.text.highlighting.rules.AntlrTargetLanguageKeywordHighlight;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.ui.texteditor.ITextEditor;

public class AntlrScopeHighlightScanner extends AntlrAbstractHighlightScanner {
  private static final String[] TOKENS =
        { AntlrColorConstants.ANTLR3_DEFAULT,
            AntlrColorConstants.ANTLR3_KEYWORD,
            AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_KEYWORD,
            AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_COMMENT };

  public AntlrScopeHighlightScanner(IColorManager manager,
      IPreferenceStore store, ITextEditor editor) {
    super(manager, store, editor);
    initialize();
  }

  @Override
  protected List<IRule> createRules() {
    List<IRule> rules = new ArrayList<IRule>();
    rules.add(new AntlrKeyWordHighlight(
        getToken(AntlrColorConstants.ANTLR3_KEYWORD), "scope"));
    rules.add(new AntlrTargetLanguageCommentHighlight(
        getToken(AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_COMMENT)));
    rules.add(new AntlrTargetLanguageKeywordHighlight(
        getToken(AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_KEYWORD),
        getToken(AntlrColorConstants.ANTLR3_DEFAULT)));
    setDefaultReturnToken(getToken(AntlrColorConstants.ANTLR3_DEFAULT));
    return rules;
  }

  @Override
  protected String[] getTokenProperties() {
    return TOKENS;
  }

}
