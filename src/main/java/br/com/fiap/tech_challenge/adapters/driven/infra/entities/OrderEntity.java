package br.com.fiap.tech_challenge.adapters.driven.infra.entities;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.OrderProduct;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Integer sequence;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Column(nullable = false)
    private boolean isPaid;

    @Column(nullable = false)
    private String paymentId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private UUID customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProductEntity> products;

    public OrderEntity() {}

    public OrderEntity(
            UUID id,
            BigDecimal amount,
            Integer sequence,
            OrderStatusEnum status,
            Boolean isPaid,
            String paymentId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            UUID customerId,
            List<OrderProductEntity> products
    ) {
        this.id = id;
        this.amount = amount;
        this.sequence = sequence;
        this.status = status;
        this.isPaid = isPaid;
        this.paymentId = paymentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.customerId = customerId;
        this.products = products;
    }

    public OrderEntity(Order order) {
        this.id = order.getId();
        this.amount = order.getAmount();
        this.sequence = order.getSequence();
        this.status = order.getStatus();
        this.isPaid = order.isPaid();
        this.customerId = order.getCustomerId();
        this.paymentId = order.getPaymentId();

        this.products = order.getProducts().stream().map(
                orderProduct -> new OrderProductEntity(this, orderProduct)
        ).toList();
    }

    public Order toOrder() {
        List<OrderProduct> orderProducts =
                products.stream().map(
                        orderProductEntity -> orderProductEntity.toOrderProduct(id)
                ).toList();

        return new Order(
                id,
                amount,
                sequence,
                status,
                isPaid,
                orderProducts,
                customerId,
                paymentId,
                createdAt,
                updatedAt
        );
    }
}
