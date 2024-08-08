package br.com.fiap.tech_challenge.core.domain.usecases.order.impl;

import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.order.PageableOrder;
import br.com.fiap.tech_challenge.core.domain.ports.OrderPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.order.FindPaidOrdersUseCase;

public class FindPaidOrdersUseCaseImpl implements FindPaidOrdersUseCase {

	private final OrderPersistence persistence;

	public FindPaidOrdersUseCaseImpl(OrderPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public PageableOrder findByIsPaidAndStatus(OrderStatusEnum status, Boolean isPaid, Integer page, Integer size) {
		return persistence.findByIsPaidAndStatus(isPaid, status, page, size);
	}

}
