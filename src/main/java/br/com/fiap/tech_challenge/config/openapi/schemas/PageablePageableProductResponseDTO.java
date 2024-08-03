package br.com.fiap.tech_challenge.config.openapi.schemas;

import io.swagger.v3.oas.annotations.media.Schema;

public record PageablePageableProductResponseDTO (
        @Schema(example = "0") Long pageNumber,
        @Schema(example = "10") Long pageSize,
        PageableSortProductResponseDTO sort,
        @Schema(example = "0") Long offset,
        @Schema(example = "true") Boolean paged,
        @Schema(example = "false") Boolean unpaged
){}
