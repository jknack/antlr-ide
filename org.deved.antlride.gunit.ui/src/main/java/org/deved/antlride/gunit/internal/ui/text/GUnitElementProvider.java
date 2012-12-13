package org.deved.antlride.gunit.internal.ui.text;

import org.deved.antlride.common.ui.SingleProjectProblem;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.actions.SelectionConverter;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.text.ScriptWordFinder;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;

@SuppressWarnings("restriction")
public class GUnitElementProvider implements IInformationProvider, IInformationProviderExtension {

	private ScriptEditor fEditor;
	
	private boolean fUseCodeResolve;

	public GUnitElementProvider(IEditorPart editor) {
		fUseCodeResolve= false;
		if (editor instanceof ScriptEditor)
			fEditor= (ScriptEditor)editor;
	}

	public GUnitElementProvider(IEditorPart editor, boolean useCodeResolve) {
		this(editor);
		fUseCodeResolve= useCodeResolve;
	}

	/*
	 * @see IInformationProvider#getSubject(ITextViewer, int)
	 */	
	public IRegion getSubject(ITextViewer textViewer, int offset) {
		if (textViewer != null && fEditor != null) {
			IRegion region= ScriptWordFinder.findWord(textViewer.getDocument(), offset);
			if (region != null)
				return region;
			else
				return new Region(offset, 0);
		}
		return null;
	}

	/*
	 * @see IInformationProvider#getInformation(ITextViewer, IRegion)
	 */
	public String getInformation(ITextViewer textViewer, IRegion subject) {
		return getInformation2(textViewer, subject).toString();
	}

	/*
	 * @see IInformationProviderExtension#getElement(ITextViewer, IRegion)
	 */
	@SingleProjectProblem
	public Object getInformation2(ITextViewer textViewer, IRegion subject) {
		if (fEditor == null)
			return null;

		try {
			if (fUseCodeResolve) {
				IStructuredSelection sel= SelectionConverter.getStructuredSelection(fEditor);
				if (!sel.isEmpty())
					return sel.getFirstElement();
			}
			IModelElement element= SelectionConverter.getElementAtOffset(fEditor);
			if (element != null)
				return element;			
			return fEditor.getInputModelElement();//EditorUtility.getEditorInputModelElement(fEditor, false);
		} catch (ModelException e) {
			return null;
		}
	}
}
