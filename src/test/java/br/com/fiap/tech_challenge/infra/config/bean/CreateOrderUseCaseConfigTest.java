package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.CustomerPersistence;
import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.gateway.client.PaymentClient;
import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.impl.CreateOrderUseCaseImpl;
import br.com.fiap.tech_challenge.infra.config.bean.CreateOrderUseCaseConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseConfigTest {

	@Mock
	OrderPersistence orderPersistence;

	@Mock
	ProductPersistence productPersistence;

	@Mock
	CustomerPersistence customerPersistence;

	@Mock
	PaymentClient paymentClient;

	@InjectMocks
	private CreateOrderUseCaseConfig createOrderUseCaseConfig;

	@Test
	@DisplayName("Should Create a Singleton Instance Of CreateOrderUseCaseImpl")
	void shouldCreateSingletonInstanceOfCreateOrderUseCaseImpl() {
		var createOrderUseCaseImpl = createOrderUseCaseConfig.createOrderUseCaseImpl(orderPersistence,
				productPersistence, customerPersistence, paymentClient);

		assertNotNull(createOrderUseCaseImpl);
		assertNotNull(orderPersistence);
		assertNotNull(productPersistence);
		assertNotNull(customerPersistence);
		assertNotNull(paymentClient);
		assertInstanceOf(CreateOrderUseCaseImpl.class, createOrderUseCaseImpl);
	}

}