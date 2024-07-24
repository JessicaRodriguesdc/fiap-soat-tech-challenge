package br.com.fiap.tech_challenge.adapters.driver.api.controller.dto;

import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDTO(@NotBlank String name,
                                @NotNull CategoryProductEnum category,
                                @NotNull BigDecimal price,
                                @NotBlank String description) {
}