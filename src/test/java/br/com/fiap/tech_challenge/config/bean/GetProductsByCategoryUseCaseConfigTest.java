package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.impl.GetProductsByCategoryUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class GetProductsByCategoryUseCaseConfigTest {

	@Mock
	ProductPersistence persistence;

	@InjectMocks
	private GetProductsByCategoryUseCaseConfig getProductsByCategoryUseCaseConfig;

	@Test
	@DisplayName("Should Create a Singleton Instance Of GetProductsByCategoryUseCaseImpl")
	void shouldCreateSingletonInstanceOfGetProductsByCategoryUseCaseImpl() {
		var getProductsByCategoryUseCaseImpl = getProductsByCategoryUseCaseConfig
			.getProductsByCategoryUseCaseImpl(persistence);

		assertNotNull(getProductsByCategoryUseCaseImpl);
		assertNotNull(persistence);
		assertInstanceOf(GetProductsByCategoryUseCaseImpl.class, getProductsByCategoryUseCaseImpl);
	}

}
