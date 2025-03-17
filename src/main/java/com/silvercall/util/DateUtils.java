package com.silvercall.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@SuppressWarnings("unused")
public class DateUtils {

	private DateUtils() throws IllegalAccessException {
		throw new IllegalAccessException("access to class not allowed.");
	}

	/**
	 * Convert LocalDateTime to Date.
	 *
	 * @param date the local date.
	 * @return the date.
	 */
	public static Date localDateToDate(final LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Convert LocalDateTime to Milliseconds.
	 *
	 * @param date the local date.
	 * @return the date milliseconds.
	 */
	public static long localDateToMilliseconds(final LocalDate date) {
		return localDateToDate(date).getTime();
	}

	/**
	 * Convert LocalDateTime to Date.
	 *
	 * @param date the local date time.
	 * @return the date.
	 */
	public static Date localDateToDate(final LocalDateTime date) {
		return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Convert LocalDateTime to Milliseconds.
	 *
	 * @param date the local date time.
	 * @return the date milliseconds.
	 */
	public static long localDateToMilliseconds(final LocalDateTime date) {
		return localDateToDate(date).getTime();
	}

}
