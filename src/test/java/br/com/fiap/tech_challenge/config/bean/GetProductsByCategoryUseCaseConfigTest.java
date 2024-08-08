package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.impl.GetProductsByCategoryUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetProductsByCategoryUseCaseConfigTest {

    @Configuration
    static class TestConfig {
        @Bean
        public ProductPersistence productPersistence() {
            return Mockito.mock(ProductPersistence.class);
        }

        @Bean
        public GetProductsByCategoryUseCaseImpl getProductsByCategoryUseCaseImpl(ProductPersistence persistence) {
            return new GetProductsByCategoryUseCaseImpl(persistence);
        }
    }

    @Test
    @DisplayName("Should be created a bean of type GetProductsByCategoryUseCaseImpl successfully.")
    public void testGetProductsByCategoryUseCaseBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        GetProductsByCategoryUseCaseImpl getProductsByCategoryUseCase = context.getBean(GetProductsByCategoryUseCaseImpl.class);
        ProductPersistence persistence = context.getBean(ProductPersistence.class);

        assertNotNull(getProductsByCategoryUseCase, "O bean GetProductsByCategoryUseCaseImpl não deve ser nulo");
        assertInstanceOf(GetProductsByCategoryUseCaseImpl.class, getProductsByCategoryUseCase, "O bean deve ser uma instância de GetProductsByCategoryUseCaseImpl");
        assertNotNull(persistence, "O bean ProductPersistence não deve ser nulo");
    }
}
