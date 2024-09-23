package br.com.fiap.tech_challenge.infra.gateway.database.entities;

import br.com.fiap.tech_challenge.domain.models.OrderProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_product")
public class OrderProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;

	@Column(nullable = false)
	private BigDecimal price;

	private String customization;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private OrderEntity order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	public OrderProductEntity() {
	}

	public OrderProductEntity(OrderProduct orderProduct, ProductEntity productEntity) {
		this.id = orderProduct.getId();
		this.price = orderProduct.getPrice();
		this.customization = orderProduct.getCustomization();
		this.product = productEntity;
	}

	public OrderProduct toOrderProduct(UUID orderId) {
		return new OrderProduct(id, price, customization, product.getId(), product.getName(), orderId, createdAt);
	}

	public void setOrder(OrderEntity orderEntity) {
		this.order = orderEntity;
	}
}
