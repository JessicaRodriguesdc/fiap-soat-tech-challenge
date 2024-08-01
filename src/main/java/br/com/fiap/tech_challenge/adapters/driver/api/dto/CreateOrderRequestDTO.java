package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.adapters.driver.api.validator.NullOrValidUUID;
import br.com.fiap.tech_challenge.adapters.driver.api.validator.ValidUUID;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequestDTO(
        @NullOrValidUUID
        String customerId,
        @NotEmpty
        @Valid
        List<OrderProducts> products) {

    public record OrderProducts(

            @ValidUUID
            @NotNull
            String id,
            String observation) {

    }
}
