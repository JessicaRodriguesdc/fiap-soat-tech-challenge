package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.gateway.client.PaymentClient;
import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.impl.UpdateOrderStatusUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateOrderStatusUseCaseConfig {

	@Bean
	public UpdateOrderStatusUseCaseImpl updateOrderStatusUseCaseImpl(OrderPersistence orderPersistence,
			PaymentClient paymentClient) {
		return new UpdateOrderStatusUseCaseImpl(orderPersistence, paymentClient);
	}

}
