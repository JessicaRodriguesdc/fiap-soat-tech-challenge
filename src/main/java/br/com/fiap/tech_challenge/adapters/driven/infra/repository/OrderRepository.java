package br.com.fiap.tech_challenge.adapters.driven.infra.repository;

import br.com.fiap.tech_challenge.adapters.driven.infra.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
