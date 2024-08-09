package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.product.PageablePageableProduct;
import br.com.fiap.tech_challenge.core.domain.models.product.PageableProduct;
import br.com.fiap.tech_challenge.core.domain.models.product.PageableSortProduct;
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
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProductsByCategoryUseCaseImplTest {

	@Mock
	private ProductPersistence persistence;

	@InjectMocks
	private GetProductsByCategoryUseCaseImpl getProductsByCategoryUseCase;

	private Product product;

	private PageableProduct pageableProduct;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
    @DisplayName("Should get Products by category successfully.")
    public void shouldGetProductsByCategorySuccessfully() {
        when(persistence.findByCategory(product.getCategory(), 0,10))
                .thenReturn(pageableProduct);

        var result = getProductsByCategoryUseCase.getByCategory(product.getCategory(),0,10 );

        assertNotNull(result);
        assertEquals(10, result.getTotalElements());
        assertEquals(product.getId(), result.getContent().getFirst().getId());
        assertEquals(product.getName(), result.getContent().getFirst().getName());
        assertEquals(product.getCategory(), result.getContent().getFirst().getCategory());
        assertEquals(product.getPrice(), result.getContent().getFirst().getPrice());
        assertEquals(product.getDescription(), result.getContent().getFirst().getDescription());

        verify(persistence).findByCategory(product.getCategory(), 0,10);
    }

	private void buildArranges() {
		var pageableSortProduct = new PageableSortProduct(false, false, false);
		var pageablePageableSortProduct = new PageableSortProduct(false, false, false);
		var pageablePageableProduct = new PageablePageableProduct(0L, 10L, pageablePageableSortProduct, 1L, true,
				false);

		product = new Product(UUID.randomUUID(), "Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE,
				new BigDecimal("99.99"), "Sanduíche de frango com salada", ProductStatusEnum.ACTIVE,
				LocalDateTime.now());
		pageableProduct = new PageableProduct(1L, 10L, 1L, List.of(product), 1L, pageableSortProduct, true, true, 10L,
				pageablePageableProduct, false);

	}

}
