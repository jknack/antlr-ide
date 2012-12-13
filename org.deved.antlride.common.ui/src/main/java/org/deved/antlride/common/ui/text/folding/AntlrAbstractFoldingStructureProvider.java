/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.common.ui.text.folding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.compiler.env.ModuleSource;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.ElementChangedEvent;
import org.eclipse.dltk.core.IElementChangedListener;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.IModelElementVisitor;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.corext.SourceRange;
import org.eclipse.dltk.internal.core.SourceMethod;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.text.DocumentCharacterIterator;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider;
import org.eclipse.dltk.ui.text.folding.DefaultElementCommentResolver;
import org.eclipse.dltk.ui.text.folding.IElementCommentResolver;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProvider;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProviderExtension;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.projection.IProjectionListener;
import org.eclipse.jface.text.source.projection.IProjectionPosition;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * Updates the projection model of a source module using AST info.
 * 
 * Just a copy of {@link AbstractASTFoldingStructureProvider} TODO: delete this
 * class when DLTK supports multiples languages
 */
@SuppressWarnings("restriction")
public abstract class AntlrAbstractFoldingStructureProvider implements
		IFoldingStructureProvider, IFoldingStructureProviderExtension {

	/**
	 * A context that contains the information needed to compute the folding
	 * structure of an {@link ISourceModule}. Computed folding regions are
	 * collected via
	 * {@link #addProjectionRange(AbstractASTFoldingStructureProvider.ScriptProjectionAnnotation, Position)
	 * addProjectionRange}.
	 */
	public static final class FoldingStructureComputationContext {
		private final ProjectionAnnotationModel fModel;
		private final IDocument fDocument;
		private final boolean fAllowCollapsing;
		protected LinkedHashMap<Annotation, Position> fMap = new LinkedHashMap<Annotation, Position>();

		public FoldingStructureComputationContext(IDocument document,
				ProjectionAnnotationModel model, boolean allowCollapsing) {
			fDocument = document;
			fModel = model;
			fAllowCollapsing = allowCollapsing;
		}

		public Map<Annotation, Position> getMap() {
			return fMap;
		}

		/**
		 * Returns <code>true</code> if newly created folding regions may be
		 * collapsed, <code>false</code> if not. This is usually
		 * <code>false</code> when updating the folding structure while typing;
		 * it may be <code>true</code> when computing or restoring the initial
		 * folding structure.
		 * 
		 * @return <code>true</code> if newly created folding regions may be
		 *         collapsed, <code>false</code> if not
		 */
		public boolean allowCollapsing() {
			return fAllowCollapsing;
		}

		/**
		 * Returns the document which contains the code being folded.
		 * 
		 * @return the document which contains the code being folded
		 */
		IDocument getDocument() {
			return fDocument;
		}

		ProjectionAnnotationModel getModel() {
			return fModel;
		}

		/**
		 * Adds a projection (folding) region to this context. The created
		 * annotation / position pair will be added to the
		 * {@link ProjectionAnnotationModel} of the {@link ProjectionViewer} of
		 * the editor.
		 * 
		 * @param annotation
		 *            the annotation to add
		 * @param position
		 *            the corresponding position
		 */
		public void addProjectionRange(ScriptProjectionAnnotation annotation,
				Position position) {
			fMap.put(annotation, position);
		}
	}

	protected static final class SourceRangeStamp {
		private int hash, length;

		public SourceRangeStamp(int hash, int lenght) {
			this.hash = hash;
			this.length = lenght;
		}

		/**
		 * @return the hash
		 */
		public int getHash() {
			return hash;
		}

		/**
		 * @param hash
		 *            the hash to set
		 */
		public void setHash(int hash) {
			this.hash = hash;
		}

		/**
		 * @return the length
		 */
		public int getLength() {
			return length;
		}

		/**
		 * @param length
		 *            the length to set
		 */
		public void setLength(int length) {
			this.length = length;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof SourceRangeStamp) {
				SourceRangeStamp s = (SourceRangeStamp) obj;
				return (s.hash == hash); // && s.length == length);
			}
			return super.equals(obj);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return hash;
		}
	}

	/**
	 * A {@link ProjectionAnnotation} for code.
	 */
	protected static final class ScriptProjectionAnnotation extends
			ProjectionAnnotation {
		private boolean fIsComment;
		private SourceRangeStamp stamp;
		private IModelElement element;

		/**
		 * Creates a new projection annotation.
		 * 
		 * @param isCollapsed
		 *            <code>true</code> to set the initial state to collapsed,
		 *            <code>false</code> to set it to expanded
		 * @param codeStamp
		 *            the stamp of source code this annotation refers to
		 * @param isComment
		 *            <code>true</code> for a foldable comment,
		 *            <code>false</code> for a foldable code element
		 */
		public ScriptProjectionAnnotation(boolean isCollapsed,
				boolean isComment, SourceRangeStamp codeStamp,
				IModelElement element) {
			super(isCollapsed);
			fIsComment = isComment;
			stamp = codeStamp;
			this.element = element;
		}

		public IModelElement getElement() {
			return element;
		}

		boolean isComment() {
			return fIsComment;
		}

		/**
		 * @return the stamp
		 */
		SourceRangeStamp getStamp() {
			return stamp;
		}

		/**
		 * @param stamp
		 *            the stamp to set
		 */
		void setStamp(SourceRangeStamp stamp) {
			this.stamp = stamp;
		}

		void setIsComment(boolean isComment) {
			fIsComment = isComment;
		}

		/*
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ScriptProjectionAnnotation:\n" + //$NON-NLS-1$
					"\tcollapsed: \t" + isCollapsed() + "\n" + //$NON-NLS-1$ //$NON-NLS-2$
					"\tcomment: \t" + isComment() + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	private static final class Tuple {
		ScriptProjectionAnnotation annotation;
		Position position;

		Tuple(ScriptProjectionAnnotation annotation, Position position) {
			this.annotation = annotation;
			this.position = position;
		}
	}

	/**
	 * Filter for annotations.
	 */
	private static interface Filter {
		boolean match(ScriptProjectionAnnotation annotation);
	}

	/**
	 * Matches comments.
	 */
	private static final class CommentFilter implements Filter {
		public boolean match(ScriptProjectionAnnotation annotation) {
			if (annotation.isComment() && !annotation.isMarkedDeleted()) {
				return true;
			}
			return false;
		}
	}

	/**
	 * Matches members.
	 */
	private static final class MemberFilter implements Filter {
		public boolean match(ScriptProjectionAnnotation annotation) {
			if (!annotation.isMarkedDeleted()
					&& annotation.getElement() instanceof IMember) {
				return true;
			}
			return false;
		}
	}

	/**
	 * Projection position that will return two foldable regions: one folding
	 * away the region from after the '/**' to the beginning of the content, the
	 * other from after the first content line until after the comment.
	 */
	private static final class CommentPosition extends Position implements
			IProjectionPosition {
		CommentPosition(int offset, int length) {
			super(offset, length);
		}

		/*
		 * @seeorg.eclipse.jface.text.source.projection.IProjectionPosition#
		 * computeFoldingRegions(org.eclipse.jface.text.IDocument)
		 */
		public IRegion[] computeProjectionRegions(IDocument document)
				throws BadLocationException {
			DocumentCharacterIterator sequence = new DocumentCharacterIterator(
					document, offset, offset + length);
			int prefixEnd = 0;
			int contentStart = findFirstContent(sequence, prefixEnd);
			int firstLine = document.getLineOfOffset(offset + prefixEnd);
			int captionLine = document.getLineOfOffset(offset + contentStart);
			int lastLine = document.getLineOfOffset(offset + length);
			// Assert.isTrue(firstLine <= captionLine, "first folded line is
			// greater than the caption line"); //$NON-NLS-1$
			// Assert.isTrue(captionLine <= lastLine, "caption line is greater
			// than the last folded line"); //$NON-NLS-1$
			IRegion preRegion;
			if (firstLine < captionLine) {
				// preRegion= new Region(offset + prefixEnd, contentStart -
				// prefixEnd);
				int preOffset = document.getLineOffset(firstLine);
				IRegion preEndLineInfo = document
						.getLineInformation(captionLine);
				int preEnd = preEndLineInfo.getOffset();
				preRegion = new Region(preOffset, preEnd - preOffset);
			} else {
				preRegion = null;
			}
			if (captionLine < lastLine) {
				int postOffset = document.getLineOffset(captionLine + 1);
				IRegion postRegion = new Region(postOffset, offset + length
						- postOffset);
				if (preRegion == null)
					return new IRegion[] { postRegion };
				return new IRegion[] { preRegion, postRegion };
			}
			if (preRegion != null)
				return new IRegion[] { preRegion };
			return null;
		}

		/**
		 * Finds the offset of the first identifier part within
		 * <code>content</code>. Returns 0 if none is found.
		 * 
		 * @param content
		 *            the content to search
		 * @return the first index of a unicode identifier part, or zero if none
		 *         can be found
		 */
		private int findFirstContent(final CharSequence content, int prefixEnd) {
			int lenght = content.length();
			for (int i = prefixEnd; i < lenght; i++) {
				if (Character.isUnicodeIdentifierPart(content.charAt(i)))
					return i;
			}
			return 0;
		}

		/*
		 * @seeorg.eclipse.jface.text.source.projection.IProjectionPosition#
		 * computeCaptionOffset(org.eclipse.jface.text.IDocument)
		 */
		public int computeCaptionOffset(IDocument document) {
			DocumentCharacterIterator sequence = new DocumentCharacterIterator(
					document, offset, offset + length);
			return findFirstContent(sequence, 0);
		}
	}

	/**
	 * Projection position that will return two foldable regions: one folding
	 * away the lines before the one containing the simple name of the script
	 * element, one folding away any lines after the caption.
	 */
	private static final class ScriptElementPosition extends Position implements
			IProjectionPosition {
		public ScriptElementPosition(int offset, int length) {
			super(offset, length);
		}

		/*
		 * @seeorg.eclipse.jface.text.source.projection.IProjectionPosition#
		 * computeFoldingRegions(org.eclipse.jface.text.IDocument)
		 */
		public IRegion[] computeProjectionRegions(IDocument document)
				throws BadLocationException {
			int nameStart = offset;
			int firstLine = document.getLineOfOffset(offset);
			int captionLine = document.getLineOfOffset(nameStart);
			int lastLine = document.getLineOfOffset(offset + length);
			/*
			 * see comment above - adjust the caption line to be inside the
			 * entire folded region, and rely on later element deltas to correct
			 * the name range.
			 */
			if (captionLine < firstLine)
				captionLine = firstLine;
			if (captionLine > lastLine)
				captionLine = lastLine;
			IRegion preRegion;
			if (firstLine < captionLine) {
				int preOffset = document.getLineOffset(firstLine);
				IRegion preEndLineInfo = document
						.getLineInformation(captionLine);
				int preEnd = preEndLineInfo.getOffset();
				preRegion = new Region(preOffset, preEnd - preOffset);
			} else {
				preRegion = null;
			}
			if (captionLine < lastLine) {
				int postOffset = document.getLineOffset(captionLine + 1);
				IRegion postRegion = new Region(postOffset, offset + length
						- postOffset);
				if (preRegion == null)
					return new IRegion[] { postRegion };
				return new IRegion[] { preRegion, postRegion };
			}
			if (preRegion != null)
				return new IRegion[] { preRegion };
			return null;
		}

		/*
		 * @seeorg.eclipse.jface.text.source.projection.IProjectionPosition#
		 * computeCaptionOffset(org.eclipse.jface.text.IDocument)
		 */
		public int computeCaptionOffset(IDocument document) {
			return 0;
		}
	}

	/**
	 * Internal projection listener.
	 */
	private final class ProjectionListener implements IProjectionListener {
		private ProjectionViewer fViewer;

		/**
		 * Registers the listener with the viewer.
		 * 
		 * @param viewer
		 *            the viewer to register a listener with
		 */
		public ProjectionListener(ProjectionViewer viewer) {
			fViewer = viewer;
			fViewer.addProjectionListener(this);
		}

		/**
		 * Disposes of this listener and removes the projection listener from
		 * the viewer.
		 */
		public void dispose() {
			if (fViewer != null) {
				fViewer.removeProjectionListener(this);
				fViewer = null;
			}
		}

		/*
		 * @seeorg.eclipse.jface.text.source.projection.IProjectionListener#
		 * projectionEnabled()
		 */
		public void projectionEnabled() {
			handleProjectionEnabled();
		}

		/*
		 * @seeorg.eclipse.jface.text.source.projection.IProjectionListener#
		 * projectionDisabled()
		 */
		public void projectionDisabled() {
			handleProjectionDisabled();
		}
	}

	private class ElementChangedListener implements IElementChangedListener {
		/*
		 * @see
		 * org.eclipse.dltk.core.IElementChangedListener#elementChanged(org.
		 * eclipse.dltk.core.ElementChangedEvent)
		 */
		public void elementChanged(ElementChangedEvent e) {
			IModelElementDelta delta = findElement(fInput, e.getDelta());
			if (delta != null
					&& (delta.getFlags() & (IModelElementDelta.F_CONTENT | IModelElementDelta.F_CHILDREN)) != 0)
				update(createContext(false));
		}

		private IModelElementDelta findElement(IModelElement target,
				IModelElementDelta delta) {
			if (delta == null || target == null)
				return null;
			IModelElement element = delta.getElement();
			if (element.getElementType() > IModelElement.SOURCE_MODULE)
				return null;
			if (target.equals(element))
				return delta;
			IModelElementDelta[] children = delta.getAffectedChildren();
			for (int i = 0; i < children.length; i++) {
				IModelElementDelta d = findElement(target, children[i]);
				if (d != null)
					return d;
			}
			return null;
		}
	}

	/* context and listeners */
	private ITextEditor fEditor;
	private ProjectionListener fProjectionListener;
	private IModelElement fInput;
	private IElementChangedListener fElementListener;
	/* filters */
	/** Member filter, matches nested members (but not top-level types). */
	private final Filter fMemberFilter = new MemberFilter();
	/** Comment filter, matches comments. */
	private final Filter fCommentFilter = new CommentFilter();
	private IPreferenceStore fStore;

	private int fBlockLinesMin;

	protected boolean fDocsFolding;
	protected boolean fCommentsFolding;
	protected boolean fFoldNewLines;

	protected boolean fInitCollapseComments;
	protected boolean fInitCollapseHeaderComments;
	protected boolean fInitCollapseClasses;
	protected boolean fInitCollapseMethods;

	private boolean fInitCollapseDocs;

	/**
	 * Creates a new folding provider. It must be
	 * {@link #install(ITextEditor, ProjectionViewer, IPreferenceStore)
	 * installed} on an editor/viewer pair before it can be used, and
	 * {@link #uninstall() uninstalled} when not used any longer.
	 * <p>
	 * The projection state may be reset by calling {@link #initialize()}.
	 * </p>
	 */
	public AntlrAbstractFoldingStructureProvider() {
		// empty constructor
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Subclasses may extend.
	 * </p>
	 * 
	 * @param editor
	 *            {@inheritDoc}
	 * @param viewer
	 *            {@inheritDoc}
	 */
	public void install(ITextEditor editor, ProjectionViewer viewer,
			IPreferenceStore store) {
		internalUninstall();
		fStore = store;
		if (editor instanceof ScriptEditor) {
			fEditor = editor;
			fProjectionListener = new ProjectionListener(viewer);
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Subclasses may extend.
	 * </p>
	 */
	public void uninstall() {
		internalUninstall();
	}

	/**
	 * Internal implementation of {@link #uninstall()}.
	 */
	private void internalUninstall() {
		if (isInstalled()) {
			handleProjectionDisabled();
			fProjectionListener.dispose();
			fProjectionListener = null;
			fEditor = null;
		}
	}

	/**
	 * Returns <code>true</code> if the provider is installed,
	 * <code>false</code> otherwise.
	 * 
	 * @return <code>true</code> if the provider is installed,
	 *         <code>false</code> otherwise
	 */
	protected final boolean isInstalled() {
		return fEditor != null;
	}

	/**
	 * Called whenever projection is enabled, for example when the viewer issues
	 * a {@link IProjectionListener#projectionEnabled() projectionEnabled}
	 * message. When the provider is already enabled when this method is called,
	 * it is first {@link #handleProjectionDisabled() disabled}.
	 * <p>
	 * Subclasses may extend.
	 * </p>
	 */
	protected void handleProjectionEnabled() {
		handleProjectionDisabled();
		if (fEditor instanceof ScriptEditor) {
			initialize();
			fElementListener = new ElementChangedListener();
			DLTKCore.addElementChangedListener(fElementListener);
		}
	}

	/**
	 * Called whenever projection is disabled, for example when the provider is
	 * {@link #uninstall() uninstalled}, when the viewer issues a
	 * {@link IProjectionListener#projectionDisabled() projectionDisabled}
	 * message and before {@link #handleProjectionEnabled() enabling} the
	 * provider. Implementations must be prepared to handle multiple calls to
	 * this method even if the provider is already disabled.
	 * <p>
	 * Subclasses may extend.
	 * </p>
	 */
	protected void handleProjectionDisabled() {
		if (fElementListener != null) {
			DLTKCore.removeElementChangedListener(fElementListener);
			fElementListener = null;
		}
	}

	public final void initialize() {
		initialize(false);
	}

	public final void initialize(boolean isReinit) {
		update(createInitialContext(isReinit));
	}

	protected FoldingStructureComputationContext createInitialContext(
			boolean isReinit) {
		initializePreferences(fStore);
		fInput = getInputElement();
		if (fInput == null)
			return null;

		// don't auto collapse if reinitializing
		return createContext((isReinit) ? false : true);
	}

	protected FoldingStructureComputationContext createInitialContext() {
		return createInitialContext(true);
	}

	protected FoldingStructureComputationContext createContext(
			boolean allowCollapse) {
		if (!isInstalled())
			return null;
		ProjectionAnnotationModel model = getModel();
		if (model == null)
			return null;
		IDocument doc = getDocument();
		if (doc == null)
			return null;
		return new FoldingStructureComputationContext(doc, model, allowCollapse);
	}

	private IModelElement getInputElement() {
		if (fEditor == null)
			return null;
		// TODO: multiple languages support
		return ((ScriptEditor) fEditor).getInputModelElement();
	}

	private void update(FoldingStructureComputationContext ctx) {
		if (ctx == null)
			return;
		Map<Annotation, Position> additions = new HashMap<Annotation, Position>();
		List<Annotation> deletions = new ArrayList<Annotation>();
		List<Annotation> updates = new ArrayList<Annotation>();
		if (!computeFoldingStructure(ctx)) {
			return;
		}
		Map<Annotation, Position> updated = ctx.fMap;
		Map<SourceRangeStamp, List<Tuple>> previous = computeCurrentStructure(ctx);
		for (Iterator<Annotation> e = updated.keySet().iterator(); e.hasNext();) {
			ScriptProjectionAnnotation newAnnotation = (ScriptProjectionAnnotation) e
					.next();
			SourceRangeStamp stamp = newAnnotation.getStamp();
			Position newPosition = updated.get(newAnnotation);
			List<Tuple> annotations = previous.get(stamp);
			if (annotations == null) {
				additions.put(newAnnotation, newPosition);
			} else {
				Iterator<Tuple> x = annotations.iterator();
				boolean matched = false;
				while (x.hasNext()) {
					Tuple tuple = x.next();
					ScriptProjectionAnnotation existingAnnotation = tuple.annotation;
					Position existingPosition = tuple.position;
					if (newAnnotation.isComment() == existingAnnotation
							.isComment()) {
						if (existingPosition != null
								&& (!newPosition.equals(existingPosition) || ctx
										.allowCollapsing()
										&& existingAnnotation.isCollapsed() != newAnnotation
												.isCollapsed())) {
							existingPosition.setOffset(newPosition.getOffset());
							existingPosition.setLength(newPosition.getLength());
							if (ctx.allowCollapsing()
									&& existingAnnotation.isCollapsed() != newAnnotation
											.isCollapsed())
								if (newAnnotation.isCollapsed())
									existingAnnotation.markCollapsed();
								else
									existingAnnotation.markExpanded();
							updates.add(existingAnnotation);
						}
						matched = true;
						x.remove();
						break;
					}
				}
				if (!matched)
					additions.put(newAnnotation, newPosition);
				if (annotations.isEmpty())
					previous.remove(stamp);
			}
		}
		for (List<Tuple> list : previous.values()) {
			int size = list.size();
			for (int i = 0; i < size; i++)
				deletions.add(list.get(i).annotation);
		}
		Annotation[] removals = new Annotation[deletions.size()];
		deletions.toArray(removals);
		Annotation[] changes = new Annotation[updates.size()];
		updates.toArray(changes);
		ctx.getModel().modifyAnnotations(removals, additions, changes);
	}

	private boolean computeFoldingStructure(
			FoldingStructureComputationContext ctx) {
		try {
			String contents = ((ISourceReference) fInput).getSource();
			return computeFoldingStructure(contents, ctx);
		} catch (ModelException e) {
			return false;
		}
	}

	protected boolean computeFoldingStructure(String contents,
			FoldingStructureComputationContext ctx) {
		CodeBlock[] blockRegions = getCodeBlocks(contents);
		if (blockRegions == null) {
			return false;
		}

		if (fCommentsFolding) {
			IRegion[] commentRegions = computeCommentsRanges(contents);
			addDocAnnotations(contents, ctx, commentRegions, false);
		}

		String docPartition = getDocPartition();
		if (fDocsFolding && docPartition != null) {
			IRegion[] commentRegions = computeCommentsRanges(contents,
					docPartition);
			addDocAnnotations(contents, ctx, commentRegions, true);
		}

		// 2. Compute blocks regions
		final Document d = new Document(contents);
		final MethodCollector methodCollector = new MethodCollector();
		if (fInput != null) {
			try {
				fInput.accept(methodCollector);
			} catch (ModelException e) {
				// empty
			}
		}
		for (int i = 0; i < blockRegions.length; i++) {
			CodeBlock codeBlock = blockRegions[i];

			if (!mayCollapse(codeBlock.statement, ctx))
				continue;

			boolean collapseCode = initiallyCollapse(codeBlock.statement, ctx);
			IRegion reg = codeBlock.region;

			// code
			boolean multiline = false;
			try {
				multiline = isMultilineRegion(d, reg);
			} catch (BadLocationException e) {
				// nothing to do
			}
			IRegion normalized = alignRegion(reg, ctx);
			if (normalized != null && multiline) {
				Position position = createMemberPosition(normalized);
				if (position != null) {
					try {
						int len = normalized.getOffset()
								+ normalized.getLength();
						if (contents.length() == len + 1) {
							len = len - 1;
						}
						if (contents.length() >= len) {
							int hash = contents.substring(
									normalized.getOffset(), len).hashCode();
							IModelElement element = null;

							if (codeBlock.statement instanceof MethodDeclaration) {
								MethodDeclaration meth = (MethodDeclaration) codeBlock.statement;
								hash = meth.getName().hashCode();
								element = methodCollector.get(
										meth.getNameStart(), meth.getNameEnd()
												- meth.getNameStart());
							}
							SourceRangeStamp codeStamp = new SourceRangeStamp(
									hash, normalized.getLength());
							ScriptProjectionAnnotation annotation = new ScriptProjectionAnnotation(
									collapseCode, false, codeStamp, element);
							ctx.addProjectionRange(annotation, position);
						}
					} catch (StringIndexOutOfBoundsException e) {
						if (DLTKCore.DEBUG) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return true;
	}

	private void addDocAnnotations(String contents,
			FoldingStructureComputationContext ctx, IRegion[] commentRegions,
			boolean isDoc) {
		if (commentRegions.length == 0) {
			return;
		}
		final IElementCommentResolver commentResolver = fInput != null ? createElementCommentResolver(
				fInput, contents) : null;
		for (int i = 0; i < commentRegions.length; i++) {
			IRegion normalized = alignRegion(commentRegions[i], ctx);
			if (normalized == null) {
				continue;
			}

			Position position = createCommentPosition(normalized);
			if (position == null) {
				continue;
			}

			int hash = contents.substring(normalized.getOffset(),
					normalized.getOffset() + normalized.getLength()).hashCode();
			final IModelElement element;
			if (commentResolver != null) {
				element = commentResolver.getElementByCommentPosition(
						position.offset, 0);
			} else {
				element = null;
			}

			boolean initCollapse = (isDoc) ? initiallyCollapseDocs(normalized,
					ctx) : initiallyCollapseComments(normalized, ctx);

			ctx.addProjectionRange(new ScriptProjectionAnnotation(initCollapse,
					true, new SourceRangeStamp(hash, normalized.getLength()),
					element), position);
		}
	}

	/**
	 * @param modelElement
	 * @param contents
	 * @return
	 */
	public IElementCommentResolver createElementCommentResolver(
			IModelElement modelElement, String contents) {
		return new DefaultElementCommentResolver((ISourceModule) modelElement,
				contents);
	}

	protected static class CodeBlock {
		public ASTNode statement;
		public IRegion region;

		/**
		 * Represents foldable statement.
		 * 
		 * @param s
		 *            AST statement
		 * @param r
		 *            <b>Absolute</b> statement position in source file
		 */
		public CodeBlock(ASTNode s, IRegion r) {
			this.statement = s;
			this.region = r;
		}
	}

	protected int getMinimalFoldableLinesCount() {
		return fBlockLinesMin;
	}

	protected void initializePreferences(IPreferenceStore store) {
		fBlockLinesMin = store
				.getInt(PreferenceConstants.EDITOR_FOLDING_LINES_LIMIT);

		fDocsFolding = store
				.getBoolean(PreferenceConstants.EDITOR_DOCS_FOLDING_ENABLED);

		fCommentsFolding = store
				.getBoolean(PreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED);

		fFoldNewLines = store
				.getBoolean(PreferenceConstants.EDITOR_COMMENT_FOLDING_JOIN_NEWLINES);

		fInitCollapseComments = store
				.getBoolean(PreferenceConstants.EDITOR_FOLDING_INIT_COMMENTS);

		fInitCollapseHeaderComments = store
				.getBoolean(PreferenceConstants.EDITOR_FOLDING_INIT_HEADER_COMMENTS);

		fInitCollapseDocs = store
				.getBoolean(PreferenceConstants.EDITOR_FOLDING_INIT_DOCS);

		fInitCollapseClasses = store.getBoolean(getInitiallyFoldClassesKey());
		fInitCollapseMethods = store.getBoolean(getInitiallyFoldMethodsKey());
	}

	/**
	 * Returns the preference key used to indicate if classes should be
	 * 'initially' folded.
	 * 
	 * <p>
	 * Sub-classes may override this method to provide an alternative preference
	 * key if they are not using the one in {@link PreferenceConstants}.
	 * </p>
	 */
	protected String getInitiallyFoldClassesKey() {
		return PreferenceConstants.EDITOR_FOLDING_INIT_CLASSES;
	}

	/**
	 * Returns the preference key used to indicate if methods should be
	 * 'initially' folded.
	 * 
	 * <p>
	 * Sub-classes may override this method to provide an alternative preference
	 * key if they are not using the one in {@link PreferenceConstants}.
	 * </p>
	 */
	protected String getInitiallyFoldMethodsKey() {
		return PreferenceConstants.EDITOR_FOLDING_INIT_METHODS;
	}

	protected boolean isEmptyRegion(IDocument d, ITypedRegion r)
			throws BadLocationException {
		return isEmptyRegion(d, r.getOffset(), r.getLength());
	}

	/**
	 * Tests if the specified region contains only space or tab characters.
	 * 
	 * @param document
	 * @param region
	 * @return
	 * @throws BadLocationException
	 * @since 2.0
	 */
	protected boolean isBlankRegion(IDocument document, ITypedRegion region)
			throws BadLocationException {
		String value = document.get(region.getOffset(), region.getLength());
		for (int i = 0; i < value.length(); ++i) {
			char ch = value.charAt(i);
			if (ch != ' ' && ch != '\t') {
				return false;
			}
		}
		return true;
	}

	protected boolean isEmptyRegion(IDocument d, int offset, int length)
			throws BadLocationException {
		return d.get(offset, length).trim().length() == 0;
	}

	protected boolean isMultilineRegion(IDocument d, IRegion region)
			throws BadLocationException {
		int line1 = d.getLineOfOffset(region.getOffset());
		int line2 = d.getLineOfOffset(region.getOffset() + region.getLength());
		final int foldMinLines = getMinimalFoldableLinesCount();
		if (foldMinLines > 0) {
			return line2 - line1 + 1 >= foldMinLines;
		} else {
			return line1 != line2;
		}
	}

	/**
	 * Creates a comment folding position from an
	 * {@link #alignRegion(IRegion, AbstractASTFoldingStructureProvider.FoldingStructureComputationContext)
	 * aligned} region.
	 * 
	 * @param aligned
	 *            an aligned region
	 * @return a folding position corresponding to <code>aligned</code>
	 */
	protected final Position createCommentPosition(IRegion aligned) {
		return new CommentPosition(aligned.getOffset(), aligned.getLength());
	}

	/**
	 * Creates a folding position that remembers its member from an
	 * {@link #alignRegion(IRegion, AbstractASTFoldingStructureProvider.FoldingStructureComputationContext)
	 * aligned} region.
	 * 
	 * @param aligned
	 *            an aligned region
	 * 
	 * @return a folding position corresponding to <code>aligned</code>
	 */
	protected final Position createMemberPosition(IRegion aligned) {
		return new ScriptElementPosition(aligned.getOffset(),
				aligned.getLength());
	}

	/**
	 * Aligns <code>region</code> to start and end at a line offset. The
	 * region's start is decreased to the next line offset, and the end offset
	 * increased to the next line start or the end of the document.
	 * <code>null</code> is returned if <code>region</code> is <code>null</code>
	 * itself or does not comprise at least one line delimiter, as a single line
	 * cannot be folded.
	 * 
	 * @param region
	 *            the region to align, may be <code>null</code>
	 * @param ctx
	 *            the folding context
	 * @return a region equal or greater than <code>region</code> that is
	 *         aligned with line offsets, <code>null</code> if the region is too
	 *         small to be foldable (e.g. covers only one line)
	 */
	protected IRegion alignRegion(IRegion region,
			FoldingStructureComputationContext ctx) {
		if (region == null)
			return null;
		IDocument document = ctx.getDocument();
		try {
			int start = document.getLineOfOffset(region.getOffset());
			int end = document.getLineOfOffset(region.getOffset()
					+ region.getLength());
			if (start >= end)
				return null;
			int offset = document.getLineOffset(start);
			int endOffset;
			if (document.getNumberOfLines() > end + 1) {
				endOffset = document.getLineOffset(end + 1);
			} else {
				endOffset = document.getLineOffset(end)
						+ document.getLineLength(end);
			}
			return new Region(offset, endOffset - offset);
		} catch (BadLocationException x) {
			// concurrent modification
			return null;
		}
	}

	private ProjectionAnnotationModel getModel() {
		return (ProjectionAnnotationModel) fEditor
				.getAdapter(ProjectionAnnotationModel.class);
	}

	private IDocument getDocument() {
		IDocumentProvider provider = fEditor.getDocumentProvider();
		return provider.getDocument(fEditor.getEditorInput());
	}

	private Map<SourceRangeStamp, List<Tuple>> computeCurrentStructure(
			FoldingStructureComputationContext ctx) {
		Map<SourceRangeStamp, List<Tuple>> map = new HashMap<SourceRangeStamp, List<Tuple>>();
		ProjectionAnnotationModel model = ctx.getModel();
		Iterator<?> e = model.getAnnotationIterator();
		while (e.hasNext()) {
			Object annotation = e.next();
			if (annotation instanceof ScriptProjectionAnnotation) {
				ScriptProjectionAnnotation ann = (ScriptProjectionAnnotation) annotation;
				Position position = model.getPosition(ann);
				List<Tuple> list = map.get(ann.getStamp());
				if (list == null) {
					list = new ArrayList<Tuple>(2);
					map.put(ann.getStamp(), list);
				}
				list.add(new Tuple(ann, position));
			}
		}
		Comparator<Tuple> comparator = new Comparator<Tuple>() {
			public int compare(Tuple o1, Tuple o2) {
				return o1.position.getOffset() - o2.position.getOffset();
			}
		};
		for (Iterator<List<Tuple>> it = map.values().iterator(); it.hasNext();) {
			List<Tuple> list = it.next();
			Collections.sort(list, comparator);
		}
		return map;
	}

	/*
	 * @see IScriptFoldingStructureProviderExtension#collapseMembers()
	 */
	public final void collapseMembers() {
		modifyFiltered(fMemberFilter, false);
	}

	/*
	 * @see IScriptFoldingStructureProviderExtension#collapseComments()
	 */
	public final void collapseComments() {
		modifyFiltered(fCommentFilter, false);
	}

	/**
	 * Collapses or expands all annotations matched by the passed filter.
	 * 
	 * @param filter
	 *            the filter to use to select which annotations to collapse
	 * @param expand
	 *            <code>true</code> to expand the matched annotations,
	 *            <code>false</code> to collapse them
	 */
	private void modifyFiltered(Filter filter, boolean expand) {
		if (!isInstalled())
			return;
		ProjectionAnnotationModel model = getModel();
		if (model == null)
			return;
		List<Annotation> modified = new ArrayList<Annotation>();
		Iterator<?> iter = model.getAnnotationIterator();
		while (iter.hasNext()) {
			Object annotation = iter.next();
			if (annotation instanceof ScriptProjectionAnnotation) {
				ScriptProjectionAnnotation annot = (ScriptProjectionAnnotation) annotation;
				if (expand == annot.isCollapsed() && filter.match(annot)) {
					if (expand)
						annot.markExpanded();
					else
						annot.markCollapsed();
					modified.add(annot);
				}
			}
		}
		model.modifyAnnotations(null, null,
				modified.toArray(new Annotation[modified.size()]));
	}

	protected abstract String getPartition();

	protected abstract String getCommentPartition();

	protected String getDocPartition() {
		return null;
	}

	protected abstract IPartitionTokenScanner getPartitionScanner();

	protected abstract String getNatureId();

	protected abstract String[] getPartitionTypes();

	protected abstract ILog getLog();

	protected FoldingASTVisitor getFoldingVisitor(int offset) {
		return new FoldingASTVisitor(offset);
	}

	protected static class FoldingASTVisitor extends ASTVisitor {
		private final List<CodeBlock> result = new ArrayList<CodeBlock>();
		private final int offset;

		protected FoldingASTVisitor(int offset) {
			this.offset = offset;
		}

		@Override
		public boolean visit(MethodDeclaration s) throws Exception {
			add(s);
			return super.visit(s);
		}

		@Override
		public boolean visit(TypeDeclaration s) throws Exception {
			add(s);
			return super.visit(s);
		}

		public CodeBlock[] getResults() {
			return result.toArray(new CodeBlock[result.size()]);
		}

		protected final void add(ASTNode s) {
			int start = offset + s.sourceStart();
			int end = s.sourceEnd() - s.sourceStart();

			result.add(new CodeBlock(s, new Region(start, end)));
		}

		protected void add(CodeBlock block) {
			result.add(block);
		}

	}

	// TODO: multiple languages support
	protected ISourceParser getSourceParser() {
		final IProject project = fInput != null ? fInput.getScriptProject()
				.getProject() : null;
		return DLTKLanguageManager.getSourceParser(project, getNatureId());
	}

	/**
	 * Should locate all statements and return
	 * 
	 * @param code
	 */
	protected CodeBlock[] getCodeBlocks(String code) {
		return getCodeBlocks(code, 0);
	}

	protected CodeBlock[] getCodeBlocks(String code, int offset) {
		ModuleDeclaration decl = parse(code, offset);
		if (decl == null) {
			return null;
		}
		return buildCodeBlocks(decl, offset);
	}

	protected final IModelElement getModuleElement() {
		return fInput;
	}

	protected final ModuleDeclaration parse(String code, int offset) {
		if (offset == 0 && fInput instanceof ISourceModule) {
			final ISourceModule module = (ISourceModule) fInput;
			try {
				// TODO: DLTK multiples languages
				if (code.equals(module.getSource())
						&& module.getElementName().endsWith(".g")) {
					// use the cache luke! ;)
					return SourceParserUtil.getModuleDeclaration(module);
				}
			} catch (ModelException e) {
				getLog().log(
						new Status(IStatus.WARNING, DLTKUIPlugin.PLUGIN_ID, e
								.getMessage(), e));
			}
		}
		ISourceModule sm = (ISourceModule) getInputElement();
		IPath path = sm.getPath();
		return (ModuleDeclaration) getSourceParser().parse(
				new ModuleSource(path.toString(), code), null);
	}

	protected CodeBlock[] buildCodeBlocks(ModuleDeclaration decl, int offset) {
		FoldingASTVisitor visitor = getFoldingVisitor(offset);

		try {
			decl.traverse(visitor);
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}

		return visitor.getResults();
	}

	/**
	 * Returns is it possible to collapse statement, or it should never be
	 * folded
	 * 
	 * @param s
	 * @param ctx
	 */
	protected boolean mayCollapse(ASTNode s,
			FoldingStructureComputationContext ctx) {
		if (s instanceof TypeDeclaration) {
			return true;
		}

		if (s instanceof MethodDeclaration) {
			return true;
		}

		return false;
	}

	protected boolean initiallyCollapse(ASTNode s,
			FoldingStructureComputationContext ctx) {
		if (ctx.allowCollapsing()) {
			return initiallyCollapse(s);
		}

		return false;
	}

	protected boolean initiallyCollapse(ASTNode s) {
		// classes, modules, etc
		if (s instanceof TypeDeclaration && fInitCollapseClasses) {
			return true;
		}

		// methods, subroutines, etc
		if (s instanceof MethodDeclaration && fInitCollapseMethods) {
			return true;
		}

		return false;
	}

	protected boolean initiallyCollapseComments(IRegion commentRegion,
			FoldingStructureComputationContext ctx) {
		if (ctx.allowCollapsing()) {
			return isHeaderRegion(commentRegion, ctx) ? fInitCollapseHeaderComments
					: fInitCollapseComments;
		}
		return false;
	}

	protected boolean initiallyCollapseDocs(IRegion commentRegion,
			FoldingStructureComputationContext ctx) {
		if (ctx.allowCollapsing()) {
			return fInitCollapseDocs;
		}

		return false;
	}

	/**
	 * Checks if the specified region is located at the beginning of the
	 * document
	 * 
	 * @param region
	 * @param ctx
	 */
	protected boolean isHeaderRegion(IRegion region,
			FoldingStructureComputationContext ctx) {
		final int offset = region.getOffset();
		if (offset == 0) {
			return true;
		} else if (offset < 100) {
			try {
				return isEmptyRegion(ctx.getDocument(), 0, offset);
			} catch (BadLocationException e) {
				//
			}
		}
		return false;
	}

	/**
	 * Installs a partitioner with <code>document</code>.
	 * 
	 * @param document
	 *            the document
	 */
	private void installDocumentStuff(Document document) {
		IDocumentPartitioner partitioner = getDocumentPartitioner();
		partitioner.connect(document);
		document.setDocumentPartitioner(getPartition(), partitioner);
	}

	protected IDocumentPartitioner getDocumentPartitioner() {
		return new FastPartitioner(getPartitionScanner(), getPartitionTypes());
	}

	/**
	 * Removes partitioner with <code>document</code>.
	 * 
	 * @param document
	 *            the document
	 */
	private void removeDocumentStuff(Document document) {
		document.setDocumentPartitioner(getPartition(), null);
	}

	public void expandElements(final IModelElement[] array) {
		modifyFiltered(new Filter() {

			public boolean match(ScriptProjectionAnnotation annotation) {
				IModelElement element = annotation.getElement();
				if (element == null)
					return false;
				for (int a = 0; a < array.length; a++) {
					IModelElement e = array[a];
					if (e.equals(element)) {
						return true;
					}
				}
				return false;
			}

		}, true);
	}

	public void collapseElements(IModelElement[] modelElements) {
		// empty implementation
	}

	private ITypedRegion getRegion(IDocument d, int offset)
			throws BadLocationException {
		return TextUtilities.getPartition(d, getPartition(), offset, true);
	}

	protected IRegion[] computeCommentsRanges(String contents) {
		// for backwards compatibility incase anyone has overridden this..
		return computeCommentsRanges(contents, getCommentPartition());
	}

	protected IRegion[] computeCommentsRanges(String contents, String partition) {
		try {
			if (contents == null)
				return new IRegion[0];
			Document d = new Document(contents);
			installDocumentStuff(d);
			List<ITypedRegion> docRegionList = new ArrayList<ITypedRegion>();
			int offset = 0;
			for (;;) {
				try {
					ITypedRegion region = getRegion(d, offset);
					docRegionList.add(region);
					offset = region.getLength() + region.getOffset() + 1;
				} catch (BadLocationException e1) {
					break;
				}
			}
			ITypedRegion start = null;
			ITypedRegion lastRegion = null;
			List<IRegion> regions = new ArrayList<IRegion>();
			for (ITypedRegion region : docRegionList) {
				if (region.getType().equals(partition)
						&& startsAtLineBegin(d, region)) {
					if (start == null)
						start = region;
				} else if (start != null
						&& (isBlankRegion(d, region) || isEmptyRegion(d, region)
								&& collapseEmptyLines())) {
					// blanks or empty lines
					// TODO introduce line limit for collapseEmptyLines() ?
				} else {
					if (start != null) {
						int offset0 = start.getOffset();
						int length0 = lastRegion.getOffset()
								+ lastRegion.getLength() - offset0 - 1;
						length0 = contents
								.substring(offset0, offset0 + length0).trim()
								.length();
						IRegion fullRegion = new Region(offset0, length0);
						if (isMultilineRegion(d, fullRegion)) {
							regions.add(fullRegion);
						}
					}
					start = null;
				}
				lastRegion = region;
			}
			if (start != null) {
				int offset0 = start.getOffset();
				int length0 = lastRegion.getOffset() - offset0
						+ lastRegion.getLength() - 1;
				IRegion fullRegion = new Region(offset0, length0);
				if (isMultilineRegion(d, fullRegion)) {
					regions.add(fullRegion);
				}
			}
			prepareRegions(d, regions);
			removeDocumentStuff(d);
			return regions.toArray(new IRegion[regions.size()]);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return new IRegion[0];
	}

	/**
	 * @param d
	 * @param regions
	 * @since 2.0
	 */
	protected void prepareRegions(Document d, List<IRegion> regions) {
		// override in descendants
	}

	private boolean startsAtLineBegin(Document d, ITypedRegion region)
			throws BadLocationException {
		int lineStart = d.getLineOffset(d.getLineOfOffset(region.getOffset()));
		if (lineStart != region.getOffset()) {
			if (!isEmptyRegion(d, lineStart, region.getOffset() - lineStart)) {
				return false;
			}
		}
		return true;
	}

	protected boolean collapseEmptyLines() {
		return fFoldNewLines;
	}

	/**
	 * @deprecated
	 */
	protected final void getElementCommentResolver() {
		// will be deleted
	}

	public static class MethodCollector implements IModelElementVisitor {
		private final Map<SourceRange, IModelElement> methodByNameRange = new HashMap<SourceRange, IModelElement>();

		public boolean visit(IModelElement element) {

			if (element instanceof SourceMethod) {
				try {
					final ISourceRange nameRange = ((SourceMethod) element)
							.getNameRange();
					methodByNameRange.put(new SourceRange(nameRange), element);
				} catch (ModelException e) {
					// empty
				}
			}
			return true;
		}

		/**
		 * @param offset
		 * @param length
		 */
		public IModelElement get(int offset, int length) {
			return methodByNameRange.get(new SourceRange(offset, length));
		}

	}
}
