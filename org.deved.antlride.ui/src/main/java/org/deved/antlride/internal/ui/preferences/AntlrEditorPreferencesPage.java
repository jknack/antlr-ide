/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.preferences;

import org.deved.antlride.ui.AntlrUI;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class AntlrEditorPreferencesPage extends AbstractConfigurationBlockPreferencePage {

  protected String getHelpId() {
    return null;
  }

  protected void setDescription() {
    String description = AntlrPreferenceMessages.ANTLREditorPreferencePage_general;
    setDescription(description);
  }

  protected Label createDescriptionLabel(Composite parent) {
    return null;
  }

  protected void setPreferenceStore() {
    setPreferenceStore(AntlrUI.getDefault().getPreferenceStore());
  }

  protected IPreferenceConfigurationBlock createConfigurationBlock(OverlayPreferenceStore overlayPreferenceStore) {
    return new AntlrEditorBlock(this, overlayPreferenceStore);
  }
}
