package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductPersistence {
    Page<Product> findByCategory(String category, Pageable pageable);

    Product create(Product product);
    List<Product> findAllByIds(List<UUID> ids);
    Optional<Product> findById(UUID id);

    void delete(UUID id);
}
