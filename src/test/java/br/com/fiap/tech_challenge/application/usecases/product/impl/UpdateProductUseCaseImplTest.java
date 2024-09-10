package br.com.fiap.tech_challenge.application.usecases.product.impl;

import br.com.fiap.tech_challenge.application.usecase.product.impl.UpdateProductUseCaseImpl;
import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.domain.models.Product;
import br.com.fiap.tech_challenge.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.domain.models.enums.ProductStatusEnum;
import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateProductUseCaseImplTest {

	@Mock
	private ProductPersistence persistence;

	@InjectMocks
	private UpdateProductUseCaseImpl updateProductUseCase;

	private Product product;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
    @DisplayName("Should update a Product of type MAIN_COURSE successfully.")
    public void shouldUpdateProduct() {
        when(persistence.findById(product.getId())).thenReturn(Optional.of(product));
        when(persistence.update(any(Product.class))).thenReturn(product);

        Product result = updateProductUseCase.update(product.getId(), product);

        assertNotNull(result);
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getCategory(), result.getCategory());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getDescription(), result.getDescription());

        verify(persistence).findById(product.getId());
        verify(persistence).update(any(Product.class));
    }

	@Test
    @DisplayName("Should be thrown an when trying exception of type DoesNotExistException to update a product of type MAIN_COURSE.")
    public void shouldThrowExceptionWhenTryingToUpdateProduct() {
        when(persistence.findById(product.getId())).thenReturn(Optional.empty());

        DoesNotExistException exception = assertThrows(DoesNotExistException.class, () ->
                updateProductUseCase.update(product.getId(), product)
        );

        assertEquals("Product Doesn't Exist", exception.getMessage());

        verify(persistence).findById(product.getId());
        verify(persistence, never()).update(any(Product.class));
    }

	private void buildArranges() {
		product = new Product(UUID.randomUUID(), "Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE,
				new BigDecimal("99.99"), "Sanduíche de frango com salada", ProductStatusEnum.ACTIVE,
				LocalDateTime.now());
	}

}
