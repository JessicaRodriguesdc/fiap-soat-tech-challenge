package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.Customer;

import java.util.Optional;

public interface CustomerPersistence {
    Customer create(Customer customer);

    Optional<Customer> findByDocument(String document);
}
