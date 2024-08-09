package br.com.fiap.tech_challenge.core.domain.usecases.product;

import br.com.fiap.tech_challenge.core.domain.models.Product;

public interface CreateProductUseCase {

	Product create(Product product);

}