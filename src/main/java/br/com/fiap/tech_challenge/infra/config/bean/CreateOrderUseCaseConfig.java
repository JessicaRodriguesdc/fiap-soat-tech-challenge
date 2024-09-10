package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.CustomerPersistence;
import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.gateway.client.PaymentClient;
import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.impl.CreateOrderUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateOrderUseCaseConfig {

	@Bean
	public CreateOrderUseCaseImpl createOrderUseCaseImpl(OrderPersistence persistence,
			ProductPersistence productPersistence, CustomerPersistence customerPersistence,
			PaymentClient paymentClient) {
		return new CreateOrderUseCaseImpl(persistence, productPersistence, customerPersistence, paymentClient);
	}

}
