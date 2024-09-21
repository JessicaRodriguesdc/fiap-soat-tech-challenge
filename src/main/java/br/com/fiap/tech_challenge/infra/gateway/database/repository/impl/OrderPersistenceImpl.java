package br.com.fiap.tech_challenge.infra.gateway.database.repository.impl;

import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.infra.gateway.database.entities.OrderEntity;
import br.com.fiap.tech_challenge.infra.gateway.database.entities.OrderProductEntity;
import br.com.fiap.tech_challenge.infra.gateway.database.entities.ProductEntity;
import br.com.fiap.tech_challenge.infra.gateway.database.mapper.PageMapper;
import br.com.fiap.tech_challenge.infra.gateway.database.repository.OrderRepository;
import br.com.fiap.tech_challenge.infra.gateway.database.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrderPersistenceImpl implements OrderPersistence {

	private final OrderRepository repository;
	private final ProductRepository productRepository;

	private final PageMapper<Order> mapper;

	public OrderPersistenceImpl(OrderRepository repository, ProductRepository productRepository, PageMapper mapper) {
		this.repository = repository;
        this.productRepository = productRepository;
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

		order.getProducts()
				.forEach(orderProduct -> {
					var productEntity = productRepository.findById(orderProduct.getProductId()).orElseThrow();
					orderEntity.addOrderProductEntity(new OrderProductEntity(orderProduct, productEntity));
				});

		var orderSaved = repository.save(orderEntity);
		return orderSaved.toOrder();
	}

	@Override
	public List<Order> findByStatusNot(OrderStatusEnum status) {
		var ordersEntity = repository.findByStatusNot(status);

		return ordersEntity.stream().map(OrderEntity::toOrder).toList();
	}

}
