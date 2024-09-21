package br.com.fiap.tech_challenge.infra.entrypoint.controller.dto;

import br.com.fiap.tech_challenge.domain.models.Order;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderWorkItemsResponseDTO(List<OrderWorkItemDto> ready, List<OrderWorkItemDto> preparing,
		List<OrderWorkItemDto> received) {
	public OrderWorkItemsResponseDTO(List<List<Order>> workItems) {
		this(workItems.get(2).stream().map(OrderWorkItemDto::new).toList(),
				workItems.get(1).stream().map(OrderWorkItemDto::new).toList(),
				workItems.get(0).stream().map(OrderWorkItemDto::new).toList());
	}
}
