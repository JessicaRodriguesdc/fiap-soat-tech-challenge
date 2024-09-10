package br.com.fiap.tech_challenge.application.usecase.order.dto;

import java.util.UUID;
import java.util.List;

public record CreateOrderDTO(UUID customerId, List<OrderProducts> products) {
	public record OrderProducts(UUID id, String observation) {
	}
}