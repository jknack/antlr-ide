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
import org.deved.antlride.internal.ui.text.highlighting.rules.AntlrEBNFOperatorHighlight;
import org.deved.antlride.internal.ui.text.highlighting.rules.AntlrRewriteOperatorHighlight;
import org.deved.antlride.internal.ui.text.highlighting.rules.AntlrRuleAssignHighlight;
import org.deved.antlride.internal.ui.text.highlighting.rules.AntlrRuleHighlight;
import org.deved.antlride.internal.ui.text.highlighting.rules.AntlrRuleRefHighlight;
import org.deved.antlride.internal.ui.text.highlighting.rules.AntlrTreeOperatorHighlight;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.ui.texteditor.ITextEditor;

public class AntlrCodeHighlightScanner extends AntlrAbstractHighlightScanner {
  private static final String keywords[] =
        { "private", "public", "protected", "fragment", "throws", "catch",
            "finally" };

  private static final String[] TOKENS =
        { AntlrColorConstants.ANTLR3_DEFAULT, AntlrColorConstants.ANTLR3_RULE,
            AntlrColorConstants.ANTLR3_LOCAL_VAR_REFERENCE,
            AntlrColorConstants.ANTLR3_LOCAL_VAR_DECLARATION,
            AntlrColorConstants.ANTLR3_LEXER_RULE,
            AntlrColorConstants.ANTLR3_KEYWORD_RETURNS,
            AntlrColorConstants.ANTLR3_KEYWORD,
            AntlrColorConstants.ANTLR3_EBNF_OPERATORS,
            AntlrColorConstants.ANTLR3_TREE_OPERATORS,
            AntlrColorConstants.ANTLR3_REWRITE_OPERATOR};

  public AntlrCodeHighlightScanner(IColorManager manager, IPreferenceStore store, ITextEditor editor) {
    super(manager, store, editor);
    initialize();
  }

  @Override
  protected List<IRule> createRules() {
    List<IRule> rules = new ArrayList<IRule>();
    // tokens
    IToken rule = getToken(AntlrColorConstants.ANTLR3_RULE);
    IToken lexerRule = getToken(AntlrColorConstants.ANTLR3_LEXER_RULE);
    IToken localVarReference =
        getToken(AntlrColorConstants.ANTLR3_LOCAL_VAR_REFERENCE);
    IToken localVarDeclaration =
        getToken(AntlrColorConstants.ANTLR3_LOCAL_VAR_DECLARATION);
    IToken keywordToken = getToken(AntlrColorConstants.ANTLR3_KEYWORD);
    IToken defaultToken = getToken(AntlrColorConstants.ANTLR3_DEFAULT);
    // highlight's
    rules.add(new AntlrKeyWordHighlight(keywordToken, keywords));

    rules.add(new AntlrKeyWordHighlight(
        getToken(AntlrColorConstants.ANTLR3_KEYWORD_RETURNS), "returns"));

    rules.add(new AntlrTreeOperatorHighlight(getToken(AntlrColorConstants.ANTLR3_TREE_OPERATORS)));

    rules.add(new AntlrRewriteOperatorHighlight(getToken(AntlrColorConstants.ANTLR3_REWRITE_OPERATOR)));

    rules.add(new AntlrEBNFOperatorHighlight(getToken(AntlrColorConstants.ANTLR3_EBNF_OPERATORS)));

    rules.add(new AntlrRuleRefHighlight(localVarReference));

    rules.add(new AntlrRuleAssignHighlight(localVarDeclaration));

    rules.add(new AntlrRuleHighlight(rule, lexerRule));
    
    rules.add(new AntlrRuleHighlight(rule, lexerRule));
    
    //rules.add(new AntlrBugHighlight(getToken(AntlrColorConstants.ANTLR3_BUG)));

    setDefaultReturnToken(defaultToken);
    return rules;
  }

  @Override
  protected String[] getTokenProperties() {
    return TOKENS;
  }
}
