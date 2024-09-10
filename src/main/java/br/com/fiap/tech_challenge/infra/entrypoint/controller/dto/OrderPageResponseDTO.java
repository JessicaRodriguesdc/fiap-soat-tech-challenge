package br.com.fiap.tech_challenge.infra.entrypoint.controller.dto;

import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPageable;

import java.util.List;

public record OrderPageResponseDTO(List<OrderResponseDTO> content, PageResponseDTO page) {
	public OrderPageResponseDTO(CustomPageable<Order> orderPage) {
		this(orderPage.content().stream().map(OrderResponseDTO::new).toList(), new PageResponseDTO(orderPage.page()));
	}

}
