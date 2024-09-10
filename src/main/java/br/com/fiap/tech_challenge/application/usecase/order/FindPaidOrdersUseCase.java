package br.com.fiap.tech_challenge.application.usecase.order;

import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPageable;

public interface FindPaidOrdersUseCase {

	CustomPageable<Order> findByIsPaidAndStatus(OrderStatusEnum status, Boolean isPaid, Integer page, Integer size);

}
