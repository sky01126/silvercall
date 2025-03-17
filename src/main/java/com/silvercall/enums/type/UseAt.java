package com.silvercall.enums.type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

/**
 * 사용 여부
 *
 * @version 3.3.5
 */
@SuppressWarnings("unused")
public enum UseAt {

	/**
	 * 사용
	 */
	YES("YES"),

	/**
	 * 대기
	 */
	WAITING("WAIT"),

	/**
	 * 보관
	 */
	STORAGE("STRGE"),

	/**
	 * 백업
	 */
	BACKUP("BACKUP"),

	/**
	 * 사용정지
	 */
	NO("NO"),

	/**
	 * 테스트
	 */
	TEST("TEST"),

	/**
	 * 임시
	 */
	TEMP("TMP"),

	/**
	 * 삭제
	 */
	DELETE("DEL");

	private final String value;

	UseAt(final String value) {
		this.value = value;
	}

	/**
	 * Return an enumeration object with a value.
	 *
	 * @param value the value
	 * @return the user at
	 */
	public static UseAt enumOf(String value) {
		// @formatter:off
		return Arrays.stream(values())
				.filter(type -> StringUtils.equalsAnyIgnoreCase(value, type.value))
				.findFirst().orElse(NO);
		// @formatter:on
	}

	/**
	 * 리스트
	 *
	 * @return the enum value list
	 */
	public static List<String> list() {
		return Stream.of(UseAt.values()).map(Enum::name).collect(Collectors.toList());
	}

	/**
	 * 리스트
	 *
	 * @return the enum value list
	 */
	public static List<UseAt> enumOfList() {
		return Stream.of(UseAt.values()).map(type -> UseAt.enumOf(type.name())).collect(Collectors.toList());
	}

	/**
	 * Return the use at value.
	 */
	public String value() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value;
	}

}
