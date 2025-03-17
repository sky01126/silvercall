package com.silvercall.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * UUID 생성 유틸리티.
 *
 * @version 2.1.0
 */
@SuppressWarnings("all")
public class UUIDUtils {

	private static final AtomicInteger COUNT = new AtomicInteger(0);

	private static final DateTimeFormatter UUID_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

	/**
	 * UUID SHA Type
	 *
	 * @version 2.1.0
	 */
	public enum Type {
		SHA256,
		SHA512_224,
		SHA512_256
	}

	private UUIDUtils() throws IllegalAccessException {
		throw new IllegalAccessException("access to class not allowed.");
	}

	/**
	 * Now date로 SHA256 (71자리) / SHA512_224 (61자리) / SHA512_256 (71자리) UUID 생성
	 *
	 * <pre>
	 * <code>
	 * UUIDUtils.get();
	 * </code>
	 * </pre>
	 *
	 * @return the sha256(size:71) or sha512_2244(size:61) or sha512_256(size:71) uuid
	 */
	public static final String get() {
		return get(Type.SHA256);
	}

	/**
	 * Now date로 SHA256 (71자리) / SHA512_224 (61자리) / SHA512_256 (71자리) UUID 생성
	 *
	 * <pre>
	 * <code>
	 * UUIDUtils.get(UUIDUtils.Type.SHA256);
	 * </code>
	 * </pre>
	 *
	 * @param key the uuid key
	 * @return the sha256(size:71) or sha512_2244(size:61) or sha512_256(size:71) uuid
	 */
	public static final String get(final String key) {
		return get(Type.SHA256, key);
	}

	/**
	 * Now date로 SHA256 (71자리) / SHA512_224 (61자리) / SHA512_256 (71자리) UUID 생성
	 *
	 * <pre>
	 * <code>
	 * UUIDUtils.get(UUIDUtils.Type.SHA256);
	 * </code>
	 * </pre>
	 *
	 * @param type the uuid sha type
	 * @return the sha256(size:71) or sha512_2244(size:61) or sha512_256(size:71) uuid
	 */
	public static final String get(final Type type) {
		String data = LocalDateTime.now().format(UUID_DATE_FORMAT) + COUNT.incrementAndGet();
		if (type == Type.SHA512_224) {
			return separate(type, new StringBuilder(DigestUtils.sha512_224Hex(data)));
		} else if (type == Type.SHA512_256) {
			return separate(type, new StringBuilder(DigestUtils.sha512_256Hex(data)));
		}
		return separate(type, new StringBuilder(DigestUtils.sha256Hex(data)));
	}

	/**
	 * String으로 SHA256 (71자리) / SHA512_224 (61자리) / SHA512_256 (71자리) UUID 생성
	 *
	 * <pre>
	 * <code>
	 * UUIDUtils.get(UUIDUtils.Type.SHA256, "TEST");
	 * </code>
	 * </pre>
	 *
	 * @param type the uuid sha type
	 * @param key the uuid key
	 * @return the sha256(size:71) or sha512_2244(size:61) or sha512_256(size:71) uuid
	 */
	public static final String get(final Type type, final String key) {
		if (type == Type.SHA512_224) {
			return separate(type, new StringBuilder(DigestUtils.sha512_224Hex(key)));
		} else if (type == Type.SHA512_256) {
			return separate(type, new StringBuilder(DigestUtils.sha512_256Hex(key)));
		}
		return separate(type, new StringBuilder(DigestUtils.sha256Hex(key)));
	}

	/**
	 * String과 Now date로 SHA256 (71자리) / SHA512_224 (61자리) / SHA512_256 (71자리) UUID 생성
	 *
	 * <pre>
	 * <code>
	 * UUIDUtils.get("TEST", new Date());
	 * </code>
	 * </pre>
	 *
	 * @param date the date
	 * @param key the uuid key
	 * @return the sha256(size:71) or sha512_2244(size:61) or sha512_256(size:71) uuid
	 */
	public static final String get(final LocalDateTime date, final String key) {
		return get(Type.SHA256, date, key);
	}

	/**
	 * String과 Now date로 SHA256 (71자리) / SHA512_224 (61자리) / SHA512_256 (71자리) UUID 생성
	 *
	 * <pre>
	 * <code>
	 * UUIDUtils.get(UUIDUtils.Type.SHA256, "TEST", new Date());
	 * </code>
	 * </pre>
	 *
	 * @param type the uuid sha type
	 * @param date the date
	 * @param key the uuid key
	 * @return the sha256(size:71) or sha512_2244(size:61) or sha512_256(size:71) uuid
	 */
	public static final String get(final Type type, final LocalDateTime date, final String key) {
		if (type == Type.SHA512_224) {
			return separate(type, new StringBuilder(DigestUtils.sha512_224Hex(key + "-" + date)));
		} else if (type == Type.SHA512_256) {
			return separate(type, new StringBuilder(DigestUtils.sha512_256Hex(key + "-" + date)));
		}
		return separate(type, new StringBuilder(DigestUtils.sha256Hex(key + "-" + date)));
	}

	/**
	 * SHA256 (71자리) / SHA512_224 (61자리) / SHA512_256 (71자리) UUID 에 구분자 추가.
	 *
	 * @param type the type
	 * @param data the hash data
	 * @return the hash data
	 */
	private static final String separate(Type type, StringBuilder data) {
		int count = 7;
		if (type == Type.SHA512_224) {
			count = 5;
		}

		int poi = 12;
		for (int i = 0; i < count; i++) {
			data.insert(poi, "-");
			poi += 1 + 4;
		}
		return data.toString();
	}

}
