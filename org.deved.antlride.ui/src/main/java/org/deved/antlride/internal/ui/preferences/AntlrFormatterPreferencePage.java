package org.deved.antlride.internal.ui.preferences;

import org.deved.antlride.core.AntlrNature;
import org.deved.antlride.internal.ui.text.AntlrSimpleSourceViewerConfiguration;
import org.deved.antlride.ui.AntlrPreferenceConstants;
import org.deved.antlride.ui.AntlrUI;
import org.deved.antlride.ui.text.AntlrTextPartitions;
import org.eclipse.dltk.ui.formatter.AbstractFormatterPreferencePage;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * Preference page for ANTLR
 */
public class AntlrFormatterPreferencePage extends
		AbstractFormatterPreferencePage {

	public static final String PROPERTY_PAGE_ID = "org.deved.antlride.ui.propertyPage.editor.formatter";//$NON-NLS-1$

	public static final String PREFERENCE_PAGE_ID = "org.deved.antlride.ui.preferences.editor.formatter";//$NON-NLS-1$

	private static final PreferenceKey FORMATTER = new PreferenceKey(
			AntlrUI.PLUGIN_ID, AntlrPreferenceConstants.FORMATTER_ID);

	protected String getNatureId() {
		return AntlrNature.NATURE_ID;
	}

	protected PreferenceKey getFormatterPreferenceKey() {
		return FORMATTER;
	}

	protected IDialogSettings getDialogSettings() {
		return AntlrUI.getDefault().getDialogSettings();
	}

	protected String getPreferencePageId() {
		return PREFERENCE_PAGE_ID;
	}

	protected String getPropertyPageId() {
		return PROPERTY_PAGE_ID;
	}

	protected ScriptSourceViewerConfiguration createSimpleSourceViewerConfiguration(
			IColorManager colorManager, IPreferenceStore preferenceStore,
			ITextEditor editor, boolean configureFormatter) {
		return new AntlrSimpleSourceViewerConfiguration(colorManager,
				getPreferenceStore(), null,
				AntlrTextPartitions.ANTLR_PARTITIONING, configureFormatter);
	}

	protected void setPreferenceStore() {
		setPreferenceStore(AntlrUI.getDefault().getPreferenceStore());
	}

}
