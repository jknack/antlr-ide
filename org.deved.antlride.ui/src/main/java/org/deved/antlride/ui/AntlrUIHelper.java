/******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.ui;

import org.deved.antlride.core.util.AntlrTextHelper;
import org.deved.antlride.ui.text.AntlrTextSelection;
import org.eclipse.dltk.ui.preferences.SectionManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class AntlrUIHelper {

	public static void select(StyledText styledText,
			AntlrTextSelection selection) {
		int line = selection.getStartLine();
		String text = styledText.getText();
		int offset = AntlrTextHelper.getOffsetAtLine(text, line);
		offset += selection.getOffset();
		int start = offset;
		int end = start + selection.getLength();
		if (start == end) {
			select(styledText, start);
		} else if (end == text.length() && (end - start) == 1) {
			select(styledText, end);
		} else {
			select(styledText, start, end);
		}
	}

	public static void select(StyledText styledText, int start, int end) {
		try {
			styledText.setSelection(start, end);
		} catch (IllegalArgumentException iaex) {
			if ((end - start) == 1) {
				select(styledText, start);
			}
		}
		styledText.setFocus();
	}

	public static void select(StyledText styledText, int start) {
		while (start > 0) {
			try {
				styledText.setSelection(start);
				start = -1;
			} catch (IllegalArgumentException iaex) {
				start--;
			}
		}
		styledText.setFocus();
	}
	
	public static ScrolledForm createScrolledForm(Composite parent) {
		FormToolkit toolkit = AntlrUI.getDefault().getDialogsFormToolkit();

		final ScrolledForm form = toolkit.createScrolledForm(parent);
		form.setLayoutData(new GridData(GridData.FILL_BOTH));

		form.getBody().setLayout(new TableWrapLayout());

		return form;
	}

	public static Composite createSubsection(Composite parent,
			SectionManager manager, String label) {
		if (manager != null) {
			return manager.createSection(label);
		} else {
			Group group = new Group(parent, SWT.SHADOW_NONE);
			group.setText(label);
			GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
			group.setLayoutData(data);
			return group;
		}
	}

	public static Composite createExpandableSection(final ScrolledForm form,
			String label, boolean expanded) {
		return createExpandableSection(form, label, expanded, new GridLayout(2,
				false));
	}

	public static Composite createExpandableSection(final ScrolledForm form,
			String label, boolean expanded, Layout layout) {
		FormToolkit toolkit = AntlrUI.getDefault().getDialogsFormToolkit();

		ExpandableComposite excomposite = toolkit
				.createSection(form.getBody(), ExpandableComposite.TWISTIE
						| ExpandableComposite.CLIENT_INDENT);

		excomposite.setText(label);

		Composite composite = toolkit.createComposite(excomposite);

		excomposite.setClient(composite);

		excomposite.setExpanded(expanded);

		excomposite.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		composite.setLayout(layout);

		return composite;
	}
}
