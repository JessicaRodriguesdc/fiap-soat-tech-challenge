package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.adapters.driver.api.validator.ValidUUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FakeCheckoutRequestDTO(
        @ValidUUID @NotNull String orderId,
        @NotBlank String qrCode
) {
}
