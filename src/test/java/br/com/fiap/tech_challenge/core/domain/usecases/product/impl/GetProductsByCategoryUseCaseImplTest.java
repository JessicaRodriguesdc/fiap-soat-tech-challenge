package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetProductsByCategoryUseCaseImplTest {

    @Mock
    private ProductPersistence persistence;

    @InjectMocks
    private GetProductsByCategoryUseCaseImpl getProductsByCategoryUseCase;

    private Product product;
    private CategoryProductEnum category;
    private PageRequest pageRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        UUID id = UUID.randomUUID();
        category = CategoryProductEnum.MAIN_COURSE;
        product = new Product(id, "Sanduíche de Frango", category,
                new BigDecimal("99.99"), "Sanduíche de frango com salada",
                StatusProductEnum.ACTIVE, LocalDateTime.now());
        pageRequest = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("Should get Products by category successfully.")
    public void testGetProductsByCategory() {
        when(persistence.findByCategory(category, pageRequest))
                .thenReturn(new PageImpl<>(List.of(product)));

        var result = getProductsByCategoryUseCase.getByCategory(category, pageRequest);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(product.getId(), result.getContent().getFirst().getId());
        assertEquals(product.getName(), result.getContent().getFirst().getName());
        assertEquals(product.getCategory(), result.getContent().getFirst().getCategory());
        assertEquals(product.getPrice(), result.getContent().getFirst().getPrice());
        assertEquals(product.getDescription(), result.getContent().getFirst().getDescription());

        verify(persistence).findByCategory(category, pageRequest);
    }
}
