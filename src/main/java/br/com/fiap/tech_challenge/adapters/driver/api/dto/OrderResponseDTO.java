package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        BigDecimal amount,
        @JsonProperty("customer_id")
        UUID customerId,
        Integer sequence,
        OrderStatusEnum status,
        @JsonProperty("is_paid")
        Boolean isPaid,
        @JsonProperty("payment_id")
        String paymentId,
        @JsonProperty("created_at")
        LocalDateTime createdAt,
        @JsonProperty("updated_at")
        LocalDateTime updatedAt

) {
    public OrderResponseDTO(Order order) {
        this(
                order.getId(),
                order.getAmount(),
                order.getCustomerId(),
                order.getSequence(),
                order.getStatus(),
                order.isPaid(),
                order.getPaymentId(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
