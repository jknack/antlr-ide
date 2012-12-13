package org.deved.antlride.app;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.ide.IDEInternalWorkbenchImages;

@SuppressWarnings("restriction")
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "org.deved.antlride.perspective";

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}
	
	public void initialize(IWorkbenchConfigurer configurer) {
		super.initialize(configurer);
		// no obvious reason to do this
		declareSharedImage(configurer, ISharedImages.IMG_OBJS_ERROR_TSK,
				IDEInternalWorkbenchImages.IMG_OBJS_ERROR_PATH);
		declareSharedImage(configurer, ISharedImages.IMG_OBJS_WARN_TSK,
				IDEInternalWorkbenchImages.IMG_OBJS_WARNING_PATH);
		declareSharedImage(configurer, ISharedImages.IMG_OBJS_INFO_TSK,
				IDEInternalWorkbenchImages.IMG_OBJS_INFO_PATH);

		// save the changes
		configurer.setSaveAndRestore(true);
	}

	private void declareSharedImage(IWorkbenchConfigurer configurer,
			String from, String to) {
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		ImageDescriptor descriptor = sharedImages.getImageDescriptor(from);
		configurer.declareImage(to, descriptor, true);
	}

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
}
