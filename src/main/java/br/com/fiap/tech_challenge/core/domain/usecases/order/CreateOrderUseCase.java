package br.com.fiap.tech_challenge.core.domain.usecases.order;

import br.com.fiap.tech_challenge.core.domain.usecases.order.dto.CreateOrderDTO;
import br.com.fiap.tech_challenge.core.domain.models.order.Order;

public interface CreateOrderUseCase {

	Order create(CreateOrderDTO input);

}
