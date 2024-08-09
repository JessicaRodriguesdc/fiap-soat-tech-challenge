package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.pageable.CustomPageable;

import java.util.List;

public record ProductPageResponseDTO(List<ProductResponseDTO> content, PageResponseDTO page) {
	public ProductPageResponseDTO(CustomPageable<Product> productPage) {
		this(productPage.content().stream().map(ProductResponseDTO::new).toList(),
				new PageResponseDTO(productPage.page()));
	}
}
