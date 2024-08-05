package br.com.fiap.tech_challenge.core.domain.models;

import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductProductEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Product {

	private UUID id;

	private String name;

	@Enumerated(EnumType.STRING)
	private ProductCategoryEnum category;

	private BigDecimal price;

	private String description;

	@Enumerated(EnumType.STRING)
	private ProductProductEnum status;

	private LocalDateTime createdAt;

	public Product() {
	}

	public Product(UUID id, String name, ProductCategoryEnum category, BigDecimal price, String description,
			ProductProductEnum status, LocalDateTime createdAt) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
	}

	public Product(String name, ProductCategoryEnum category, BigDecimal price, String description) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
	}

	public Product update(Product product) {
		this.name = product.getName();
		this.category = product.getCategory();
		this.price = product.getPrice();
		this.description = product.getDescription();
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