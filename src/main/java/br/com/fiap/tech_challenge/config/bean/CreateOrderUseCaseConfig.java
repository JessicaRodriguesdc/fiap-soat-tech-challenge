package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.CustomerPersistence;
import br.com.fiap.tech_challenge.core.domain.ports.OrderPersistence;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.customer.impl.CreateCustomerUseCaseImpl;
import br.com.fiap.tech_challenge.core.domain.usecases.order.impl.CreateOrderUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateOrderUseCaseConfig {

    @Bean
    public CreateOrderUseCaseImpl createOrderUseCaseImpl(OrderPersistence persistence, ProductPersistence productPersistence){
        return new CreateOrderUseCaseImpl(persistence, productPersistence);
    }
}
