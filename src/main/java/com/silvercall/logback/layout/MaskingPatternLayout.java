package com.silvercall.logback.layout;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.silvercall.util.MaskingUtils;

public class MaskingPatternLayout extends PatternLayout {

	@Override
	public String doLayout(ILoggingEvent event) {
		return (String) MaskingUtils.mask(super.doLayout(event));
	}

}
