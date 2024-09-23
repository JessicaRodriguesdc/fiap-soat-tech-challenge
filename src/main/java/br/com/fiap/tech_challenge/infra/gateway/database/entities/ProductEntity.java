package br.com.fiap.tech_challenge.infra.gateway.database.entities;

import br.com.fiap.tech_challenge.domain.models.Product;
import br.com.fiap.tech_challenge.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.domain.models.enums.ProductStatusEnum;
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
	private ProductStatusEnum status = ProductStatusEnum.ACTIVE;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public ProductEntity() {
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

	public ProductStatusEnum getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setStatus(ProductStatusEnum status) {
		this.status = status;
	}

}