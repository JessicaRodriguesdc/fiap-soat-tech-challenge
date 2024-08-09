package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.pageable.CustomPageable;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.FindProductsByCategoryUseCase;

public class FindProductsByCategoryUseCaseImpl implements FindProductsByCategoryUseCase {

	private final ProductPersistence persistence;

	public FindProductsByCategoryUseCaseImpl(ProductPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public CustomPageable<Product> findByCategory(ProductCategoryEnum category, Integer page, Integer size) {
		var products = persistence.findByCategory(category, page, size);

		if (products.content().isEmpty()) {
			throw new DoesNotExistException("Products not found by category");
		}

		return products;
	}

}