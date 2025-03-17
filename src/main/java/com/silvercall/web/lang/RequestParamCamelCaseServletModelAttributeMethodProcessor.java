package com.silvercall.web.lang;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.ArrayUtils;

import jakarta.servlet.ServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;
import org.springframework.web.util.WebUtils;

/**
 * The request parameter name servlet model attribute method processor.
 *
 * <pre>
 * <b>Register in java config example</b>
 * <code>
 * &#64;Override
 * public void addArgumentResolvers(List&#60;HandlerMethodArgumentResolver&#62; argumentResolvers) {
 *     argumentResolvers.add(new RequestParamCamelCaseServletModelAttributeMethodProcessor(true));
 * }
 * </code>
 * </pre>
 *
 * @version 1.0.1
 * @see ServletModelAttributeMethodProcessor
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class RequestParamCamelCaseServletModelAttributeMethodProcessor extends ServletModelAttributeMethodProcessor {

	public RequestParamCamelCaseServletModelAttributeMethodProcessor() {
		this(true);
	}

	public RequestParamCamelCaseServletModelAttributeMethodProcessor(boolean annotationNotRequired) {
		super(annotationNotRequired);
	}

	private static Map<String, String> analyzeClass(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Map<String, String> renameMap = new HashMap<>();
		for (Field field : fields) {
			RequestParamCamelCase annotation = field.getAnnotation(RequestParamCamelCase.class);
			if (annotation != null && ArrayUtils.isNotEmpty(annotation.name())) {
				for (String value : annotation.name()) {
					renameMap.put(value, field.getName());
				}
			}
		}
		if (renameMap.isEmpty()) {
			return Collections.emptyMap();
		}
		return renameMap;
	}

	@Override
	protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
		ServletRequest servletRequest = request.getNativeRequest(ServletRequest.class);
		ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
		bind(servletRequest, servletBinder);
	}

	@SuppressWarnings("unchecked")
	private void bind(ServletRequest request, ServletRequestDataBinder binder) {
		MutablePropertyValues mpvs = new ServletRequestParameterPropertyValues(request);
		MultipartRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartRequest.class);
		if (multipartRequest != null) {
			bindMultipart(multipartRequest.getMultiFileMap(), mpvs);
		}

		String attr = HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;
		Map<String, String> attribute = (Map<String, String>) request.getAttribute(attr);
		if (attribute != null) {
			for (Map.Entry<String, String> entry : attribute.entrySet()) {
				if (mpvs.contains(entry.getKey())) {
					log.warn("Skipping URI variable \"{}\" since the request contains a bind value with the same name.",
							entry.getKey());
				} else {
					mpvs.addPropertyValue(entry.getKey(), entry.getValue());
				}
			}
		}
		doExtendBind(mpvs, binder);
		binder.bind(mpvs);
	}

	private void bindMultipart(Map<String, List<MultipartFile>> files, MutablePropertyValues mpvs) {
		for (Map.Entry<String, List<MultipartFile>> entry : files.entrySet()) {
			String key = entry.getKey();
			List<MultipartFile> values = entry.getValue();
			if (values.size() == 1) {
				MultipartFile value = values.get(0);
				if (!value.isEmpty()) {
					mpvs.add(key, value);
				}
			} else {
				mpvs.add(key, values);
			}
		}
	}

	public void doExtendBind(MutablePropertyValues mpvs, ServletRequestDataBinder binder) {
		Object target = binder.getTarget();
		Class<?> targetClass = target.getClass();
		Map<Class<?>, Map<String, String>> replaceMap = new ConcurrentHashMap<>();
		if (!replaceMap.containsKey(targetClass)) {
			Map<String, String> mapping = analyzeClass(targetClass);
			replaceMap.put(targetClass, mapping);
		}

		Map<String, String> mapping = replaceMap.get(targetClass);
		for (Map.Entry<String, String> entry : mapping.entrySet()) {
			String from = entry.getKey();
			String to = entry.getValue();
			if (mpvs.contains(from)) {
				log.debug("Request parameter name bind from \"{}\" to \"{}\"", from, to);
				mpvs.add(to, mpvs.getPropertyValue(from).getValue());
			}
		}
	}

}
