package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.adapters.driver.api.validator.ValidUUID;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FakeCheckoutRequestDTO(
		@Schema(example = "ab69e046-fb5a-4a79-98d6-363efdf20e11") @ValidUUID @NotNull String orderId,
		@Schema(example = "00020126580014br.gov.bcb.pix0136123e4567-e12b-12d1-a456-426655440000 5204000053039865802BR5913Fulano de Tal6008BRASILIA62070503***63041D3D") @NotBlank String qrCode) {
}
