package br.com.fiap.tech_challenge.application.usecases.product.impl;

import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.product.impl.FindProductsByCategoryUseCaseImpl;
import br.com.fiap.tech_challenge.domain.models.Product;
import br.com.fiap.tech_challenge.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.domain.models.enums.ProductStatusEnum;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPage;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPageable;
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
class FindProductsByCategoryUseCaseImplTest {

	@Mock
	private ProductPersistence persistence;

	@InjectMocks
	private FindProductsByCategoryUseCaseImpl getProductsByCategoryUseCase;

	private Product product;

	private CustomPage domainPage;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
    @DisplayName("Should get Products by category successfully.")
    void shouldGetProductsByCategorySuccessfully() {
        when(persistence.findByCategory(product.getCategory(), 0,10))
                .thenReturn(new CustomPageable<Product>(List.of(product), domainPage));

        var result = getProductsByCategoryUseCase.findByCategory(product.getCategory(),0,10 );

        assertNotNull(result);
        assertEquals(domainPage.numberOfElements(), result.page().numberOfElements());
        assertEquals(product.getId(), result.content().getFirst().getId());
        assertEquals(product.getName(), result.content().getFirst().getName());
        assertEquals(product.getCategory(), result.content().getFirst().getCategory());
        assertEquals(product.getPrice(), result.content().getFirst().getPrice());
        assertEquals(product.getDescription(), result.content().getFirst().getDescription());

        verify(persistence).findByCategory(product.getCategory(), 0,10);
    }

	private void buildArranges() {
		product = new Product(UUID.randomUUID(), "Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE,
				BigDecimal.valueOf(99.99), "Sanduíche de frango com salada", ProductStatusEnum.ACTIVE,
				LocalDateTime.now());

		domainPage = new CustomPage(1L, 1L, 1L, 1L, true, true, 1L, false);

	}

}
