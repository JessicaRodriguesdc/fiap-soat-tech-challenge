package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.product.impl.CreateProductUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateProductUseCaseConfig {

	@Bean
	public CreateProductUseCaseImpl createProductUseCaseImpl(ProductPersistence persistence) {
		return new CreateProductUseCaseImpl(persistence);
	}

}