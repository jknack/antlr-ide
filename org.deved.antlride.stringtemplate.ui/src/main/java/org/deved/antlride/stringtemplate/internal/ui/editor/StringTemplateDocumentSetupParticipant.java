/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.stringtemplate.internal.ui.editor;

import org.deved.antlride.stringtemplate.internal.ui.text.StringTemplateTextTools;
import org.deved.antlride.stringtemplate.internal.ui.text.partitions.StringTemplatePartitions;
import org.deved.antlride.stringtemplate.ui.StringTemplateUI;
import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;

public class StringTemplateDocumentSetupParticipant implements IDocumentSetupParticipant {

  public void setup(IDocument document) {
    StringTemplateTextTools textTools = StringTemplateUI.getDefault().getTextTools();
    textTools.setupDocumentPartitioner(document, StringTemplatePartitions.PARTITION_ID);
  }

}
