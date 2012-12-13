/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.gunit.internal.ui.text;

import org.deved.antlride.gunit.internal.ui.text.partitions.GUnitPartitionScanner;
import org.deved.antlride.gunit.internal.ui.text.partitions.GUnitPartitioner;
import org.deved.antlride.gunit.internal.ui.text.partitions.GUnitPartitions;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.ITextEditor;

public class GUnitTextTools extends ScriptTextTools {
	private GUnitPartitionScanner fScanner;
	
	public GUnitTextTools(boolean autoDisposeOnDisplayDispose) {
		super(GUnitPartitions.PARTITION_ID, GUnitPartitions.LEGAL_CONTENT_TYPES, autoDisposeOnDisplayDispose);
		fScanner = new GUnitPartitionScanner();
	}

	@Override
	public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(
			IPreferenceStore preferenceStore, ITextEditor editor,
			String partitioning) {
		return new GUnitSourceViewerConfiguration(getColorManager(),
				preferenceStore, editor, partitioning);
	}
	
	public void setupDocumentPartitioner(IDocument document, String partitioning) {
		IDocumentPartitioner partitioner = createDocumentPartitioner();
		partitioner.connect(document);
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension3 = (IDocumentExtension3) document;
			extension3.setDocumentPartitioner(partitioning, partitioner);
		} else {
			document.setDocumentPartitioner(partitioner);
		}
	}

	@Override
	public IPartitionTokenScanner getPartitionScanner() {
		return fScanner;
	}
	
	@Override
	public IDocumentPartitioner createDocumentPartitioner() {
		return new GUnitPartitioner(getPartitionScanner());
	}
}
