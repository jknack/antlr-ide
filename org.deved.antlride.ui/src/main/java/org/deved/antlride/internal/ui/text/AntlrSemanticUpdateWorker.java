/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.text;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.editor.highlighting.ISemanticHighlightingRequestor;
import org.eclipse.dltk.ui.editor.highlighting.SemanticHighlighting;

public class AntlrSemanticUpdateWorker extends ASTVisitor {

	public static SemanticHighlighting[] getSemanticHighlightings() {
		return new SemanticHighlighting[0]; 
	}


	/**
	 * @param code
	 * @throws ModelException
	 */
	public AntlrSemanticUpdateWorker(ISemanticHighlightingRequestor requestor,
			ISourceModule code) throws ModelException {
	}


	public boolean visitGeneral(ASTNode node) throws Exception {
		return true;
	}


	public void endvisitGeneral(ASTNode node) throws Exception {
	}


}
