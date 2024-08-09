package br.com.fiap.tech_challenge.core.domain.usecases.order;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.pageable.CustomPageable;

public interface FindPaidOrdersUseCase {

	CustomPageable<Order> findByIsPaidAndStatus(OrderStatusEnum status, Boolean isPaid, Integer page, Integer size);

}
