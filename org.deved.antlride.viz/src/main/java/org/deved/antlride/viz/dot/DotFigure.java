/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.viz.dot;

import java.util.List;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class DotFigure extends Ellipse {

	public DotFigure(String name, int size, String shape) {
		setLayoutManager(new BorderLayout());
		setLineWidth(2);
		if ("doublecircle".equals(shape)) {
			DotFigure inner = new DotFigure(name, size, "circle");
			add(inner, BorderLayout.CENTER);
			setBorder(new MarginBorder(5));
		} else {
			Label label = new Label(name);
			label.setFont(getDefaultFont());
			add(label, BorderLayout.CENTER);
		}
		setSize(size, size);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setForegroundColor(Color fg) {
		super.setForegroundColor(fg);
		List<IFigure> children = getChildren();
		for (IFigure figure : children) {
			figure.setForegroundColor(fg);
		}
	}

	@Override
	public void erase() {
		super.erase();
	}

	@Override
	protected void fillShape(Graphics graphics) {
		Color backgroundColor = getBackgroundColor();
		int blue = backgroundColor.getBlue();
		blue = (int) (blue - (blue * 0.20));
		blue = blue > 0 ? blue : 0;

		int red = backgroundColor.getRed();
		red = (int) (red - (red * 0.20));
		red = red > 0 ? red : 0;

		int green = backgroundColor.getGreen();
		green = (int) (green - (green * 0.20));
		green = green > 0 ? green : 0;

		Color lightenColor = new Color(Display.getCurrent(), new RGB(red,
				green, blue));
		graphics.setForegroundColor(lightenColor);
		graphics.setBackgroundColor(getBackgroundColor());

		Path path = new Path(null);
		path.addArc(getBounds().x, getBounds().y, getBounds().width,
				getBounds().height, 0, 360);
		graphics.setClip(path);
		Rectangle rect = new Rectangle(getBounds());
		graphics.fillGradient(rect, true);
		lightenColor.dispose();
		// super.fillShape(graphics);
	}

	private Font getDefaultFont() {
		return Display.getDefault().getSystemFont();
	}
}
