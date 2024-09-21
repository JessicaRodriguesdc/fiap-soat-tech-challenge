package br.com.fiap.tech_challenge.infra.entrypoint.controller;

import br.com.fiap.tech_challenge.application.exceptions.AlreadyExistsException;
import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.application.usecase.product.CreateProductUseCase;
import br.com.fiap.tech_challenge.application.usecase.product.DeleteProductByIdUseCase;
import br.com.fiap.tech_challenge.application.usecase.product.FindProductsByCategoryUseCase;
import br.com.fiap.tech_challenge.application.usecase.product.UpdateProductUseCase;
import br.com.fiap.tech_challenge.domain.models.Product;
import br.com.fiap.tech_challenge.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.domain.models.enums.ProductStatusEnum;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPage;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPageable;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.PageResponseDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.ProductPageResponseDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.ProductRequestDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.ProductResponseDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.handler.ControllerAdvice;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.mapper.ProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	private MockMvc mockMvc;

	@Mock
	private CreateProductUseCase createProductUseCase;

	@Mock
	private UpdateProductUseCase updateProductUseCase;

	@Mock
	private FindProductsByCategoryUseCase findProductsByCategoryUseCase;

	@Mock
	private DeleteProductByIdUseCase deleteProductByIdUseCase;

	@Mock
	private ProductMapper mapper;

	@InjectMocks
	private ProductController productController;

	private final String baseUrl = "/v1/products";

	private Product product;

	private ProductRequestDTO requestDTO;

	private ProductResponseDTO responseDTO;

	private CustomPage domainPage;

	private ProductPageResponseDTO productPageResponseDto;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(productController)
			.setControllerAdvice(new ControllerAdvice())
			.build();
		this.buildArranges();
	}

	@Test
	@DisplayName("Should Create A New Product")
	void shouldCreateANewProduct() throws Exception {
		when(mapper.toProduct(requestDTO)).thenReturn(product);
		when(createProductUseCase.create(product)).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(product))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(responseDTO.id().toString()))
			.andExpect(jsonPath("$.name").value(responseDTO.name()))
			.andExpect(jsonPath("$.category").value(responseDTO.category().toString()))
			.andExpect(jsonPath("$.price").value(responseDTO.price().toString()))
			.andExpect(jsonPath("$.description").value(responseDTO.description()));
	}

	@Test
	@DisplayName("Should return Conflict when Create a Product already exists")
	void shouldReturnConflictWhenProductAlreadyExists() throws Exception {
		when(mapper.toProduct(requestDTO)).thenReturn(product);
		when(createProductUseCase.create(product)).thenThrow(AlreadyExistsException.class);

		mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(product))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Should Update A Exist Product")
	void shouldUpdateAProduct() throws Exception {
		when(mapper.toProduct(requestDTO)).thenReturn(product);
		when(updateProductUseCase.update(any(UUID.class), any(Product.class))).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.put(baseUrl.concat("/{id}"), product.getId())
			.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(product))
						.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(responseDTO.id().toString()))
			.andExpect(jsonPath("$.name").value(responseDTO.name()))
			.andExpect(jsonPath("$.category").value(responseDTO.category().toString()))
			.andExpect(jsonPath("$.price").value(responseDTO.price().toString()))
			.andExpect(jsonPath("$.description").value(responseDTO.description()));
	}

	@Test
	@DisplayName("Should return NotFound when Update a Product Doesn't Exist")
	void shouldReturnNotFoundWhenUpdateProductDoesNotExist() throws Exception {
		when(mapper.toProduct(requestDTO)).thenReturn(product);
		when(updateProductUseCase.update(any(UUID.class), any(Product.class))).thenThrow(DoesNotExistException.class);

		mockMvc.perform(MockMvcRequestBuilders.put(baseUrl.concat("/{id}"), product.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(product))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Should get Products by category successfully.")
	void shouldGetProductsByCategory() throws Exception {
		when(findProductsByCategoryUseCase.findByCategory(ProductCategoryEnum.MAIN_COURSE, 0, 10))
				.thenReturn(new CustomPageable<>(List.of(product), domainPage));

		mockMvc.perform(MockMvcRequestBuilders.get(baseUrl)
						.queryParam("category", ProductCategoryEnum.MAIN_COURSE.toString())
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.page.totalPages").value(productPageResponseDto.page().totalPages()))
				.andExpect(jsonPath("$.page.totalElements").value(productPageResponseDto.page().totalElements()))
				.andExpect(jsonPath("$.content[0].id").value(productPageResponseDto.content().getFirst().id().toString()))
				.andExpect(jsonPath("$.content[0].name").value(productPageResponseDto.content().getFirst().name()))
				.andExpect(jsonPath("$.content[0].category").value(productPageResponseDto.content().getFirst().category().toString()))
				.andExpect(jsonPath("$.content[0].price").value(productPageResponseDto.content().getFirst().price().toString()))
				.andExpect(jsonPath("$.content[0].description").value(productPageResponseDto.content().getFirst().description()));
	}

	@Test
	@DisplayName("Should delete a Product by id successfully.")
	void shouldDeleteAProductById() throws Exception {
		UUID id = UUID.randomUUID();

		mockMvc.perform(MockMvcRequestBuilders.delete("/v1/products/{id}", id).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());

		verify(deleteProductByIdUseCase).delete(id);
	}

	private void buildArranges() {
		var id = UUID.randomUUID();
		var name = "Sanduíche de Frango";
		var category = ProductCategoryEnum.MAIN_COURSE;
		var price = BigDecimal.valueOf(99.99);
		var description = "Sanduíche de frango com salada";
		var status = ProductStatusEnum.ACTIVE;
		var createdAt = LocalDateTime.now();

		product = new Product(id, name, category, price, description, status, createdAt);
		requestDTO = new ProductRequestDTO(name, category, price, description);
		responseDTO = new ProductResponseDTO(product);
		domainPage = new CustomPage(1L, 1L, 1L, 1L, true, true, 1L, false);

		productPageResponseDto = new ProductPageResponseDTO(List.of(responseDTO),
				new PageResponseDTO(1L, 1L, 1L, 1L, true, true, 1L, false));
	}

	public static String asJsonString(final Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			return mapper.writeValueAsString(obj);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
