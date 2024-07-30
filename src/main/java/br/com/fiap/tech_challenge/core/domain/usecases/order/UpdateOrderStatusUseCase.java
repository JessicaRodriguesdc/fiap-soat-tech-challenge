package br.com.fiap.tech_challenge.core.domain.usecases.order;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatus;

import java.util.UUID;

public interface UpdateOrderStatusUseCase {
    Order updateStatusById(OrderStatus status, UUID id);
}
