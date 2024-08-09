package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.OrderPage;

import java.util.List;

public record OrderPageResponseDTO(List<OrderResponseDTO> content, PageResponseDTO page) {
	public OrderPageResponseDTO(OrderPage orderPage) {
		this(orderPage.getContent().stream().map(OrderResponseDTO::new).toList(),
				new PageResponseDTO(orderPage.getPage()));
	}

}
