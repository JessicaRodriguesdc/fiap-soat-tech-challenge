package br.com.fiap.tech_challenge.application.usecase.product.impl;

import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.product.CreateProductUseCase;
import br.com.fiap.tech_challenge.domain.models.Product;

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