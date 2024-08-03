package br.com.fiap.tech_challenge.core.domain.models;

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

public class ProductTest {

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product(UUID.randomUUID(), "Sanduíche de Frango", CategoryProductEnum.MAIN_COURSE,
                new BigDecimal("99.99"), "Sanduíche de frango com salada",
                StatusProductEnum.ACTIVE, LocalDateTime.now());
    }

    @Test
    @DisplayName("Deve criar um Product do tipo MAIN_COURSE por meio do construtor com sucesso.")
    public void testProductConstructor() {
        assertNotNull(product.getId());
        assertEquals("Sanduíche de Frango", product.getName());
        assertEquals(CategoryProductEnum.MAIN_COURSE, product.getCategory());
        assertEquals(new BigDecimal("99.99"), product.getPrice());
        assertEquals("Sanduíche de frango com salada", product.getDescription());
        assertEquals(StatusProductEnum.ACTIVE, product.getStatus());
        assertNotNull(product.getCreatedAt());
    }

    @Test
    @DisplayName("Deve atualizar um Product do tipo MAIN_COURSE com sucesso.")
    public void testProductUpdate() {
        Product newProduct = new Product("Sanduíche de Bacon", CategoryProductEnum.MAIN_COURSE,
                new BigDecimal("149.99"), "Sanduíche de bacon com salada");

        product.update(newProduct);

        assertEquals("Sanduíche de Bacon", product.getName());
        assertEquals(CategoryProductEnum.MAIN_COURSE, product.getCategory());
        assertEquals(new BigDecimal("149.99"), product.getPrice());
        assertEquals("Sanduíche de bacon com salada", product.getDescription());
    }
}
