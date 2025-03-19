package com.silvercall.web.lang;

import org.apache.commons.lang3.StringUtils;

import com.silvercall.dto.response.ResultError;
import com.silvercall.lang.AbstractObject;
import com.silvercall.util.MessageUtils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Spring MVC abstract controller
 *
 * @version 1.0.1
 */
@Slf4j
@Component
public abstract class AbstractController extends AbstractObject {

	/**
	 * Return parameter error.
	 *
	 * @param messageSource the message source.
	 * @param result the binding result
	 */
	protected final void checkForErrors(final MessageSourceAccessor messageSource, final BindingResult result) {
		checkForErrors(messageSource, null, result, null);
	}

	/**
	 * Return parameter error.
	 *
	 * @param messageSource the message source.
	 * @param clazz the request class
	 * @param result the binding result
	 */
	protected final void checkForErrors(final MessageSourceAccessor messageSource, final Class<?> clazz,
			final BindingResult result) {
		checkForErrors(messageSource, clazz, result, null);
	}

	/**
	 * Return parameter error.
	 *
	 * @param messageSource the message source.
	 * @param clazz the request class
	 * @param result the binding result
	 * @param message the error message
	 */
	protected final void checkForErrors(final MessageSourceAccessor messageSource, final Class<?> clazz,
			final BindingResult result, final String message) {
		if (result == null || !result.hasErrors()) {
			return;
		}
		ResultError res = ResultError.builder().code(HttpStatus.PRECONDITION_FAILED.value()).message(message).build();
		if (StringUtils.isBlank(message)) {
			res.setMessage(messageSource.getMessage(MessageUtils.responseCode(HttpStatus.PRECONDITION_FAILED)));
		}
		for (FieldError error : result.getFieldErrors()) {
			log.debug("Field name : {}, Error message : {}", error.getField(), error.getDefaultMessage());
			if (clazz != null) {
				res.add(clazz, error.getField(), error.getDefaultMessage());
			} else {
				res.add(error.getField(), error.getDefaultMessage());
			}
		}
		throw new RequestException(res);
	}

}
