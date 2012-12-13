/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text;

import java.io.IOException;
import java.io.Reader;

import org.eclipse.jface.text.rules.ICharacterScanner;

public class AntlrCharacterReader extends Reader {
  private ICharacterScanner fScanner;
  private int fCount;
  private StringBuilder builder;

  public AntlrCharacterReader(ICharacterScanner scanner) {
    this.fScanner = scanner;
    this.fCount = 0;
    builder = new StringBuilder();
  }

  @Override
  public void close() throws IOException {
  }

  public int count() {
    return fCount;
  }

  @Override
  public int read() throws IOException {
    fCount++;
    int ch = fScanner.read();
    builder.append((char) ch);
    return ch;
  }

  public void unread() {
    while (fCount > 0) {
      fScanner.unread();
      fCount--;
      if(builder.length()> 0)
      builder.deleteCharAt(builder.length() - 1);
    }
  }

  public void unread(int count) {
    fCount -= count;
    while (count > 0) {
      fScanner.unread();
      count--;
      if(builder.length()> 0)
      builder.deleteCharAt(builder.length() - 1);
    }
  }

  @Override
  public String toString() {
    if (fCount == 0) {
      return "";
    }
    return builder.toString();
  }

  @Override
  public int read(char[] cbuf, int off, int len) throws IOException {
    return 0;
  }

}
