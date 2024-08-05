package br.com.fiap.tech_challenge.adapters.driven.infra.entities;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductProductEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product")
@SQLRestriction("status <> 'INACTIVE'")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;

	private String name;

	@Enumerated(EnumType.STRING)
	private ProductCategoryEnum category;

	private BigDecimal price;

	private String description;

	@Enumerated(EnumType.STRING)
	private ProductProductEnum status = ProductProductEnum.ACTIVE;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public ProductEntity() {
	}

	public ProductEntity(UUID id, String name, ProductCategoryEnum category, BigDecimal price, String description) {
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

	public Product toProduct() {
		return new Product(id, name, category, price, description, status, createdAt);
	}

	public ProductEntity update(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.category = product.getCategory();
		this.price = product.getPrice();
		this.description = product.getDescription();
		this.status = product.getStatus();
		this.createdAt = product.getCreatedAt();
		return this;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ProductCategoryEnum getCategory() {
		return category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public ProductProductEnum getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setStatus(ProductProductEnum status) {
		this.status = status;
	}

}