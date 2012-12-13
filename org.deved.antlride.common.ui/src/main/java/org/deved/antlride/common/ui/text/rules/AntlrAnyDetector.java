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

public class AntlrAnyDetector implements IWordDetector {
  private static AntlrAnyDetector instance;

  private AntlrAnyDetector() {
  }

  public static AntlrAnyDetector instance() {
    if(instance == null) {
      instance = new AntlrAnyDetector();
    }
    return instance;
  }

  public boolean isWordPart(char c) {
    return !Character.isWhitespace(c);
  }

  public boolean isWordStart(char c) {
    return !Character.isWhitespace(c);
  }

}
