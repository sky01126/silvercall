package com.silvercall.enums.type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

/**
 * The build / deployment environment setup information.
 *
 * @version 1.0.1
 */
@SuppressWarnings("unused")
public enum Environment {

	/**
	 * Local development environment
	 */
	LOCAL("local", "Local development environment"),

	/**
	 * Development environment
	 */
	DEVELOP("dev", "Development environment"),

	/**
	 * Integrated development environment
	 */
	INTEGRATION("integration", "Integrated development environment"),

	/**
	 * Test environment
	 */
	TEST("test", "Test environment"),

	/**
	 * Testbed environment
	 */
	TESTBED("tb", "Testbed environment"),

	/**
	 * QA environment
	 */
	QA("qa", "QA environment"),

	/**
	 * QA test environment
	 */
	QAT("qat", "QA test environment"),

	/**
	 * Staging environment
	 */
	STAGING("stage", "Staging environment"),

	/**
	 * Production environment
	 */
	PRODUCTION("prod", "Production environment");

	private final String value;

	private final String message;

	Environment(final String value, final String message) {
		this.value = value;
		this.message = message;
	}

	/**
	 * Return an enumeration object with a value.
	 *
	 * @param value the value
	 * @return the environment
	 */
	public static Environment enumOf(String value) {
		// @formatter:off
		return Arrays.stream(values())
				.filter(type -> StringUtils.equalsAnyIgnoreCase(value, type.value))
				.findFirst().orElse(null);
		// @formatter:on
	}

	/**
	 * 리스트
	 *
	 * @return the enum value list
	 */
	public static List<String> list() {
		return Stream.of(Environment.values()).map(Enum::name).collect(Collectors.toList());
	}

	/**
	 * 리스트
	 *
	 * @return the enum value list
	 */
	public static List<Environment> enumOfList() {
		return Stream.of(Environment.values()).map(type -> Environment.enumOf(type.name())).collect(Collectors.toList());
	}

	/**
	 * Return the active profile value.
	 */
	public String value() {
		return this.value;
	}

	/**
	 * Return the active profile message.
	 */
	public String message() {
		return this.message;
	}

	@Override
	public String toString() {
		return this.value;
	}

}
