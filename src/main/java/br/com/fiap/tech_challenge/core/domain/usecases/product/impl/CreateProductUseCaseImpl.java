package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.CreateProductUseCase;

public class CreateProductUseCaseImpl implements CreateProductUseCase {

	private final ProductPersistence persistence;

	public CreateProductUseCaseImpl(ProductPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public Product create(Product product) {
		return persistence.create(product);
	}

}