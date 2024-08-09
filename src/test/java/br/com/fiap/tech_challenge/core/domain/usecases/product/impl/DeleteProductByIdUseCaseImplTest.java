package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.product.Product;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteProductByIdUseCaseImplTest {

	@Mock
	private ProductPersistence persistence;

	@InjectMocks
	private DeleteProductByIdUseCaseImpl deleteProductByIdUseCase;

	private Product product;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
    @DisplayName("Should delete a Product by ID successfully.")
    public void shouldDeleteProductById() {
        when(persistence.findById(product.getId())).thenReturn(Optional.of(product));

        doNothing().when(persistence).delete(product.getId());

        deleteProductByIdUseCase.delete(product.getId());

        verify(persistence).delete(product.getId());
    }

	@Test
    @DisplayName("Should throw DoesNotExistException when trying to delete a non-existent Product.")
    public void shouldThrowDoesNotExistException() {
        when(persistence.findById(product.getId())).thenReturn(Optional.empty());

        DoesNotExistException exception = assertThrows(DoesNotExistException.class, () ->
                deleteProductByIdUseCase.delete(product.getId()));

        assertEquals("Product not found", exception.getMessage());

        verify(persistence, never()).delete(product.getId());
    }

	private void buildArranges() {
		product = new Product(UUID.randomUUID(), "Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE,
				new BigDecimal("99.99"), "Sanduíche de frango com salada", ProductStatusEnum.ACTIVE,
				LocalDateTime.now());
	}

}
