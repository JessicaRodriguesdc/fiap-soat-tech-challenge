package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.product.PageableProduct;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.GetProductsByCategoryUseCase;

public class GetProductsByCategoryUseCaseImpl implements GetProductsByCategoryUseCase {

	private final ProductPersistence persistence;

	public GetProductsByCategoryUseCaseImpl(ProductPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public PageableProduct getByCategory(ProductCategoryEnum category, Integer page, Integer size) {
		var products = persistence.findByCategory(category, page, size);

		if (products.getContent().isEmpty()) {
			throw new DoesNotExistException("Products not found by category");
		}

		return products;
	}

}