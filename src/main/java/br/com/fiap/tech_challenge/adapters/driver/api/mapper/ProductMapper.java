package br.com.fiap.tech_challenge.adapters.driver.api.mapper;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.ProductRequestDTO;
import br.com.fiap.tech_challenge.core.domain.models.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

	public Product toProduct(ProductRequestDTO dto) {
		return new Product(dto.name(), dto.category(), dto.price(), dto.description());
	}

}