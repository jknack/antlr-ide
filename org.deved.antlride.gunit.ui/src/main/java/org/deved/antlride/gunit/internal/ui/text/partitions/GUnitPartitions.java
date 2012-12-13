/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.internal.ui.text.partitions;

import org.deved.antlride.gunit.core.GUnitConstants;
import org.eclipse.jface.text.IDocument;

public interface GUnitPartitions extends GUnitConstants {
	/**
	 * Partitions
	 */
	String PARTITION_ID = "__gunit_partitioning__";

	String MULTI_LINE_COMMENT = PARTITION_ID + "multi_line_comment";

	String SINGLE_LINE_COMMENT = PARTITION_ID + "single_line_comment";

	String STRING = PARTITION_ID + "string";

	String MULTI_LINE_STRING = PARTITION_ID + "multi_line_string";

	String[] PARTITION_TYPES = { MULTI_LINE_STRING, STRING,
			SINGLE_LINE_COMMENT, MULTI_LINE_COMMENT,
			IDocument.DEFAULT_CONTENT_TYPE };

	String[] LEGAL_CONTENT_TYPES = { MULTI_LINE_STRING, STRING,
			SINGLE_LINE_COMMENT, MULTI_LINE_COMMENT };
}
