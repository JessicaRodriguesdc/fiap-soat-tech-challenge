package br.com.fiap.tech_challenge.application.usecase.order.impl;

import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.IsPaidUseCase;

import java.util.UUID;

public class IsPaidUseCaseImpl implements IsPaidUseCase {

	private final OrderPersistence persistence;

	public IsPaidUseCaseImpl(OrderPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public Boolean isOrderPaid(UUID id) {
		var orderFound = persistence.findById(id).orElseThrow(() -> new DoesNotExistException("Order does not exist!"));
		return orderFound.isPaid();
	}

}
