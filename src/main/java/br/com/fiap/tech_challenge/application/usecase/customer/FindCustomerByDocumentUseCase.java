package br.com.fiap.tech_challenge.application.usecase.customer;

import br.com.fiap.tech_challenge.domain.models.Customer;

public interface FindCustomerByDocumentUseCase {

	Customer findByDocument(String document);

}
