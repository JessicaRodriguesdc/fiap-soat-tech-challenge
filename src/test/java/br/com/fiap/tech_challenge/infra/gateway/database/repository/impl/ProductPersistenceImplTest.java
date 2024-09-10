package br.com.fiap.tech_challenge.infra.gateway.database.repository.impl;

import br.com.fiap.tech_challenge.infra.gateway.database.entities.ProductEntity;
import br.com.fiap.tech_challenge.infra.gateway.database.repository.ProductRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductPersistenceImplTest {

	@Mock
	private ProductRepository repository;

	@InjectMocks
	private ProductPersistenceImpl productPersistence;

	private Product product;

	private ProductEntity productEntity;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
    @DisplayName("Should successfully save a ProductEntity to the database.")
    public void shouldSaveProductEntity() {
        when(repository.save(any(ProductEntity.class))).thenReturn(productEntity);

        Product createdProduct = productPersistence.create(product);

        assertEquals(product.getId(), createdProduct.getId());
        assertEquals(product.getName(), createdProduct.getName());
        assertEquals(product.getCategory(), createdProduct.getCategory());
        assertEquals(product.getPrice(), createdProduct.getPrice());
        assertEquals(product.getDescription(), createdProduct.getDescription());
        assertEquals(product.getStatus(), createdProduct.getStatus());

        verify(repository).save(any(ProductEntity.class));
    }

	@Test
    @DisplayName("Should successfully return a specific ProductEntity in the database")
    public void shouldReturnAProductEntity() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(productEntity));

        Optional<Product> foundProduct = productPersistence.findById(product.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals(product.getId(), foundProduct.get().getId());
        assertEquals(product.getName(), foundProduct.get().getName());
        assertEquals(product.getCategory(), foundProduct.get().getCategory());
        assertEquals(product.getPrice(), foundProduct.get().getPrice());
        assertEquals(product.getDescription(), foundProduct.get().getDescription());
        assertEquals(product.getStatus(), foundProduct.get().getStatus());

        verify(repository).findById(any(UUID.class));
    }

	@Test
    @DisplayName("Should successfully update a ProductEntity to the database")
    public void shouldUpdateProductEntity() {
        when(repository.save(any(ProductEntity.class))).thenReturn(productEntity);

        productEntity.update(product);

        Product result = productPersistence.update(product);

        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getCategory(), result.getCategory());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getStatus(), result.getStatus());
        assertEquals(product.getDescription(), result.getDescription());
        assertNotNull(result.getCreatedAt());

        verify(repository).save(any(ProductEntity.class));
    }

	private void buildArranges() {
		UUID id = UUID.randomUUID();
		product = new Product(id, "Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE, new BigDecimal("99.99"),
				"Sanduíche de frango com salada", ProductStatusEnum.ACTIVE, LocalDateTime.now());
		productEntity = new ProductEntity(product);
	}

}
