package org.deved.antlride.internal.jdt.compiler;

import org.deved.antlride.core.AntlrNature;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.compiler.BuildContext;
import org.eclipse.jdt.core.compiler.CompilationParticipant;

public class AntlrJavaCompilationParticipant extends CompilationParticipant {
	@Override
	public boolean isActive(IJavaProject javaProject) {
		try {
			return javaProject.getProject().hasNature(AntlrNature.NATURE_ID);
		} catch (CoreException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void processAnnotations(BuildContext[] files) {
		for (BuildContext buildContext : files) {
			IFile file = buildContext.getFile();
			try {
				IMarker[] markers = file.findMarkers(
						IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER, false,
						IResource.DEPTH_INFINITE);
				for (IMarker marker : markers) {
					int severity = (Integer) marker
							.getAttribute(IMarker.SEVERITY);
					if (severity == IMarker.SEVERITY_ERROR) {
						System.out
								.println(marker.getAttribute(IMarker.MESSAGE));
					}
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean isAnnotationProcessor() {
		return true;
	}
}
