package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.impl.UpdateProductUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateProductUseCaseConfig {

	@Bean
	public UpdateProductUseCaseImpl updateProductUseCaseImpl(ProductPersistence persistence) {
		return new UpdateProductUseCaseImpl(persistence);
	}

}