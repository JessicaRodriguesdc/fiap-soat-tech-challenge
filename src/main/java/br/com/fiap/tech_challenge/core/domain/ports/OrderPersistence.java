package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.order.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.order.PageableOrder;

import java.util.Optional;
import java.util.UUID;

public interface OrderPersistence {

	Optional<Order> findById(UUID id);

	Order create(Order customer);

	PageableOrder findByIsPaidAndStatus(Boolean isPaid, OrderStatusEnum status, Integer page, Integer size);

}
