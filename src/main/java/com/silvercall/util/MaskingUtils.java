package com.silvercall.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.replace;
import static org.apache.commons.lang3.StringUtils.replaceOnce;

public class MaskingUtils {

	/**
	 * Email 주소 패턴
	 */
	// @formatter:off
	private static final Pattern PATTERN_EMAIL = Pattern.compile("[a-zA-Z0-9]+[a-zA-Z0-9._-]*@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+");
	// @formatter:on

	/**
	 * 핸드폰 패턴
	 */
	// @formatter:off
	private static final Pattern PATTERN_MOBILE_PHONE = Pattern.compile("(01(?:0|1|[6-9]))[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})+");
	// @formatter:on

	/**
	 * 전화 패턴
	 */
	// @formatter:off
	private static final Pattern PATTERN_TELEPHONE = Pattern.compile("(0\\d{1,2})[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})+");
	// @formatter:on

	/**
	 * 생일 패턴
	 */
	// @formatter:off
	private static final Pattern PATTERN_BIRTHDAY = Pattern.compile("((19|20)\\d{2}[.-]?(0[1-9]|1[0-2])[.-]?(0[1-9]|[12][0-9]|3[01]))+");
	// @formatter:on

	/**
	 * 주민번호 패턴
	 */
	// @formatter:off
	private static final Pattern PATTERN_RRN = Pattern.compile("\\b(\\d{6})-[1-4]\\d{6}\\b");
	// @formatter:on

	public static CharSequence mask(final CharSequence value) {
		CharSequence message;

		// 주민번호 마스킹.
		message = maskRrn((String) value);

		// 생일 마스킹.
		message = maskBirthday((String) message);

		// 전화 마스킹.
		message = maskTelephone((String) message);

		// 핸드폰번호 마스킹.
		message = maskMobilePhone((String) message);

		// 메일주소 마스킹.
		message = maskEmail((String) message);

		return message;
	}

	private static CharSequence maskRrn(String message) {
		try {
			return message.replaceAll(PATTERN_RRN.pattern(), "$1-*******");
		} catch (Exception e) {
			return message;
		}
	}

	private static CharSequence maskBirthday(String message) {
		try {
			return find(PATTERN_BIRTHDAY.matcher(message), message, 3, 4);
		} catch (Exception e) {
			return message;
		}
	}

	private static CharSequence maskMobilePhone(String message) {
		try {
			return find(PATTERN_MOBILE_PHONE.matcher(message), message, 2);
		} catch (Exception e) {
			return message;
		}
	}

	private static CharSequence maskTelephone(String message) {
		try {
			return find(PATTERN_TELEPHONE.matcher(message), message, 2);
		} catch (Exception e) {
			return message;
		}
	}

	/**
	 * Email Masking
	 *
	 * @param message the log message
	 * @return the log message
	 */
	private static CharSequence maskEmail(String message) {
		try {
			String maskMessage = message;
			Matcher matcher = PATTERN_EMAIL.matcher(message);
			while (matcher.find()) {
				String email = matcher.group();
				String regex = "(?<=.{3}).(?=.*@)";
				maskMessage = replaceOnce(maskMessage, email, email.replaceAll(regex, "*"));
			}
			return maskMessage;
		} catch (Exception e) {
			return message;
		}
	}

	private static CharSequence find(final Matcher matcher, final String message, final Integer... groups) {
		String makeMessage = message;
		while (matcher.find()) {
			String value = matcher.group();
			for (Integer group : groups) {
				String replaceTarget = matcher.group(group);
				char[] c = new char[replaceTarget.length()];
				Arrays.fill(c, '*');

				String replace = replace(value, replaceTarget, String.valueOf(c));
				makeMessage = replaceOnce(makeMessage, value, replace);
				value = replace;
			}
		}
		return makeMessage;
	}

}
