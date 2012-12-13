/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class AntlrEditorContentAssistBlock extends AbstractConfigurationBlock {

  public AntlrEditorContentAssistBlock(OverlayPreferenceStore store,
      PreferencePage preferencePage) {
    super(store, preferencePage);
    store.addKeys(createOverlayStoreKeys());
  }

  private OverlayPreferenceStore.OverlayKey[] createOverlayStoreKeys() {
    OverlayPreferenceStore.OverlayKey[] keys =
          {
              new OverlayPreferenceStore.OverlayKey(
                  OverlayPreferenceStore.BOOLEAN,
                  PreferenceConstants.CODEASSIST_INSERT_COMPLETION),
              new OverlayPreferenceStore.OverlayKey(
                  OverlayPreferenceStore.STRING,
                  PreferenceConstants.CODEASSIST_SORTER),
              new OverlayPreferenceStore.OverlayKey(
                  OverlayPreferenceStore.BOOLEAN,
                  PreferenceConstants.CODEASSIST_SHOW_VISIBLE_PROPOSALS),
              new OverlayPreferenceStore.OverlayKey(
                  OverlayPreferenceStore.BOOLEAN,
                  PreferenceConstants.CODEASSIST_AUTOACTIVATION),
              new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.INT,
                  PreferenceConstants.CODEASSIST_AUTOACTIVATION_DELAY),
              new OverlayPreferenceStore.OverlayKey(
                  OverlayPreferenceStore.STRING,
                  PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS) };
    return keys;
  }

  public Control createControl(Composite parent) {
    initializeDialogUnits(parent);

    Composite control = new Composite(parent, SWT.NONE);
    control.setLayout(new GridLayout());
    Composite composite;
    // insertion
    composite =
        createSubsection(
            control,
            null,
            AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_insertionTitle);
    createInsertionGroup(composite);
    // sorting and filtering
    composite =
        createSubsection(
            control,
            null,
            AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_sortTitle);
    createSortingAndFilteringGroup(composite);
    // auto activation
    composite =
        createSubsection(
            control,
            null,
            AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_autoActivationTitle);
    createAutoActivationGroup(composite);
    return control;
  }

  private Control createInsertionGroup(Composite composite) {
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    composite.setLayout(layout);

    boolean insertCompletation =
        getPreferenceStore().getBoolean(
            PreferenceConstants.CODEASSIST_INSERT_COMPLETION);
    String label;
    label =
        AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_insertionCompletionInserts;
    addRadioButton(composite, label,
        PreferenceConstants.CODEASSIST_INSERT_COMPLETION, insertCompletation, "insert");

    label =
        AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_insertionCompletionOverwrites;
    addRadioButton(composite, label,
        PreferenceConstants.CODEASSIST_INSERT_COMPLETION, !insertCompletation, "ovewrite");
    new Label(composite, SWT.NONE)
        .setText(AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_insertionDescription);

    return composite;
  }

  private Control createSortingAndFilteringGroup(Composite composite) {
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    composite.setLayout(layout);

    String label;

    label =
        AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_sortProposals;
    String[] items =
          {
              AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_sortProposalsByRelevance,
              AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_sortProposalsAlphabetically };
    String[] values =
          { "org.eclipse.dltk.ui.RelevanceSorter",
              "org.eclipse.dltk.ui.AlphabeticSorter" };

    addComboBox(composite, label, PreferenceConstants.CODEASSIST_SORTER, items,
        values);
    label =
        AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_sortShowVisibleProposals;
    addCheckBox(composite, label,
        PreferenceConstants.CODEASSIST_SHOW_VISIBLE_PROPOSALS, 0);
    return composite;
  }

  private Control createAutoActivationGroup(Composite composite) {
    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    composite.setLayout(layout);

    String label;

    label =
        AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_autoActivation;
    addCheckBox(composite, label,
        PreferenceConstants.CODEASSIST_AUTOACTIVATION, 0);

    label =
        AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_autoActivationDelay;
    addLabelledTextField(composite, label,
        PreferenceConstants.CODEASSIST_AUTOACTIVATION_DELAY, 3, 0, true);

    label =
        AntlrPreferenceMessages.ANTLREditorContentAssistPreferencePage_autoActivationTriggers;
    addLabelledTextField(composite, label,
        PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS, 5, 0, false);

    return composite;
  }

  private SelectionListener fRadioButtonListener = new SelectionListener() {

    public void widgetSelected(SelectionEvent e) {
      Button button = (Button) e.widget;
      if (button.getSelection()) {
        String[] info = (String[]) button.getData();
        getPreferenceStore().setValue(info[0], info[1].equals("insert")?new Boolean(true):new Boolean(false));
      }
    }

    public void widgetDefaultSelected(SelectionEvent e) {
    }

  };

  protected Button addRadioButton(Composite parent, String label, String key,
		  boolean selected, String value) {
    GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);

    Button button = new Button(parent, SWT.RADIO);
    button.setText(label);
    button.setData(new String[]
      { key, value });
    button.addSelectionListener(fRadioButtonListener);
    button.setLayoutData(gd);

    button.setSelection(selected);

    return button;
  }
}
