/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.ui;

import java.util.Hashtable;
import java.util.List;

import org.deved.antlride.core.AntlrConsole;
import org.deved.antlride.core.AntlrCore;
import org.deved.antlride.core.util.AntlrCoreExtensionPointHelper;
import org.deved.antlride.internal.ui.AntlrConsoleImpl;
import org.deved.antlride.internal.ui.text.AntlrTextTools;
import org.deved.antlride.ui.editor.AntlrEditorPageContribution;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.DataFormatException;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
public class AntlrUI extends AbstractUIPlugin {

	private AntlrTextTools textTools;

	private IPropertyChangeListener fPropertyChangeListener;

	private FormToolkit fDialogsFormToolkit;

	// The plug-in ID
	public static final String PLUGIN_ID = "org.deved.antlride.ui";

	// The shared instance
	private static AntlrUI plugin;

	/**
	 * The constructor
	 */
	public AntlrUI() {
	}

	public synchronized AntlrTextTools getTextTools() {
		if (textTools == null) {
			textTools = new AntlrTextTools(true);
		}
		return textTools;
	}

	public AntlrEditorPageContribution[] getPageContributions() {
		List<AntlrEditorPageContribution> pages = AntlrCoreExtensionPointHelper
				.loadExtensions(AntlrUI.PLUGIN_ID, "editorPageContribution");
		return pages.toArray(new AntlrEditorPageContribution[pages.size()]);
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		context.registerService(AntlrConsole.class.getName(),
				new AntlrConsoleImpl(), new Hashtable<String, String>());
	}

	@Override
	public IPreferenceStore getPreferenceStore() {
		final IPreferenceStore preferenceStore = super.getPreferenceStore();
		if (fPropertyChangeListener == null) {
			fPropertyChangeListener = new IPropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent event) {
					Preferences preferences = AntlrCore.getDefault()
							.getPluginPreferences();
					String property = event.getProperty();
					if (property.startsWith("antlr_core")) {
						preferences.setValue(property, String.valueOf(event
								.getNewValue()));
					} else if (AntlrPreferenceConstants.getColorKeys()
							.contains(property)) {
						Object value = event.getNewValue();
						try {
							RGB color = value instanceof RGB ? (RGB) value
									: StringConverter.asRGB(value.toString());
							JFaceResources.getColorRegistry().put(property,
									color);
						} catch (DataFormatException ex) {
							//Some values are not colors
							//ignore the exception
						}
					}
				}
			};
			preferenceStore.addPropertyChangeListener(fPropertyChangeListener);
		}
		return preferenceStore;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		try {
			ServiceReference serviceReference = context
					.getServiceReference(AntlrConsole.class.getName());
			context.ungetService(serviceReference);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static AntlrUI getDefault() {
		return plugin;
	}

	public FormToolkit getDialogsFormToolkit() {
		if (fDialogsFormToolkit == null) {
			FormColors colors = new FormColors(Display.getCurrent());
			colors.setBackground(null);
			colors.setForeground(null);
			fDialogsFormToolkit = new FormToolkit(colors);
		}
		return fDialogsFormToolkit;
	}

	public static void error(String message) {
		error(message, null);
	}

	public static void warning(String message) {
		warning(message, null);
	}

	public static void info(String message) {
		info(message, null);
	}

	public static void error(Throwable throwable) {
		error(throwable.getMessage(), throwable);
	}

	public static void error(final String message, Throwable throwable) {
		org.eclipse.core.runtime.IStatus status = new Status(Status.ERROR,
				getDefault().getBundle().getSymbolicName(), 42, message,
				throwable);
		getDefault().getLog().log(status);
	}

	public static void warning(final String message, Throwable throwable) {
		org.eclipse.core.runtime.IStatus status = new Status(2, getDefault()
				.getBundle().getSymbolicName(), 43, message, throwable);
		getDefault().getLog().log(status);
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				MessageDialog.openWarning(null, "Antlr 3 Warning", message);
			}
		});
	}

	public static void info(String message, Throwable throwable) {
		org.eclipse.core.runtime.IStatus status = new Status(1, getDefault()
				.getBundle().getSymbolicName(), 44, message, throwable);
		getDefault().getLog().log(status);
	}

	public static ISourceModule getEditorInputModelElement(IEditorInput input) {
		ISourceModule sourceModule = (ISourceModule) DLTKUIPlugin
				.getEditorInputModelElement(input);
		if (sourceModule == null) {
			sourceModule = DLTKUIPlugin.getDefault().getWorkingCopyManager()
					.getWorkingCopy(input, false);
		}
		return sourceModule;
	}
}
