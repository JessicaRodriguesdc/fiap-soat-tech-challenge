package br.com.fiap.tech_challenge.core.domain.usecases.customer;

import br.com.fiap.tech_challenge.core.domain.models.Customer;

public interface FindCustomerByDocumentUseCase {
    Customer findByDocument(String document);
}
