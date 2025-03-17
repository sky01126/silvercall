package com.silvercall.config;

import java.util.List;

import com.silvercall.web.lang.RequestParamCamelCaseServletModelAttributeMethodProcessor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.LiteWebJarsResourceResolver;

/**
 * The web mvc configuration.
 *
 * @version 1.0.2
 * @see WebMvcConfigurer
 */
@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	// @formatter:off
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/",
			"classpath:/public/",
			"classpath:/resources/",
			"classpath:/static/"
	};
	// @formatter:on

	private final LocaleChangeInterceptor localeChangeInterceptor;

	public WebMvcConfiguration(LocaleChangeInterceptor localeChangeInterceptor) {
		this.localeChangeInterceptor = localeChangeInterceptor;
	}

	/**
	 * Add resolvers to support custom controller method argument types.
	 *
	 * @param argumentResolvers initially an empty list
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		log.debug("Add resolvers to support custom controller method argument types.");
		argumentResolvers.add(new RequestParamCamelCaseServletModelAttributeMethodProcessor());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
	}

	/**
	 * Add Spring MVC lifecycle interceptors for pre- and post-processing of
	 * controller method invocations and resource handler requests.
	 *
	 * @param registry the interceptor registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.debug("Add Spring MVC lifecycle interceptors.");
		registry.addInterceptor(this.localeChangeInterceptor);
	}

	/**
	 * Add handlers to serve static resources.
	 *
	 * @param registry the resource handler registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.debug("Add handlers to serve static resources");

		// @formatter:off
		registry.addResourceHandler("/**")
				.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
				// .setCachePeriod(31_536_000); // Cache 기간 설정

		// Register Webjars Resource
		// Webjars registration sample : ex. /webjars/jquery/jquery.min.js
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/")
				// .setCachePeriod(31_536_000)
				.resourceChain(true)
				.addResolver(new LiteWebJarsResourceResolver());
		// @formatter:on
	}

	/**
	 * Spring commons request logging filter.
	 *
	 * @return the commons request logging filter
	 */
	@Bean
	public FilterRegistrationBean<CommonsRequestLoggingFilter> filterRegistrationBean() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setIncludeHeaders(true);
		loggingFilter.setMaxPayloadLength(1024 * 1024);

		// FilterRegistrationBean로 특정 url에서만 filter 적용 가능 (registration.addUrlPatterns("/v1/api/*"))
		FilterRegistrationBean<CommonsRequestLoggingFilter> registration = new FilterRegistrationBean<>(loggingFilter);
		// registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registration;
	}

}
