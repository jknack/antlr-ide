/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.rules;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * Ascriptaware white space detector.
 */
public class AntlrWhitespaceDetector implements IWhitespaceDetector {
  private static AntlrWhitespaceDetector instance;

  private AntlrWhitespaceDetector() {
  }

  public static AntlrWhitespaceDetector instance() {
    if(instance == null) {
      instance = new AntlrWhitespaceDetector();
    }
    return instance;
  }

	public boolean isWhitespace(char character) {
		return Character.isWhitespace(character);
	}
}
