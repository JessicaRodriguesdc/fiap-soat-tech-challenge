package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.impl.UpdateProductUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UpdateProductUseCaseConfigTest {

	@Mock
	ProductPersistence persistence;

	@InjectMocks
	private UpdateProductUseCaseConfig updateProductUseCaseConfig;

	@Test
	@DisplayName("Should Create a Singleton Instance Of UpdateProductUseCaseImpl")
	void shouldCreateSingletonInstanceOfUpdateProductUseCaseImpl() {
		var updateProductUseCaseImpl = updateProductUseCaseConfig.updateProductUseCaseImpl(persistence);

		assertNotNull(updateProductUseCaseImpl);
		assertNotNull(persistence);
		assertInstanceOf(UpdateProductUseCaseImpl.class, updateProductUseCaseImpl);
	}

}
