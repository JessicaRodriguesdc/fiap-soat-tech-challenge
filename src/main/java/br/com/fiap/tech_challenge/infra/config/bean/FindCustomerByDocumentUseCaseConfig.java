package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.CustomerPersistence;
import br.com.fiap.tech_challenge.application.usecase.customer.impl.FindCustomerByDocumentUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindCustomerByDocumentUseCaseConfig {

	@Bean
	public FindCustomerByDocumentUseCaseImpl findCustomerByDocumentUseCaseImpl(CustomerPersistence persistence) {
		return new FindCustomerByDocumentUseCaseImpl(persistence);
	}

}
