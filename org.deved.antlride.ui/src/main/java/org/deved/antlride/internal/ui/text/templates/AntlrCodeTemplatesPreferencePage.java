/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text.templates;

import org.deved.antlride.internal.ui.text.AntlrSimpleSourceViewerConfiguration;
import org.deved.antlride.internal.ui.text.AntlrTextTools;
import org.deved.antlride.ui.AntlrUI;
import org.deved.antlride.ui.text.AntlrTextPartitions;
import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class AntlrCodeTemplatesPreferencePage extends
		ScriptTemplatePreferencePage implements IWorkbenchPreferencePage {

	protected class AntlrEditTemplateDialog extends EditTemplateDialog {
		public AntlrEditTemplateDialog(Shell parent, Template template,
				boolean edit, boolean isNameModifiable,
				ContextTypeRegistry registry) {
			super(parent, template, edit, isNameModifiable, registry);
		}

		protected SourceViewer createViewer(Composite parent) {
			return AntlrCodeTemplatesPreferencePage.this.createViewer(parent);
		}
	}

	public AntlrCodeTemplatesPreferencePage() {
		setPreferenceStore(AntlrUI.getDefault().getPreferenceStore());

		setTemplateStore(AntlrTemplateAccess.getInstance().getTemplateStore());
		setContextTypeRegistry(AntlrTemplateAccess.getInstance()
				.getContextTypeRegistry());
	}

	protected ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
		IPreferenceStore store = AntlrUI.getDefault().getPreferenceStore();

		AntlrTextTools textTools = AntlrUI.getDefault().getTextTools();

		return new AntlrSimpleSourceViewerConfiguration(textTools
				.getColorManager(), store, null,
				AntlrTextPartitions.ANTLR_PARTITIONING, false);
	}

	@Override
	protected void setDocumentParticioner(IDocument document) {
		AntlrTextTools textTools = AntlrUI.getDefault().getTextTools();
		textTools.setupDocumentPartitioner(document,
				AntlrTextPartitions.ANTLR_PARTITIONING);
	}

	@Override
	protected ScriptTemplateAccess getTemplateAccess() {
		return AntlrTemplateAccess.getInstance();
	}

	@Override
	protected void setPreferenceStore() {
		setPreferenceStore(AntlrUI.getDefault().getPreferenceStore());
	}

	/*
	 * protected Template editTemplate(Template template, boolean edit, boolean
	 * isNameModifiable) { EditTemplateDialog dialog = new
	 * RubyEditTemplateDialog(getShell(), template, edit, isNameModifiable,
	 * getContextTypeRegistry()); if (dialog.open() == Window.OK) { return
	 * dialog.getTemplate(); } return null; }
	 */
}
