package com.silvercall.util;

import java.util.Collection;

/**
 * Check if List is Empty or Not Empty check utils
 *
 * @version 1.0.0
 */
@SuppressWarnings("unused")
public class ListUtils {

	private ListUtils() throws IllegalAccessException {
		throw new IllegalAccessException("access to class not allowed.");
	}

	/**
	 * Check if List is Empty.
	 *
	 * @param connection the left-hand array to compare, may be {@code null}
	 * @return {@code true} if the arrays are equal
	 */
	public static boolean isEmpty(final Collection<?> connection) {
		return null == connection || connection.isEmpty();
	}

	/**
	 * Check if List is Not Empty.
	 *
	 * @param connection the left-hand array to compare, may be {@code null}
	 * @return {@code true} if the arrays are equal
	 */
	public static boolean isNotEmpty(final Collection<?> connection) {
		return !isEmpty(connection);
	}

}
