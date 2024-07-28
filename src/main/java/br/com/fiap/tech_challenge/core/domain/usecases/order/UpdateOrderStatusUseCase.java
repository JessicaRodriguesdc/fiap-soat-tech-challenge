package br.com.fiap.tech_challenge.core.domain.usecases.order;

import br.com.fiap.tech_challenge.core.domain.models.Order;

import java.util.UUID;

public interface UpdateOrderStatusUseCase {
    Order updateStatusById(String status, UUID id);
}
