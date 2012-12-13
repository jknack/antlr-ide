/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.ui;

import java.io.IOException;
import java.io.OutputStream;

import org.deved.antlride.core.AntlrConsole;
import org.deved.antlride.core.AntlrCore;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.console.ConsoleColorProvider;
import org.eclipse.debug.ui.console.IConsoleColorProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleOutputStream;

//@SuppressWarnings("restriction")
public class AntlrConsoleImpl implements AntlrConsole {

	private class AntlrOutputTask implements Runnable {

		private String message;

		private boolean error;

		public AntlrOutputTask(String message, boolean error) {
			this.message = message;
			this.error = error;
		}

		public void run() {
			try {
				OutputStream outputStream = newOutputStream(error);
				outputStream.write(message.getBytes());
				outputStream.write('\n');
				outputStream.close();
			} catch (IOException e) {
				AntlrCore.error(e);
			}
		}
	}

	public void error(final String message) {
		Display.getDefault().syncExec(new AntlrOutputTask(message, true));
	}

	public OutputStream getErrorOutputStream() {
		return newOutputStream(true);
	}

	public OutputStream getOutputStream() {
		return newOutputStream(false);
	}

	public void setAttribute(String key, Object value) {
		IOConsole console = AntlrConsoleFactory.getConsole();
		console.setAttribute(key, value);
	}

	private OutputStream newOutputStream(boolean error) {
		IOConsole console = AntlrConsoleFactory.getConsole();
		IOConsoleOutputStream outputStream = console.newOutputStream();
		IConsoleColorProvider colorProvider = new ConsoleColorProvider();
		String streamId = error ? IDebugUIConstants.ID_STANDARD_ERROR_STREAM
				: IDebugUIConstants.ID_STANDARD_OUTPUT_STREAM;
		Color color = colorProvider.getColor(streamId);
		outputStream.setColor(color);
		outputStream.setActivateOnWrite(true);
		return outputStream;
	}

	public void info(final String message) {
		Display.getDefault().syncExec(new AntlrOutputTask(message, false));
	}
}
