/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package net.hydromatic.clapham.chart.draw2d;

import java.awt.Dimension;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.hydromatic.clapham.chart.AbstractChart;
import net.hydromatic.clapham.chart.AbstractChartOptions;
import net.hydromatic.clapham.chart.ChartOptions;
import net.hydromatic.clapham.graph.Grammar;
import net.hydromatic.clapham.graph.NodeType;

import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;

public class Draw2dChart extends AbstractChart {
	private Graphics graphics;

//	private TracePath tracePath;

	public Draw2dChart(Grammar grammar) {
		super(grammar);
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
		graphics.setFont(getDefaultFont());
	}

	private ChartOptions createOptions(Font font) {
		return new AbstractChartOptions() {

			public int stringWidth(String text) {
				int size = FigureUtilities.getTextExtents(text,
						getDefaultFont()).width
						+ symbolGapWidth() * 6;
				return size;
			}

			public int fontHeight() {
				int size = FigureUtilities
						.getTextExtents("A", getDefaultFont()).height;
				return size;
			}
		};
	}

	public ChartOptions createOptions() {
		return createOptions(getDefaultFont());
	}

	public ChartOptions createOptions(String fontName) {
		// TODO: create a font
		throw new UnsupportedOperationException(fontName);
	}

	@Override
	public void draw(String symbolName) {
//		tracePath = new TracePath(getOptions());

		super.draw(symbolName);

//		int[] colors = { SWT.COLOR_RED, SWT.COLOR_GREEN, SWT.COLOR_BLUE,
//				SWT.COLOR_CYAN, SWT.COLOR_GRAY, SWT.COLOR_MAGENTA,
//				SWT.COLOR_YELLOW, SWT.COLOR_DARK_RED, SWT.COLOR_DARK_GREEN,
//				SWT.COLOR_DARK_BLUE, SWT.COLOR_DARK_CYAN, SWT.COLOR_DARK_GRAY,
//				SWT.COLOR_DARK_MAGENTA };
//
//		Color foregroundColor = graphics.getForegroundColor();
//
//		System.out.println(tracePath);
//
//		Path p = tracePath.first();
//		Map<String, Collection<Path>> trace = tracePath.trace(p);
//		List<Path> pl = new ArrayList<Path>();
//		pl.add(p);
//		pl.addAll(trace.get("alt"));
//		int c = 0;
//		int l = Math.min(pl.size(), colors.length);
//		while (c < l) {
//			trace = tracePath.trace(pl.get(c));
//			Collection<Path> t = trace.get("path");
//			System.out.println(c + ". " + pl.get(c) + "->" + t);
//			graphics.setForegroundColor(Display.getDefault().getSystemColor(
//					colors[c++]));
//			graphics.setLineWidth(2);
//			for (Path path : t) {
//				path.draw(graphics);
//			}
//			// if (trace.get("alt").size() > 0) {
//			// p = trace.get("alt").iterator().next();
//			// } else {
//			// break;
//			// }
//		}
//		graphics.setForegroundColor(foregroundColor);
//		graphics.setLineWidth(1);
	}

	@Override
	protected void internalDrawRoundRectangle(int x, int y, int width,
			int height, int arcWidth, int arcHeight) {
		Rectangle r = Rectangle.SINGLETON;
		r.x = x;
		r.y = y;
		r.width = width;
		r.height = height;
		graphics.drawRoundRectangle(r, arcWidth, arcHeight);
	}

	public void drawAndExport(String symbolName, File output) {
		OutputStream out = null;

		Dimension size = size(symbolName);

		Image image = null;
		GC gc = null;
		Graphics g = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(output));
			image = new Image(Display.getDefault(), size.width, size.height);
			Color color = JFaceResources.getColorRegistry().get(
					AntlrPreferenceConstants.EDITOR_BACKGROUND_COLOR);
			image.setBackground(color == null ? ColorConstants.white : color);
			gc = new GC(image);
			g = new SWTGraphics(gc);
			g.translate(0, 0);
			setGraphics(g);

			draw(symbolName);

			ImageLoader imageLoader = new ImageLoader();
			imageLoader.data = new ImageData[] { image.getImageData() };
			imageLoader.save(out, SWT.IMAGE_PNG);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (g != null) {
				g.dispose();
			}
			if (gc != null) {
				gc.dispose();
			}
			if (image != null) {
				image.dispose();
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void internalDrawArc(int x, int y, int width, int height,
			int startAngle, int arcAngle) {

//		tracePath.add(new ArcPath(x, y, width, height, startAngle, arcAngle));

		Color foregroundColor = graphics.getForegroundColor();
		Color otherColor = JFaceResources.getColorRegistry().get(
				AntlrPreferenceConstants.EDITOR_OTHERS_COLOR);
		graphics.setForegroundColor(otherColor);
		graphics.drawArc(x, y, width, height, startAngle, arcAngle);
		graphics.setForegroundColor(foregroundColor);
	}

	@Override
	protected void internalDrawArrow(int x1, int y1, int x2, int y2,
			int[] xpoints, int[] ypoints) {
		Color foregroundColor = graphics.getForegroundColor();
		Color backgroundColor = graphics.getBackgroundColor();

		Color fgColor = JFaceResources.getColorRegistry().get(
				AntlrPreferenceConstants.EDITOR_OTHERS_COLOR);

		graphics.setForegroundColor(fgColor);

		internalDrawLine(x1, y1, x2, y2);

		PointList pointList = new PointList(xpoints.length);
		for (int i = 0; i < xpoints.length; i++) {
			pointList.addPoint(xpoints[i], ypoints[i]);
		}
		graphics.setBackgroundColor(fgColor);
		graphics.fillPolygon(pointList);

		graphics.setBackgroundColor(backgroundColor);
		graphics.setForegroundColor(foregroundColor);

	}

	protected void internalDrawLine(int x1, int y1, int x2, int y2) {
		Color foregroundColor = graphics.getForegroundColor();

		Color fgColor = JFaceResources.getColorRegistry().get(
				AntlrPreferenceConstants.EDITOR_OTHERS_COLOR);

		graphics.setForegroundColor(fgColor);
		graphics.drawLine(x1, y1, x2, y2);
		graphics.setForegroundColor(foregroundColor);

//		tracePath.add(new LinePath(x1, y1, x2, y2));
	}

	protected void internalDrawRectangle(int x, int y, int width, int height) {
		Color foregroundColor = graphics.getForegroundColor();

		Color fgColor = JFaceResources.getColorRegistry().get(
				AntlrPreferenceConstants.EDITOR_OTHERS_COLOR);

		graphics.setForegroundColor(fgColor);
		graphics.drawRectangle(x, y, width, height);
		graphics.setForegroundColor(foregroundColor);
	}

	protected void internalDrawString(NodeType nodeType, String name, int x,
			int y) {
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		String colorKey;
		if (nodeType == NodeType.TERM) {
			colorKey = AntlrPreferenceConstants.EDITOR_STRING_COLOR;
		} else if (nodeType == NodeType.NONTERM) {
			if (Character.isUpperCase(name.charAt(0))) {
				colorKey = AntlrPreferenceConstants.EDITOR_LEXER_RULE_COLOR;
			} else {
				colorKey = AntlrPreferenceConstants.EDITOR_RULE_COLOR;
			}
		} else {
			colorKey = AntlrPreferenceConstants.EDITOR_OTHERS_COLOR;
		}

//		tracePath.add(new LabeledPath(name, x, y));
		Color prevColor = graphics.getForegroundColor();
		Color color = colorRegistry.get(colorKey);
		graphics.setForegroundColor(color);
		graphics.drawString(name, x, y);
		graphics.setForegroundColor(prevColor);
	}

	private org.eclipse.swt.graphics.Font getDefaultFont() {
		return Display.getDefault().getSystemFont();
	}

	public int fontHeightCorrectness() {
		return 0;
	}
}
