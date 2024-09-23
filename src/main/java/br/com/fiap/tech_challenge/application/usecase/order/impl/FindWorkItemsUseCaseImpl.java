package br.com.fiap.tech_challenge.application.usecase.order.impl;

import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.FindWorkItemsUseCase;
import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindWorkItemsUseCaseImpl implements FindWorkItemsUseCase {

	private final OrderPersistence persistence;

	public FindWorkItemsUseCaseImpl(OrderPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public List<List<Order>> findWorkItems() {
		List<Order> orders = persistence.findByStatusNot(OrderStatusEnum.FINISHED);

		Map<OrderStatusEnum, List<Order>> groupedByStatus = orders.stream()
			.sorted(Comparator.comparing(Order::getCreatedAt))
			.collect(Collectors.groupingBy(Order::getStatus));

		List<Order> readyItems = groupedByStatus.getOrDefault(OrderStatusEnum.READY, new ArrayList<>());
		readyItems.forEach(Order::removeProducts);

		return List.of(groupedByStatus.getOrDefault(OrderStatusEnum.RECEIVED, new ArrayList<>()),
				groupedByStatus.getOrDefault(OrderStatusEnum.PREPARING, new ArrayList<>()), readyItems);
	}

}
