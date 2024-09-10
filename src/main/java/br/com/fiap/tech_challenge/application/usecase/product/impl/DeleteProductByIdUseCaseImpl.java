package br.com.fiap.tech_challenge.application.usecase.product.impl;

import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.domain.models.enums.ProductStatusEnum;
import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.product.DeleteProductByIdUseCase;

import java.util.UUID;

public class DeleteProductByIdUseCaseImpl implements DeleteProductByIdUseCase {

	private final ProductPersistence persistence;

	public DeleteProductByIdUseCaseImpl(ProductPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public void delete(UUID id) {
		var productOpt = persistence.findById(id);

		if (productOpt.isEmpty()) {
			throw new DoesNotExistException("Product not found");
		}

		var product = productOpt.get();
		product.setStatus(ProductStatusEnum.INACTIVE);
		persistence.update(product);
	}

}