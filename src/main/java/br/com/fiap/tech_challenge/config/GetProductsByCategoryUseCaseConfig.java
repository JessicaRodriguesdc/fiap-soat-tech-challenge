package br.com.fiap.tech_challenge.config;

import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.impl.GetProductsByCategoryUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetProductsByCategoryUseCaseConfig {

    @Bean
    public GetProductsByCategoryUseCaseImpl createGetProductsByCategoryUseCaseImpl(ProductPersistence persistence) {
        return new GetProductsByCategoryUseCaseImpl(persistence);
    }
}
