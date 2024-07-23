package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.core.domain.models.Customer;

import java.util.UUID;

public record CustomerResponseDTO(UUID id,
                                  String name,
                                  String document,
                                  String email) {
    public CustomerResponseDTO(Customer customer){
        this(customer.getId(), customer.getName(), customer.getDocument(), customer.getEmail());
    }
}
