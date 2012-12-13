/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.common.ui.text.rules;

import org.eclipse.jface.text.rules.IWordDetector;

public class AntlrWordDetector implements IWordDetector {

  private static AntlrWordDetector instance;

  private AntlrWordDetector() {
  }

  public static AntlrWordDetector instance() {
    if(instance == null) {
      instance = new AntlrWordDetector();
    }
    return instance;
  }

  public boolean isWordPart(char c) {
    return isLetter(c) || isDigit(c) || c == '_';
  }

  public boolean isWordStart(char c) {
    return isLetter(c);
  }

  protected boolean isLetter(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }

  protected boolean isDigit(char c) {
    return (c >= '0' && c <= '9');
  }

  protected boolean isRuleStart(char c) {
    return (c >= 'a' && c <= 'z');
  }

  protected boolean isTokenStart(char c) {
    return (c >= 'A' && c <= 'Z');
  }
}
