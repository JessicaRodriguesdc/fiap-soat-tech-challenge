package br.com.fiap.tech_challenge.application.usecase.order.impl;

import br.com.fiap.tech_challenge.application.exceptions.AlreadyInStatusException;
import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.application.exceptions.InvalidStatusUpdateException;
import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.UpdateOrderStatusUseCase;
import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;

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

		var validPreviousStatus = status.validPreviousStatus();
		if (!validPreviousStatus.contains(orderFound.getStatus())) {
			throw new InvalidStatusUpdateException(
					"Order must be at one of the following status: " + validPreviousStatus);
		}

		var isPaid = OrderStatusEnum.PREPARING.equals(status) || orderFound.isPaid();
		var updatedOrder = new Order(orderFound.getId(), orderFound.getAmount(), orderFound.getSequence(), status,
				isPaid, orderFound.getProducts(), orderFound.getCustomer(), orderFound.getPaymentId(),
				orderFound.getCreatedAt(), orderFound.getUpdatedAt());

		persistence.create(updatedOrder);
	}

}
