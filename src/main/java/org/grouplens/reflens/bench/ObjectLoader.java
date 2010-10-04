/* RefLens, a reference implementation of recommender algorithms.
 * Copyright 2010 Michael Ekstrand <ekstrand@cs.umn.edu>
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package org.grouplens.reflens.bench;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities to make class loading easier.
 * @author Michael Ekstrand <ekstrand@cs.umn.edu>
 *
 */
public class ObjectLoader {
	private static Logger logger = LoggerFactory.getLogger(ObjectLoader.class);
	
	public static <T> Class<T> getClass(String name) {
		try {
			@SuppressWarnings("unchecked")
			Class<T> factClass =
				(Class<T>) Class.forName(name);
			logger.debug("Loaded class {}", factClass.getName());
			return factClass;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Class not found", e);
		}
	}
	
	/**
	 * Cosntruct a new instance of the class named by <tt>name</tt>.
	 * @param <T> A supertype of the class to construct.
	 * @param name The name of the class to construct.
	 * @return A new instance of the class <tt>name</tt>.
	 */
	public static <T> T makeInstance(String name) {
		try {
			Class<T> factClass = getClass(name);
			Constructor<T> ctor = factClass.getConstructor();
			return ctor.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("Invalid recommender fatory", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Invalid recommender fatory", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Invalid recommender fatory", e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("Invalid recommender fatory", e);
		}
	}
}
