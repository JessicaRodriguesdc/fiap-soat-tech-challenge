package br.com.fiap.tech_challenge.infra.entrypoint.controller;

import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.ProductPageResponseDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.ProductRequestDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.ProductResponseDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.mapper.ProductMapper;
import br.com.fiap.tech_challenge.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.openapi.ProductControllerOpenApi;
import br.com.fiap.tech_challenge.application.usecase.product.CreateProductUseCase;
import br.com.fiap.tech_challenge.application.usecase.product.UpdateProductUseCase;
import br.com.fiap.tech_challenge.application.usecase.product.DeleteProductByIdUseCase;
import br.com.fiap.tech_challenge.application.usecase.product.FindProductsByCategoryUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/products")
public class ProductController implements ProductControllerOpenApi {

	private final CreateProductUseCase createProductUseCase;

	private final FindProductsByCategoryUseCase findProductsByCategoryUseCase;

	private final UpdateProductUseCase updateProductUseCase;

	private final DeleteProductByIdUseCase deleteProductByIdUseCase;

	private final ProductMapper mapper;

	public ProductController(CreateProductUseCase createProductUseCase,
			FindProductsByCategoryUseCase findProductsByCategoryUseCase, UpdateProductUseCase updateProductUseCase,
			DeleteProductByIdUseCase deleteProductByIdUseCase, ProductMapper mapper) {
		this.createProductUseCase = createProductUseCase;
		this.findProductsByCategoryUseCase = findProductsByCategoryUseCase;
		this.updateProductUseCase = updateProductUseCase;
		this.deleteProductByIdUseCase = deleteProductByIdUseCase;
		this.mapper = mapper;
	}

	@Override
	@PostMapping
	public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO productDTO) {
		var productSaved = createProductUseCase.create(mapper.toProduct(productDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponseDTO(productSaved));
	}

	@Override
	@GetMapping
	public ResponseEntity<ProductPageResponseDTO> findProductsByCategory(@RequestParam ProductCategoryEnum category,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		var pageableProduct = findProductsByCategoryUseCase.findByCategory(category, page, size);
		return ResponseEntity.status(HttpStatus.OK).body(new ProductPageResponseDTO(pageableProduct));
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> update(@PathVariable("id") final UUID id,
			@RequestBody @Valid ProductRequestDTO productDTO) {
		var product = updateProductUseCase.update(id, mapper.toProduct(productDTO));
		return ResponseEntity.status(HttpStatus.OK).body(new ProductResponseDTO(product));
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable UUID id) {
		deleteProductByIdUseCase.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}