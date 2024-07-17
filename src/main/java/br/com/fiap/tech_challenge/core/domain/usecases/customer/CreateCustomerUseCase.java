package br.com.fiap.tech_challenge.core.domain.usecases.customer;

import br.com.fiap.tech_challenge.core.domain.models.Customer;

public interface CreateCustomerUseCase {
    Customer create(Customer customer);
}
