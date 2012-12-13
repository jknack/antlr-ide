/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.debug;

public final class AntlrDebugConstants {
	private AntlrDebugConstants() {
	}

	public static final String ANTLR_LAUNCH_CONFIGURATION_TYPE = "org.deved.antlride.launching.AntlrLaunchConfigurationType"; //$NON-NLS-1$
	
	public static final String DEBUG_MODEL_ID = "org.deved.antlride.debug.antlrModel"; //$NON-NLS-1$

	public static final String ATTR_PROJECT_NAME = "project"; //$NON-NLS-1$
	
	public static final String ATTR_ANTLR_PROGRAM = "mainScript";//$NON-NLS-1$
	
	public static final String ATTR_JAVA_LAUNCH_TYPE = DEBUG_MODEL_ID + ".javaLauncherType";//$NON-NLS-1$
	
	public static final String ATTR_JAVA_LAUNCHER_NAME = DEBUG_MODEL_ID + ".javaLauncherName";//$NON-NLS-1$
	
	public static final String JAVA_DEFAULT_LAUNCHER = ATTR_JAVA_LAUNCH_TYPE + ".default";//$NON-NLS-1$
	
	public static final String JAVA_CUSTOM_LAUNCHER = ATTR_JAVA_LAUNCH_TYPE + ".custom";//$NON-NLS-1$
	
	public static final String JAVA_EXTERNAL_SOURCE_PATH = ATTR_JAVA_LAUNCH_TYPE + ".externalSourcePath";//$NON-NLS-1$
	
	//Errors
	
	public static final int ERR_INTERNAL_ERROR = 100;
}
