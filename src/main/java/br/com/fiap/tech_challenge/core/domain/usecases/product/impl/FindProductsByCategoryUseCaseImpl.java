package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.ProductPage;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.FindProductsByCategoryUseCase;

public class FindProductsByCategoryUseCaseImpl implements FindProductsByCategoryUseCase {

	private final ProductPersistence persistence;

	public FindProductsByCategoryUseCaseImpl(ProductPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public ProductPage findByCategory(ProductCategoryEnum category, Integer page, Integer size) {
		var products = persistence.findByCategory(category, page, size);

		if (products.getContent().isEmpty()) {
			throw new DoesNotExistException("Products not found by category");
		}

		return products;
	}

}