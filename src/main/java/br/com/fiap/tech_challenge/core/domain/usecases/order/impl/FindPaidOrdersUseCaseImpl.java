package br.com.fiap.tech_challenge.core.domain.usecases.order.impl;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatus;
import br.com.fiap.tech_challenge.core.domain.ports.OrderPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.order.FindPaidOrdersUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class FindPaidOrdersUseCaseImpl implements FindPaidOrdersUseCase {

    private final OrderPersistence persistence;

    public FindPaidOrdersUseCaseImpl(OrderPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public Page<Order> findAllPaidOrders(OrderStatus status, Boolean isPaid, Pageable pageable) {
        return persistence.findByIsPaidAndStatus(isPaid, status, pageable);
    }
}
