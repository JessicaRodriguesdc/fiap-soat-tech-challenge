package br.com.fiap.tech_challenge.core.domain.usecases.product;

import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.core.domain.models.ProductPage;

public interface FindProductsByCategoryUseCase {

	ProductPage findByCategory(ProductCategoryEnum category, Integer page, Integer size);

}
