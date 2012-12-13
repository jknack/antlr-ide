/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core;

import org.eclipse.osgi.util.NLS;

public class AntlrCoreMessages extends NLS {
  public static String convention_unit_nullName;

  public static String convention_unit_notScriptName;

  private static final String BUNDLE_NAME =
      AntlrCoreMessages.class.getPackage().getName() + ".messages";
  static {
    initializeMessages(BUNDLE_NAME, AntlrCoreMessages.class);
  }
}
