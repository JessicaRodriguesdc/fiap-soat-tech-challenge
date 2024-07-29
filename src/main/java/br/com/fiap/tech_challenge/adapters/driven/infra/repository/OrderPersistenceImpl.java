package br.com.fiap.tech_challenge.adapters.driven.infra.repository;

import br.com.fiap.tech_challenge.adapters.driven.infra.entities.OrderEntity;
import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.ports.OrderPersistence;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrderPersistenceImpl implements OrderPersistence {

    private final OrderRepository repository;

    public OrderPersistenceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Order> findById(UUID id) {
        var orderFound = repository.findById(id);
        return orderFound.map(OrderEntity::toOrder);
    }

    @Override
    public Order create(Order order) {
        var orderEntity = new OrderEntity(order);
        var orderSaved = repository.save(orderEntity);
        return orderSaved.toOrder();
    }

    @Override
    public Integer getLastSequence() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        return repository.findLastSequenceForToday(startOfDay, endOfDay).orElse(0);
    }
}
