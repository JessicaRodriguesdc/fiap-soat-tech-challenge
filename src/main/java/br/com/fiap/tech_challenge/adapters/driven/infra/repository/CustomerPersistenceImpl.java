package br.com.fiap.tech_challenge.adapters.driven.infra.repository;

import br.com.fiap.tech_challenge.adapters.driven.infra.entities.CustomerEntity;
import br.com.fiap.tech_challenge.core.domain.models.Customer;
import br.com.fiap.tech_challenge.core.domain.ports.CustomerPersistence;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        var customerFound = repository.findByDocument(document);
        return customerFound.map(CustomerEntity::toCustomer);
    }
}
