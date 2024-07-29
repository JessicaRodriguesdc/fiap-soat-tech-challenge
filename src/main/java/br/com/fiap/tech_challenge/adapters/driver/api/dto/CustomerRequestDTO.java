package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerRequestDTO(@Schema(example = "So And So") @NotBlank String name,
                                 @Schema(example = "11122233344") @NotBlank @CPF String document,
                                 @Schema(example = "email@email.com") @NotBlank @Email String email) {
}
