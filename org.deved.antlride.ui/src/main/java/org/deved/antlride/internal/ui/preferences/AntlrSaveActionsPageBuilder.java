package org.deved.antlride.internal.ui.preferences;

import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class AntlrSaveActionsPageBuilder implements SelectionListener {

	private AntlrSaveActionsPreferences preferences;
	
	public AntlrSaveActionsPageBuilder(AntlrSaveActionsPreferences preferences) {
		this.preferences = preferences;
	}
	
	public Control build(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		composite.setLayout(layout);
		PreferenceKey[] keys = AntlrSaveActionsPreferences.KEYS;
		String[] keystext = AntlrSaveActionsPreferences.LABELS;
		for (int i = 0; i < keys.length; i++) {
			Button button = new Button(composite, SWT.CHECK);
			button.addSelectionListener(this);
			button.setData(keys[i]);
			button.setSelection(preferences.getBoolean(keys[i]));
			button.setText(keystext[i]);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			if (i > 0) {
				gd.horizontalIndent = 10;
			}
			button.setLayoutData(gd);
		}

		return composite;
	}

	public void widgetDefaultSelected(SelectionEvent event) {

	}

	public void widgetSelected(SelectionEvent event) {
		Button button = (Button) event.widget;
		PreferenceKey key = (PreferenceKey) button.getData();
		preferences.setBoolean(key, button.getSelection());
	}
}
