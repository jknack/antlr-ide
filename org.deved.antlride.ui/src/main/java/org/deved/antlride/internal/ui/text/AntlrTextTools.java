/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text;

import org.deved.antlride.internal.ui.text.partitions.AntlrPartitionScanner;
import org.deved.antlride.internal.ui.text.partitions.AntlrPartitioner;
import org.deved.antlride.ui.text.AntlrTextPartitions;
import org.eclipse.dltk.ui.editor.highlighting.SemanticHighlighting;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.ui.texteditor.ITextEditor;

public class AntlrTextTools extends ScriptTextTools {
	private AntlrPartitionScanner scanner;

	public AntlrTextTools(boolean autoDisposeOnDisplayDispose) {
		super(AntlrTextPartitions.ANTLR_PARTITIONING,
				AntlrTextPartitions.LEGAL_CONTENT_TYPES,
				autoDisposeOnDisplayDispose);
		scanner = new AntlrPartitionScanner();
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
	public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(
			IPreferenceStore preferenceStore, ITextEditor editor,
			String partitioning) {
		return new AntlrSourceViewerConfiguration(getColorManager(),
				preferenceStore, editor, partitioning);
	}

	@Override
	public AntlrPartitionScanner getPartitionScanner() {
		return scanner;
	}

	@Override
	public IDocumentPartitioner createDocumentPartitioner() {
		return new AntlrPartitioner(getPartitionScanner());
	}

	@Override
	public SemanticHighlighting[] getSemanticHighlightings() {
		return AntlrSemanticUpdateWorker.getSemanticHighlightings();
	}

}
