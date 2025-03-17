package com.silvercall.dto.request;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import com.silvercall.web.lang.RequestParamCamelCase;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * The example request
 *
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@ToString
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class ExampleRequest implements Serializable {

	@Serial
	private static final long serialVersionUID = -7278111829925276379L;

	/**
	 * Example ID
	 */
	@Schema(name = "example_id")
	@NotEmpty(message = "{validation.exampleRequest.exampleId.NotEmpty.message}")
	@RequestParamCamelCase(name = { "example_id", "EXAMPLE_ID" })
	private String exampleId;

	/**
	 * Example name
	 */
	@Schema(name = "example_name")
	@NotEmpty(message = "{validation.exampleRequest.exampleName.NotEmpty.message}")
	@Size(min = 10, max = 32, message = "{validation.exampleRequest.exampleName.Size.message}")
	@RequestParamCamelCase(name = { "example_name", "EXAMPLE_NAME" })
	private String exampleName;

}
