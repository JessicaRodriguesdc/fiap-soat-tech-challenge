package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.impl.DeleteProductByIdUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeleteProductByIdUseCaseConfigTest {

    @Configuration
    static class TestConfig {
        @Bean
        public ProductPersistence productPersistence() {
            return Mockito.mock(ProductPersistence.class);
        }

        @Bean
        public DeleteProductByIdUseCaseImpl deleteProductByIdUseCaseImpl(ProductPersistence persistence) {
            return new DeleteProductByIdUseCaseImpl(persistence);
        }
    }

    @Test
    @DisplayName("Should be created a bean of type DeleteProductByIdUseCaseImpl successfully.")
    public void testDeleteProductByIdUseCaseBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        DeleteProductByIdUseCaseImpl deleteProductByIdUseCase = context.getBean(DeleteProductByIdUseCaseImpl.class);
        ProductPersistence persistence = context.getBean(ProductPersistence.class);

        assertNotNull(deleteProductByIdUseCase, "O bean DeleteProductByIdUseCaseImpl não deve ser nulo");
        assertInstanceOf(DeleteProductByIdUseCaseImpl.class, deleteProductByIdUseCase, "O bean deve ser uma instância de DeleteProductByIdUseCaseImpl");
        assertNotNull(persistence, "O bean ProductPersistence não deve ser nulo");
    }
}
