package br.com.fiap.tech_challenge.core.domain.models;

import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.StatusProductEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryProductEnum category;

    private BigDecimal price;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusProductEnum status;

    public Product(UUID id, String name, CategoryProductEnum category, BigDecimal price, String description,StatusProductEnum status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    public Product(String name, CategoryProductEnum category, BigDecimal price, String description) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryProductEnum getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public StatusProductEnum getStatus() {
        return status;
    }
}