package br.com.fiap.tech_challenge.application.usecase.order;

import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;

import java.util.UUID;

public interface UpdateOrderStatusUseCase {

	void updateStatusById(OrderStatusEnum status, UUID id);

}
