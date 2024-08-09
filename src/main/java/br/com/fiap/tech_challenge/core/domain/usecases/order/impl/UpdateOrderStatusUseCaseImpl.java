package br.com.fiap.tech_challenge.core.domain.usecases.order.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.AlreadyInStatusException;
import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.exceptions.InvalidStatusUpdateException;
import br.com.fiap.tech_challenge.core.domain.models.order.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.core.domain.ports.OrderPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.order.UpdateOrderStatusUseCase;

import java.util.UUID;

public class UpdateOrderStatusUseCaseImpl implements UpdateOrderStatusUseCase {

	private final OrderPersistence persistence;

	public UpdateOrderStatusUseCaseImpl(OrderPersistence persistence) {
		this.persistence = persistence;
	}

	@Override
	public void updateStatusById(OrderStatusEnum status, UUID id) {
		var orderFound = persistence.findById(id).orElseThrow(() -> new DoesNotExistException("Order does no exist!"));

		if (orderFound.getStatus().equals(status)) {
			throw new AlreadyInStatusException("Order already in " + status + " status!");
		}

		var previousStatus = status.previous();
		if (!orderFound.getStatus().equals(status.previous())) {
			throw new InvalidStatusUpdateException("Order must be on " + previousStatus + " status!");
		}

		var isPaid = orderFound.isPaid();
		if (OrderStatusEnum.PREPARING.equals(status)) {
			isPaid = true;
		}
		var updatedOrder = new Order(orderFound.getId(), orderFound.getAmount(), orderFound.getSequence(), status,
				isPaid, orderFound.getProducts(), orderFound.getCustomer(), orderFound.getPaymentId(),
				orderFound.getCreatedAt(), orderFound.getUpdatedAt());

		persistence.create(updatedOrder);
	}

}
