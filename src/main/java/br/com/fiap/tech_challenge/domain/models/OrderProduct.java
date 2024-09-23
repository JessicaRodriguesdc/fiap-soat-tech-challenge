package br.com.fiap.tech_challenge.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderProduct {

	private final UUID id;

	private final BigDecimal price;

	private final String customization;

	private final UUID productId;

	private String productName;

	private final UUID orderId;

	private final LocalDateTime createdAt;

	public OrderProduct(UUID id, BigDecimal price, String customization, UUID productId, String productName,
			UUID orderId, LocalDateTime createdAt) {
		this.id = id;
		this.price = price;
		this.customization = customization;
		this.productId = productId;
		this.orderId = orderId;
		this.createdAt = createdAt;
		this.productName = productName;
	}

	public static OrderProduct create(BigDecimal price, String customization, UUID productId) {
		return new OrderProduct(null, price, customization, productId, null, null, null);
	}

	public UUID getId() {
		return this.id;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public String getCustomization() {
		return this.customization;
	}

	public UUID getProductId() {
		return this.productId;
	}

	public String getProductName() {
		return this.productName;
	}

}