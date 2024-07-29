package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerRequestDTO(@NotBlank String name,
                                 @NotBlank @CPF String document,
                                 @NotBlank @Email String email) {
}
