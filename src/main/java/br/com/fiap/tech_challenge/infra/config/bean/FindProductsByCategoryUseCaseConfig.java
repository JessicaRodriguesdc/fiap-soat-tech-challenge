package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.product.impl.FindProductsByCategoryUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindProductsByCategoryUseCaseConfig {

	@Bean
	public FindProductsByCategoryUseCaseImpl findProductsByCategoryUseCaseImpl(ProductPersistence persistence) {
		return new FindProductsByCategoryUseCaseImpl(persistence);
	}

}
