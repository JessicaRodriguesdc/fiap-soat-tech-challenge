package br.com.fiap.tech_challenge.application.usecase.product;

import br.com.fiap.tech_challenge.domain.models.Product;
import br.com.fiap.tech_challenge.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPageable;

public interface FindProductsByCategoryUseCase {

	CustomPageable<Product> findByCategory(ProductCategoryEnum category, Integer page, Integer size);

}
