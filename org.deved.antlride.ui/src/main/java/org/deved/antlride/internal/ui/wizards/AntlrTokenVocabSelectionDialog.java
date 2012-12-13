package org.deved.antlride.internal.ui.wizards;

import java.util.Comparator;
import java.util.List;

import org.deved.antlride.ui.dialogs.FilteredElementsSelectionDialog;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Shell;

public class AntlrTokenVocabSelectionDialog extends
		FilteredElementsSelectionDialog<IResource> {

	public AntlrTokenVocabSelectionDialog(Shell shell) {
		super(shell);
	}

	@Override
	protected ItemsFilter createFilter() {
		return new ItemsFilter() {

			@Override
			public boolean matchItem(Object item) {
				if (item instanceof IResource) {
					IResource resource = (IResource) item;
					return matches(resource.getName());
				}
				return false;
			}

			@Override
			public boolean isConsistentItem(Object item) {
				return item instanceof IResource;
			}
		};
	}

	@Override
	protected IResource findElement(String id) {
		List<IResource> resources = getElements();
		for (IResource resource : resources) {
			if (id.equals(getElementDescription(resource)))
				return resource;
		}
		return null;
	}

	@Override
	public String getElementDescription(Object element) {
		if (element instanceof IResource) {
			return ((IResource) element).getFullPath().toString().substring(1);
		}
		return null;
	}

	@Override
	public String getElementId(Object element) {
		return getElementDescription(element);
	}

	@Override
	public String getElementName(Object item) {
		return getElementDescription(item);
	}

	@Override
	protected Comparator<IResource> getItemsComparator() {
		return new Comparator<IResource>() {
			public int compare(IResource o1, IResource o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		};
	}

	@Override
	protected String getTitle() {
		return "Select a lexer or combined grammar";
	}

}
