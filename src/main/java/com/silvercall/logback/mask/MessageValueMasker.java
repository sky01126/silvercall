package com.silvercall.logback.mask;

import com.fasterxml.jackson.core.JsonStreamContext;

import com.silvercall.util.MaskingUtils;
import net.logstash.logback.mask.ValueMasker;

import static org.apache.commons.lang3.StringUtils.equalsAnyIgnoreCase;

/**
 * Mask email and cell phone number in messages
 * <p>
 * <a href="https://develop-sense.tistory.com/62">[JAVA] 정규식을 이용한 마스킹(정규표현식 마스킹 처리)</a>
 *
 * @version 1.0.0
 * @see ValueMasker
 */
public class MessageValueMasker implements ValueMasker {

	@Override
	public Object mask(JsonStreamContext context, Object value) {
		if (equalsAnyIgnoreCase(context.getCurrentName(), "message") && value instanceof CharSequence) {
			return MaskingUtils.mask((CharSequence) value);
		}
		return value;
	}

}
