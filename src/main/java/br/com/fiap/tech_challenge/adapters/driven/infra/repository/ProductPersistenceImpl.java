package br.com.fiap.tech_challenge.adapters.driven.infra.repository;

import br.com.fiap.tech_challenge.adapters.driven.infra.entities.ProductEntity;
import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductPersistenceImpl implements ProductPersistence {

    private final ProductRepository repository;

    public ProductPersistenceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product product) {
        var productEntity = new ProductEntity(product);
        var productSaved = repository.save(productEntity);
        return productSaved.toProduct();
    }

    @Override
    public List<Product> findAllByIds(List<UUID> ids) {
        var products = repository.findAllById(ids);
        return products.stream().map(ProductEntity::toProduct).toList();
    }

}