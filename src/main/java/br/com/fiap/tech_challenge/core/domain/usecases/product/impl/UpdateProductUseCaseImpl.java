package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.product.Product;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;

import br.com.fiap.tech_challenge.core.domain.usecases.product.UpdateProductUseCase;

import java.util.UUID;

public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

	private final ProductPersistence persistence;

	public UpdateProductUseCaseImpl(ProductPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public Product update(UUID id, Product product) {
		var productFound = persistence.findById(id);

		if (productFound.isEmpty()) {
			throw new DoesNotExistException("Product Doesn't Exist");
		}

		var productUpdated = productFound.get().update(product);

		return persistence.update(productUpdated);
	}

}