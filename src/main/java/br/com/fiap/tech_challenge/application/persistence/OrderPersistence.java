package br.com.fiap.tech_challenge.application.persistence;

import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderPersistence {

	Optional<Order> findById(UUID id);

	Order create(Order customer);

	List<Order> findByStatusNot(OrderStatusEnum status);

	Optional<Order> findByPaymentId(String paymentId);

	List<Order> findByStatusAndCreatedAtBefore(OrderStatusEnum status, LocalDateTime createdAt);

}
