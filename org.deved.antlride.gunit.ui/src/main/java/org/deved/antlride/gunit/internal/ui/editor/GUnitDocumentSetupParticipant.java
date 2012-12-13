/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.gunit.internal.ui.editor;

import org.deved.antlride.gunit.internal.ui.text.GUnitTextTools;
import org.deved.antlride.gunit.internal.ui.text.partitions.GUnitPartitions;
import org.deved.antlride.gunit.ui.GUnitUI;
import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;

public class GUnitDocumentSetupParticipant implements IDocumentSetupParticipant {

  public void setup(IDocument document) {
    GUnitTextTools textTools = GUnitUI.getDefault().getTextTools();
    textTools.setupDocumentPartitioner(document, GUnitPartitions.PARTITION_ID);
  }

}
