/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.gunit.ui;

import org.deved.antlride.gunit.core.GUnitConstants;

public interface GUnitUIConstants extends GUnitConstants {

	String PREFIX = "__gunit_";

	String SH_KEYWORD = PREFIX + "keyword"; //$NON-NLS-1$

	String SH_DEFAULT = PREFIX + "default"; //$NON-NLS-1$	
	
	String SH_STRING = PREFIX + "string"; //$NON-NLS-1$	

	String SH_SINGLE_LINE_COMMENT = PREFIX + "single_line_comment"; //$NON-NLS-1$

	String SH_MULTI_LINE_COMMENT = PREFIX + "multi_line_comment"; //$NON-NLS-1$
	
	String SH_DOC_COMMENT = PREFIX + "doc_comment"; //$NON-NLS-1$
	
	String SH_ML_STRING = PREFIX + "ml_string"; //$NON-NLS-1$
	
	String SH_OK_KEYWORD = PREFIX + "ok_keyword"; //$NON-NLS-1$
	
	String SH_FAIL_KEYWORD = PREFIX + "fail_keyword"; //$NON-NLS-1$
	
	String SH_ML_STRING_OPERATORS = PREFIX + "ml_string_operators"; //$NON-NLS-1$
	
	String SH_TEST_SUITE = PREFIX + "test_suite"; //$NON-NLS-1$	
	
	String SH_DIRECTIVE = PREFIX + "directive"; //$NON-NLS-1$
	
	String SH_EXPECT_OPERATOR = PREFIX + "expect"; //$NON-NLS-1$
}
