package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.models.DomainPage;
import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.ProductPage;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductStatusEnum;
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
class FindProductsByCategoryUseCaseImplTest {

	@Mock
	private ProductPersistence persistence;

	@InjectMocks
	private FindProductsByCategoryUseCaseImpl getProductsByCategoryUseCase;

	private Product product;

	private DomainPage domainPage;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
    @DisplayName("Should get Products by category successfully.")
    void shouldGetProductsByCategorySuccessfully() {
        when(persistence.findByCategory(product.getCategory(), 0,10))
                .thenReturn(new ProductPage(List.of(product), domainPage));

        var result = getProductsByCategoryUseCase.findByCategory(product.getCategory(),0,10 );

        assertNotNull(result);
        assertEquals(domainPage.getNumberOfElements(), result.getPage().getNumberOfElements());
        assertEquals(product.getId(), result.getContent().getFirst().getId());
        assertEquals(product.getName(), result.getContent().getFirst().getName());
        assertEquals(product.getCategory(), result.getContent().getFirst().getCategory());
        assertEquals(product.getPrice(), result.getContent().getFirst().getPrice());
        assertEquals(product.getDescription(), result.getContent().getFirst().getDescription());

        verify(persistence).findByCategory(product.getCategory(), 0,10);
    }

	private void buildArranges() {
		product = new Product(UUID.randomUUID(), "Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE,
				BigDecimal.valueOf(99.99), "Sanduíche de frango com salada", ProductStatusEnum.ACTIVE,
				LocalDateTime.now());

		domainPage = new DomainPage(1L, 1L, 1L, 1L, true, true, 1L, false);

	}

}
