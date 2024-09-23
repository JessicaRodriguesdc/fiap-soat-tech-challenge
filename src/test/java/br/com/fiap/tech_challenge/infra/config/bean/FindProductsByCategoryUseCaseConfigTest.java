package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.product.impl.FindProductsByCategoryUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class FindProductsByCategoryUseCaseConfigTest {

	@Mock
	ProductPersistence persistence;

	@InjectMocks
	private FindProductsByCategoryUseCaseConfig findProductsByCategoryUseCaseConfig;

	@Test
	@DisplayName("Should Create a Singleton Instance Of FindProductsByCategoryUseCaseImpl")
	void shouldCreateSingletonInstanceOfFindProductsByCategoryUseCaseImpl() {
		var findProductsByCategoryUseCaseImpl = findProductsByCategoryUseCaseConfig
			.findProductsByCategoryUseCaseImpl(persistence);

		assertNotNull(findProductsByCategoryUseCaseImpl);
		assertNotNull(persistence);
		assertInstanceOf(FindProductsByCategoryUseCaseImpl.class, findProductsByCategoryUseCaseImpl);
	}

}
