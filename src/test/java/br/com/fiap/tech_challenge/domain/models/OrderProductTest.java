package br.com.fiap.tech_challenge.domain.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderProductTest {

	private UUID id;

	private UUID productId;

	private UUID orderId;

	private String customization;

	private String productName;

	private LocalDateTime createdAt;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
	@DisplayName("Should return OrderProduct attributes as the object was created without ID")

	public void shouldInstantiateOrderProductConstructor() {
		BigDecimal price = BigDecimal.valueOf(100.00);
		OrderProduct orderProduct = new OrderProduct(id, price, customization, productId, productName, orderId, createdAt);

		assertEquals(id, orderProduct.getId());
		assertEquals(price, orderProduct.getPrice());
		assertEquals(customization, orderProduct.getCustomization());
		assertEquals(productId, orderProduct.getProductId());
	}

	@Test
	@DisplayName("Should return OrderProducts attributes as the object was created")
	public void shouldCreateOrderProduct() {
		BigDecimal price = BigDecimal.valueOf(99.99);

		OrderProduct orderProduct = OrderProduct.create(price, customization, productId);

		assertNull(orderProduct.getId());
		assertEquals(price, orderProduct.getPrice());
		assertEquals(customization, orderProduct.getCustomization());
		assertEquals(productId, orderProduct.getProductId());
	}

	private void buildArranges() {
		this.id = UUID.randomUUID();
		this.productId = UUID.randomUUID();
		this.orderId = UUID.randomUUID();
		this.customization = "Extra cheese";
		this.productName = "X-Burger";
		this.createdAt = LocalDateTime.now();
	}

}
