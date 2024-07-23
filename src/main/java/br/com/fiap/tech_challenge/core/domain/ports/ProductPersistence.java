package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductPersistence {
    Page<Product> findByCategory(String category, Pageable pageable);

    Optional<Product> findById(UUID id);

    void delete(UUID id);
}
