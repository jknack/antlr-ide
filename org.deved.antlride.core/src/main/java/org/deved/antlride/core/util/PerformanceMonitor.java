/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.deved.antlride.core.util;

import java.util.concurrent.TimeUnit;

public class PerformanceMonitor {
	private long start;

	private long end;

	public PerformanceMonitor() {
	}

	public PerformanceMonitor start() {
		this.start = System.currentTimeMillis();
		return this;
	}

	public PerformanceMonitor end() {
		this.end = System.currentTimeMillis();
		return this;
	}

	public long seconds() {
		return TimeUnit.MILLISECONDS.toSeconds(end - start);
	}

	public long milliSecongds() {
		return end - start;
	}
}
