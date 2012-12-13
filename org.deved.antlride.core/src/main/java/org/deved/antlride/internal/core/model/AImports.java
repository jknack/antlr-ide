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
import org.deved.antlride.core.model.ast.IModelElementVisitor;
import org.deved.antlride.core.model.dltk.ast.DASTImport;
import org.deved.antlride.core.model.dltk.ast.DASTImports;
import org.eclipse.dltk.ast.ASTNode;

public class AImports extends AAbstractModelElement implements IImports {

	private IImport[] imports;

	private DASTImports node;

	private static final IImport[] IMPORTS = new IImport[0];

	public AImports(IImport[] imports) {
		this.imports = imports == null ? IMPORTS : imports;
		for (IImport imp : this.imports) {
			((AAbstractModelElement)imp).setParent(this);
		}
	}

	public IImport[] getImports() {
		return imports;
	}

	@SuppressWarnings("unchecked")
	public <E> E getAdapter(Class<E> adapter) {
		if (IGrammar.class == adapter) {
			return (E) getParent();
		}
		if (IImports.class == adapter) {
			return (E) this;
		}
		if (adapter == ASTNode.class)
			return (E) getAST();
		return null;
	}

	private DASTImports getAST() {
		if (node == null) {
			IImport[] imports = getImports();
			DASTImport[] astImports = new DASTImport[imports.length];
			for (int i = 0; i < imports.length; i++) {
				astImports[i] = (DASTImport) imports[i]
						.getAdapter(ASTNode.class);
			}
			node = new DASTImports(getElementKind().ordinal(), sourceStart(),
					sourceEnd(), astImports);
		}
		return node;
	}

	public ElementKind getElementKind() {
		return ElementKind.IMPORTS;
	}

	public String getElementName() {
		return "import";
	}

	public void traverse(IModelElementVisitor visitor) {
		if(visitor.visitImports(this)) {
			if(imports != null && imports.length > 0) {
				for (IImport iimport : imports) {
					iimport.traverse(visitor);
				}
			}
			visitor.endvisitImports(this);
		}
	}

}
