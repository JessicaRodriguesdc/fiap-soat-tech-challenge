package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.impl.CreateProductUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

public class CreateProductUseCaseConfigTest {

	@Configuration
	static class TestConfig {

		@Bean
		public ProductPersistence productPersistence() {
			return Mockito.mock(ProductPersistence.class);
		}

		@Bean
		public CreateProductUseCaseImpl createProductUseCaseImpl(ProductPersistence persistence) {
			return new CreateProductUseCaseImpl(persistence);
		}

	}

	@Test
	@DisplayName("Should be created a bean of type CreateProductUseCaseImpl successfully.")
	public void testCreateProductUseCaseBean() {
		ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
		CreateProductUseCaseImpl createProductUseCase = context.getBean(CreateProductUseCaseImpl.class);
		ProductPersistence persistence = context.getBean(ProductPersistence.class);

		assertNotNull(createProductUseCase, "O bean CreateProductUseCaseImpl não deve ser nulo");
		assertInstanceOf(CreateProductUseCaseImpl.class, createProductUseCase,
				"O bean deve ser uma instância de CreateProductUseCaseImpl");
		assertNotNull(persistence, "O bean ProductPersistence não deve ser nulo");
	}

}
