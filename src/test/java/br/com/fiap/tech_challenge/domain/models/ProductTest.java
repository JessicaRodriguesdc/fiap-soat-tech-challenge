package br.com.fiap.tech_challenge.domain.models;

import br.com.fiap.tech_challenge.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.domain.models.enums.ProductStatusEnum;
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

	private String name;

	private ProductCategoryEnum category;

	private BigDecimal price;

	private String description;

	private ProductStatusEnum status;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
	@DisplayName("Should create a Product via constructor successfully")
	public void shouldCreateProductViaConstructor() {
		assertNotNull(product.getId());
		assertEquals(name, product.getName());
		assertEquals(category, product.getCategory());
		assertEquals(price, product.getPrice());
		assertEquals(description, product.getDescription());
		assertEquals(status, product.getStatus());
		assertNotNull(product.getCreatedAt());
	}

	@Test
	@DisplayName("Should update a Product successfully")
	public void shouldUpdateProduct() {
		product.update(product);

		assertEquals(name, product.getName());
		assertEquals(category, product.getCategory());
		assertEquals(price, product.getPrice());
		assertEquals(description, product.getDescription());
	}

	private void buildArranges() {
		UUID id = UUID.randomUUID();
		name = "Sanduíche de Frango";
		category = ProductCategoryEnum.MAIN_COURSE;
		price = BigDecimal.valueOf(99.99);
		description = "Sanduíche de frango com salada";
		status = ProductStatusEnum.ACTIVE;
		var createdAt = LocalDateTime.now();

		product = new Product(id, name, category, price, description, status, createdAt);
	}

}
