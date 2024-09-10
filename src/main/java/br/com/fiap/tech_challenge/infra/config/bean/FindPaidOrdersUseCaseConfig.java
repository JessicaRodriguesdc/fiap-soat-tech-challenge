package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.impl.FindPaidOrdersUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindPaidOrdersUseCaseConfig {

	@Bean
	public FindPaidOrdersUseCaseImpl findPaidOrdersUseCaseImpl(OrderPersistence persistence) {
		return new FindPaidOrdersUseCaseImpl(persistence);
	}

}
