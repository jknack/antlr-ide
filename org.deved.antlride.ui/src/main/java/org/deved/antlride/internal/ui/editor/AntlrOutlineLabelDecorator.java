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
import org.deved.antlride.core.model.ElementKind;
import org.deved.antlride.ui.AntlrUI;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class AntlrOutlineLabelDecorator extends LabelProvider implements
		ILabelDecorator {

	public String decorateText(String text, Object element) {
		return text;
	}

	public Image decorateImage(Image image, Object element) {
		String compositeImageName = null;
		Image baseImage = null;
		int imageDirection = SWT.LEFT;
		try {
			if (element instanceof IMember) {
				IMember member = (IMember) element;
				if (member.exists()) {
					if (member.getElementType() == IMember.FIELD) {
						IField field = (IField) member;
						int modifiers = field.getFlags();
						if (modifiers == ElementKind.GRAMMAR_ACTION.ordinal()
								|| modifiers == ElementKind.RULE_ACTION
										.ordinal()) {
							compositeImageName = AntlrImages.ACTION;
						}
					} else if (member.getElementType() == IMember.METHOD) {
						IMethod rule = (IMethod) member;
						if (Character.isUpperCase(rule.getElementName().charAt(
								0))) {
							compositeImageName = AntlrImages.LEXER_RULE;
							baseImage = image;
							imageDirection = SWT.RIGHT;
						}
					} else if (member.getElementType() == IMember.TYPE) {
						IType type = (IType) member;
						if (type.getFlags() == ElementKind.GRAMMAR_SCOPE
								.ordinal()) {
							compositeImageName = AntlrImages.SCOPE;
						}
					}
				}
			}
		} catch (ModelException e) {
			AntlrUI.error(e);
		}
		if (compositeImageName != null) {
			Image compositeImage = AntlrImages.getImage(compositeImageName, true);
			if(compositeImage == null) {
				compositeImage = AntlrImages.createCompositeImage(compositeImageName, getImageSize(image), imageDirection, baseImage);
			}
			return compositeImage;
		}
		return image;
	}
	
	private Point getImageSize(Image image) {
		Rectangle bounds = image.getBounds();
		Point size = new Point(bounds.width, bounds.height);
		return size;
	}
}
