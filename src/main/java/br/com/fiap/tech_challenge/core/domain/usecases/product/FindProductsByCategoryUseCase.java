package br.com.fiap.tech_challenge.core.domain.usecases.product;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.pageable.CustomPageable;

public interface FindProductsByCategoryUseCase {

	CustomPageable<Product> findByCategory(ProductCategoryEnum category, Integer page, Integer size);

}
