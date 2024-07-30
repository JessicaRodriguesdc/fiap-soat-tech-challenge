package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        BigDecimal amount,
        UUID customerId,
        Integer sequence,
        OrderStatus status,
        Boolean isPaid,
        String paymentId,
        LocalDateTime createdAt,
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
