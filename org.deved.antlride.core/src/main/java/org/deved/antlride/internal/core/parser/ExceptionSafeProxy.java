/*******************************************************************************
 * Copyright (c) 2007, 2008 Edgar Espina.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.deved.antlride.internal.core.parser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ExceptionSafeProxy implements InvocationHandler {

	private Object target;

	public ExceptionSafeProxy(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object retVal = null;
		try {
			if (isNullSafeCall(args)) {
				retVal = method.invoke(target, args);
			} else {
				System.err.println(String.format("NPE on %s. Args: %s => %s",
						method.getName(), Arrays.toString(method
								.getParameterTypes()), Arrays.toString(args)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retVal == target ? proxy : retVal;
	}

	private boolean isNullSafeCall(Object[] args) {
		if (args != null) {
			for (Object arg : args) {
				if (arg == null)
					return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> T create(T target, Class<? extends T> type) {
		return (T) create(target, new Class[] { type });
	}

	public static Object create(Object target, Class<? extends Object>... types) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				types, new ExceptionSafeProxy(target));
	}
}
