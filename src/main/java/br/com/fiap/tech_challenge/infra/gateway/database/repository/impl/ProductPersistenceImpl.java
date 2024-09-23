package br.com.fiap.tech_challenge.infra.gateway.database.repository.impl;

import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.domain.models.Product;
import br.com.fiap.tech_challenge.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPageable;
import br.com.fiap.tech_challenge.infra.gateway.database.entities.ProductEntity;
import br.com.fiap.tech_challenge.infra.gateway.database.mapper.PageMapper;
import br.com.fiap.tech_challenge.infra.gateway.database.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProductPersistenceImpl implements ProductPersistence {

	private final ProductRepository repository;

	private final PageMapper<Product> mapper;

	public ProductPersistenceImpl(ProductRepository repository, PageMapper<Product> mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Product create(Product product) {
		var productEntity = new ProductEntity(product);
		var productSaved = repository.save(productEntity);
		return productSaved.toProduct();
	}

	@Override
	public CustomPageable<Product> findByCategory(ProductCategoryEnum category, Integer page, Integer size) {
		var products = repository.findByCategory(category, PageRequest.of(page, size)).map(ProductEntity::toProduct);
		return mapper.toDomainPage(products);
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

}