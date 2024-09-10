package br.com.fiap.tech_challenge.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderProduct {

	private final UUID id;

	private final BigDecimal price;

	private final String customization;

	private final UUID productId;

	private final UUID orderId;

	private final LocalDateTime createdAt;

	public OrderProduct(UUID id, BigDecimal price, String customization, UUID productId, UUID orderId,
			LocalDateTime createdAt) {
		this.id = id;
		this.price = price;
		this.customization = customization;
		this.productId = productId;
		this.orderId = orderId;
		this.createdAt = createdAt;
	}

	public static OrderProduct create(BigDecimal price, String customization, UUID productId) {
		return new OrderProduct(null, price, customization, productId, null, null);
	}

	public UUID getId() {
		return id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getCustomization() {
		return customization;
	}

	public UUID getProductId() {
		return productId;
	}

}