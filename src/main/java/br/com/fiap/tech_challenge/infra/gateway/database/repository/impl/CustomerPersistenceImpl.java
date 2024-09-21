package br.com.fiap.tech_challenge.infra.gateway.database.repository.impl;

import br.com.fiap.tech_challenge.application.persistence.CustomerPersistence;
import br.com.fiap.tech_challenge.domain.models.Customer;
import br.com.fiap.tech_challenge.infra.gateway.database.entities.CustomerEntity;
import br.com.fiap.tech_challenge.infra.gateway.database.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerPersistenceImpl implements CustomerPersistence {

	private final CustomerRepository repository;

	public CustomerPersistenceImpl(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public Customer create(Customer customer) {
		var customerEntity = new CustomerEntity(customer);

		var customerSaved = repository.save(customerEntity);
		return customerSaved.toCustomer();
	}

	@Override
	public Optional<Customer> findByDocument(String document) {
		var customerFound = repository.findByDocument(document.replace(".", "").replace("-", ""));
		return customerFound.map(CustomerEntity::toCustomer);
	}

	@Override
	public Optional<Customer> findById(UUID id) {
		return repository.findById(id).map(CustomerEntity::toCustomer);
	}

}
