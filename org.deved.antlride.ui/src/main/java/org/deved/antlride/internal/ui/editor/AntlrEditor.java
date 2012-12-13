/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.editor;

import java.util.HashMap;
import java.util.Map;

import org.deved.antlride.common.ui.text.editor.AntlrBaseEditor;
import org.deved.antlride.core.AntlrLanguageToolkit;
import org.deved.antlride.core.build.AntlrSourceParserRepository;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.ast.AntlrModelElementLocator;
import org.deved.antlride.core.resources.AntlrPackages;
import org.deved.antlride.internal.ui.actions.AntlrGenerateActionGroup;
import org.deved.antlride.internal.ui.preferences.AntlrBuilderPreferencesPage;
import org.deved.antlride.internal.ui.text.AntlrTextTools;
import org.deved.antlride.internal.ui.text.folding.AntlrFoldingStructureProvider;
import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.deved.antlride.ui.AntlrUI;
import org.deved.antlride.ui.AntlrUIConstants;
import org.deved.antlride.ui.AntlrWorkbenchAdapter;
import org.deved.antlride.ui.text.AntlrTextPartitions;
import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProvider;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.INavigationLocation;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.TextSelectionNavigationLocation;

public class AntlrEditor extends AntlrBaseEditor {

	public static final String EDITOR_CONTEXT_MENU_ID = "#AntlrEditorContext";

	private class AntlrStructuredSelectionProvider implements
			ISelectionProvider {

		public void addSelectionChangedListener(
				ISelectionChangedListener listener) {
		}

		public ISelection getSelection() {
			IModelElement element = getSelectedElement();
			if (element == null)
				return new StructuredSelection();
			IWorkbenchAdapter workbenchAdapter = new AntlrWorkbenchAdapter(
					element);
			return new StructuredSelection(workbenchAdapter);
		}

		public void removeSelectionChangedListener(
				ISelectionChangedListener listener) {
		}

		public void setSelection(ISelection selection) {
		}
	}

	private class AntlrTextSelectionNavigationLocation extends
			TextSelectionNavigationLocation {

		public AntlrTextSelectionNavigationLocation(ITextEditor part,
				boolean initialize) {
			super(part, initialize);
		}

		@Override
		protected IEditorPart getEditorPart() {
			IEditorPart editorPart = super.getEditorPart();
			AntlrEditor editor = null;
			if (editorPart != null) {
				editor = (AntlrEditor) editorPart.getAdapter(AntlrEditor.class);
			}
			return editor;
		}
	}

	public AntlrEditor() {
	}

	@Override
	public Object getAdapter(Class required) {
		if (required == AntlrEditor.class) {
			return this;
		}
		return super.getAdapter(required);
	}

	private IWorkbenchPart getActivePart() {
		IWorkbenchWindow window = getSite().getWorkbenchWindow();
		IPartService service = window.getPartService();
		IWorkbenchPart part = service.getActivePart();
		return part;
	}

	@Override
	protected void doSetInput(IEditorInput input) throws CoreException {
		String[] packageNames = AntlrPackages.getAvailablePackageNames();
		if (packageNames.length == 0) {
			Display.getDefault().asyncExec(new Runnable() {

				public void run() {
					// synchronized (lock) {
					while (getActivePart() == null) {
						try {
							Thread.sleep(200L);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					AntlrEditor.this.setFocus();
					String[] pkgs = AntlrPackages.getAvailablePackageNames();
					if (pkgs.length == 0
							&& (getActivePart() instanceof AntlrMultiPageEditor)) {
						Shell shell = Display.getDefault().getActiveShell();
						boolean yes = MessageDialog
								.openQuestion(
										shell,
										"No package defined",
										"In order to build your grammar you must to create an ANTLR package. Would you like to define one now?");
						if (yes) {
							Map<String, Object> data = new HashMap<String, Object>();
							data.put("PropertyAndPreferencePage.nolink",
									Boolean.TRUE);
							String id = AntlrBuilderPreferencesPage.PREFERENCE_PAGE_ID;
							PreferencesUtil.createPreferenceDialogOn(shell, id,
									new String[] { id }, data).open();
						}
					}
					// }
				}
			});
		}
		super.doSetInput(input);
	}

	@Override
	public void createActions() {
		super.createActions();
		Action action = new PropertyDialogAction(getEditorSite(),
				new AntlrStructuredSelectionProvider());// new
		// AddBreakpointsActionDelegate(this);
		action.setText("ANTLR Breakpoints");//$NON-NLS-1$
		setAction("AddBreakpointsAction", action); //$NON-NLS-1$
		markAsStateDependentAction("AddBreakpointsAction", true); //$NON-NLS-1$

		// action = new ToggleCommentAction(DLTKEditorMessages
		//				.getBundleForConstructedKeys(), "ToggleComment.", this); //$NON-NLS-1$
		// action
		// .setActionDefinitionId(IScriptEditorActionDefinitionIds.TOGGLE_COMMENT);
		//		setAction("ToggleComment", action); //$NON-NLS-1$
		// ((ToggleCommentAction) action).configure(getSourceViewer(),
		// getSourceViewerConfiguration());
		//		markAsStateDependentAction("ToggleComment", true); //$NON-NLS-1$
	}

	protected ActionGroup createGenerateActionGroup() {
		return new AntlrGenerateActionGroup(
				this, ITextEditorActionConstants.GROUP_EDIT);
	}

	@Override
	public void editorContextMenuAboutToShow(IMenuManager menu) {
		super.editorContextMenuAboutToShow(menu);
		menu.insertBefore(ITextEditorActionConstants.GROUP_OPEN, new Separator(
				ITextEditorActionConstants.GROUP_GENERATE));
		// menu.insertBefore(ITextEditorActionConstants.GROUP_OPEN, new
		// GroupMarker(ITextEditorActionConstants.GROUP_GENERATE));
		IAction action = getAddBreakpointsAction();
		// menu.appendToGroup(ITextEditorActionConstants.GROUP_GENERATE, new
		// Separator());
		menu.appendToGroup(ITextEditorActionConstants.GROUP_GENERATE, action);
	}

	private IAction getAddBreakpointsAction() {
		return getAction("AddBreakpointsAction");
	}

	@Override
	protected String getEditorContextId() {
		return AntlrUI.PLUGIN_ID + ".antlrEditorScope";
	}

	@Override
	protected String getPartitionId() {
		return AntlrTextPartitions.ANTLR_PARTITIONING;
	}

	@Override
	protected IDocumentSetupParticipant createDocumentSetupParticipant() {
		return new AntlrDocumentSetupParticipant();
	}

	@Override
	protected ScriptOutlinePage doCreateOutlinePage() {
		return new AntlrOutlinePage(this, getScriptPreferenceStore());
	}

	@Override
	public String getEditorId() {
		return AntlrUIConstants.EDITOR_ID;
	}

	@Override
	protected void setPreferenceStore(IPreferenceStore store) {
		store.addPropertyChangeListener(new IPropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty().equals(
						AbstractTextEditor.PREFERENCE_COLOR_BACKGROUND)) {
					IPreferenceStore preferenceStore = AntlrUI.getDefault()
							.getPreferenceStore();
					preferenceStore.setValue(
							AntlrPreferenceConstants.EDITOR_BACKGROUND_COLOR,
							event.getNewValue().toString());
				} else if (event
						.getProperty()
						.equals(
								AbstractTextEditor.PREFERENCE_COLOR_BACKGROUND_SYSTEM_DEFAULT)) {
					IPreferenceStore preferenceStore = AntlrUI.getDefault()
							.getPreferenceStore();
					PreferenceConverter.setValue(preferenceStore,
							AntlrPreferenceConstants.EDITOR_BACKGROUND_COLOR,
							ColorConstants.white.getRGB());
				}
			}
		});
		IPreferenceStore preferenceStore = AntlrUI.getDefault()
				.getPreferenceStore();
		if (store
				.getBoolean(AbstractTextEditor.PREFERENCE_COLOR_BACKGROUND_SYSTEM_DEFAULT)) {
			PreferenceConverter.setValue(preferenceStore,
					AntlrPreferenceConstants.EDITOR_BACKGROUND_COLOR,
					ColorConstants.white.getRGB());
		} else {
			preferenceStore
					.setValue(
							AntlrPreferenceConstants.EDITOR_BACKGROUND_COLOR,
							store
									.getString(AbstractTextEditor.PREFERENCE_COLOR_BACKGROUND));
		}
		super.setPreferenceStore(store);
	}

	@Override
	protected IFoldingStructureProvider createFoldingStructureProvider() {
		return new AntlrFoldingStructureProvider(/* this */);
	}

	private IModelElement getSelectedElement() {
		TextSelection textSelection = (TextSelection) getSelectionProvider()
				.getSelection();
		ISourceModule sourceModule = getSourceModule();
		IGrammar grammar = AntlrSourceParserRepository.getGrammar(sourceModule);
		AntlrModelElementLocator locator = new AntlrModelElementLocator(grammar);
		IModelElement element = locator.getElementAt(textSelection.getOffset());
		return element;
	}

	@Override
	public IDLTKLanguageToolkit getLanguageToolkit() {
		return AntlrLanguageToolkit.getDefault();
	}

	@Override
	public IPreferenceStore getScriptPreferenceStore() {
		return AntlrUI.getDefault().getPreferenceStore();
	}

	@Override
	public AntlrTextTools getTextTools() {
		return AntlrUI.getDefault().getTextTools();
	}

	@Override
	protected String getPairMatcherCharacters() {
		return "{}()[]";
	}

	@Override
	protected String getContextMenuId() {
		return EDITOR_CONTEXT_MENU_ID;
	}

	@Override
	public INavigationLocation createEmptyNavigationLocation() {
		return new AntlrTextSelectionNavigationLocation(this, false);
	}

	@Override
	public INavigationLocation createNavigationLocation() {
		return new AntlrTextSelectionNavigationLocation(this, true);
	}
}
