package br.com.fiap.tech_challenge.adapters.driven.infra.repository.impl;

import br.com.fiap.tech_challenge.adapters.driven.infra.entities.OrderEntity;
import br.com.fiap.tech_challenge.adapters.driven.infra.mapper.OrderPageMapper;
import br.com.fiap.tech_challenge.adapters.driven.infra.repository.OrderRepository;
import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.OrderPage;
import br.com.fiap.tech_challenge.core.domain.ports.OrderPersistence;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class OrderPersistenceImpl implements OrderPersistence {

	private final OrderRepository repository;

	private final OrderPageMapper mapper;

	public OrderPersistenceImpl(OrderRepository repository, OrderPageMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Optional<Order> findById(UUID id) {
		var orderFound = repository.findById(id);
		return orderFound.map(OrderEntity::toOrder);
	}

	@Override
	public Order create(Order order) {
		var orderEntity = new OrderEntity(order);
		var orderSaved = repository.save(orderEntity);
		return orderSaved.toOrder();
	}

	@Override
	public OrderPage findByIsPaidAndStatus(Boolean isPaid, OrderStatusEnum status, Integer page, Integer size) {
		var orders = repository.findByIsPaidAndStatus(isPaid, status, PageRequest.of(page, size));

		return mapper.toDomainPage(orders.map(OrderEntity::toOrder));
	}

}
