/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.views.interpreter;

import org.deved.antlride.core.model.evaluation.EvalElementKind;
import org.deved.antlride.core.model.evaluation.IEvalElement;
import org.deved.antlride.core.model.evaluation.IExceptionEvalElement;
import org.deved.antlride.core.model.evaluation.IRuleEvalElement;
import org.deved.antlride.core.model.evaluation.ITokenEvalElement;
import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Clickable;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
//import org.eclipse.draw2d.SimpleRaisedBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

public class AntlrFigure extends Clickable {

	public int x;

	public int y;

	public int width;

	public int height;

	public final IEvalElement element;

	private static final String RULE_TOOL_TIP_PATTERN = " select \"%1$s\" in editor";

	private static final String LOCATION_TOOL_TIP_PATTERN = " \'%1$s\' found at %2$s:%3$s ";

	public AntlrFigure(IEvalElement evalElement, int x, int y) {
		//setStyle(Clickable.STYLE_BUTTON);
		setCursor(Cursors.HAND);
		this.element = evalElement;
		setLayoutManager(new BorderLayout());
		Font font = JFaceResources.getFont("Monospaced");
		Label label = new Label();
		Label tooltip = new Label();
		EvalElementKind kind = evalElement.getElementKind();
		String labelText = null;
		String labelToolTipText = null;
		Color foregroundColor = Display.getDefault().getSystemColor(
				SWT.COLOR_BLACK);
		switch (kind) {
		case RULE: {			
			labelText = evalElement.getElementName();
			
			labelToolTipText = String.format(RULE_TOOL_TIP_PATTERN, labelText);
			
			if (!((IRuleEvalElement) evalElement).isLexerRule()) {
				foregroundColor = JFaceResources.getColorRegistry().get(
						AntlrPreferenceConstants.EDITOR_RULE_COLOR);
			}
		}
			break;
		case TOKEN: {
			ITokenEvalElement token = (ITokenEvalElement) evalElement;
			
			labelText = "'" + evalElement.getElementName() + "'";
			
			labelToolTipText = String.format(LOCATION_TOOL_TIP_PATTERN,
					evalElement.getElementName(), token.getLine(), token.getColumn() + 1);			
			
			foregroundColor = JFaceResources.getColorRegistry().get(
					AntlrPreferenceConstants.EDITOR_STRING_COLOR);
		}
			break;
		case EXCEPTION: {
			IExceptionEvalElement exception = (IExceptionEvalElement) evalElement;
			
			labelText = evalElement.getElementName();
			
//			labelToolTipText = String.format(LOCATION_TOOL_TIP_PATTERN,
//					labelText, exception.getLine(), exception.getColumn() + 1);
			labelToolTipText = exception.getMessage();
			
			foregroundColor = Display.getCurrent()
					.getSystemColor(SWT.COLOR_RED);
		}
			break;
		}
		// text
		label.setFont(font);
		label.setText(labelText);
		label.setForegroundColor(foregroundColor);
		// tooltip
		tooltip.setText(labelToolTipText);
		setToolTip(tooltip);
		// add text label
		add(label, BorderLayout.CENTER);
		//setBorder(new SimpleRaisedBorder());
		setBorder(new LineBorder(ColorConstants.black, 1));
		Dimension size = FigureUtilities.getTextExtents(labelText + "  ", font);
		this.x = x;
		this.y = y;
		this.width = size.width + (size.width * 2 / 10);
		this.height = size.height + (size.height * 2 / 5);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{'");
		builder.append(element.getElementName());
		builder.append("' (x=");
		builder.append(x);
		builder.append("; y=");
		builder.append(y);
		builder.append("; w=");
		builder.append(width);
		builder.append("; h=");
		builder.append(height);
		builder.append(")}");
		return builder.toString();
	}
}
