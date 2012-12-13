/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.stringtemplate.ui;

import org.deved.antlride.stringtemplate.core.StringTemplateConstants;

public interface StringTemplateUIConstants extends StringTemplateConstants {

	String PREFIX = "__string_template_";

	String SH_KEYWORD = PREFIX + "keyword"; //$NON-NLS-1$

	String SH_DEFAULT = PREFIX + "default"; //$NON-NLS-1$	
	
	String SH_STRING = PREFIX + "string"; //$NON-NLS-1$	

	String SH_COMMENT = PREFIX + "comment"; //$NON-NLS-1$
	
	String SH_STG_SINGLE_LINE_COMMENT = PREFIX + "stg_single_line_comment"; //$NON-NLS-1$

	String SH_STG_MULTI_LINE_COMMENT = PREFIX + "stg_multi_line_comment"; //$NON-NLS-1$
	
	String SH_STG_DOC_COMMENT = PREFIX + "stg_doc_comment"; //$NON-NLS-1$

	String SH_TEMPLATE_DELIMETERS = PREFIX + "template_delimeters"; //$NON-NLS-1$
	
	String SH_DOUBLE_ANGLE_BRACKETS = PREFIX + "double_angle_brackets"; //$NON-NLS-1$
	
	String SH_TEMPLATE = PREFIX + "template"; //$NON-NLS-1$
	
	String SH_ASSIGN_TEMPLATE = PREFIX + "assign_template"; //$NON-NLS-1$
	
	String SH_TEMPLATE_REGION = PREFIX + "template_region"; //$NON-NLS-1$
	
	String SH_ESPECIAL_CHARACTERS = PREFIX + "especial_characters"; //$NON-NLS-1$	
}
