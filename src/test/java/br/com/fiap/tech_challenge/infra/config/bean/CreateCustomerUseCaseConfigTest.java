package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.CustomerPersistence;
import br.com.fiap.tech_challenge.application.usecase.customer.impl.CreateCustomerUseCaseImpl;
import br.com.fiap.tech_challenge.infra.config.bean.CreateCustomerUseCaseConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseConfigTest {

	@Mock
	CustomerPersistence persistence;

	@InjectMocks
	private CreateCustomerUseCaseConfig createCustomerUseCaseConfig;

	@Test
	@DisplayName("Should Create a Singleton Instance Of CreateCustomerUseCaseImpl")
	void shouldCreateSingletonInstanceOfCreateCustomerUseCaseImpl() {
		var createCustomerUseCaseImpl = createCustomerUseCaseConfig.createCustomerUseCaseImpl(persistence);

		assertNotNull(createCustomerUseCaseImpl);
		assertNotNull(persistence);
		assertInstanceOf(CreateCustomerUseCaseImpl.class, createCustomerUseCaseImpl);
	}

}