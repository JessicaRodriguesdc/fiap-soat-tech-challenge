package br.com.fiap.tech_challenge.core.domain.models;

import java.math.BigDecimal;
import java.util.UUID;

public class Order {

    private UUID id;

    private UUID customerId;

    private BigDecimal amount;

    private Integer sequence;

    private String status;

    private String paymentId;

    private Boolean isPaid;

    public Order(
            UUID id,
            UUID customerId,
            BigDecimal amount,
            Integer sequence,
            String status,
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

    public Order(
            UUID customerId,
            BigDecimal amount,
            Integer sequence,
            String status,
            String paymentId,
            Boolean isPaid
    ) {
        this.customerId = customerId;
        this.amount = amount;
        this.sequence = sequence;
        this.status = status;
        this.paymentId = paymentId;
        this.isPaid = isPaid;
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

    public String getStatus() {
        return status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }
}
