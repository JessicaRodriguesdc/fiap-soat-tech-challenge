package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.impl.FindWorkItemsUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindWorkItemsUseCaseConfig {

	@Bean
	public FindWorkItemsUseCaseImpl findWorkItemsUseCaseImpl(OrderPersistence orderPersistence) {
		return new FindWorkItemsUseCaseImpl(orderPersistence);
	}

}
