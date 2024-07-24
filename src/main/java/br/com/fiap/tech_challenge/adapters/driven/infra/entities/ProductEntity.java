package br.com.fiap.tech_challenge.adapters.driven.infra.entities;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.StatusProductEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryProductEnum category;

    private Double price;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusProductEnum status = StatusProductEnum.ACTIVE;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ProductEntity(UUID id, String name, CategoryProductEnum category, Double price, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public ProductEntity(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }

    public Product toProduct(){
        return new Product(id, name, category, price, description, status);
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

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public StatusProductEnum getStatus() {
        return status;
    }
}