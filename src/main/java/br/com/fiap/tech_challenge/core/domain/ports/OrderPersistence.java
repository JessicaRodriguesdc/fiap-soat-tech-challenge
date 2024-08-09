package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.OrderPage;

import java.util.Optional;
import java.util.UUID;

public interface OrderPersistence {

	Optional<Order> findById(UUID id);

	Order create(Order customer);

	OrderPage findByIsPaidAndStatus(Boolean isPaid, OrderStatusEnum status, Integer page, Integer size);

}
