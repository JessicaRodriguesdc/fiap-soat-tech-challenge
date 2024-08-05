package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface OrderPersistence {
    Optional<Order> findById(UUID id);
    Order create(Order customer);
    Page<Order> findByIsPaidAndStatus(Boolean isPaid, OrderStatus status, Pageable pageable);
}
