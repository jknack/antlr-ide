/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.model.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.runtime.IPath;

public class AntlrTestCase {

	private String input;

	private IPath path;

	private String name;

	private String rule;

	public AntlrTestCase(IPath path, String input) {
		this.path = path;
		IPath parent = path.removeLastSegments(1);
		rule = parent.lastSegment();
		name = path.lastSegment();
		this.input = input;
	}

	public String getInput() throws IOException {
		return input;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AntlrTestCase) {
			AntlrTestCase that = (AntlrTestCase) obj;
			return this.rule.equals(that.rule) && this.name.equals(that.name);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public IPath getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public String getRule() {
		return rule;
	}

	@Override
	public String toString() {
		return new StringBuilder(rule).append(" :: ").append(name).toString();
	}

	void save(String content) throws IOException {
		this.input = content;
		File file = path.toFile();
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		// path.toFile();
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(file));
		out.write(content.getBytes());
		out.close();
	}
}
