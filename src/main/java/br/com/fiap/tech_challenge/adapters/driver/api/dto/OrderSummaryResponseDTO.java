package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.order.Order;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record OrderSummaryResponseDTO(@Schema(example = "1") Integer sequence,
		@Schema(example = "Charles") String customerName,
		@Schema(example = "2024-08-03T01:15:58Z") LocalDateTime createdAt,
		@Schema(example = "2024-08-03T01:17:58Z") LocalDateTime updatedAt) {
	public OrderSummaryResponseDTO(Order order) {
		this(order.getSequence(), order.getCustomer() != null ? order.getCustomer().getName() : null,
				order.getCreatedAt(), order.getUpdatedAt());
	}
}
