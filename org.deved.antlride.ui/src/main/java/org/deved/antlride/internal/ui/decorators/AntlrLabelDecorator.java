/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.internal.ui.decorators;

import java.util.ArrayList;
import java.util.List;

import org.deved.antlride.core.resources.AntlrGeneratedResource;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;

public class AntlrLabelDecorator implements ILightweightLabelDecorator {
  private final List<ILabelProviderListener> listeners =
      new ArrayList<ILabelProviderListener>();

  public void decorate(Object element, IDecoration decoration) {
    StringBuilder builder = new StringBuilder(" [");
    AntlrGeneratedResource resource = (AntlrGeneratedResource) element;
    builder.append(resource.getGrammarName());
    builder.append("]");
    decoration.addSuffix(builder.toString());
  }

  public void addListener(ILabelProviderListener listener) {
    if(!listeners.contains(listener)) {
      listeners.add(listener);
    }
  }

  public void dispose() {
    listeners.clear();
  }

  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  public void removeListener(ILabelProviderListener listener) {
    listeners.remove(listener);
  }

}
