package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.Order;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        UUID customer_id,
        BigDecimal amount,
        Integer sequence,
        String status,
        String payment_id,
        Boolean is_paid
) {
    public OrderResponseDTO(Order order) {
        this(
                order.getId(),
                order.getCustomerId(),
                order.getAmount(),
                order.getSequence(),
                order.getStatus(),
                order.getPaymentId(),
                order.getIsPaid()
        );
    }
}
