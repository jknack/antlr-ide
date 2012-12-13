/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.model;

public interface ICompositeStatement extends IStatement, Iterable<IStatement> {

	void add(int index, IStatement statement);
	
	void add(IStatement statement);
	
	int find(IStatement statement);
	
	IStatement get(int index);
	
	IStatement first();
	
	IStatement last();
	
	int size();
	
	void remove(IStatement statement);	
	
	void set(int index, IStatement statement);	
	
	IStatement remove(int index);
}
