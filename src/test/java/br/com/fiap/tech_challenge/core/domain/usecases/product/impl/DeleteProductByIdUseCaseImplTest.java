package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.StatusProductEnum;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteProductByIdUseCaseImplTest {

    @Mock
    private ProductPersistence persistence;

    @InjectMocks
    private DeleteProductByIdUseCaseImpl deleteProductByIdUseCase;

    private UUID productId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productId = UUID.randomUUID();
    }

    @Test
    @DisplayName("Should delete a Product by ID successfully.")
    public void testDeleteProductByIdSuccess() {
        // Configure mock to find the product by ID
        when(persistence.findById(productId)).thenReturn(Optional.of(new Product(
                productId, "Sanduíche de Frango", CategoryProductEnum.MAIN_COURSE,
                new BigDecimal("99.99"), "Sanduíche de frango com salada",
                StatusProductEnum.ACTIVE, LocalDateTime.now()
        )));

        doNothing().when(persistence).delete(productId);

        // Call the method under test
        deleteProductByIdUseCase.delete(productId);

        // Verify the behavior
        verify(persistence, times(1)).delete(productId);
    }

    @Test
    @DisplayName("Should throw DoesNotExistException when trying to delete a non-existent Product.")
    public void testDeleteProductByIdNotFound() {
        // Configure mock to return empty for product ID
        when(persistence.findById(productId)).thenReturn(Optional.empty());

        // Assert that the exception is thrown
        DoesNotExistException exception = assertThrows(DoesNotExistException.class, () ->
                deleteProductByIdUseCase.delete(productId)
        );

        assertEquals("Product not found", exception.getMessage());

        // Verify no delete operation was performed
        verify(persistence, never()).delete(productId);
    }
}
