/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.gunit.internal.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.dltk.core.ScriptContentDescriber;

public class GUnitContentDescriber extends ScriptContentDescriber {

	public int describe(Reader contents, IContentDescription description)
			throws IOException {
		return VALID;
	}

	public int describe(InputStream contents, IContentDescription description)
			throws IOException {
		return VALID;
	}

	@Override
	protected Pattern[] getHeaderPatterns() {
		// TODO Auto-generated method stub
		return null;
	}

}
