package br.com.fiap.tech_challenge.infra.entrypoint.controller.dto;

import br.com.fiap.tech_challenge.infra.entrypoint.controller.validator.ValidUUID;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record FakeCheckoutRequestDTO(
		@Schema(example = "ab69e046-fb5a-4a79-98d6-363efdf20e11") @ValidUUID @NotNull String orderId) {
}
