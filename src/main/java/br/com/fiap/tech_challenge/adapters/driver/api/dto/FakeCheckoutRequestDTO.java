package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.adapters.driver.api.validator.ValidUUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record FakeCheckoutRequestDTO(
        @JsonProperty("order_id")
        @ValidUUID String orderId,
        @JsonProperty("qr_code")
        @NotBlank String qrCode
) {
}
