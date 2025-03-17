package com.silvercall.config;

import org.springdoc.core.customizers.OpenApiCustomizer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import com.silvercall.dto.response.DefaultResponse;
import com.silvercall.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * The spring doc ui configuration.
 * <br />
 * <a href="https://springdoc.org/#migrating-from-springfox">migrating-from-springfox</a>
 * <a href="https://springdoc.org/v2/#Introduction">springdoc-openapi v2</a>
 *
 * @version 1.0.1
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class SpringdocConfiguration {

	private final MessageSourceAccessor messageSource;

	@Value("${spring.application.name:NULL}")
	private String title;

	@Value("${info.app.version:NULL}")
	private String version;

	@Value("${info.app.description:NULL}")
	private String description;

	@Bean
	public OpenAPI openAPI() {
		// @formatter:off
		return new OpenAPI()
				.info(new Info()
						.title(this.title)
						.version(this.version)
						.description(this.description)
						.license(new License()
								.name("Apache License Version 2.0")
								.url("https://www.apache.org/licenses/LICENSE-2.0")
						)
				);
		// @formatter:on
	}

	@Bean
	public OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
		return this::customise;
	}

	private void customise(OpenAPI openApi) {
		openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
			// @formatter:off
			operation.getResponses().addApiResponse(ApiResponses.DEFAULT,
					defaultApiResponse(
							null,
							201, 202, 204,
							400, 401, 402, 403, 404, 405, 406, 409, 412,
							500, 503, 504
					)
			);
			// @formatter:on
		}));
	}

	private ApiResponse defaultApiResponse(Schema<?> schema, int... responseCode) {
		// @formatter:off
		MediaType mediaType = new MediaType().schema(schema);
		for (int code : responseCode) {
			mediaType.addExamples(String.valueOf(code), new Example().value(
							DefaultResponse.builder()
									.code(code)
									.message(MessageUtils.response(this.messageSource, code))
									.build()
					)
			);
		}

		return new ApiResponse()
				.description("Default Response Message.")
				.content(new Content()
						.addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, mediaType)
				);
		// @formatter:on
	}

}
