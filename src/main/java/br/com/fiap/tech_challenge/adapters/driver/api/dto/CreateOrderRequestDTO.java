package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequestDTO(
        UUID customerId,
        @NotEmpty List<OrderProducts> products) {

    public record OrderProducts(
            @NotBlank UUID id,
            String observation) {

    }
}
