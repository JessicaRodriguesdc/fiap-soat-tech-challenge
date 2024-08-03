package br.com.fiap.tech_challenge.config.openapi.schemas;

import io.swagger.v3.oas.annotations.media.Schema;

public record PageableSortProductResponseDTO (
        @Schema(example = "true") Boolean empty,
        @Schema(example = "false") Boolean sorted,
        @Schema(example = "true") Boolean unsorted
){}