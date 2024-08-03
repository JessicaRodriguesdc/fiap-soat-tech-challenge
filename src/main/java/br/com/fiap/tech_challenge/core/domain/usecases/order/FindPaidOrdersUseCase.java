package br.com.fiap.tech_challenge.core.domain.usecases.order;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.StatusOrderEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindPaidOrdersUseCase {
    Page<Order> findAllPaidOrders(StatusOrderEnum status, Boolean isPaid, Pageable pageable);
}
