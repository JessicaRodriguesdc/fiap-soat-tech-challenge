package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductProductEnum;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdateProductUseCaseImplTest {

	@Mock
	private ProductPersistence persistence;

	@InjectMocks
	private UpdateProductUseCaseImpl updateProductUseCase;

	private Product existingProduct;

	private Product updatedProduct;

	private UUID productId;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		productId = UUID.randomUUID();
		existingProduct = new Product(productId, "Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE,
				new BigDecimal("99.99"), "Sanduíche de frango com salada", ProductProductEnum.ACTIVE,
				LocalDateTime.now());

		updatedProduct = new Product(productId, "Sanduíche de Bacon", ProductCategoryEnum.MAIN_COURSE,
				new BigDecimal("149.99"), "Sanduíche de bacon com salada", ProductProductEnum.ACTIVE,
				LocalDateTime.now());
	}

	@Test
    @DisplayName("Should update a Product of type MAIN_COURSE successfully.")
    public void testUpdateProductSuccess() {
        when(persistence.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(persistence.update(any(Product.class))).thenReturn(updatedProduct);

        Product result = updateProductUseCase.update(productId, updatedProduct);

        assertNotNull(result);
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getCategory(), result.getCategory());
        assertEquals(updatedProduct.getPrice(), result.getPrice());
        assertEquals(updatedProduct.getDescription(), result.getDescription());

        verify(persistence).findById(productId);
        verify(persistence).update(any(Product.class));
    }

	@Test
    @DisplayName("Should be thrown an when trying exception of type DoesNotExistException to update a product of type MAIN_COURSE.")
    public void testUpdateProductNotFound() {
        when(persistence.findById(productId)).thenReturn(Optional.empty());

        DoesNotExistException exception = assertThrows(DoesNotExistException.class, () ->
                updateProductUseCase.update(productId, updatedProduct)
        );

        assertEquals("Product Doesn't Exist", exception.getMessage());

        verify(persistence).findById(productId);
        verify(persistence, never()).update(any(Product.class));
    }

}
