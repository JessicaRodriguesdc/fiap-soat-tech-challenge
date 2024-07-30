package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.mapper.ProductMapper;
import br.com.fiap.tech_challenge.adapters.driver.api.openapi.ProductControllerOpenApi;
import br.com.fiap.tech_challenge.core.domain.usecases.product.CreateProductUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/v1/products")
public class ProductController implements ProductControllerOpenApi {

    private final CreateProductUseCase createProductUseCase;
    private final ProductMapper mapper;

    public ProductController(CreateProductUseCase createProductUseCase, ProductMapper mapper) {
        this.createProductUseCase = createProductUseCase;
        this.mapper = mapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO productDTO){
        var productSaved = createProductUseCase.create(mapper.toProduct(productDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponseDTO(productSaved));
    }

}