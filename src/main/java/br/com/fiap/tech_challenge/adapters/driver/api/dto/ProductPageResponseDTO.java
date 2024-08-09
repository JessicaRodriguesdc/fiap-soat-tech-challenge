package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.ProductPage;

import java.util.List;

public record ProductPageResponseDTO(List<ProductResponseDTO> content, PageResponseDTO page) {
    public ProductPageResponseDTO(ProductPage productPage) {
        this(productPage.getContent().stream().map(ProductResponseDTO::new).toList(),
                new PageResponseDTO(productPage.getPage()));
    }
}
