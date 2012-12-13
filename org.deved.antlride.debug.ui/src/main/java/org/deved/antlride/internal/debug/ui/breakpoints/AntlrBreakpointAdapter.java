/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.debug.ui.breakpoints;

import org.deved.antlride.core.AntlrConstants;
import org.deved.antlride.debug.AntlrDebugConstants;
import org.deved.antlride.debug.breakpoints.AntlrLocationBreakpoint;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTargetExtension;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class AntlrBreakpointAdapter implements
		IToggleBreakpointsTargetExtension {

	public boolean canToggleBreakpoints(IWorkbenchPart part,
			ISelection selection) {
		return canToggleLineBreakpoints(part, selection)
				|| canToggleWatchpoints(part, selection);
	}

	public void toggleBreakpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {
		if (canToggleWatchpoints(part, selection)) {
            toggleWatchpoints(part, selection);
        } else {
            toggleLineBreakpoints(part, selection);
        }  
	}

	public boolean canToggleLineBreakpoints(IWorkbenchPart part,
			ISelection selection) {
		return getEditor(part) != null;
	}

	public boolean canToggleMethodBreakpoints(IWorkbenchPart part,
			ISelection selection) {
		return false;
	}

	public boolean canToggleWatchpoints(IWorkbenchPart part,
			ISelection selection) {
		return false || getVariableAndFunctionName(part, selection) != null;
	}

	public void toggleLineBreakpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {
		ITextEditor textEditor = getEditor(part);
		if (textEditor != null) {
			IResource resource = (IResource) textEditor.getEditorInput().getAdapter(IResource.class);
			ITextSelection textSelection = (ITextSelection) selection;
			int lineNumber = textSelection.getStartLine();
			IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints(AntlrDebugConstants.DEBUG_MODEL_ID);
			for (int i = 0; i < breakpoints.length; i++) {
				IBreakpoint breakpoint = breakpoints[i];
				if (breakpoint instanceof AntlrLocationBreakpoint && resource.equals(breakpoint.getMarker().getResource())) {
					if (((AntlrLocationBreakpoint)breakpoint).getLineNumber() == (lineNumber + 1)) {
						// remove
						breakpoint.delete();
						return;
					}
				}
			}
			// create line breakpoint (doc line numbers start at 0)
			//TODO: ANTLR IDE Remove this
//			AntlrLocationBreakpoint lineBreakpoint = new AntlrLocationBreakpoint(resource, lineNumber + 1, 0);
//			DebugPlugin.getDefault().getBreakpointManager().addBreakpoint(lineBreakpoint);
		}
	}

	public void toggleMethodBreakpoints(IWorkbenchPart part,
			ISelection selection) throws CoreException {
	}

	public void toggleWatchpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {		
	}

	private ITextEditor getEditor(IWorkbenchPart part) {
		if (part instanceof ITextEditor) {
			ITextEditor editorPart = (ITextEditor) part;
			IResource resource = (IResource) editorPart.getEditorInput()
					.getAdapter(IResource.class);
			if (resource != null) {
				String extension = resource.getFileExtension();
				if (extension != null
						&& extension
								.equals(AntlrConstants.ANTLR_GRAMMAR_FILE_EXTENSION)) {
					return editorPart;
				}
			}
		}
		return null;
	}
	
	private String getFunctionName(IDocument document, String varName, int line) {
	    // This is a simple guess at the function name - look for the labels preceeding
	    // the variable definition, and then see if there are any 'calls' to that
	    // label. If none, assumet the variable is in the "_main_" function
	    String source = document.get();
	    int lineIndex = line - 1;
	    while (lineIndex >= 0) {
            try {
                IRegion information = document.getLineInformation(lineIndex);
                String lineText = document.get(information.getOffset(), information.getLength());
                if (lineText.startsWith(":")) {
                    String label = lineText.substring(1);
                    if (source.indexOf("call " + label) >= 0) {
                        return label;
                    }
                }
                lineIndex--;
            } catch (BadLocationException e) {
            }
	    }
	    return "_main_";
	}
	
	private String[] getVariableAndFunctionName(IWorkbenchPart part, ISelection selection) {
	    ITextEditor editor = getEditor(part);
	    if (editor != null && selection instanceof ITextSelection) {
	        ITextSelection textSelection = (ITextSelection) selection;
	        IDocumentProvider documentProvider = editor.getDocumentProvider();
	        try {
	            documentProvider.connect(this);
	            IDocument document = documentProvider.getDocument(editor.getEditorInput());
	            IRegion region = document.getLineInformationOfOffset(textSelection.getOffset());
	            String string = document.get(region.getOffset(), region.getLength()).trim();
	            if (string.startsWith("var ")) {
	                String varName = string.substring(4).trim(); 
	                String fcnName = getFunctionName(document, varName, document.getLineOfOffset(textSelection.getOffset()));
	                return new String[] {varName, fcnName};
	            }
	        } catch (CoreException e) {
	        } catch (BadLocationException e) {
	        } finally {
	            documentProvider.disconnect(this);
	        }
	    }	    
	    return null;
	}
}
