package br.com.fiap.tech_challenge.infra.entrypoint.controller.openapi;

import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.CustomerRequestDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.CustomerResponseDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.handler.ErrorsValidateData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Customer")
public interface CustomerControllerOpenApi {

	@Operation(summary = "Find a Customer By Document")
	@ApiResponse(responseCode = "200", description = "Ok Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "CustomerResponseDTO")))
	@ApiResponse(responseCode = "404", description = "Not Found Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	@ApiResponse(responseCode = "500", description = "Internal Server Error Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	ResponseEntity<CustomerResponseDTO> findByDocument(@PathVariable() String document);

	@Operation(summary = "Register a Customer")
	@ApiResponse(responseCode = "201", description = "Created Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "CustomerResponseDTO")))
	@ApiResponse(responseCode = "400", description = "Bad Request Response",
			content = @Content(mediaType = "application/json",
					array = @ArraySchema(schema = @Schema(implementation = ErrorsValidateData.class))))
	@ApiResponse(responseCode = "500", description = "Internal Server Error Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	ResponseEntity<CustomerResponseDTO> create(
			@RequestBody(description = "Representation of a Customer", required = true) CustomerRequestDTO customerDTO);

}
