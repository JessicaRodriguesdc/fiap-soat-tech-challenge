package br.com.fiap.tech_challenge.core.domain.usecases.order;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.usecases.order.dto.CreateOrderDTO;

public interface CreateOrderUseCase {
    Order create(CreateOrderDTO input);
}
