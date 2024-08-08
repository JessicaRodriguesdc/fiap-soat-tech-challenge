package br.com.fiap.tech_challenge.core.domain.usecases.product;

import br.com.fiap.tech_challenge.core.domain.models.product.PageableProduct;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;

public interface GetProductsByCategoryUseCase {

	PageableProduct getByCategory(ProductCategoryEnum category, Integer page, Integer size);

}
