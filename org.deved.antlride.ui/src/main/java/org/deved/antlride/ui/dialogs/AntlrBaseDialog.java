/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.ui.dialogs;

import java.util.HashMap;
import java.util.Map;

import org.deved.antlride.ui.AntlrUI;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class AntlrBaseDialog extends TitleAreaDialog {

	protected Rectangle cachedBounds;

	private static Map<String, IDialogSettings> settings = new HashMap<String, IDialogSettings>();

	private static final String TAG_X = "x";

	private static final String TAG_Y = "y";

	private static final String TAG_WIDTH = "width";

	private static final String TAG_HEIGHT = "height";

	private BoundsListener boundsListener;

	private class BoundsListener implements ControlListener {

		public void controlMoved(ControlEvent e) {
			cachedBounds = getShell().getBounds();
		}

		public void controlResized(ControlEvent e) {
			cachedBounds = getShell().getBounds();
		}

	}

	public AntlrBaseDialog(Shell shell) {
		super(shell);
		setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MAX);
	}
	
	@Override
	protected final Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle();
		return contents;
	}

	protected void setTitle() {
		// TODO Auto-generated method stub
		
	}

	protected abstract String getDialogName();

	@Override
	protected Control createDialogArea(Composite parent) {
		return super.createDialogArea(parent);
	}

	private Rectangle loadBounds() {
		IDialogSettings settings = getDialogSettings();
		try {
			return new Rectangle(settings.getInt(TAG_X),
					settings.getInt(TAG_Y), settings.getInt(TAG_WIDTH),
					settings.getInt(TAG_HEIGHT));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private IDialogSettings getDialogSettings() {
		IDialogSettings dialogSettings = settings.get(getDialogName());
		if (dialogSettings == null) {
			dialogSettings = AntlrUI.getDefault().getDialogSettings()
					.addNewSection(getDialogName());
			settings.put(getDialogName(), dialogSettings);
		}
		return dialogSettings;
	}

	private void saveBounds(Rectangle bounds) {
		IDialogSettings settings = getDialogSettings();
		settings.put(TAG_X, bounds.x);
		settings.put(TAG_Y, bounds.y);
		settings.put(TAG_WIDTH, bounds.width);
		settings.put(TAG_HEIGHT, bounds.height);
	}

	protected Point getInitialSize() {
		// Track the current dialog bounds.
		if (boundsListener == null) {
			boundsListener = new BoundsListener();
			getShell().addControlListener(boundsListener);
		}
		// Answer the size from the previous incarnation.
		Rectangle b1 = getShell().getDisplay().getBounds();
		Rectangle b2 = loadBounds();
		if (b2 != null) {
			return new Point(b1.width < b2.width ? b1.width : b2.width,
					b1.height < b1.height ? b2.height : b2.height);
		}
		Point defaultSize = getDefaultSize();
		return defaultSize == null ? super.getInitialSize() : defaultSize;
	}

	protected Point getDefaultSize() {
		return null;
	}

	protected Point getInitialLocation(Point initialSize) {
		Display display = getShell().getDisplay();
		Rectangle displayBounds = display.getBounds();
		Rectangle bounds = loadBounds();
		if (bounds != null) {
			int x = bounds.x;
			int y = bounds.y;
			int maxX = displayBounds.x + displayBounds.width - initialSize.x;
			int maxY = displayBounds.y + displayBounds.height - initialSize.y;
			if (x > maxX)
				x = maxX;
			if (y > maxY)
				y = maxY;
			if (x < displayBounds.x)
				x = displayBounds.x;
			if (y < displayBounds.y)
				y = displayBounds.y;
			return new Point(x, y);
		} else {
			int width = display.getClientArea().width;
			int height = display.getClientArea().height;
			Point size = getInitialSize();
			int x = (width - size.x) / 2;
			int y = ((height - size.y) / 2);
			return new Point(x, y);

		}
	}

	public boolean close() {
		boolean closed = super.close();
		if (closed && cachedBounds != null)
			saveBounds(cachedBounds);
		return closed;
	}
}
