package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.controller.dto.ProductRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.controller.dto.ProductResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.mapper.ProductMapper;
import br.com.fiap.tech_challenge.core.domain.usecases.product.CreateProductUseCase;
import jakarta.validation.Valid;
import br.com.fiap.tech_challenge.core.domain.usecases.product.DeleteProductByIdUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.product.GetProductsByCategoryUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final GetProductsByCategoryUseCase getProductsByCategoryUseCase;
    private final DeleteProductByIdUseCase deleteProductByIdUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final ProductMapper mapper;

    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getProductsByCategory(
            @RequestParam String category,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        var products = getProductsByCategoryUseCase
                .getByCategory(category, PageRequest.of(page, size))
                .map(ProductResponseDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    @PostMapping
    private ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO productDTO){
        var productSaved = createProductUseCase.create(mapper.toProduct(productDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponseDTO(productSaved));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable UUID id) {
        deleteProductByIdUseCase.delete(id);
    }
}
