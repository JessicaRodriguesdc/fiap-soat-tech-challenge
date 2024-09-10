package br.com.fiap.tech_challenge.infra.config.bean;

import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.product.impl.DeleteProductByIdUseCaseImpl;
import br.com.fiap.tech_challenge.infra.config.bean.DeleteProductByIdUseCaseConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class DeleteProductByIdUseCaseConfigTest {

	@Mock
	ProductPersistence persistence;

	@InjectMocks
	private DeleteProductByIdUseCaseConfig deleteProductByIdUseCaseConfig;

	@Test
	@DisplayName("Should Create a Singleton Instance Of DeleteProductByIdUseCaseImpl")
	void shouldCreateSingletonInstanceOfDeleteProductByIdUseCaseImpl() {
		var deleteProductByIdUseCaseImpl = deleteProductByIdUseCaseConfig.deleteProductByIdUseCaseImpl(persistence);

		assertNotNull(deleteProductByIdUseCaseImpl);
		assertNotNull(persistence);
		assertInstanceOf(DeleteProductByIdUseCaseImpl.class, deleteProductByIdUseCaseImpl);
	}

}
