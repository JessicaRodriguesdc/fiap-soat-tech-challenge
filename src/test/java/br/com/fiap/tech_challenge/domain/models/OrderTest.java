package br.com.fiap.tech_challenge.domain.models;

import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

	private UUID id;

	private String paymentId;

	private BigDecimal amount;

	private List<OrderProduct> products;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private Customer customer;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
	@DisplayName("Should return Order attributes as the object was created without ID")
	public void shouldInstantiateOrder() {
		Integer sequence = 2;
		OrderStatusEnum status = OrderStatusEnum.RECEIVED;
		boolean isPaid = true;

		Order order = new Order(id, amount, sequence, status, isPaid, products, customer, paymentId, createdAt,
				updatedAt);

		assertEquals(id, order.getId());
		assertEquals(amount, order.getAmount());
		assertEquals(sequence, order.getSequence());
		assertEquals(status, order.getStatus());
		assertTrue(order.isPaid());
		assertEquals(products, order.getProducts());
		assertEquals(customer, order.getCustomer());
		assertEquals(createdAt, order.getCreatedAt());
		assertEquals(updatedAt, order.getUpdatedAt());
	}

	@Test
	@DisplayName("Should return Order attributes as the object was created")
	public void shouldCreateOrder() {
		Order order = Order.create(amount, products, customer, paymentId);

		assertNull(order.getId());
		assertEquals(amount, order.getAmount());
		assertNull(order.getSequence());
		assertEquals(OrderStatusEnum.RECEIVED, order.getStatus());
		assertFalse(order.isPaid());
		assertEquals(products, order.getProducts());
		assertNull(order.getCreatedAt());
		assertNull(order.getUpdatedAt());
	}

	private void buildArranges() {
		this.id = UUID.randomUUID();
		this.paymentId = "paymentIdMock";
		this.amount = new BigDecimal("200.00");
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.customer = new Customer(id, "Walter White", "31739380037", "heisenberg@gmail.com");
		OrderProduct orderProduct1 = new OrderProduct(UUID.randomUUID(), new BigDecimal("100.00"), "Customization 1",
				UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
		OrderProduct orderProduct2 = new OrderProduct(UUID.randomUUID(), new BigDecimal("100.00"), "Customization 2",
				UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
		this.products = List.of(orderProduct1, orderProduct2);
	}

}