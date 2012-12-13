/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.partitions;

import org.deved.antlride.ui.text.AntlrTextPartitions;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

public class AntlrPartitioner extends FastPartitioner {

  public AntlrPartitioner(IPartitionTokenScanner scanner) {
    super(scanner, AntlrTextPartitions.LEGAL_CONTENT_TYPES);
  }

  @Override
  public void connect(IDocument document, boolean delayInitialization) {
    super.connect(document, false);
  }

}
