package com.silvercall.web.rest.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import com.silvercall.dto.request.ExampleRequest;
import com.silvercall.dto.response.DefaultResponse;
import com.silvercall.web.lang.AbstractController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The spring mvc example controller
 *
 * @version 1.0.1
 */
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Example", description = "The spring mvc example controller")
@RestController
public class ExampleController extends AbstractController {

	private final MessageSourceAccessor messageSource;

	/**
	 * Test GET
	 *
	 * @param ignoredRequest the http servlet request.
	 * @param ignoredResponse the http servlet response.
	 * @param params the example request parameter.
	 * @param errors the error binding result
	 * @return the default response entity.
	 */
	// @formatter:off
	@Operation(
			summary = "Test GET",
			description = "Test GET Example",
			parameters = {
					@Parameter(in = ParameterIn.QUERY, name = "example_id", description = "Example ID"),
					@Parameter(in = ParameterIn.QUERY, name = "example_name", description = "Example name")
			}, responses = {
					@ApiResponse(
							responseCode = "200",
							description = "OK",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									examples = @ExampleObject(value = """
{
  "code": 200,
  "message": "OK"
}"""
									)
							)
					)
			}
	)
	// @formatter:on
	@GetMapping(path = "test/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DefaultResponse> getExample(HttpServletRequest ignoredRequest,
			HttpServletResponse ignoredResponse, @Parameter(hidden = true) @Valid ExampleRequest params,
			BindingResult errors) {
		checkForErrors(messageSource, params.getClass(), errors);
		log.debug("Example request : {}", params);
		// @formatter:off
		return ResponseEntity.ok().body(
				DefaultResponse.builder()
						.code(200)
						.message(message(messageSource, 200)).build()
		);
		// @formatter:on
	}

	/**
	 * Test GET2
	 *
	 * @return the default response entity.
	 */
	// @formatter:off
	@Operation(
			summary = "Test2 GET",
			description = "Test2 GET Example",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "OK",
							content = @Content(
									mediaType = MediaType.APPLICATION_JSON_VALUE,
									examples = @ExampleObject(value = """
{
  "code": 200,
  "message": "OK"
}"""
									)
							)
					)
			}
	)
	// @formatter:on
	@GetMapping(path = "test/get2", produces = MediaType.APPLICATION_JSON_VALUE)
	public DefaultResponse getExample2() {
		// @formatter:off
		log.debug("Test list : {}", List.of(
				"010-1234-5678",
				"01012345678",
				"02-1234-5678",
				"0212345678",
				"1980-11-11",
				"19801111",
				"801111-1381234",
				"sky01126@gmail.com",
				"ky.son@kt.com"
		));
		return DefaultResponse.builder()
				.code(200)
				.message(message(messageSource, 200))
				.build();
		// @formatter:on
	}

}
