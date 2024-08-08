package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.CustomerPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.customer.impl.FindCustomerByDocumentUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindCustomerByDocumentUseCaseConfig {

	@Bean
	public FindCustomerByDocumentUseCaseImpl findCustomerByDocumentUseCaseImpl(CustomerPersistence persistence) {
		return new FindCustomerByDocumentUseCaseImpl(persistence);
	}

}
