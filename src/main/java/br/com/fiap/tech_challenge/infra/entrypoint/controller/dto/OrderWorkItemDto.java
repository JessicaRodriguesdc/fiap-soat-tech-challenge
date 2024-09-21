package br.com.fiap.tech_challenge.infra.entrypoint.controller.dto;

import br.com.fiap.tech_challenge.domain.models.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderWorkItemDto(@Schema(example = "ab69e046-fb5a-4a79-98d6-363efdf20e11") UUID id,
							   @Schema(example = "1") Integer sequence,
							   @Schema(example = "Charles") String customerName,
							   List<OrderProductWorkItemDto> products,
							   @Schema(example = "2024-08-03T01:15:58Z") LocalDateTime createdAt,
							   @Schema(example = "2024-08-03T01:17:58Z") LocalDateTime updatedAt
							   ) {

	public OrderWorkItemDto(Order order) {
		this(order.getId(), order.getSequence(), order.getCustomer() != null ? order.getCustomer().getName() : null, !order.getProducts().isEmpty() ?
				order.getProducts().stream().map(OrderProductWorkItemDto::new).toList() : null, order.getCreatedAt(),
				order.getUpdatedAt());
	}
}
