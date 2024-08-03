package br.com.fiap.tech_challenge.adapters.driven.infra.repository;

import br.com.fiap.tech_challenge.adapters.driven.infra.entities.ProductEntity;
import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.StatusProductEnum;
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

public class ProductPersistenceImplTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductPersistenceImpl productPersistence;

    private Product product;
    private ProductEntity productEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        UUID id = UUID.randomUUID();
        product = new Product(id, "Sanduíche de Frango", CategoryProductEnum.MAIN_COURSE,
                new BigDecimal("99.99"), "Sanduíche de frango com salada",
                StatusProductEnum.ACTIVE, LocalDateTime.now());
        productEntity = new ProductEntity(product);
    }

    @Test
    @DisplayName("Deve salvar na base de dados um ProductEntity com sucesso.")
    public void testCreate() {
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
    @DisplayName("Deve retornar na base de dados um ProductEntity específico com sucesso.")
    public void testFindById() {
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
    @DisplayName("Deve atualizar na base de dados um ProductEntity com sucesso.")
    public void testUpdate() {
        when(repository.save(any(ProductEntity.class))).thenReturn(productEntity);

        Product updatedProduct = new Product(product.getId(), "Sanduíche de Bacon", CategoryProductEnum.MAIN_COURSE,
                new BigDecimal("149.99"), "Sanduíche de bacon com salada", StatusProductEnum.ACTIVE, LocalDateTime.now());
        productEntity.update(updatedProduct);

        Product result = productPersistence.update(updatedProduct);

        assertEquals(updatedProduct.getId(), result.getId());
        assertEquals("Sanduíche de Bacon", result.getName());
        assertEquals(CategoryProductEnum.MAIN_COURSE, result.getCategory());
        assertEquals(new BigDecimal("149.99"), result.getPrice());
        assertEquals(StatusProductEnum.ACTIVE, result.getStatus());
        assertEquals("Sanduíche de bacon com salada", result.getDescription());
        assertNotNull(result.getCreatedAt());

        verify(repository).save(any(ProductEntity.class));
    }

}
