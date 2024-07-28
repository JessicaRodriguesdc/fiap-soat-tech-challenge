package br.com.fiap.tech_challenge.adapters.driven.infra.entities;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "customer_id")
    private UUID customerId;

    private BigDecimal amount;

    private Integer sequence;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public OrderEntity() {}

    public OrderEntity(
            UUID id,
            UUID customerId,
            BigDecimal amount,
            Integer sequence,
            OrderStatusEnum status,
            String paymentId,
            Boolean isPaid
    ) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.sequence = sequence;
        this.status = status;
        this.paymentId = paymentId;
        this.isPaid = isPaid;
    }

    public OrderEntity(Order order) {
        this.id = order.getId();
        this.customerId = order.getCustomerId();
        this.amount = order.getAmount();
        this.sequence = order.getSequence();
        this.status = order.getStatus();
        this.paymentId = order.getPaymentId();
        this.isPaid = order.getIsPaid();
    }

    public Order toOrder() {
        return new Order(id, customerId, amount, sequence, status, paymentId, isPaid);
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
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

    public String getPaymentId() {
        return paymentId;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }
}
