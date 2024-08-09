package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.product.PageablePageableProduct;
import io.swagger.v3.oas.annotations.media.Schema;

public record PageablePageableResponseDTO(@Schema(example = "0") Long pageNumber, @Schema(example = "10") Long pageSize,
		PageableSortResponseDTO sort, @Schema(example = "0") Long offset, @Schema(example = "true") Boolean paged,
		@Schema(example = "false") Boolean unpaged) {

	public PageablePageableResponseDTO(PageablePageableProduct pageable) {
		this(pageable.getPageNumber(), pageable.getPageSize(), new PageableSortResponseDTO(pageable.getSort()),
				pageable.getOffset(), pageable.getPaged(), pageable.getUnpaged());
	}
}
