/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.highlighting;

import java.util.List;

import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;

public class AntlrStringHighlightScanner extends AntlrAbstractHighlightScanner {

  private static final String[] TOKENS =
    { AntlrColorConstants.ANTLR3_STRING_LITERAL };

  public AntlrStringHighlightScanner(IColorManager manager, IPreferenceStore store) {
    super(manager, store);
    initialize();
  }

  @Override
  protected List<IRule> createRules() {
    setDefaultReturnToken(getToken(AntlrColorConstants.ANTLR3_STRING_LITERAL));
    return null;
  }

  @Override
  protected String[] getTokenProperties() {
    return TOKENS;
  }

}
