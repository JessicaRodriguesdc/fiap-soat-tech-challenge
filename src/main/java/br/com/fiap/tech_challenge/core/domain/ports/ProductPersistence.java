package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.ProductPage;

import java.util.Optional;
import java.util.UUID;

public interface ProductPersistence {

	ProductPage findByCategory(ProductCategoryEnum category, Integer page, Integer size);

	Product create(Product product);

	Optional<Product> findById(UUID id);

	Product update(Product product);

	void delete(UUID id);

}