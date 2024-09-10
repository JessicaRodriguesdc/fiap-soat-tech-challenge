package br.com.fiap.tech_challenge.infra.gateway.database.repository;

import br.com.fiap.tech_challenge.infra.gateway.database.entities.OrderEntity;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

	Page<OrderEntity> findByIsPaidAndStatus(Boolean isPaid, OrderStatusEnum status, Pageable pageable);

}
