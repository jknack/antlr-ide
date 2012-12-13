/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.internal.ui.editor;

import org.deved.antlride.common.ui.AntlrImages;
import org.deved.antlride.stringtemplate.core.model.StringTemplateKind;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class StringTemplateOutlineLabelDecorator extends LabelProvider
		implements ILabelDecorator {

	public String decorateText(String text, Object element) {
		return text;
	}

	public Image decorateImage(Image image, Object element) {
		try {
			if (element instanceof IMember) {
				IMember member = (IMember) element;
				if (member.exists()) {
					if (member.getElementType() == IMember.METHOD) {
						IMethod rule = (IMethod) member;
						int modifiers = rule.getFlags();
						if ((modifiers & StringTemplateKind.ST.kind()) != 0) {
							image = AntlrImages
									.getImage(AntlrImages.DOLLAR_ST);
						} else if ((modifiers & StringTemplateKind.ST_ANGLE_BRACKET.kind()) != 0) {
							image = AntlrImages
									.getImage(AntlrImages.ANGLE_BRACKET_ST);
						}
					}
				}
			}
		} catch (ModelException e) {
			// shhhh
		}
		return image;
	}
}
