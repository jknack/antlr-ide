/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.osgi.framework.Bundle;

public class AntlrCoreHelper {

	public static String loadFileAsString(Class<?> loader, String filename)
			throws IOException {
		return loadFileAsString(loader.getResourceAsStream(filename));
	}

	public static String loadFileAsString(File in) throws IOException {
		return loadFileAsString(new FileInputStream(in));		
	}
	
	public static String loadFileAsString(InputStream in) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedInputStream bis = new BufferedInputStream(in);
		int b = bis.read();
		while (b != -1) {
			builder.append((char) b);
			b = bis.read();
		}
		bis.close();
		return builder.toString();
	}

	public static int copy(InputStream in, OutputStream out) throws IOException {
		try {
			int byteCount = 0;
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			return byteCount;
		} finally {
			try {
				in.close();
			} catch (IOException ex) {
			}
			try {
				out.close();
			} catch (IOException ex) {
			}
		}
	}

	public static void copyFileFromBundle(Bundle bundle, String from, File to)
			throws IOException {
		URL resource = bundle.getResource(from);
		BufferedInputStream in = new BufferedInputStream(resource.openStream());
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(to));
		copy(in, out);
	}
}
