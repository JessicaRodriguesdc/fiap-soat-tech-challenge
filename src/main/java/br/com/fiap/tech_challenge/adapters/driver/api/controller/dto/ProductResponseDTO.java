package br.com.fiap.tech_challenge.adapters.driver.api.controller.dto;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;

import java.util.UUID;

public record ProductResponseDTO(UUID id,
                                 String name,
                                 CategoryProductEnum category,
                                 Double price,
                                 String description) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getDescription());
    }

}