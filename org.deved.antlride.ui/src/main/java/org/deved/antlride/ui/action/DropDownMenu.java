/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.ui.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

public class DropDownMenu extends Action implements IMenuCreator {

	private Menu fMenu;

	private Action[] delegates;

	public DropDownMenu(Action... delegates) {
		super("", delegates.length > 1 ? IAction.AS_DROP_DOWN_MENU
				: IAction.AS_PUSH_BUTTON);
		setMenuCreator(this);
		this.delegates = delegates;
		setImageDescriptor(delegates[0].getImageDescriptor());
		setToolTipText(delegates[0].getToolTipText());
	}

	public void dispose() {
		if (fMenu != null)
			fMenu.dispose();
		delegates = null;
	}

	public Menu getMenu(Control parent) {
		fMenu = new Menu(parent);
		for (int i = 0; i < delegates.length; i++) {
			ActionContributionItem item = new ActionContributionItem(
					delegates[i]);
			item.fill(fMenu, -1);
		}

		return fMenu;
	}

	public Menu getMenu(Menu parent) {
		return null;
	}

	@Override
	public void run() {
//		fMenu.setVisible(true);
		delegates[0].run();
	}

	@Override
	public void setEnabled(boolean enabled) {
		for (int i = 0; i < delegates.length; i++) {
			delegates[i].setEnabled(enabled);
		}
		super.setEnabled(enabled);
	}
	
	public void setEnabledAll(boolean enabled) {
		for (int i = 0; i < delegates.length; i++) {
			delegates[i].setEnabled(enabled);
		}
		super.setEnabled(enabled);
	}
}
