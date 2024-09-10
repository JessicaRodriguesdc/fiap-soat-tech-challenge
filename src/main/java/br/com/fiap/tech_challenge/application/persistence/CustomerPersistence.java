package br.com.fiap.tech_challenge.application.persistence;

import br.com.fiap.tech_challenge.domain.models.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerPersistence {

	Customer create(Customer customer);

	Optional<Customer> findByDocument(String document);

	Optional<Customer> findById(UUID id);

}
