package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.adapters.driver.api.validator.ValidUUID;
import jakarta.validation.constraints.NotBlank;

public record FakeCheckoutRequestDTO(
        @ValidUUID String orderId,
        @NotBlank String qrCode
) {
}
