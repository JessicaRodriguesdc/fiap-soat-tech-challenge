package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.OrderPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.order.impl.UpdateOrderStatusUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateOrderStatusUseCaseConfig {

    @Bean
    public UpdateOrderStatusUseCaseImpl updateOrderStatusUseCaseImpl(OrderPersistence orderPersistence) {
        return new UpdateOrderStatusUseCaseImpl(orderPersistence);
    }
}
