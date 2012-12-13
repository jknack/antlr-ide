/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.ui.dialogs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.deved.antlride.ui.AntlrUI;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

public abstract class FilteredElementsSelectionDialog<E> extends
		FilteredItemsSelectionDialog {

	private List<E> elements = new ArrayList<E>();

	public FilteredElementsSelectionDialog(Shell shell) {
		super(shell);
		setTitle(getTitle());
		setSelectionHistory(new ElementSelectionHistory());
		setListLabelProvider(new ElementLabelProvider());
		setDetailsLabelProvider(new ElementDetailLabelProvider());
		setBlockOnOpen(true);
	}

	@Override
	protected Control createExtendedContentArea(Composite parent) {
		return null;
	}

	@Override
	protected abstract ItemsFilter createFilter();

	protected abstract String getTitle();

	protected abstract E findElement(String id);

	@SuppressWarnings("unchecked")
	@Override
	protected void fillContentProvider(AbstractContentProvider contentProvider,
			ItemsFilter itemsFilter, IProgressMonitor progressMonitor)
			throws CoreException {
		progressMonitor.beginTask("Searching", getElements().size()); //$NON-NLS-1$
		for (Iterator iter = getElements().iterator(); iter.hasNext();) {
			contentProvider.add(iter.next(), itemsFilter);
			progressMonitor.worked(1);
		}
		progressMonitor.done();
	}

	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings = AntlrUI.getDefault().getDialogSettings()
				.getSection(getDialogName());
		if (settings == null) {
			settings = AntlrUI.getDefault().getDialogSettings().addNewSection(
					getDialogName());
		}
		return settings;
	}

	protected String getDialogName() {
		return getClass().getSimpleName();
	}

	public abstract String getElementName(Object item);

	public abstract String getElementDescription(Object element);
	
	public abstract String getElementId(Object element);

	@Override
	protected abstract Comparator<E> getItemsComparator();

	@Override
	protected IStatus validateItem(Object item) {
		return Status.OK_STATUS;
	}

	public void setElements(List<E> elements) {
		this.elements = elements;
	}

	public void setElements(E[] elements) {
		this.elements = new ArrayList<E>();
		for (E e : elements) {
			this.elements.add(e);
		}
	}

	public List<E> getElements() {
		return elements;
	}

	private class ElementLabelProvider implements ILabelProvider {

		public Image getImage(Object element) {
			return null;
		}

		public String getText(Object element) {
			return getElementName(element);
		}

		public void addListener(ILabelProviderListener listener) {
		}

		public void dispose() {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
		}
	}

	private class ElementDetailLabelProvider implements ILabelProvider {

		public Image getImage(Object element) {
			return null;
		}

		public String getText(Object element) {
			return getElementDescription(element);
		}

		public void addListener(ILabelProviderListener listener) {
		}

		public void dispose() {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
		}
	}

	private class ElementSelectionHistory extends SelectionHistory {
		protected Object restoreItemFromMemento(IMemento element) {
			return findElement(element.getString("resource")); //$NON-NLS-1$
		}

		protected void storeItemToMemento(Object item, IMemento element) {
			element.putString("resource", getElementId(item)); //$NON-NLS-1$
		}
	}
}
