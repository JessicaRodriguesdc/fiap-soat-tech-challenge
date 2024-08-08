package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.CustomerPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.customer.impl.CreateCustomerUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateCustomerUseCaseConfig {

	@Bean
	public CreateCustomerUseCaseImpl createCustomerUseCaseImpl(CustomerPersistence persistence) {
		return new CreateCustomerUseCaseImpl(persistence);
	}

}
