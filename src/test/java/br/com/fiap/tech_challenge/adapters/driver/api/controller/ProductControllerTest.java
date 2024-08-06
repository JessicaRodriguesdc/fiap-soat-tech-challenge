package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.mapper.ProductMapper;
import br.com.fiap.tech_challenge.core.domain.models.product.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductStatusEnum;
import br.com.fiap.tech_challenge.core.domain.usecases.product.CreateProductUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.product.UpdateProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

	private MockMvc mockMvc;

	@Mock
	private CreateProductUseCase createProductUseCase;

	@Mock
	private UpdateProductUseCase updateProductUseCase;

	@Mock
	private ProductMapper mapper;

	@InjectMocks
	private ProductController productController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	@DisplayName("Should create a Product via the create endpoint successfully.")
	public void testCreateProduct() throws Exception {
		UUID id = UUID.randomUUID();
		Product product = new Product(id, "Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE,
				BigDecimal.valueOf(99.99), "Sanduíche de frango com salada", ProductStatusEnum.ACTIVE,
				LocalDateTime.now());
		ProductRequestDTO requestDTO = new ProductRequestDTO("Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE,
				BigDecimal.valueOf(99.99), "Sanduíche de frango com salada");
		ProductResponseDTO responseDTO = new ProductResponseDTO(product);

		when(mapper.toProduct(requestDTO)).thenReturn(product);
		when(createProductUseCase.create(product)).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/products")
			.contentType(MediaType.APPLICATION_JSON)
			.content(
					"{\"name\":\"Sanduíche de Frango\",\"category\":\"MAIN_COURSE\",\"price\":99.99,\"description\":\"Sanduíche de frango com salada\"}")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(responseDTO.id().toString()))
			.andExpect(jsonPath("$.name").value(responseDTO.name().toString()))
			.andExpect(jsonPath("$.category").value(responseDTO.category().toString()))
			.andExpect(jsonPath("$.price").value(responseDTO.price().toString()))
			.andExpect(jsonPath("$.description").value(responseDTO.description().toString()));
	}

	@Test
	@DisplayName("Should create a Product via the update endpoint successfully.")
	public void testUpdateProduct() throws Exception {
		UUID id = UUID.randomUUID();
		Product product = new Product(id, "Sanduíche de Bacon", ProductCategoryEnum.MAIN_COURSE,
				BigDecimal.valueOf(199.99), "Sanduíche de bacon com salada", ProductStatusEnum.ACTIVE,
				LocalDateTime.now());
		ProductRequestDTO requestDTO = new ProductRequestDTO("Sanduíche de Bacon", ProductCategoryEnum.MAIN_COURSE,
				BigDecimal.valueOf(199.99), "Sanduíche de bacon com salada");
		ProductResponseDTO responseDTO = new ProductResponseDTO(product);

		when(mapper.toProduct(requestDTO)).thenReturn(product);
		when(updateProductUseCase.update(any(UUID.class), any(Product.class))).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.put("/v1/products/{id}", id)
			.contentType(MediaType.APPLICATION_JSON)
			.content(
					"{\"name\":\"Sanduíche de Bacon\",\"category\":\"MAIN_COURSE\",\"price\":199.99,\"description\":\"Sanduíche de bacon com salada\"}")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(responseDTO.id().toString()))
			.andExpect(jsonPath("$.name").value(responseDTO.name().toString()))
			.andExpect(jsonPath("$.category").value(responseDTO.category().toString()))
			.andExpect(jsonPath("$.price").value(responseDTO.price().toString()))
			.andExpect(jsonPath("$.description").value(responseDTO.description().toString()));
	}

}
