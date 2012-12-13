/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.folding;

import org.eclipse.osgi.util.NLS;

public class AntlrFoldingMessages extends NLS {

  public static String FoldingPreferenceBlock_title;

  public static String FoldingPreferenceBlock_grammarActions;

  public static String FoldingPreferenceBlock_scopes;

  public static String FoldingPreferenceBlock_rules;

  public static String FoldingPreferenceBlock_options;

  public static String FoldingPreferenceBlock_tokensSpecification;

  private static final String BUNDLE_NAME =
      AntlrFoldingMessages.class.getPackage().getName() + ".foldingMessages";

  static {
    initializeMessages(BUNDLE_NAME, AntlrFoldingMessages.class);
  }
}
