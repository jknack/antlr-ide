/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.deved.antlride.internal.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.deved.antlride.common.ui.AntlrImages;
import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.resources.AntlrResourceListener;
import org.deved.antlride.core.resources.AntlrResourceTracker;
import org.deved.antlride.ui.AntlrUI;
import org.deved.antlride.ui.editor.AntlrEditorPageContribution;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.services.IDisposable;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class AntlrMultiPageEditor extends MultiPageEditorPart implements
		AntlrResourceListener, ITextEditor {

	private AntlrEditor editor;

	private AntlrInterpreterPage interpreterPage;

	private List<Viewer> viewers = new ArrayList<Viewer>();

	public AntlrMultiPageEditor() {
		AntlrResourceTracker.addResourceChangeListener(this);
	}

	@Override
	protected void createPages() {

		createEditorPage();
		createInterpreterPage();
		AntlrEditorPageContribution[] pages = AntlrUI.getDefault()
				.getPageContributions();
		IPreferenceStore store = editor.getScriptPreferenceStore();
		for (AntlrEditorPageContribution page : pages) {
			if (store.getBoolean(page.getId())) {
				Viewer viewer = page.createPage(getContainer());
				int index = addPage(viewer.getControl());
				viewers.add(viewer);
				setPageText(index, page.getText());
				setPageImage(index, AntlrImages.getImage(page.getImage()));
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == AntlrEditor.class) {
			return editor;
		} else if (adapter == ITextEditor.class) {
			return editor;
		}
		return super.getAdapter(adapter);
	}

	private void createEditorPage() {
		try {
			editor = new AntlrEditor();
			viewers.add(null);
			getSite().getSelectionProvider().addSelectionChangedListener(
					new ISelectionChangedListener() {

						public void selectionChanged(SelectionChangedEvent event) {
							try {
								if (!event.getSelection().isEmpty()) {
									if (getActivePage() > 0) {
										setActivePage(0);
									}
								}
							} catch (Exception ex) {
								// do nothing
							}
						}
					});
			int index = addPage(editor, getEditorInput());
			this.setPageText(index, "Grammar");
			this.setPageImage(index, AntlrImages.getImage(AntlrImages.G));
			setPartName(editor.getTitle());
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(),
					"Error creating nested text editor", null, e.getStatus());
		}
	}

	@Override
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		Viewer viewer = viewers.get(newPageIndex);
		if (viewer != null) {
			ISourceModule sourceModule = (ISourceModule) editor
					.getInputModelElement();
			IGrammar grammar = AntlrSourceParserRepository
					.getGrammar(sourceModule);
			viewer.setInput(grammar);
		}
	}

	@Override
	public void setActivePage(int pageIndex) {
		super.setActivePage(pageIndex);
		// editor.setFocus();
	}

	/**
	 * Creates page 1 of the multi-page editor, which allows you to change the
	 * font used in page 2.
	 */
	void createInterpreterPage() {
		interpreterPage = new AntlrInterpreterPage(this, getContainer());
		viewers.add(interpreterPage);
		int index = addPage(interpreterPage.getControl());
		setPageText(index, "Interpreter");
		setPageImage(index, AntlrImages.getImage(AntlrImages.PARSE_TREE));
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
	}

	@Override
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, "Grammar");
		setInput(editor.getEditorInput());
		setPartName(editor.getTitle());
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public void dispose() {
		for (int i = 1; i < viewers.size(); i++) {
			IDisposable disposable = (IDisposable) viewers.get(i);
			disposable.dispose();
		}
		AntlrResourceTracker.removeResourceChangeListener(this);
		super.dispose();
	}

	public void resourceChanged(final IResourceChangeEvent event) {
		// if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
		// Display.getDefault().asyncExec(new Runnable() {
		// public void run() {
		// IWorkbenchPage[] pages = getSite().getWorkbenchWindow()
		// .getPages();
		// for (int i = 0; i < pages.length; i++) {
		// IFile file = ((FileEditorInput) editor.getEditorInput())
		// .getFile();
		// if (file.getProject().equals(event.getResource())) {
		// IEditorPart editorPart = pages[i].findEditor(editor
		// .getEditorInput());
		// pages[i].closeEditor(editorPart, true);
		// }
		// }
		// }
		// });
		// }
	}

	public void added(IResource resource) {
	}

	public void changed(IResource resource) {
	}

	public void removed(final IResource resource) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage[] pages = getSite().getWorkbenchWindow()
						.getPages();
				for (int i = 0; i < pages.length; i++) {
					IFile file = ((FileEditorInput) editor.getEditorInput())
							.getFile();
					if (resource.equals(file)) {
						IEditorPart editorPart = pages[i].findEditor(editor
								.getEditorInput());
						pages[i].closeEditor(editorPart, true);
					}
				}
			}
		});
	}

	public void close(boolean save) {
		editor.close(save);
	}

	public void doRevertToSaved() {
		editor.doRevertToSaved();
	}

	public IAction getAction(String actionId) {
		return editor.getAction(actionId);
	}

	public IDocumentProvider getDocumentProvider() {
		return editor.getDocumentProvider();
	}

	public IRegion getHighlightRange() {
		return editor.getHighlightRange();
	}

	public ISelectionProvider getSelectionProvider() {
		return editor.getSelectionProvider();
	}

	public boolean isEditable() {
		return editor.isEditable();
	}

	public void removeActionActivationCode(String actionId) {
		editor.removeActionActivationCode(actionId);
	}

	public void resetHighlightRange() {
		editor.resetHighlightRange();
	}

	public void selectAndReveal(int offset, int length) {
		editor.selectAndReveal(offset, length);
	}

	public void setAction(String actionID, IAction action) {
		editor.setAction(actionID, action);
	}

	public void setActionActivationCode(String actionId,
			char activationCharacter, int activationKeyCode,
			int activationStateMask) {
		editor.setActionActivationCode(actionId, activationCharacter,
				activationKeyCode, activationStateMask);
	}

	public void setHighlightRange(int offset, int length, boolean moveCursor) {
		editor.setHighlightRange(offset, length, moveCursor);
	}

	public void showHighlightRangeOnly(boolean showHighlightRangeOnly) {
		editor.showHighlightRangeOnly(showHighlightRangeOnly);

	}

	public boolean showsHighlightRangeOnly() {
		return editor.showsHighlightRangeOnly();
	}

}
