package br.com.fiap.tech_challenge.infra.entrypoint.controller.mapper;

import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.ProductRequestDTO;
import br.com.fiap.tech_challenge.domain.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

	public Product toProduct(ProductRequestDTO dto) {
		return new Product(dto.name(), dto.category(), dto.price(), dto.description());
	}

}