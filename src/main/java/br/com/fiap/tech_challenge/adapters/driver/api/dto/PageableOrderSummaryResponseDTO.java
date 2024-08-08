package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.order.PageableOrder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record PageableOrderSummaryResponseDTO(@Schema(example = "1") Long totalPages,
		@Schema(example = "1") Long totalElements, @Schema(example = "10") Long size,
		List<OrderSummaryResponseDTO> content, @Schema(example = "0") Long number, PageableSortResponseDTO sort,
		@Schema(example = "true") Boolean first, @Schema(example = "true") Boolean last,
		@Schema(example = "1") Long numberOfElements, PageablePageableResponseDTO pageable,
		@Schema(example = "false") Boolean empty) {
	public PageableOrderSummaryResponseDTO(PageableOrder page) {
		this(page.getTotalPages(), page.getTotalElements(), page.getSize(),
				page.getContent().stream().map(OrderSummaryResponseDTO::new).toList(), page.getNumber(),
				new PageableSortResponseDTO(page.getSort()), page.getFirst(), page.getLast(),
				page.getNumberOfElements(), new PageablePageableResponseDTO(page.getPageable()), page.getEmpty());
	}
}
