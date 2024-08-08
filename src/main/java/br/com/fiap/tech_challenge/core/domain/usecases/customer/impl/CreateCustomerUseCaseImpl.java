package br.com.fiap.tech_challenge.core.domain.usecases.customer.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.AlreadyExistsException;
import br.com.fiap.tech_challenge.core.domain.models.Customer;
import br.com.fiap.tech_challenge.core.domain.ports.CustomerPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.customer.CreateCustomerUseCase;

public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

	private final CustomerPersistence persistence;

	public CreateCustomerUseCaseImpl(CustomerPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public Customer create(Customer customer) {
		var customerFound = persistence.findByDocument(customer.getDocument());

		if (customerFound.isPresent()) {
			throw new AlreadyExistsException("Customer Already Exists");
		}

		return persistence.create(customer);
	}

}
