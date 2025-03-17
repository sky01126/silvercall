package com.silvercall.lang;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import com.silvercall.dto.response.ResultError;
import com.silvercall.util.MessageUtils;
import com.silvercall.util.RequestUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public abstract class AbstractObject {

	@Value("${http.response.json:false}")
	private boolean isJson;

	@Value("${http.response.error.page.400:error/400}")
	private String badRequestPage;

	@Value("${http.response.error.page.401:error/401}")
	private String unauthorizedPage;

	@Value("${http.response.error.page.402:error/402}")
	private String paymentRequiredViewPage;

	@Value("${http.response.error.page.403:error/403}")
	private String forbiddenPage;

	@Value("${http.response.error.page.404:error/404}")
	private String notFoundPage;

	@Value("${http.response.error.page.405:error/405}")
	private String methodNotAllowedPage;

	@Value("${http.response.error.page.default:error/error}")
	private String defaultErrorPage;

	/**
	 * Response message.
	 *
	 * @param messageSource the message source.
	 * @param code the message source code.
	 * @return the message.
	 */
	protected final String message(final MessageSourceAccessor messageSource, final int code) {
		return MessageUtils.response(messageSource, code);
	}

	/**
	 * Response message.
	 *
	 * @param messageSource the message source.
	 * @param code the message source code.
	 * @param args arguments for the message, or {@code null} if none.
	 * @return the message.
	 */
	protected final String message(final MessageSourceAccessor messageSource, final int code, Object... args) {
		return MessageUtils.response(messageSource, code, args);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request) {
		return result(request, HttpStatus.OK, null, null, null, null);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param status the http status
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final HttpStatus status) {
		return result(request, status, null, null, null, null);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param obj the result object
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, Object obj) {
		return result(request, HttpStatus.OK, obj, null, null, null);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param status the http status
	 * @param obj the result object
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final HttpStatus status, Object obj) {
		return result(request, status, obj, null, null, null);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param code the result code
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final int code) {
		return result(request, HttpStatus.OK, null, code, null, null);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param obj the result object
	 * @param code the result code
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, Object obj, final int code) {
		return result(request, HttpStatus.OK, obj, code, null, null);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param status the http status
	 * @param code the result code
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final HttpStatus status, final int code) {
		return result(request, status, null, code, null, null);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param status the http status
	 * @param obj the result object
	 * @param code the result code
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final HttpStatus status, Object obj,
			final int code) {
		return result(request, status, obj, code, null, null);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param code the result code
	 * @param message the result message
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final Integer code, final String message) {
		return result(request, HttpStatus.OK, null, code, message, null);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param obj the result object
	 * @param code the result code
	 * @param message the result message
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, Object obj, final Integer code,
			final String message) {
		return result(request, HttpStatus.OK, obj, code, message, null);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param status the http status
	 * @param code the result code
	 * @param message the result message
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final HttpStatus status, final Integer code,
			final String message) {
		return result(request, status, null, code, message, null);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param status the http status
	 * @param obj the result object
	 * @param code the result code
	 * @param message the result message
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final HttpStatus status, Object obj,
			final Integer code, final String message) {
		return result(request, status, obj, code, message, null);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param view the model and view
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final String view) {
		return result(request, HttpStatus.OK, null, null, null, view);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param status the http status
	 * @param view the model and view
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final HttpStatus status, final String view) {
		return result(request, status, null, null, null, view);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param code the result code
	 * @param message the result message
	 * @param view the model and view
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final Integer code, final String message,
			final String view) {
		return result(request, HttpStatus.OK, null, code, message, view);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param status the http status
	 * @param code the result code
	 * @param message the result message
	 * @param view the model and view
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final HttpStatus status, final Integer code,
			final String message, final String view) {
		return result(request, status, null, code, message, view);
	}

	/**
	 * Result model and view or restful
	 *
	 * @param request the http servlet request
	 * @param status the http status
	 * @param obj the result object
	 * @param code the result code
	 * @param message the result message
	 * @param view the model and view
	 * @return error page or restful
	 */
	protected final Object result(final HttpServletRequest request, final HttpStatus status, final Object obj,
			final Integer code, final String message, final String view) {
		if (this.isJson || RequestUtils.isAcceptAsJson(request)) {
			HttpHeaders headers = new HttpHeaders();
			if (RequestUtils.isIE(request)) {
				headers.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
			} else {
				headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			}
			if (obj != null) {
				return ResponseEntity.status(status).headers(headers).body(obj);
			}
			ResultError result = ResultError.builder().code(status.value()).message(status.getReasonPhrase()).build();
			if (code != null) {
				result.setCode(code);
			}
			if (StringUtils.isNotBlank(message)) {
				result.setMessage(message);
			}
			return ResponseEntity.status(status).headers(headers).body(result);
		}
		if (StringUtils.isNotBlank(view)) {
			return new ModelAndView(view);
		} else if (code != null) {
			return new ModelAndView(getView(code), status);
		}
		return new ModelAndView(getView(status.value()), status);
	}

	/**
	 * Getting error view.
	 *
	 * @param statusCode the http status code.
	 * @return the error view.
	 */
	private String getView(int statusCode) {
		try {
			return switch (HttpStatus.valueOf(statusCode)) {
				case BAD_REQUEST -> this.badRequestPage;
				case UNAUTHORIZED -> this.unauthorizedPage;
				case PAYMENT_REQUIRED -> this.paymentRequiredViewPage;
				case FORBIDDEN -> this.forbiddenPage;
				case NOT_FOUND -> this.notFoundPage;
				case METHOD_NOT_ALLOWED -> this.methodNotAllowedPage;
				default -> this.defaultErrorPage;
			};
		} catch (Exception e) {
			return defaultErrorPage;
		}
	}

}
