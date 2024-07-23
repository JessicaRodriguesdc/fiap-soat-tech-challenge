package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.controller.dto.ProductResponseDTO;
import br.com.fiap.tech_challenge.core.domain.usecases.product.DeleteProductByIdUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.product.GetProductsByCategoryUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final GetProductsByCategoryUseCase getProductsByCategoryUseCase;
    private final DeleteProductByIdUseCase deleteProductByIdUseCase;

    public ProductController(GetProductsByCategoryUseCase getProductsByCategoryUseCase, DeleteProductByIdUseCase deleteProductByIdUseCase) {
        this.getProductsByCategoryUseCase = getProductsByCategoryUseCase;
        this.deleteProductByIdUseCase = deleteProductByIdUseCase;
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
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable UUID id) {
        deleteProductByIdUseCase.delete(id);
    }
}
