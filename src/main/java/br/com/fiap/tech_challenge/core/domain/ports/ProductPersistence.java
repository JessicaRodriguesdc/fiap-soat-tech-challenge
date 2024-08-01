package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductPersistence {
    Page<Product> findByCategory(CategoryProductEnum category, Pageable pageable);
    Product create(Product product);
    Optional<Product> findById(UUID id);
    Product update(Product product);
    void delete(UUID id);
}