/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import java.util.HashMap;
import java.util.Map;

import org.deved.antlride.core.AntlrConfiguration;
import org.deved.antlride.core.AntlrConstants;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;

public class AntlrGrammarPropertyPage extends PropertyPage {

	private IResource grammarFile;
	
	private boolean fEnabled;
	
	private Map<PreferenceKey, String> preferences;
	
	private static final String PROTOTYPE_MESSAGE = "%1$s is a prototype grammar. You can't set properties on this grammar";

	protected IResource getGrammarFile() {
		return grammarFile;
	}
	
	protected boolean isEnabled() {
		return fEnabled;
	}
	
	protected Map<PreferenceKey, String> getPreferences() {
		if (preferences == null)
			preferences = loadPreferences();
		return preferences;
	}
	
	private Map<PreferenceKey, String> loadPreferences() {
		Map<PreferenceKey, String> preferences = new HashMap<PreferenceKey, String>();
		try {
			AntlrConfiguration configuration = new AntlrConfiguration(
					grammarFile);
			for (PreferenceKey key : getKeys()) {
				String value = grammarFile
						.getPersistentProperty(new QualifiedName(key
								.getQualifier(), key.getName()));
				if (value == null) {
					value = configuration.getOption(key.getName());					
				}
				preferences.put(key, value);
			}
		} catch (CoreException ex) {
			ex.printStackTrace();
		}
		return preferences;
	}
	
	public void savePreferences() {
		for (PreferenceKey key : preferences.keySet()) {
			String value = preferences.get(key);
			try {
				grammarFile.setPersistentProperty(new QualifiedName(key
						.getQualifier(), key.getName()), value);
			} catch (CoreException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	protected PreferenceKey[] getKeys() {return null;}
	
	@Override
	public void setElement(IAdaptable element) {
		grammarFile = (IResource) element.getAdapter(IResource.class);
		fEnabled = true;
		try {
			String prototype = grammarFile
					.getPersistentProperty(AntlrConstants.Q_ANTLR_PROTOTYPE_GRAMMAR);
			if ("true".equals(prototype)) {
				setErrorMessage(String.format(PROTOTYPE_MESSAGE, grammarFile
						.getName()));
				fEnabled = false;
			}
		} catch (CoreException ex) {
			ex.printStackTrace();
		}
		super.setElement(element);
	}
	
	@Override
	protected Control createContents(Composite parent) {
		return new Composite(parent, SWT.NONE);
	}

}
