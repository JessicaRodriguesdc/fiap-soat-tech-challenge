package br.com.fiap.tech_challenge.application.usecase.order;

import br.com.fiap.tech_challenge.application.usecase.order.dto.CreateOrderDTO;
import br.com.fiap.tech_challenge.domain.models.Order;

public interface CreateOrderUseCase {

	Order create(CreateOrderDTO input);

}
