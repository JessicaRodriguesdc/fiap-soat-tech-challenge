package br.com.fiap.tech_challenge.application.usecase.order.impl;

import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.FindWorkItemsUseCase;
import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FindWorkItemsUseCaseImpl implements FindWorkItemsUseCase {

	private final OrderPersistence persistence;

	public FindWorkItemsUseCaseImpl(OrderPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public List<List<Order>> findWorkItems() {
		List<Order> orders = persistence.findByStatusNot(OrderStatusEnum.FINISHED);

		List<Order> receivedItems = new ArrayList<>(
				orders.stream().filter(order -> order.getStatus().equals(OrderStatusEnum.RECEIVED)).toList());
		List<Order> preparingItems = new ArrayList<>(
				orders.stream().filter(order -> order.getStatus().equals(OrderStatusEnum.PREPARING)).toList());
		List<Order> readyItems = new ArrayList<>(
				orders.stream().filter(order -> order.getStatus().equals(OrderStatusEnum.READY)).toList());

		receivedItems.sort(Comparator.comparing(Order::getCreatedAt));
		preparingItems.sort(Comparator.comparing(Order::getCreatedAt));
		readyItems.sort(Comparator.comparing(Order::getCreatedAt));

		return List.of(receivedItems, preparingItems, readyItems);
	}

}
