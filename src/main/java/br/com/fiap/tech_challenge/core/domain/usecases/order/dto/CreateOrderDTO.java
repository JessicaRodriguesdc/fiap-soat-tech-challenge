package br.com.fiap.tech_challenge.core.domain.usecases.order.dto;

import java.util.UUID;
import java.util.List;

public record CreateOrderDTO(UUID customerId, List<OrderProducts> products) {
    public record OrderProducts(UUID id, String observation) {
    }
}


