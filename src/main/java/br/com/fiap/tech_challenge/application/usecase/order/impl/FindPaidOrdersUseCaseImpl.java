package br.com.fiap.tech_challenge.application.usecase.order.impl;

import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPageable;
import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.FindPaidOrdersUseCase;

public class FindPaidOrdersUseCaseImpl implements FindPaidOrdersUseCase {

	private final OrderPersistence persistence;

	public FindPaidOrdersUseCaseImpl(OrderPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public CustomPageable<Order> findByIsPaidAndStatus(OrderStatusEnum status, Boolean isPaid, Integer page,
			Integer size) {
		return persistence.findByIsPaidAndStatus(isPaid, status, page, size);
	}

}
