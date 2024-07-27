package br.com.fiap.tech_challenge.adapters.driver.api.openapi;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.handler.ErrorsValidateData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Product")
public interface ProductControllerOpenApi {

    @Operation(summary = "Register a Product")
    @ApiResponse(responseCode = "201", description = "Created Response", content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProductResponseDTO")))
    @ApiResponse(responseCode = "400", description = "Bad Request Response", content =
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ErrorsValidateData.class))))
    @ApiResponse(responseCode = "500", description = "Internal Server Error Response", content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
    ResponseEntity<ProductResponseDTO> create(ProductRequestDTO productDTO);
}
