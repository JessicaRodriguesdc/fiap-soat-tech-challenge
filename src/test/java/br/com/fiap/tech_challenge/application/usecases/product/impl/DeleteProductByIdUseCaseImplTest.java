package br.com.fiap.tech_challenge.application.usecases.product.impl;

import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.product.impl.DeleteProductByIdUseCaseImpl;
import br.com.fiap.tech_challenge.domain.models.Product;
import br.com.fiap.tech_challenge.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.domain.models.enums.ProductStatusEnum;
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

		when(persistence.update(any(Product.class))).thenReturn(product);

        deleteProductByIdUseCase.delete(product.getId());

        verify(persistence).update(product);
    }

	@Test
    @DisplayName("Should throw DoesNotExistException when trying to delete a non-existent Product.")
    public void shouldThrowDoesNotExistException() {
        when(persistence.findById(product.getId())).thenReturn(Optional.empty());

        DoesNotExistException exception = assertThrows(DoesNotExistException.class, () ->
                deleteProductByIdUseCase.delete(product.getId()));

        assertEquals("Product not found", exception.getMessage());

        verify(persistence, never()).update(product);
    }

	private void buildArranges() {
		product = new Product(UUID.randomUUID(), "Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE,
				new BigDecimal("99.99"), "Sanduíche de frango com salada", ProductStatusEnum.ACTIVE,
				LocalDateTime.now());
	}

}
