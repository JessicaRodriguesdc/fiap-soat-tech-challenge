package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.product.impl.CreateProductUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CreateProductUseCaseConfigTest {

	@Mock
	ProductPersistence persistence;

	@InjectMocks
	private CreateProductUseCaseConfig createProductUseCaseConfig;

	@Test
	@DisplayName("Should Create a Singleton Instance Of CreateProductUseCaseImpl")
	void shouldCreateSingletonInstanceOfCreateProductUseCaseImpl() {
		var createProductUseCaseImpl = createProductUseCaseConfig.createProductUseCaseImpl(persistence);

		assertNotNull(createProductUseCaseImpl);
		assertNotNull(persistence);
		assertInstanceOf(CreateProductUseCaseImpl.class, createProductUseCaseImpl);
	}

}
