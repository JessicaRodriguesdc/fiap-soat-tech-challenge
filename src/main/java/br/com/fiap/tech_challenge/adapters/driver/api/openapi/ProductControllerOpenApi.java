package br.com.fiap.tech_challenge.adapters.driver.api.openapi;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.handler.ErrorsValidateData;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "Product")
public interface ProductControllerOpenApi {

	@Operation(summary = "Register a Product")
	@ApiResponse(responseCode = "201", description = "Created Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProductResponseDTO")))
	@ApiResponse(responseCode = "400", description = "Bad Request Response",
			content = @Content(mediaType = "application/json",
					array = @ArraySchema(schema = @Schema(implementation = ErrorsValidateData.class))))
	@ApiResponse(responseCode = "500", description = "Internal Server Error Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	ResponseEntity<ProductResponseDTO> create(ProductRequestDTO productDTO);

	@Operation(summary = "Find a Product By Category")
	@ApiResponse(responseCode = "200", description = "Ok Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "PageableProductResponseDTO")))
	@ApiResponse(responseCode = "404", description = "Not Found Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	@ApiResponse(responseCode = "500", description = "Internal Server Error Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	ResponseEntity<Page<ProductResponseDTO>> getProductsByCategory(ProductCategoryEnum category, int page, int size);

	@Operation(summary = "Update a Product By ID")
	@ApiResponse(responseCode = "200", description = "Ok Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProductResponseDTO")))
	@ApiResponse(responseCode = "404", description = "Not Found Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	@ApiResponse(responseCode = "500", description = "Internal Server Error Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	ResponseEntity<ProductResponseDTO> update(final UUID id, ProductRequestDTO productDTO);

	@Operation(summary = "Delete a Product By ID")
	@ApiResponse(responseCode = "204", description = "No Content Response")
	@ApiResponse(responseCode = "404", description = "Not Found Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	@ApiResponse(responseCode = "500", description = "Internal Server Error Response",
			content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
	ResponseEntity<?> deleteProductById(UUID id);

}
