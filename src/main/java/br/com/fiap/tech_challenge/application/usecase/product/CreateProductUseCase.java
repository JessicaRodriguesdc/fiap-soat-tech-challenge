package br.com.fiap.tech_challenge.application.usecase.product;

import br.com.fiap.tech_challenge.domain.models.Product;

public interface CreateProductUseCase {

	Product create(Product product);

}