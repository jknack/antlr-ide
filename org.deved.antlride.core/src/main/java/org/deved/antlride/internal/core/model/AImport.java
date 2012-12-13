/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IImport;
import org.deved.antlride.core.model.IImports;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTImport;
import org.eclipse.dltk.ast.ASTNode;

public class AImport extends AAbstractModelElement implements IImport {

	private ISourceElement alias;

	private ISourceElement importedGrammar;

	private DASTImport node;

	public AImport(ISourceElement importedGrammar, ISourceElement alias) {
		this.importedGrammar = importedGrammar;
		this.alias = alias;
		setSourceStart(importedGrammar.sourceStart());
		setSourceEnd(importedGrammar.sourceEnd());
	}

	public ISourceElement getAlias() {
		return alias;
	}

	public ISourceElement getImportedGrammar() {
		return importedGrammar;
	}

	@SuppressWarnings("unchecked")
	public <E> E getAdapter(Class<E> adapter) {
		if (IGrammar.class == adapter) {
			return (E) getParent().getParent();
		}
		if (IImports.class == adapter) {
			return (E) getParent();
		}
		if (IImport.class == adapter) {
			return (E) this;
		}
		if (ASTNode.class == adapter) {
			return (E) getAST();
		}
		return null;
	}

	private DASTImport getAST() {
		if (node == null) {
			String elementName = getElementName();
			node = new DASTImport(elementName, getElementKind().ordinal(),
					elementName, sourceStart(), sourceEnd(), sourceStart(),
					sourceEnd());
		}
		return node;
	}

	public ElementKind getElementKind() {
		return ElementKind.IMPORT;
	}

	public String getElementName() {
		return importedGrammar == null ? "" : importedGrammar.getText();
	}

	public void traverse(IModelElementVisitor visitor) {
		if (visitor.visitImport(this)) {
			visitor.endvisitImport(this);
		}
	}

}
