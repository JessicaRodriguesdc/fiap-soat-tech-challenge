package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.product.PageableProduct;
import br.com.fiap.tech_challenge.core.domain.models.product.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;

import java.util.Optional;
import java.util.UUID;

public interface ProductPersistence {

	PageableProduct findByCategory(ProductCategoryEnum category, Integer page, Integer size);

	Product create(Product product);

	Optional<Product> findById(UUID id);

	Product update(Product product);

	void delete(UUID id);

}