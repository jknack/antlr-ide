/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text;

import org.deved.antlride.ui.AntlrUI;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

public class AntlrContentAssistPreference extends ContentAssistPreference {

  private static AntlrContentAssistPreference sDefault;

  protected ScriptTextTools getTextTools() {
    return AntlrUI.getDefault().getTextTools();
  }

  public static ContentAssistPreference getDefault() {
    if (sDefault == null) {
      sDefault = new AntlrContentAssistPreference();
    }
    return sDefault;
  }

}
