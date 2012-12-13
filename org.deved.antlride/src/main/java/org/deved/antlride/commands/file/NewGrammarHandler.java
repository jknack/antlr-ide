package org.deved.antlride.commands.file;

import org.deved.antlride.internal.ui.wizards.AntlrDefaultGrammarWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

public class NewGrammarHandler extends AbstractHandler implements IHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShell(event);
		AntlrDefaultGrammarWizard wizard = new AntlrDefaultGrammarWizard();
		wizard.init(PlatformUI.getWorkbench(), (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event));
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
		return null;
	}

}
