package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.OrderPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.order.impl.FindPaidOrdersUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindPaidOrdersUseCaseConfig {

	@Bean
	public FindPaidOrdersUseCaseImpl findPaidOrdersUseCaseImpl(OrderPersistence persistence) {
		return new FindPaidOrdersUseCaseImpl(persistence);
	}

}
