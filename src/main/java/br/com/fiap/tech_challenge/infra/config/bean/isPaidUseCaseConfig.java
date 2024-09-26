package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.impl.IsPaidUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class isPaidUseCaseConfig {

	@Bean
	public IsPaidUseCaseImpl isPaidUseCaseImpl(OrderPersistence orderPersistence) {
		return new IsPaidUseCaseImpl(orderPersistence);
	}

}
