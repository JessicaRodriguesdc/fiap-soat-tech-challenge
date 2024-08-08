package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.product.PageableProduct;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record PageableProductResponseDTO(@Schema(example = "1") Long totalPages,
		@Schema(example = "1") Long totalElements, @Schema(example = "10") Long size, List<ProductResponseDTO> content,
		@Schema(example = "0") Long number, PageableSortResponseDTO sort, @Schema(example = "true") Boolean first,
		@Schema(example = "true") Boolean last, @Schema(example = "1") Long numberOfElements,
		PageablePageableResponseDTO pageable, @Schema(example = "false") Boolean empty) {
	public PageableProductResponseDTO(PageableProduct page) {
		this(page.getTotalPages(), page.getTotalElements(), page.getSize(),
				page.getContent().stream().map(ProductResponseDTO::new).toList(), page.getNumber(),
				new PageableSortResponseDTO(page.getSort()), page.getFirst(), page.getLast(),
				page.getNumberOfElements(), new PageablePageableResponseDTO(page.getPageable()), page.getEmpty());
	}
}
