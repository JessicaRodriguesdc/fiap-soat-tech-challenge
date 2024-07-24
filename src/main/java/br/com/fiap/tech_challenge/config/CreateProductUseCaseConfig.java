package br.com.fiap.tech_challenge.config;

import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.impl.CreateProductUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateProductUseCaseConfig {

    @Bean
    public CreateProductUseCaseImpl createProductUseCaseImpl(ProductPersistence persistence){
        return new CreateProductUseCaseImpl(persistence);
    }

}