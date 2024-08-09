package br.com.fiap.tech_challenge.core.domain.usecases.order;

import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.OrderPage;

public interface FindPaidOrdersUseCase {

	OrderPage findByIsPaidAndStatus(OrderStatusEnum status, Boolean isPaid, Integer page, Integer size);

}
