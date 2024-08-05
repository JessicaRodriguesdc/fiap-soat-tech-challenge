package br.com.fiap.tech_challenge.adapters.driven.infra.repository.impl;

import br.com.fiap.tech_challenge.adapters.driven.infra.entities.ProductEntity;
import br.com.fiap.tech_challenge.adapters.driven.infra.repository.ProductRepository;
import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductProductEnum;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
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
	public Page<Product> findByCategory(ProductCategoryEnum category, Pageable pageable) {
		return repository.findByCategory(category, pageable).map(ProductEntity::toProduct);
	}

	@Override
	public Optional<Product> findById(UUID id) {
		return repository.findById(id).map(ProductEntity::toProduct);
	}

	@Override
	public Product update(Product product) {
		var productEntity = new ProductEntity().update(product);
		var productUpdated = repository.save(productEntity);
		return productUpdated.toProduct();
	}

	@Override
	public void delete(UUID id) {
		repository.findById(id).ifPresent(product -> {
			product.setStatus(ProductProductEnum.INACTIVE);
			repository.save(product);
		});
	}

}