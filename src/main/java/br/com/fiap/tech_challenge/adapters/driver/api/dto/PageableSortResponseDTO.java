package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.order.PageableSortOrder;
import br.com.fiap.tech_challenge.core.domain.models.product.PageableSortProduct;
import io.swagger.v3.oas.annotations.media.Schema;

public record PageableSortResponseDTO(@Schema(example = "true") Boolean empty,
		@Schema(example = "false") Boolean sorted, @Schema(example = "true") Boolean unsorted) {
	public PageableSortResponseDTO(PageableSortOrder sort) {
		this(sort.getEmpty(), sort.getSorted(), sort.getUnsorted());
	}

	public PageableSortResponseDTO(PageableSortProduct sort) {
		this(sort.getEmpty(), sort.getSorted(), sort.getUnsorted());
	}
}