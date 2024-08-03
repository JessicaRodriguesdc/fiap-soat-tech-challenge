package br.com.fiap.tech_challenge.adapters.driver.api.openapi;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.handler.ErrorsValidateData;
import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;
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
    @ApiResponse(responseCode = "201", description = "Created Response", content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProductResponseDTO")))
    @ApiResponse(responseCode = "400", description = "Bad Request Response", content =
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ErrorsValidateData.class))))
    @ApiResponse(responseCode = "500", description = "Internal Server Error Response", content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
    ResponseEntity<ProductResponseDTO> create(ProductRequestDTO productDTO);


    ResponseEntity<Page<ProductResponseDTO>> getProductsByCategory(CategoryProductEnum category, int page, int size);



    ResponseEntity<ProductResponseDTO> update(final UUID id, ProductRequestDTO productDTO);


    ResponseEntity<?> deleteProductById(UUID id);
}
