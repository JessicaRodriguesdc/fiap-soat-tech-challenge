package br.com.fiap.tech_challenge.adapters.driver.api.mapper;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.CreateOrderRequestDTO;
import br.com.fiap.tech_challenge.core.domain.usecases.order.dto.CreateOrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public CreateOrderDTO toCreateOrder(CreateOrderRequestDTO dto) {
        return new CreateOrderDTO(
                dto.customerId(),
                dto.products().stream().map(orderProducts -> new CreateOrderDTO.OrderProducts(
                        orderProducts.id(),
                        orderProducts.observation()
                )).toList()
        );
    }
}
