package org.deved.antlride;

import org.deved.antlride.core.build.AntlrBuildUnitRepository;
import org.deved.antlride.core.build.AntlrBuildUnit.AntlrBuildUnitType;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class IDE extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.deved.antlride";

	// The shared instance
	private static IDE plugin;
	
	/**
	 * The constructor
	 */
	public IDE() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		AntlrBuildUnitRepository.setBuildUnit(AntlrBuildUnitType.FILE_SYSTEM);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static IDE getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public void error(String message, Throwable cause) {
		getLog().log(new Status(IStatus.ERROR, IDE.PLUGIN_ID, message, cause));
	}
}
