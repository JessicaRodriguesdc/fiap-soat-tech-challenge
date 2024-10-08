package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.impl.DeleteProductByIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteProductByIdUseCaseConfig {

	@Bean
	public DeleteProductByIdUseCaseImpl deleteProductByIdUseCaseImpl(ProductPersistence persistence) {
		return new DeleteProductByIdUseCaseImpl(persistence);
	}

}
