package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.GetProductsByCategoryUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetProductsByCategoryUseCaseImpl implements GetProductsByCategoryUseCase {

	private final ProductPersistence persistence;

	public GetProductsByCategoryUseCaseImpl(ProductPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public Page<Product> getByCategory(ProductCategoryEnum category, Pageable pageable) {
		var products = persistence.findByCategory(category, pageable);

		if (products.isEmpty()) {
			throw new DoesNotExistException("Products not found by category");
		}

		return products;
	}

}