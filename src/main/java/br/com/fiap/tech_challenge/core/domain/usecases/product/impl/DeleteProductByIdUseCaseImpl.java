package br.com.fiap.tech_challenge.core.domain.usecases.product.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductStatusEnum;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.product.DeleteProductByIdUseCase;

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