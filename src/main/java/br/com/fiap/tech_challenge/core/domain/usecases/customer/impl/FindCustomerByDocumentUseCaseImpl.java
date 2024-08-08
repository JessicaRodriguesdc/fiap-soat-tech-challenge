package br.com.fiap.tech_challenge.core.domain.usecases.customer.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.Customer;
import br.com.fiap.tech_challenge.core.domain.ports.CustomerPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.customer.FindCustomerByDocumentUseCase;

public class FindCustomerByDocumentUseCaseImpl implements FindCustomerByDocumentUseCase {

	private final CustomerPersistence persistence;

	public FindCustomerByDocumentUseCaseImpl(CustomerPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public Customer findByDocument(String document) {
		var customerFound = persistence.findByDocument(document);

		if (customerFound.isEmpty()) {
			throw new DoesNotExistException("Customer Doesn't Exist");
		}

		return customerFound.get();
	}

}
