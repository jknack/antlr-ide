/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.stringtemplate.internal.ui.text;

import org.deved.antlride.stringtemplate.internal.ui.text.partitions.StringTemplatePartitions;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.ITextEditor;

public class StringTemplateSimpleSourceViewerConfiguration extends StringTemplateSourceViewerConfiguration {

  public StringTemplateSimpleSourceViewerConfiguration(IColorManager colorManager, IPreferenceStore preferenceStore, ITextEditor editor) {
    super(colorManager, preferenceStore, editor, StringTemplatePartitions.PARTITION_ID);
  }

  @Override
  public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
    return null;
  }

  @Override
  public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {
    return null;
  }

  @Override
  public int[] getConfiguredTextHoverStateMasks(ISourceViewer sourceViewer, String contentType) {
    return null;
  }

  @Override
  public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
    return null;
  }

  public IInformationPresenter getHierarchyPresenter(ISourceViewer sourceViewer, boolean doCodeResolve) {
    return null;
  }

  public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
    return null;
  }

  public IInformationControlCreator getInformationControlCreator(ISourceViewer sourceViewer) {
    return null;
  }

  public IInformationPresenter getInformationPresenter(ISourceViewer sourceViewer) {
    return null;
  }

  public IInformationPresenter getOutlinePresenter(ISourceViewer sourceViewer, boolean doCodeResolve) {
    return null;
  }

  public IAnnotationHover getOverviewRulerAnnotationHover(ISourceViewer sourceViewer) {
    return null;
  }

  public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) {
    return null;
  }

  public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType, int stateMask) {
    return null;
  }
}
