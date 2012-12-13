/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.partitions.rules;

import org.eclipse.jface.text.rules.IToken;
/**
 * grammar structure:
 * ( cmt:DOC_COMMENT )?
 * gr:grammarType gid:id SEMI
 * optionsSpec?
 * tokensSpec?
 * scopes?
 * (actions*)?
 * rules+
 * @author edgar
 *
 */
public class AntlrOptionsPartition extends AntlrSpecificationPartition {
  public AntlrOptionsPartition(IToken successToken) {
    super(successToken, "options");
    addExclusionPattern("options");
    addExclusionPattern("tokens");
    addExclusionPattern("scope");
    addExclusionPattern("@");
  }
}
