/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.deved.antlride.core.AntlrCore;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

public final class AntlrResourceTracker implements IResourceChangeListener {

	private static final List<AntlrResourceListener> listeners = Collections
			.synchronizedList(new ArrayList<AntlrResourceListener>());

	private static AntlrResourceTracker tracker = null;

	private AntlrResourceTracker() {
	}

	public static void startTracking() {
		if (tracker == null) {
			tracker = new AntlrResourceTracker();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(tracker);
		}
	}

	public static void addResourceChangeListener(AntlrResourceListener listener) {
		listeners.add(listener);
	}

	public static void removeResourceChangeListener(
			AntlrResourceListener listener) {
		listeners.remove(listener);
	}

	public static void stopTracking() {
		if (tracker != null) {
			ResourcesPlugin.getWorkspace()
					.removeResourceChangeListener(tracker);
			listeners.clear();
			tracker = null;
		}
	}

	private void fireResourceAdded(IResource resource) {
		List<AntlrResourceListener> list = new ArrayList<AntlrResourceListener>(
				listeners);
		for (AntlrResourceListener listener : list) {
			listener.added(resource);
		}
	}

	private void fireResourceChanged(IResource resource) {
		List<AntlrResourceListener> list = new ArrayList<AntlrResourceListener>(
				listeners);
		for (AntlrResourceListener listener : list) {
			listener.changed(resource);
		}
	}

	private void fireResourceRemoved(IResource resource) {
		List<AntlrResourceListener> list = new ArrayList<AntlrResourceListener>(
				listeners);
		for (AntlrResourceListener listener : list) {
			listener.removed(resource);
		}
	}

	public void resourceChanged(IResourceChangeEvent event) {
		try {
			IResourceDelta delta = event.getDelta();
			if (delta == null) return;
			delta.accept(new IResourceDeltaVisitor() {

				private boolean isValid(IResource resource) {
					return "g".equals(resource.getFileExtension());
				}

				public boolean visit(IResourceDelta delta) throws CoreException {
					IResource resource = delta.getResource();
					if (isValid(resource)) {
						switch (delta.getKind()) {
						case IResourceDelta.ADDED:
							fireResourceAdded(resource);
							break;
						case IResourceDelta.REMOVED:
							fireResourceRemoved(resource);
							break;
						case IResourceDelta.CHANGED:
							fireResourceChanged(resource);
							break;
						}
						return false;
					}
					// return true to continue visiting children.
					return true;
				}
			});
		} catch (CoreException ex) {
			AntlrCore.error(ex);
		}
	}
}
