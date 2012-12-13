/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.core.model;

import org.deved.antlride.core.model.IScope;
import org.deved.antlride.core.model.IScopeAttribute;
import org.deved.antlride.core.model.ISourceElement;
import org.deved.antlride.core.model.dltk.ast.DASTScope;
import org.deved.antlride.core.model.dltk.ast.DASTScopeAttribute;

public abstract class AScope extends AAbstractModelElement implements IScope {
	private static final IScopeAttribute[] EMPTY_ATTRIBUTES = new IScopeAttribute[0];

	protected IScopeAttribute[] attributes;

	private String text;

	public AScope() {
	}
	
	public String getText() {
		return text;
	}

	public IScopeAttribute findAttribute(String name) {
		for (int i = 0; i < attributes.length; i++) {
			IScopeAttribute attribute = attributes[i];
			if (attribute.getName().getText().equals(name)) {
				return attribute;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected void attributes(DASTScope scopeNode)  {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < attributes.length; i++) {
			IScopeAttribute scopeAttribute = attributes[i];
			ISourceElement name = scopeAttribute.getName();
			ISourceElement attrType = scopeAttribute.getType();
			builder.append(name.getText());
			if (attrType != null) {
				builder.append(": ");
				builder.append(attrType.getText());
			}
			DASTScopeAttribute scopeAttributeNode = new DASTScopeAttribute(builder.toString(), getElementKind().ordinal(), sourceStart(), sourceEnd());
			scopeNode.getFieldList().add(scopeAttributeNode);
			builder.setLength(0);
		}
	}
	
	public IScopeAttribute[] getAttributes() {
		return attributes;
	}

	public void setAttributes(IScopeAttribute[] attributes) {
		if (attributes == null || attributes.length == 0) {
			this.attributes = EMPTY_ATTRIBUTES;
		} else {
			this.attributes = attributes;
		}
	}

	public void setText(String text) {
		this.text = text;
	}
}
