/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.editor;

import org.deved.antlride.internal.ui.text.AntlrTextTools;
import org.deved.antlride.ui.AntlrUI;
import org.deved.antlride.ui.text.AntlrTextPartitions;
import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;

public class AntlrDocumentSetupParticipant implements IDocumentSetupParticipant {

  public void setup(IDocument document) {
    AntlrTextTools textTools = AntlrUI.getDefault().getTextTools();
    textTools.setupDocumentPartitioner(document, AntlrTextPartitions.ANTLR_PARTITIONING);
  }

}
