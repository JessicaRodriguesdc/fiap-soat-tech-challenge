package br.com.fiap.tech_challenge.adapters.driver.api.controller.dto;

import br.com.fiap.tech_challenge.core.domain.models.Product;

import java.util.UUID;

public record ProductResponseDTO(UUID id,
                                 String name,
                                 String category,
                                 double price,
                                 String description,
                                 String status) {
    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getDescription(), product.getStatus());
    }
}
