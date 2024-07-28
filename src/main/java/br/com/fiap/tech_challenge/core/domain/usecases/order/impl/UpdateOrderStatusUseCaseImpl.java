package br.com.fiap.tech_challenge.core.domain.usecases.order.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.AlreadyInStatusException;
import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.core.domain.ports.OrderPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.order.UpdateOrderStatusUseCase;

import java.util.Objects;
import java.util.UUID;

public class UpdateOrderStatusUseCaseImpl implements UpdateOrderStatusUseCase {

    private final OrderPersistence persistence;

    public UpdateOrderStatusUseCaseImpl(OrderPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public Order updateStatusById(OrderStatusEnum status, UUID id) {
        var orderFound = persistence.findById(id).orElseThrow(
                () -> new DoesNotExistException("Order does no exist!")
        );

        if (orderFound.getStatus().equals(status)) {
            throw new AlreadyInStatusException("Order already in PREPARING status!");
        }

        var isPaid = orderFound.getIsPaid();
        if (Objects.equals(status, OrderStatusEnum.PREPARING)) {
            isPaid = true;
        }
        var newOrder = new Order(
                orderFound.getId(),
                orderFound.getCustomerId(),
                orderFound.getAmount(),
                orderFound.getSequence(),
                status,
                orderFound.getPaymentId(),
                isPaid
        );

        return persistence.create(newOrder);
    }
}
