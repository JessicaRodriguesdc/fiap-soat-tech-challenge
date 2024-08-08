package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.mapper.ProductMapper;
import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.StatusProductEnum;
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
    private GetProductsByCategoryUseCase getProductsByCategoryUseCase;

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

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(productController)
			.setControllerAdvice(new ControllerAdvice())
			.build();
		this.buildArranges();
	}

	@Test
	@DisplayName("Should Create A New Product")
	public void shouldCreateANewProduct() throws Exception {
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
	public void shouldUpdateAProduct() throws Exception {
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

    @Test
    @DisplayName("Should get Products by category successfully.")
    public void testGetProductsByCategory() throws Exception {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Sanduíche de Bacon", CategoryProductEnum.MAIN_COURSE, BigDecimal.valueOf(199.99), "Sanduíche de bacon com salada", StatusProductEnum.ACTIVE, LocalDateTime.now());
        var page = PageRequest.of(0, 10);

        when(getProductsByCategoryUseCase.getByCategory(CategoryProductEnum.MAIN_COURSE, page))
                .thenReturn(new PageImpl<>(List.of(product)));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/products")
                        .queryParam("category", CategoryProductEnum.MAIN_COURSE.name())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should delete a Product by id successfully.")
    public void testDeleteProductById() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/products/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(deleteProductByIdUseCase).delete(id);
    }
}
