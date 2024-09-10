package br.com.fiap.tech_challenge.infra.entrypoint.controller.dto;

import br.com.fiap.tech_challenge.domain.models.Order;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponseDTO(@Schema(example = "ab69e046-fb5a-4a79-98d6-363efdf20e11") UUID id,
		@Schema(example = "1") Integer sequence, @Schema(example = "Charles") String customerName,
		@Schema(example = "2024-08-03T01:15:58Z") LocalDateTime createdAt,
		@Schema(example = "2024-08-03T01:17:58Z") LocalDateTime updatedAt) {
	public OrderResponseDTO(Order order) {
		this(order.getId(), order.getSequence(), order.getCustomer() != null ? order.getCustomer().getName() : null,
				order.getCreatedAt(), order.getUpdatedAt());
	}
}