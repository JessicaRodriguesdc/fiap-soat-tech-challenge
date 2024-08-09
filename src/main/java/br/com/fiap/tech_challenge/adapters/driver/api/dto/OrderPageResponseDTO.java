package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.pageable.CustomPageable;

import java.util.List;

public record OrderPageResponseDTO(List<OrderResponseDTO> content, PageResponseDTO page) {
	public OrderPageResponseDTO(CustomPageable<Order> orderPage) {
		this(orderPage.content().stream().map(OrderResponseDTO::new).toList(), new PageResponseDTO(orderPage.page()));
	}

}
