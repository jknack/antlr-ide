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

import org.deved.antlride.internal.ui.text.highlighting.rules.AntlrRuleRefHighlight;
import org.deved.antlride.internal.ui.text.highlighting.rules.AntlrTargetLanguageCommentHighlight;
import org.deved.antlride.internal.ui.text.highlighting.rules.AntlrTargetLanguageKeywordHighlight;
import org.deved.antlride.internal.ui.text.highlighting.rules.AntlrTargetLanguageLiteralHighlight;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.ui.texteditor.ITextEditor;

public class AntlrTargetActionHighlightScanner extends
    AntlrAbstractHighlightScanner {
  private static final String[] TOKENS =
        { AntlrColorConstants.ANTLR3_LOCAL_VAR_REFERENCE,
            AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_KEYWORD,
            AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_LITERAL,
            AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_COMMENT,
            AntlrColorConstants.ANTLR3_DEFAULT };

  public AntlrTargetActionHighlightScanner(IColorManager manager,
      IPreferenceStore store, ITextEditor editor) {
    super(manager, store, editor);
    initialize();
  }

  @Override
  protected List<IRule> createRules() {
    List<IRule> rules = new ArrayList<IRule>();
    IToken ruleRefToken =
        getToken(AntlrColorConstants.ANTLR3_LOCAL_VAR_REFERENCE);
    IToken defaultToken = getToken(AntlrColorConstants.ANTLR3_DEFAULT);
    rules.add(new AntlrTargetLanguageCommentHighlight(
        getToken(AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_COMMENT)));
    rules.add(new AntlrTargetLanguageLiteralHighlight(
        getToken(AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_LITERAL)));
    rules.add(new AntlrTargetLanguageKeywordHighlight(
        getToken(AntlrColorConstants.ANTLR3_TARGET_LANGUAGE_KEYWORD),
        defaultToken));
    rules.add(new AntlrRuleRefHighlight(ruleRefToken));
    setDefaultReturnToken(defaultToken);
    return rules;
  }

  @Override
  protected String[] getTokenProperties() {
    return TOKENS;
  }

}
