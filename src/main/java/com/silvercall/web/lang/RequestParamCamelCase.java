package com.silvercall.web.lang;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The request parameter mapping annotation (UNDERSCORE or HYPHEN to CAMEL)
 *
 * <pre>
 * <b>Example #1</b>
 * <code>
 * &#64;RequestParamCamelCase("param_name")
 * private String paramName;
 * </code>
 * </pre>
 *
 * <pre>
 * <b>Example #2</b>
 * <code>
 * &#64;RequestParamCamelCase("param-name")
 * private String paramName;
 * </code>
 * </pre>
 *
 * @version 1.0.1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RequestParamCamelCase {

	/**
	 * The name of the request parameter to bind to.
	 *
	 * @return the string name
	 */
	String[] name() default "";

}
