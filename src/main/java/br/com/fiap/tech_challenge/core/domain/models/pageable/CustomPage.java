package br.com.fiap.tech_challenge.core.domain.models.pageable;

public record CustomPage(Long totalPages, Long totalElements, Long size, Long number, Boolean first, Boolean last,
		Long numberOfElements, Boolean empty) {

}
