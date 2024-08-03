package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.mapper.ProductMapper;
import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;
import br.com.fiap.tech_challenge.adapters.driver.api.openapi.ProductControllerOpenApi;
import br.com.fiap.tech_challenge.core.domain.usecases.product.CreateProductUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.product.UpdateProductUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.product.DeleteProductByIdUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.product.GetProductsByCategoryUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/products")
public class ProductController implements ProductControllerOpenApi {
    private final CreateProductUseCase createProductUseCase;
    private final GetProductsByCategoryUseCase getProductsByCategoryUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductByIdUseCase deleteProductByIdUseCase;
    private final ProductMapper mapper;

    public ProductController(CreateProductUseCase createProductUseCase, GetProductsByCategoryUseCase getProductsByCategoryUseCase,
                             UpdateProductUseCase updateProductUseCase, DeleteProductByIdUseCase deleteProductByIdUseCase, ProductMapper mapper) {
        this.createProductUseCase = createProductUseCase;
        this.getProductsByCategoryUseCase = getProductsByCategoryUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductByIdUseCase = deleteProductByIdUseCase;
        this.mapper = mapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO productDTO){
        var productSaved = createProductUseCase.create(mapper.toProduct(productDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponseDTO(productSaved));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getProductsByCategory(
            @RequestParam CategoryProductEnum category,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        var products = getProductsByCategoryUseCase
                .getByCategory(category, PageRequest.of(page, size))
                .map(ProductResponseDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable("id") final UUID id, @RequestBody @Valid ProductRequestDTO productDTO){
        var product = updateProductUseCase.update(id, mapper.toProduct(productDTO));
        return ResponseEntity.status(HttpStatus.OK).body(new ProductResponseDTO(product));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteProductById(@PathVariable UUID id) {
        deleteProductByIdUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}