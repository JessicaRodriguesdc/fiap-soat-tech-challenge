package br.com.fiap.tech_challenge.core.domain.models;

import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.product.Product;
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
		product = new Product(UUID.randomUUID(), "Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE,
				new BigDecimal("99.99"), "Sanduíche de frango com salada", ProductStatusEnum.ACTIVE,
				LocalDateTime.now());
	}

	@Test
	@DisplayName("Should create a Product of type MAIN_COURSE via constructor successfully.")
	public void testProductConstructor() {
		assertNotNull(product.getId());
		assertEquals("Sanduíche de Frango", product.getName());
		assertEquals(ProductCategoryEnum.MAIN_COURSE, product.getCategory());
		assertEquals(new BigDecimal("99.99"), product.getPrice());
		assertEquals("Sanduíche de frango com salada", product.getDescription());
		assertEquals(ProductStatusEnum.ACTIVE, product.getStatus());
		assertNotNull(product.getCreatedAt());
	}

	@Test
	@DisplayName("Should update a Product of type MAIN_COURSE successfully.")
	public void testProductUpdate() {
		Product newProduct = new Product("Sanduíche de Bacon", ProductCategoryEnum.MAIN_COURSE,
				new BigDecimal("149.99"), "Sanduíche de bacon com salada");

		product.update(newProduct);

		assertEquals("Sanduíche de Bacon", product.getName());
		assertEquals(ProductCategoryEnum.MAIN_COURSE, product.getCategory());
		assertEquals(new BigDecimal("149.99"), product.getPrice());
		assertEquals("Sanduíche de bacon com salada", product.getDescription());
	}

}
