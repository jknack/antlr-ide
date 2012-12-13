/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model;

public interface IParameters extends IModelElement {
	
	ITargetAction getAction();
	
	IParameter findParameter(String name);
	
	IParameter getParemeter(int index);
	
	IParameter[] getParemeters();
	
	int getParametersCount();

}
