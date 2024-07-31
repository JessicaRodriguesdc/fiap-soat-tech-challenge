package br.com.fiap.tech_challenge.adapters.driver.api.dto;

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
    }
}