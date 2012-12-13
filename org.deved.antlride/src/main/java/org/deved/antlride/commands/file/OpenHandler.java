package org.deved.antlride.commands.file;

import java.lang.reflect.InvocationTargetException;

import org.deved.antlride.IDE;
import org.deved.antlride.core.AntlrNature;
import org.deved.antlride.ui.AntlrUIConstants;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;

public class OpenHandler extends AbstractHandler implements IHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindow(event);
		final Shell shell = window.getShell();
		// File standard dialog
		FileDialog fileDialog = new FileDialog(shell);
		// Set the text
		fileDialog.setText("Open");
		// Set filter on .g files
		fileDialog.setFilterExtensions(new String[] { "*.g" });

		final String stringPath = fileDialog.open();
		if (stringPath != null) {
			ProgressMonitorDialog monitor = new ProgressMonitorDialog(shell);
			try {
				monitor.run(false, false, new IRunnableWithProgress() {
					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						try {
							IPath location = new Path(stringPath);
							IWorkspace ws = ResourcesPlugin.getWorkspace();
							IProject project = ws.getRoot().getProject(
									"workspace");
							if (!project.exists()) {
								// create the project
								project.create(monitor);
								// open it
								project.open(monitor);

								// add the nature
								IProjectDescription description = project
										.getDescription();
								String[] natures = description.getNatureIds();
								String[] newNatures = new String[natures.length + 2];
								System.arraycopy(natures, 0, newNatures, 0,
										natures.length);
								newNatures[natures.length] = AntlrNature.NATURE_ID;
								newNatures[natures.length + 1] = "org.eclipse.jdt.core.javanature";
								description.setNatureIds(newNatures);
								project.setDescription(description, monitor);
							}
							// check for open
							if (!project.isOpen()) {
								project.open(monitor);
							}
							//create subfolders
							createPath(monitor, project, location);
							// create a link
							IFile file = project.getFile(location);
							if (!file.exists()) {
								file.createLink(location, IResource.NONE,
										monitor);
							}
							// open the ANTLR editor
							IWorkbenchPage page = window.getActivePage();
							if (page != null) {
								page.openEditor(new FileEditorInput(file),
										AntlrUIConstants.EDITOR_ID);
							}
						} catch (PartInitException e) {
							throw new InvocationTargetException(e);
						} catch (CoreException e) {
							throw new InvocationTargetException(e);
						}
					}

					private void createPath(IProgressMonitor monitor,
							IProject project, IPath location)
							throws CoreException {
						IPath containerLocation = location
								.removeLastSegments(1);
						IContainer container = project;
						for (int i = 0; i < containerLocation
								.segmentCount(); i++) {
							IPath folderPath = new Path(containerLocation
									.segment(i));
							// create a folder with the same name
							IFolder folder = container
									.getFolder(folderPath);
							if (!folder.exists()) {
								folder
										.create(IResource.NONE, true,
												monitor);
							}
							container = folder;
						}
					}
				});
			} catch (InvocationTargetException e) {
				ErrorDialog.openError(shell, "ANTLR IDE", null, new Status(
						IStatus.ERROR, IDE.PLUGIN_ID, "Couldn't open "
								+ stringPath, e.getTargetException()));
				IDE.getDefault().error("Couldn't open " + stringPath,
						e.getTargetException());
			} catch (InterruptedException e) {
				IDE.getDefault().error("Couldn't open " + stringPath, e);
			}
		}
		return null;
	}

}
