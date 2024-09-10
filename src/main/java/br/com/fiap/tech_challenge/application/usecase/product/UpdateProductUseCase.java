package br.com.fiap.tech_challenge.application.usecase.product;

import br.com.fiap.tech_challenge.domain.models.Product;

import java.util.UUID;

public interface UpdateProductUseCase {

	Product update(UUID id, Product product);

}