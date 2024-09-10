package br.com.fiap.tech_challenge.infra.entrypoint.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record CreateOrderResponseDTO(@Schema(example = "ab69e046-fb5a-4a79-98d6-363efdf20e11") UUID orderId, @Schema(
		example = "00020126580014br.gov.bcb.pix0136123e4567-e12b-12d1-a456-426655440000 5204000053039865802BR5913Fulano de Tal6008BRASILIA62070503***63041D3D") String qrCode) {
}
