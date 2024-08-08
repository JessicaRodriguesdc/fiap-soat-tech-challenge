package br.com.fiap.tech_challenge.core.domain.usecases.product;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.ProductCategoryEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetProductsByCategoryUseCase {

	Page<Product> getByCategory(ProductCategoryEnum category, Pageable pageable);

}
