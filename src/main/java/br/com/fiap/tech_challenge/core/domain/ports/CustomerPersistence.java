package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerPersistence {
    Customer create(Customer customer);

    Optional<Customer> findByDocument(String document);

    Optional<Customer> findById(UUID id);
}
