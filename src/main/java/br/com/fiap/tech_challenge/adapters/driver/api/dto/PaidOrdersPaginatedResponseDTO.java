package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.Customer;
import br.com.fiap.tech_challenge.core.domain.models.Order;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public record PaidOrdersPaginatedResponseDTO(
        List<OrderSummary> content,
        Pageable pageable) {

    public record OrderSummary(
            Integer sequence,
            String customerName,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        public OrderSummary(Order order) {
            this(
                    order.getSequence(),
                    order.getCustomer() != null ? order.getCustomer().getName() : "",
                    order.getCreatedAt(),
                    order.getUpdatedAt()
            );
        }
    }
}