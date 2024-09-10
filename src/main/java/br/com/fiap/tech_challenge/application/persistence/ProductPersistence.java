package br.com.fiap.tech_challenge.application.persistence;

import br.com.fiap.tech_challenge.domain.models.Product;
import br.com.fiap.tech_challenge.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductPersistence {

	CustomPageable<Product> findByCategory(ProductCategoryEnum category, Integer page, Integer size);

	Product create(Product product);

	Optional<Product> findById(UUID id);

	Product update(Product product);

}