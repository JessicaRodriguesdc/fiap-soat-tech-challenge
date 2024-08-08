package br.com.fiap.tech_challenge.core.domain.usecases.product;

import br.com.fiap.tech_challenge.core.domain.models.product.Product;

import java.util.UUID;

public interface UpdateProductUseCase {

	Product update(UUID id, Product product);

}