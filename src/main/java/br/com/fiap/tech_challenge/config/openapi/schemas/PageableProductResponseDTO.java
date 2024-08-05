package br.com.fiap.tech_challenge.config.openapi.schemas;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record PageableProductResponseDTO(@Schema(example = "1") Long totalPages,
		@Schema(example = "1") Long totalElements, @Schema(example = "10") Long size, List<ProductResponseDTO> content,
		@Schema(example = "0") Long number, PageableSortResponseDTO sort, @Schema(example = "true") Boolean first,
		@Schema(example = "true") Boolean last, @Schema(example = "1") Long numberOfElements,
		PageablePageableResponseDTO pageable, @Schema(example = "false") Boolean empty) {
}
