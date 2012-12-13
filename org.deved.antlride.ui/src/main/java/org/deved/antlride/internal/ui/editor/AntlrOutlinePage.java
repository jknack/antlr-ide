/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.editor;

import org.deved.antlride.common.ui.AntlrImages;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.actions.MemberFilterActionGroup;
import org.eclipse.dltk.ui.viewsupport.MemberFilterAction;
import org.eclipse.dltk.ui.viewsupport.ModelElementFilter;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;

public class AntlrOutlinePage extends ScriptOutlinePage {

	public AntlrOutlinePage(ScriptEditor editor, IPreferenceStore store) {
		super(editor, store);
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		fOutlineViewer.collapseAll();
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
	protected void registerSpecialToolbarActions(IActionBars actionBars) {
		IToolBarManager toolBarManager = actionBars.getToolBarManager();

		MemberFilterActionGroup fMemberFilterActionGroup = new MemberFilterActionGroup(
				fOutlineViewer, fStore); //$NON-NLS-1$

		String title, helpContext;
		// procedures

		title = AntlrEditorMessages.MemberFilterActionGroup_hide_rules_label;
		// TODO help support
		helpContext = "";// IDLTKHelpContextIds.FILTER_STATIC_ACTION;
		MemberFilterAction hideRules = new MemberFilterAction(
				fMemberFilterActionGroup, title, new ModelElementFilter(
						IModelElement.METHOD), helpContext, true);
		hideRules
				.setDescription(AntlrEditorMessages.MemberFilterActionGroup_hide_rules_description);
		hideRules
				.setToolTipText(AntlrEditorMessages.MemberFilterActionGroup_hide_rules_tooltip);
		// TODO: add correct icon
		DLTKPluginImages.setLocalImageDescriptors(hideRules,
				"filter_methods.gif"); //$NON-NLS-1$

		title = AntlrEditorMessages.MemberFilterActionGroup_hide_scopes_label;

		// TODO help support
		helpContext = "";// IDLTKHelpContextIds.FILTER_PUBLIC_ACTION;
		MemberFilterAction hideScopes = new MemberFilterAction(
				fMemberFilterActionGroup, title, new ModelElementFilter(
						IModelElement.TYPE), helpContext, true);
		hideScopes
				.setDescription(AntlrEditorMessages.MemberFilterActionGroup_hide_scopes_description);
		hideScopes
				.setToolTipText(AntlrEditorMessages.MemberFilterActionGroup_hide_scopes_tooltip);
		hideScopes.setImageDescriptor(AntlrImages
				.getDescriptor(AntlrImages.FILTER_SCOPES));

		// order corresponds to order in toolbar
		MemberFilterAction[] fFilterActions = {hideRules, hideScopes};

		fMemberFilterActionGroup.setActions(fFilterActions);

		fMemberFilterActionGroup.contributeToToolBar(toolBarManager);
	}

	@Override
	protected ILabelDecorator getLabelDecorator() {
		return new AntlrOutlineLabelDecorator();
	}
}
