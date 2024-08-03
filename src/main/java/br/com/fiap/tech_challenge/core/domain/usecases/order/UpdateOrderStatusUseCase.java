package br.com.fiap.tech_challenge.core.domain.usecases.order;

import br.com.fiap.tech_challenge.core.domain.models.enums.StatusOrderEnum;

import java.util.UUID;

public interface UpdateOrderStatusUseCase {
    void updateStatusById(StatusOrderEnum status, UUID id);
}
