package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductStatusEnum;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateProductUseCaseImplTest {

	@Mock
	private ProductPersistence persistence;

	@InjectMocks
	private CreateProductUseCaseImpl createProductUseCase;

	private Product product;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		UUID id = UUID.randomUUID();
		product = new Product(id, "Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE, new BigDecimal("99.99"),
				"Sanduíche de frango com salada", ProductStatusEnum.ACTIVE, LocalDateTime.now());
	}

	@Test
    @DisplayName("Should create a Product of type MAIN_COURSE successfully.")
    public void testCreateProduct() {
        when(persistence.create(any(Product.class))).thenReturn(product);

        Product createdProduct = createProductUseCase.create(product);

        assertNotNull(createdProduct);
        assertEquals(product.getId(), createdProduct.getId());
        assertEquals(product.getName(), createdProduct.getName());

        verify(persistence).create(any(Product.class));
    }

}
