/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.internal.ui.text;

import org.deved.antlride.common.ui.SingleProjectProblem;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension5;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension;
import org.eclipse.jface.text.information.IInformationProviderExtension2;
import org.eclipse.jface.text.information.InformationPresenter;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@SingleProjectProblem
public class GUnitInformationPresenter extends InformationPresenter {

	private ITextViewer fTextViewer;

	private int fOffset;

	public GUnitInformationPresenter(IInformationControlCreator creator) {
		super(creator);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void computeInformation() {
		int offset = fOffset < 0 ? fTextViewer.getSelectedRange().x : fOffset;
		if (offset == -1)
			return;

		setOffset( -1 );

		IInformationProvider provider = null;
		try {
			String contentType = TextUtilities.getContentType(fTextViewer
					.getDocument(), getDocumentPartitioning(), offset, true);
			provider = getInformationProvider(contentType);
		} catch (BadLocationException x) {
		}
		if (provider == null)
			return;

		IRegion subject = provider.getSubject(fTextViewer, offset);
		if (subject == null)
			return;

		Object info;
		if (provider instanceof IInformationProviderExtension) {
			IInformationProviderExtension extension = (IInformationProviderExtension) provider;
			info = extension.getInformation2(fTextViewer, subject);
		} else {
			// backward compatibility code
			info = provider.getInformation(fTextViewer, subject);
		}

		if (provider instanceof IInformationProviderExtension2)
			setCustomInformationControlCreator(((IInformationProviderExtension2) provider)
					.getInformationPresenterControlCreator());
		else
			setCustomInformationControlCreator(null);

		setInformation(info, computeArea(subject));
	}

	@Override
	public void install(ITextViewer textViewer) {
		// hook text viewer
		fTextViewer = textViewer;
		super.install(textViewer);
	}

	@Override
	public void setOffset(int offset) {
		// hook offset
		fOffset = offset;
		super.setOffset(offset);
	}

	/**
	 * Determines the graphical area covered by the given text region.
	 * 
	 * @param region
	 *            the region whose graphical extend must be computed
	 * @return the graphical extend of the given region
	 */
	private Rectangle computeArea(IRegion region) {

		int start = 0;
		int end = 0;

		IRegion widgetRegion = modelRange2WidgetRange(region);
		if (widgetRegion != null) {
			start = widgetRegion.getOffset();
			end = widgetRegion.getOffset() + widgetRegion.getLength();
		}

		StyledText styledText = fTextViewer.getTextWidget();
		Rectangle bounds;
		if (end > 0 && start < end)
			bounds = styledText.getTextBounds(start, end - 1);
		else {
			Point loc = styledText.getLocationAtOffset(start);
			bounds = new Rectangle(loc.x, loc.y, 0, styledText
					.getLineHeight(start));
		}

		return bounds;
	}

	/**
	 * Translated the given range in the viewer's document into the
	 * corresponding range of the viewer's widget.
	 * 
	 * @param region
	 *            the range in the viewer's document
	 * @return the corresponding widget range
	 * @since 2.1
	 */
	private IRegion modelRange2WidgetRange(IRegion region) {
		if (fTextViewer instanceof ITextViewerExtension5) {
			ITextViewerExtension5 extension = (ITextViewerExtension5) fTextViewer;
			return extension.modelRange2WidgetRange(region);
		}

		IRegion visibleRegion = fTextViewer.getVisibleRegion();
		int start = region.getOffset() - visibleRegion.getOffset();
		int end = start + region.getLength();
		if (end > visibleRegion.getLength())
			end = visibleRegion.getLength();

		return new Region(start, end - start);
	}
}
