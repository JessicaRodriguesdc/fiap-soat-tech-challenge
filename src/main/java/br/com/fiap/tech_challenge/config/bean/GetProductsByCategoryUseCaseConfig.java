package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.impl.FindProductsByCategoryUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetProductsByCategoryUseCaseConfig {

	@Bean
	public FindProductsByCategoryUseCaseImpl getProductsByCategoryUseCaseImpl(ProductPersistence persistence) {
		return new FindProductsByCategoryUseCaseImpl(persistence);
	}

}
