/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package org.deved.antlride.formatter.preferences;

import org.deved.antlride.core.formatter.AntlrFormatterPreference;
import org.eclipse.dltk.ui.formatter.IFormatterControlManager;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class AntlrFormatterControlManager implements IFormatterControlManager {

	private IFormatterControlManager manager;

	public AntlrFormatterControlManager(IFormatterControlManager manager) {
		this.manager = manager;
	}

	public void addInitializeListener(IInitializeListener listener) {
		manager.addInitializeListener(listener);
	}

	private Button layout(Button button) {
		return button;
	}

	public Button createCheckbox(Composite parent, Object key, String text) {
		return layout(manager.createCheckbox(parent,
				((AntlrFormatterPreference) key).getName(), text));
	}

	public Button createCheckbox(Composite parent, Object key, String text,
			int hspan) {
		return layout(manager.createCheckbox(parent,
				((AntlrFormatterPreference) key).getName(), text, hspan));
	}

	@Deprecated
	public Combo createCombo(Composite parent, Object key, String label,
			String[] items) {
		return manager.createCombo(parent, ((AntlrFormatterPreference) key)
				.getName(), label, items);
	}

	public Combo createCombo(Composite parent, Object key, String label,
			String[] itemValues, String[] itemLabels) {
		return layout(manager.createCombo(parent,
				((AntlrFormatterPreference) key).getName(), label, itemValues,
				itemLabels));
	}

	private Combo layout(Combo combo) {
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 120;
		gd.grabExcessHorizontalSpace = true;
		combo.setLayoutData(gd);
		return combo;
	}

	public Text createNumber(Composite parent, Object key, String label) {
		return layout(manager.createNumber(parent,
				((AntlrFormatterPreference) key).getName(), label));
	}

	private Text layout(Text text) {
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 25;
		gd.grabExcessHorizontalSpace = true;
		text.setLayoutData(gd);
		return text;
	}

	public void enableControl(Control control, boolean enabled) {
		manager.enableControl(control, enabled);
	}

	public void removeInitializeListener(IInitializeListener listener) {
		manager.removeInitializeListener(listener);
	}

	public boolean getBoolean(Object key) {
		return manager.getBoolean(key);
	}

	public String getString(Object key) {
		return manager.getString(key);
	}

	public void setBoolean(Object key, boolean value) {
		manager.setBoolean(key, value);
	}

	public void setString(Object key, String value) {
		manager.setString(key, value);
	}

}
