package br.com.fiap.tech_challenge.core.domain.usecases.product;

import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetProductsByCategoryUseCase {
    Page<Product> getByCategory(CategoryProductEnum category, Pageable pageable);
}
