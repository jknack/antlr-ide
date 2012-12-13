/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.common.ui;

import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.internal.ui.editor.AnnotatedImageDescriptor;
import org.eclipse.dltk.ui.PluginImagesHelper;
import org.eclipse.dltk.ui.viewsupport.ImageImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

public final class AntlrImages {
	private static final PluginImagesHelper helper = new PluginImagesHelper(
			AntlrCommonUI.getDefault().getBundle(), new Path("/icons"));

	public static final String ACTION = "action";

	public static final String LEXER_RULE = "lexer_rule";

	public static final String SCOPE = "scope";

	public static final String FILTER_SCOPES = "filterScopes";

	public static final String RUN = "run";

	public static final String DEBUG = "debug";

	public static final String RUN_JAVA = "runJava";

	public static final String DEBUG_JAVA = "debugJava";

	public static final String ZOOM_IN = "zoomIn";

	public static final String ZOOM_OUT = "zoomOut";

//	public static final String STATUS_OK = "statusOk";

	public static final String CLEAR = "clear";

//	public static final String SYNC_EDITOR = "syncEditor";

	public static final String SAVE_AS = "saveAs";

//	public static final String REMOVE_TEST_CASE = "removeTestCase";

	public static final String SAVE_TEST_CASE = "saveTestCase";

//	public static final String SEARCH_HISTORY = "searchHistory";

	public static final String NEW_TEST_CASE = "newTestCase";

	public static final String G = "g";

	public static final String ANGLE_BRACKET_ST = "angleBracketST";

	public static final String DOLLAR_ST = "dollarST";

	public static final String PARSE_TREE = "parseTree";

//	public static final String ITEM_SELECTED = "selected";

	public static final String CONSOLE = "console";

	public static final String RULE = "rule";

//	public static final String OPEN_TEST_CASE = "openTestCase";

	public static final String LAYOUT = "viewMenu";

	public static final String DECISION = "decision";
	
	public static final String TARGET_LANGUAGE = "target_language";
	
	public static final String PACKAGE = "package";
	
	public static final String RAILROAD = "railroad";
	
	public static final String OPTIMIZE_GRAPH = "optG";
	
	public static final String APPEND_GRAPH = "appendG";

	static {
		helper.createManaged(PluginImagesHelper.T_OBJ, "grammar_action.gif",
				ACTION);
		
		helper.createManaged(PluginImagesHelper.T_OBJ, "optimize_graph.gif",
				OPTIMIZE_GRAPH);

		helper.createManaged(PluginImagesHelper.T_OBJ, "lexer_rule.gif",
				LEXER_RULE);

		helper.createManaged(PluginImagesHelper.T_OBJ, "scope_obj.gif", SCOPE);

		helper.createManaged(PluginImagesHelper.T_OBJ, "clear_diagram.gif",
				CLEAR);

//		helper.createManaged(PluginImagesHelper.T_OBJ, "status_ok.gif",
//				STATUS_OK);

		helper.createManaged(PluginImagesHelper.T_OBJ, "filter_scopes.gif",
				FILTER_SCOPES);

		helper.createManaged(PluginImagesHelper.T_ETOOL, "run_exc.gif", RUN);

		helper
				.createManaged(PluginImagesHelper.T_ETOOL, "debug_exc.gif",
						DEBUG);

		helper.createManaged(PluginImagesHelper.T_ETOOL, "zoomIn.gif", ZOOM_IN);

		helper.createManaged(PluginImagesHelper.T_ETOOL, "zoomOut.gif",
				ZOOM_OUT);

//		helper.createManaged(PluginImagesHelper.T_ETOOL, "synced.gif",
//				SYNC_EDITOR);

//		helper.createManaged(PluginImagesHelper.T_OBJ, "remove_testcase.gif",
//				REMOVE_TEST_CASE);

		helper.createManaged(PluginImagesHelper.T_OBJ, "save.gif",
				SAVE_TEST_CASE);

		helper.createManaged(PluginImagesHelper.T_OBJ, "saveas.gif", SAVE_AS);

//		helper.createManaged(PluginImagesHelper.T_OBJ, "search_history.gif",
//				SEARCH_HISTORY);

		helper.createManaged(PluginImagesHelper.T_OBJ, "testcase_obj.gif",
				NEW_TEST_CASE);

		helper.createManaged(PluginImagesHelper.T_OBJ, "g.gif", G);

		helper.createManaged(PluginImagesHelper.T_OBJ,
				"angle_bracket_st_obj.gif", ANGLE_BRACKET_ST);

		helper.createManaged(PluginImagesHelper.T_OBJ, "dollar_st_obj.gif",
				DOLLAR_ST);

		helper.createManaged(PluginImagesHelper.T_ETOOL, "parsetree.gif",
				PARSE_TREE);

//		helper.createManaged(PluginImagesHelper.T_OBJ, "selected.gif",
//				ITEM_SELECTED);

		helper
				.createManaged(PluginImagesHelper.T_ETOOL, "console.gif",
						CONSOLE);

		helper.createManaged(PluginImagesHelper.T_OBJ, "def_rule.gif", RULE);

//		helper.createManaged(PluginImagesHelper.T_OBJ, "open_testcase.gif",
//				OPEN_TEST_CASE);

		helper.createManaged(PluginImagesHelper.T_ETOOL, "run_java.gif",
				RUN_JAVA);

		helper.createManaged(PluginImagesHelper.T_ETOOL, "debug_java.gif",
				DEBUG_JAVA);

		helper.createManaged(PluginImagesHelper.T_ETOOL, "layout.gif", LAYOUT);

		helper.createManaged(PluginImagesHelper.T_ETOOL, "decision.gif",
				DECISION);
		
		helper.createManaged(PluginImagesHelper.T_ETOOL, "railroad.gif",
				RAILROAD);
		
		helper.createManaged(PluginImagesHelper.T_ETOOL, "append_railroad.gif",
				APPEND_GRAPH);
		
		helper.createManaged(PluginImagesHelper.T_OBJ, "target_language.gif",
				TARGET_LANGUAGE);
		
		helper.createManaged(PluginImagesHelper.T_OBJ, "package.gif",
				PACKAGE);
		// create some descriptors first
		getDescriptor(G);
		
		getDescriptor(NEW_TEST_CASE);

		getDescriptor(SAVE_TEST_CASE);

		getDescriptor(CLEAR);

		getDescriptor(LAYOUT);

		getDescriptor(DECISION);
		
		getDescriptor(TARGET_LANGUAGE);
		
		getDescriptor(PACKAGE);
	}

	private static final String COMPOSITE = "composite_";

	public static ImageDescriptor getDescriptor(String key) {
		ImageDescriptor descriptor = helper.getDescriptor(key);
		if (!(descriptor instanceof CachedImageDescriptor)) {
			CachedImageDescriptor cachedDescriptor = new CachedImageDescriptor(
					descriptor);
			helper.getImageRegistry().put(key, cachedDescriptor);
			descriptor = cachedDescriptor;
		}
		return descriptor;
	}

	public static Image getImage(String key) {
		return getImage(key, false);
	}

	public static Image createCompositeImage(String key, Point imageSize,
			int direction, Image baseImage) {
		ImageImageDescriptor baseImageDescriptor = baseImage == null ? null
				: new ImageImageDescriptor(baseImage);
		CompositeImageDescriptor descriptor = new CompositeImageDescriptor(key,
				baseImageDescriptor, imageSize, direction);
		Image image = descriptor.createImage();
		helper.getImageRegistry().put(COMPOSITE + key, image);
		return image;
	}

	public static Image getImage(String key, boolean composite) {
		String imageName;
		if (composite) {
			imageName = COMPOSITE + key;
		} else {
			imageName = key;
		}
		Image image = helper.get(imageName);
		return image;
	}

	static private class CompositeImageDescriptor extends
			AnnotatedImageDescriptor {

		private String imageName;

		private boolean hasBaseImageDescriptor;

		private int direction;

		public CompositeImageDescriptor(String imageName,
				ImageDescriptor baseImageDescriptor, Point size, int direction) {
			super(baseImageDescriptor, size);
			this.direction = direction;
			this.imageName = imageName;
			hasBaseImageDescriptor = baseImageDescriptor != null;
		}

		@Override
		protected void drawCompositeImage(int width, int height) {
			if (hasBaseImageDescriptor) {
				super.drawCompositeImage(width, height);
			} else {
				drawAnnotations();
			}
		}

		@Override
		protected void drawAnnotations() {
			Image image = AntlrImages.getImage(imageName);
			ImageData imageData = image.getImageData();
			if (direction == SWT.LEFT)
				drawImageTopLeft(imageData);
			else if (direction == SWT.RIGHT)
				drawImageTopRight(imageData);
		}
	}

	static private class CachedImageDescriptor extends ImageDescriptor {

		private ImageData imageData;

		private ImageDescriptor descriptor;

		private Image image;

		public CachedImageDescriptor(ImageDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		@Override
		public Image createImage() {
			if (image == null) {
				image = descriptor.createImage();
			}
			return image;
		}

		@Override
		public ImageData getImageData() {
			if (imageData == null) {
				imageData = descriptor.getImageData();
			}
			return imageData;
		}

	}
}
