package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderPersistence {
    Optional<Order> findById(UUID id);
    Order create (Order order);
}
