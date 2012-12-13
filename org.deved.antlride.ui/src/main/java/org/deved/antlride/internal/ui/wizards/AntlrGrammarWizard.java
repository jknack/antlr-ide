/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.ui.wizards;

import org.deved.antlride.ui.AntlrUI;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public abstract class AntlrGrammarWizard extends Wizard implements IWizard,
		INewWizard {

	private IResource selectedResource;
	
	private AntlrGrammarPage grammarPage;

	public AntlrGrammarWizard() {
		setDialogSettings(AntlrUI.getDefault().getDialogSettings());
		setWindowTitle(getWizardTitle());
	}

	protected abstract String getWizardTitle();

	@Override
	public boolean performFinish() {
		IFile grammar = grammarPage.createGrammar(new NullProgressMonitor());
		if(grammar != null) {
			grammarPage.openGrammar(grammar);
			return true;
		}
		return false;
	}
	
	protected abstract AntlrGrammarPage createGrammarPage();

	public final void addPages() {
		grammarPage = createGrammarPage();
		grammarPage.init(selectedResource);
		super.addPage(grammarPage);
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		selectedResource = null;
		if (selection != null && !selection.isEmpty()) {
			Object element = selection.getFirstElement();
			if (element instanceof IAdaptable) {
				selectedResource = (IResource) ((IAdaptable) element)
						.getAdapter(IResource.class);
			} else if (element instanceof IResource) {
				selectedResource = (IResource) element;
			}
		}
	}

}
