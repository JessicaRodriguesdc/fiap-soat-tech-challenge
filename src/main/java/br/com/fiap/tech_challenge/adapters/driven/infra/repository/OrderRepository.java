package br.com.fiap.tech_challenge.adapters.driven.infra.repository;

import br.com.fiap.tech_challenge.adapters.driven.infra.entities.OrderEntity;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID>{
    Page<OrderEntity> findByIsPaidAndStatus(Boolean isPaid, OrderStatus status, Pageable pageable);
}
