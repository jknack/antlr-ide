/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.debug.ui.breakpoints;

import java.lang.reflect.Constructor;

import org.deved.antlride.core.model.IGrammar;
import org.deved.antlride.core.model.IModelElement;
import org.deved.antlride.core.model.ast.ModelElementQuery;
import org.deved.antlride.core.util.AntlrTextHelper;
import org.deved.antlride.debug.AntlrDebugConstants;
import org.deved.antlride.debug.breakpoints.AntlrBreakpoint;
import org.deved.antlride.debug.breakpoints.AntlrConsumeTokenBreakpoint;
import org.deved.antlride.debug.breakpoints.AntlrLTBreakpoint;
import org.deved.antlride.debug.breakpoints.AntlrLocationBreakpoint;
import org.deved.antlride.ui.AntlrWorkbenchAdapter;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PropertyPage;

public class AntlrBreakpointsPropertyPage extends PropertyPage {

	private int fLine;

	private int fColumn;

	private IGrammar fGrammar;

	private String fBreakpointType;

	private String fElementName;

	private static final String LOCATION_BREAKPOINT = "LOCATION";

	private static final String CONSUME_TOKEN_BREAKPOINT = "CONSUME TOKEN";

	private static final String LT_BREAKPOINT = "LT";

	private static final String[] PARSER_RULE_BREAKPONT = { LOCATION_BREAKPOINT };

	private static final String[] BREAKPOINTS = { LOCATION_BREAKPOINT,
			CONSUME_TOKEN_BREAKPOINT, LT_BREAKPOINT };

	private class BreakpointTypeSelectionListener implements SelectionListener {

		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			Combo combo = (Combo) e.widget;
			fBreakpointType = combo.getItem(combo.getSelectionIndex());
		}
	}

	private class CreateBreakpointTask implements IWorkspaceRunnable {

		private IPath fPath;

		public CreateBreakpointTask(IPath path) {
			fPath = path;
		}

		public void run(IProgressMonitor monitor) throws CoreException {
			createBreakpoint(fPath);
		}
	}

	public AntlrBreakpointsPropertyPage() {
	}

	@Override
	public boolean performOk() {
		try {
			ResourcesPlugin.getWorkspace().run(
					new CreateBreakpointTask(fGrammar.getFile()), null);
		} catch (CoreException e) {
			DebugPlugin.log(e);
		}
		return super.performOk();
	}

	@Override
	public boolean performCancel() {
		return super.performCancel();
	}

	@Override
	public void dispose() {
		// clear reference
		fGrammar = null;
		setElement(null);
		super.dispose();
	}

	private IBreakpointManager getBreakpointManager() {
		return DebugPlugin.getDefault().getBreakpointManager();
	}

	private IBreakpoint[] getInstalledBreakpoints() {
		return getBreakpointManager().getBreakpoints(
				AntlrDebugConstants.DEBUG_MODEL_ID);
	}

	private void createBreakpoint(IPath path) throws CoreException {
		IResource resource = ResourcesPlugin.getWorkspace().getRoot().getFile(
				path);
		IBreakpoint[] breakpoints = getInstalledBreakpoints();
		Class<? extends AntlrBreakpoint> breakpointClass = AntlrLocationBreakpoint.class;
		if (fBreakpointType.equals(CONSUME_TOKEN_BREAKPOINT)) {
			breakpointClass = AntlrConsumeTokenBreakpoint.class;
		} else if (fBreakpointType.equals(LT_BREAKPOINT)) {
			breakpointClass = AntlrLTBreakpoint.class;
		}
		for (int i = 0; i < breakpoints.length; i++) {
			IBreakpoint breakpoint = breakpoints[i];
			if (breakpoint instanceof AntlrLocationBreakpoint
					&& resource.equals(breakpoint.getMarker().getResource())) {
				AntlrLocationBreakpoint locationBreakpoint = (AntlrLocationBreakpoint) breakpoint;
				if (locationBreakpoint.getLineNumber() == fLine
						&& locationBreakpoint.getColumnNumber() == fColumn) {
					if (locationBreakpoint.getClass() == breakpointClass) {
						// already exist
						return;
					}
				}
			}
		}
		AntlrBreakpoint breakpoint = null;
		try {
			@SuppressWarnings("unchecked")
			Constructor<AntlrBreakpoint> constructor = (Constructor<AntlrBreakpoint>) breakpointClass
					.getDeclaredConstructor(IResource.class, String.class,
							int.class, int.class);
			breakpoint = constructor.newInstance(resource, fElementName, fLine,
					fColumn);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		getBreakpointManager().addBreakpoint(breakpoint);
	}

	@Override
	protected Control createContents(Composite parent) {
		AntlrWorkbenchAdapter adapter = (AntlrWorkbenchAdapter) getElement()
				.getAdapter(AntlrWorkbenchAdapter.class);
		IModelElement element = adapter.getElement();
		fGrammar = element.getAdapter(IGrammar.class);
		fElementName = element.getElementName();
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, true);
		composite.setLayout(layout);
		Label label = new Label(composite, SWT.NONE);
		label.setText("Grammar:");//$NON-NLS-1$

		label = new Label(composite, SWT.NONE);
		label.setText(fGrammar.getFile().lastSegment());

		label = new Label(composite, SWT.NONE);
		fLine = AntlrTextHelper.getLineAtOffset(fGrammar.getSource(), element
				.sourceStart());
		int offsetAtLine = AntlrTextHelper.getOffsetAtLine(
				fGrammar.getSource(), fLine);
		fColumn = element.sourceStart() - offsetAtLine + 1;
		label.setText("Location:");//$NON-NLS-1$
		label = new Label(composite, SWT.NONE);
		label.setText(fLine + " : " + fColumn);//$NON-NLS-1$

		label = new Label(composite, SWT.NONE);
		label.setText("Element:");//$NON-NLS-1$

		label = new Label(composite, SWT.NONE);
		label.setText(fElementName);

		label = new Label(composite, SWT.NONE);
		label.setText("Breakpoint Type: ");//$NON-NLS-1$

		Combo combo = new Combo(composite, SWT.READ_ONLY | SWT.NONE);
		combo.addSelectionListener(new BreakpointTypeSelectionListener());
		String[] breakpoints = getAvailableBreakpoints(element);
		combo.setItems(breakpoints);
		combo.select(0);
		fBreakpointType = breakpoints[0];
		return composite;
	}

	private String[] getAvailableBreakpoints(IModelElement element) {
		if (ModelElementQuery.isRuleInvocation(element)) {
			return PARSER_RULE_BREAKPONT;
		}
		if (ModelElementQuery.isRuleDeclaration(element)
				&& element.getElementName() != null
				&& Character.isLowerCase(element.getElementName().charAt(0))) {
			return PARSER_RULE_BREAKPONT;
		}
		// List<String> breakpoints = new ArrayList<String>(Arrays
		// .asList(BREAKPOINTS));
		// breakpoints.remove(LOCATION_BREAKPOINT);
		// return breakpoints.toArray(new String[breakpoints.size()]);
		return BREAKPOINTS;
	}
}
