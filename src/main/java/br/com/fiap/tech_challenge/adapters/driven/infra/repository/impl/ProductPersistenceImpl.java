package br.com.fiap.tech_challenge.adapters.driven.infra.repository.impl;

import br.com.fiap.tech_challenge.adapters.driven.infra.entities.ProductEntity;
import br.com.fiap.tech_challenge.adapters.driven.infra.mapper.PageMapper;
import br.com.fiap.tech_challenge.adapters.driven.infra.repository.ProductRepository;
import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.pageable.CustomPageable;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
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

	@Override
	public void delete(UUID id) {
		repository.findById(id).ifPresent(repository::save);
	}

}