package br.com.fiap.tech_challenge.adapters.driver.api.mapper;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.CreateOrderRequestDTO;
import br.com.fiap.tech_challenge.core.domain.usecases.order.dto.CreateOrderDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMapper {

	public CreateOrderDTO toCreateOrder(CreateOrderRequestDTO dto) {
		return new CreateOrderDTO(dto.customerId() != null ? UUID.fromString(dto.customerId()) : null,
				dto.products()
					.stream()
					.map(orderProducts -> new CreateOrderDTO.OrderProducts(UUID.fromString(orderProducts.id()),
							orderProducts.observation()))
					.toList());
	}

}
