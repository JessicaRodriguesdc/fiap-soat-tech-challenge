package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.UpdateProductUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.product.impl.UpdateProductUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateProductUseCaseConfigTest {

    @Configuration
    static class TestConfig {
        @Bean
        public ProductPersistence productPersistence() {
            return Mockito.mock(ProductPersistence.class);
        }

        @Bean
        public UpdateProductUseCaseImpl updateProductUseCase(ProductPersistence persistence) {
            return new UpdateProductUseCaseImpl(persistence);
        }
    }

    @Test
    @DisplayName("Should be created a bean of type UpdateProductUseCaseImpl successfully.")
    public void testCreateProductUseCaseBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        UpdateProductUseCase createProductUseCase = context.getBean(UpdateProductUseCaseImpl.class);
        ProductPersistence persistence = context.getBean(ProductPersistence.class);

        assertNotNull(createProductUseCase, "O bean CreateProductUseCaseImpl não deve ser nulo");
        assertInstanceOf(UpdateProductUseCaseImpl.class, createProductUseCase, "O bean deve ser uma instância de UpdateProductUseCaseImpl");
        assertNotNull(persistence, "O bean ProductPersistence não deve ser nulo");
    }
}
