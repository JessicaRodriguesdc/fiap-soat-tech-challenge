package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.impl.FindProductsByCategoryUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindProductsByCategoryUseCaseConfig {

	@Bean
	public FindProductsByCategoryUseCaseImpl findProductsByCategoryUseCaseImpl(ProductPersistence persistence) {
		return new FindProductsByCategoryUseCaseImpl(persistence);
	}

}
