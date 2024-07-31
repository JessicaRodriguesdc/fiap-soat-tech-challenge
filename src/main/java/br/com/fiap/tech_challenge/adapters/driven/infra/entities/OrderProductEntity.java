package br.com.fiap.tech_challenge.adapters.driven.infra.entities;

import br.com.fiap.tech_challenge.core.domain.models.OrderProduct;
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

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    private UUID productId;

    public OrderProductEntity() {}

    public OrderProductEntity(OrderEntity order, OrderProduct orderProduct) {
        this.order = order;
        this.id = orderProduct.getId();
        this.price = orderProduct.getPrice();
        this.customization = orderProduct.getCustomization();
        this.productId = orderProduct.getProductId();
    }

    public OrderProduct toOrderProduct(UUID orderId) {
        return new OrderProduct(id, price, customization, productId, orderId, createdAt);
    }
}
