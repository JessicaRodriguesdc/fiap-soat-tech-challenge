package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.Customer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record CustomerResponseDTO(@Schema(example = "ab69e046-fb5a-4a79-98d6-363efdf20e11") UUID id,
		@Schema(example = "So And So") String name, @Schema(example = "11122233344") String document,
		@Schema(example = "email@email.com") String email) {
	public CustomerResponseDTO(Customer customer) {
		this(customer.getId(), customer.getName(), customer.getDocument(), customer.getEmail());
	}
}
