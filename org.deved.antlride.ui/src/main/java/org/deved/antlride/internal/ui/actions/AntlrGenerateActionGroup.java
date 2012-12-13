package org.deved.antlride.internal.ui.actions;

import org.eclipse.dltk.internal.ui.editor.DLTKEditorMessages;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.actions.DLTKActionConstants;
import org.eclipse.dltk.ui.actions.GenerateActionGroup;
import org.eclipse.dltk.ui.actions.IScriptEditorActionDefinitionIds;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.TextOperationAction;

public class AntlrGenerateActionGroup extends GenerateActionGroup {

	public AntlrGenerateActionGroup(ScriptEditor editor, String groupName) {
		super(editor, groupName);
		Action action = new TextOperationAction(DLTKEditorMessages
				.getBundleForConstructedKeys(),
				"Format.", editor, ISourceViewer.FORMAT); //$NON-NLS-1$
		action.setActionDefinitionId(IScriptEditorActionDefinitionIds.FORMAT);
		editor.setAction(DLTKActionConstants.FORMAT, action);
		editor.markAsStateDependentAction(DLTKActionConstants.FORMAT, true);
		editor.markAsSelectionDependentAction(DLTKActionConstants.FORMAT, true);
	}

}
