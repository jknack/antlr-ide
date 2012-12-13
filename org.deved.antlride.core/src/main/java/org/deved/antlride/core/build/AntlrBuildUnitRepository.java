/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.core.build;

import org.deved.antlride.core.build.AntlrBuildUnit.AntlrBuildUnitType;
import org.deved.antlride.core.model.IGrammar;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.builder.IBuildContext;

public class AntlrBuildUnitRepository {

	private static AntlrBuildUnitType buildUnitType = AntlrBuildUnitType.WORKSPACE;

	public static void setBuildUnit(AntlrBuildUnitType buildUnitType) {
		if (buildUnitType == null) {
			throw new IllegalArgumentException("buildUnitType can't be null");
		}
		AntlrBuildUnitRepository.buildUnitType = buildUnitType;
	}

	public static AntlrBuildUnit create(IBuildContext buildContext) {
		return buildUnitType.create(buildContext);
	}

	public static AntlrBuildUnit create(ISourceModule sourceModule) {
		return buildUnitType.create(sourceModule);
	}

	/**
	 * This method is not intend for public use
	 * 
	 * @param grammar
	 * @return
	 */
	public static AntlrBuildUnit create(IGrammar grammar) {
		return buildUnitType.create(grammar);
	}
}
