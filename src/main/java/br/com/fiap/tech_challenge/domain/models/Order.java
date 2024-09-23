package br.com.fiap.tech_challenge.domain.models;

import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

	private final UUID id;

	private final BigDecimal amount;

	private final Integer sequence;

	private OrderStatusEnum status;

	private final boolean isPaid;

	private final String paymentId;

	private final String qr;

	private List<OrderProduct> products;

	private final Customer customer;

	private final LocalDateTime createdAt;

	private final LocalDateTime updatedAt;

	public static Order create(BigDecimal amount, List<OrderProduct> products, Customer customer, String paymentId,
			String qr) {
		return new Order(null, amount, null, OrderStatusEnum.RECEIVED, false, products, customer, paymentId, qr, null,
				null);
	}

	public Order(UUID id, BigDecimal amount, Integer sequence, OrderStatusEnum status, boolean isPaid,
			List<OrderProduct> products, Customer customer, String paymentId, String qr, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.amount = amount;
		this.sequence = sequence;
		this.status = status;
		this.isPaid = isPaid;
		this.products = products;
		this.customer = customer;
		this.paymentId = paymentId;
		this.qr = qr;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UUID getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Integer getSequence() {
		return sequence;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public List<OrderProduct> getProducts() {
		return products;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void removeProducts() {
		this.products = new ArrayList<>();
	}

	public String getQr() {
		return qr;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}

}
