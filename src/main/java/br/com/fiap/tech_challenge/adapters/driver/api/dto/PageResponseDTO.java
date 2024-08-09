package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.DomainPage;
import io.swagger.v3.oas.annotations.media.Schema;

public record PageResponseDTO(@Schema(example = "1") Long totalPages, @Schema(example = "1") Long totalElements,
		@Schema(example = "10") Long size, @Schema(example = "0") Long number, @Schema(example = "true") Boolean first,
		@Schema(example = "true") Boolean last, @Schema(example = "1") Long numberOfElements,
		@Schema(example = "false") Boolean empty) {
	public PageResponseDTO(DomainPage page) {
		this(page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber(), page.getFirst(),
				page.getLast(), page.getNumberOfElements(), page.getEmpty());
	}
}
