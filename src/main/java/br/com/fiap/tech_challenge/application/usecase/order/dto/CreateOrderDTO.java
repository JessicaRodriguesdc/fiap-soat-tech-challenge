package br.com.fiap.tech_challenge.application.usecase.order.dto;

import java.util.List;
import java.util.UUID;

public record CreateOrderDTO(UUID customerId, List<OrderProducts> products) {
	public record OrderProducts(UUID id, String observation) {
	}
}