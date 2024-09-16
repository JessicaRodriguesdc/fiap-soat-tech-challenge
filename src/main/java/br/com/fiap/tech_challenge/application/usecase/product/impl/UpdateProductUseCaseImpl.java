package br.com.fiap.tech_challenge.application.usecase.product.impl;

import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.product.UpdateProductUseCase;
import br.com.fiap.tech_challenge.domain.models.Product;

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