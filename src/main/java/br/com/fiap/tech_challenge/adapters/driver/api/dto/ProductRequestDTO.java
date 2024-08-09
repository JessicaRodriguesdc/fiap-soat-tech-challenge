package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDTO(@Schema(example = "Cheese Hamburger") @NotBlank String name,
		@Schema(example = "MAIN_COURSE") @NotNull ProductCategoryEnum category,
		@Schema(example = "25.90") @NotNull @Positive BigDecimal price,
		@Schema(example = "Hamburger with Cheese") @NotBlank String description) {
}