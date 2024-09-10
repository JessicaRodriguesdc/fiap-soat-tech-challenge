package br.com.fiap.tech_challenge.application.usecase.product.impl;

import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.domain.models.Product;
import br.com.fiap.tech_challenge.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPageable;
import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.product.FindProductsByCategoryUseCase;

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