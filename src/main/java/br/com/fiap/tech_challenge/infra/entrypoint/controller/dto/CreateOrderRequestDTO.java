package br.com.fiap.tech_challenge.infra.entrypoint.controller.dto;

import br.com.fiap.tech_challenge.infra.entrypoint.controller.validator.NullOrValidUUID;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.validator.ValidUUID;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequestDTO(
		@Schema(example = "ab69e046-fb5a-4a79-98d6-363efdf20e11") @NullOrValidUUID String customerId,
		@NotEmpty @Valid List<OrderProducts> products) {

	public record OrderProducts(

			@Schema(example = "ab69e046-fb5a-4a79-98d6-363efdf20e11") @ValidUUID @NotNull String id,
			@Schema(example = "Without Onions please") String observation) {

	}
}
