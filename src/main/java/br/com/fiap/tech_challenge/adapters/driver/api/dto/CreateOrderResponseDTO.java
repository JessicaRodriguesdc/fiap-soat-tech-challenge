package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import java.util.UUID;

public record CreateOrderResponseDTO(UUID orderId, String qrCode) {
}
