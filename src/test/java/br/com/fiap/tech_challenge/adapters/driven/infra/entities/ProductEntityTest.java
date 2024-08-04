package br.com.fiap.tech_challenge.adapters.driven.infra.entities;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.StatusProductEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductEntityTest {

    private ProductEntity productEntity;

    @BeforeEach
    public void setUp() {
        productEntity = new ProductEntity(UUID.randomUUID(), "Sanduíche de Frango", CategoryProductEnum.MAIN_COURSE,
                new BigDecimal("99.99"), "Sanduíche de frango com salada");
        productEntity.setStatus(StatusProductEnum.ACTIVE);
    }

    @Test
    @DisplayName("Should create a ProductEntity of type MAIN_COURSE via constructor successfully.")
    public void testProductEntityConstructor() {
        assertNotNull(productEntity.getId());
        assertEquals("Sanduíche de Frango", productEntity.getName());
        assertEquals(CategoryProductEnum.MAIN_COURSE, productEntity.getCategory());
        assertEquals(new BigDecimal("99.99"), productEntity.getPrice());
        assertEquals("Sanduíche de frango com salada", productEntity.getDescription());
        assertEquals(StatusProductEnum.ACTIVE, productEntity.getStatus());
    }

    @Test
    @DisplayName("Should update a ProductEntity of type MAIN_COURSE successfully.")
    public void testProductEntityUpdate() {
        Product product = new Product(UUID.randomUUID(), "Sanduíche de Bacon", CategoryProductEnum.MAIN_COURSE,
                new BigDecimal("149.99"), "Sanduíche de bacon com salada", StatusProductEnum.INACTIVE, LocalDateTime.now());

        productEntity.update(product);

        assertEquals(product.getId(), productEntity.getId());
        assertEquals("Sanduíche de Bacon", productEntity.getName());
        assertEquals(CategoryProductEnum.MAIN_COURSE, productEntity.getCategory());
        assertEquals(new BigDecimal("149.99"), productEntity.getPrice());
        assertEquals("Sanduíche de bacon com salada", productEntity.getDescription());
        assertEquals(StatusProductEnum.INACTIVE, productEntity.getStatus());
        assertNotNull(productEntity.getCreatedAt());
    }

    @Test
    @DisplayName("Should convert a ProductEntity object to Product successfully.")
    public void testToProduct() {
        Product product = productEntity.toProduct();

        assertEquals(productEntity.getId(), product.getId());
        assertEquals(productEntity.getName(), product.getName());
        assertEquals(productEntity.getCategory(), product.getCategory());
        assertEquals(productEntity.getPrice(), product.getPrice());
        assertEquals(productEntity.getDescription(), product.getDescription());
        assertEquals(productEntity.getStatus(), product.getStatus());
        assertEquals(productEntity.getCreatedAt(), product.getCreatedAt());
    }
}
