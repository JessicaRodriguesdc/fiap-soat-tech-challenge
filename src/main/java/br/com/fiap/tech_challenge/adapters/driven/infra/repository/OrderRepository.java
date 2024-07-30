package br.com.fiap.tech_challenge.adapters.driven.infra.repository;

import br.com.fiap.tech_challenge.adapters.driven.infra.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID>{

    @Query("SELECT MAX(o.sequence) FROM OrderEntity o WHERE o.createdAt >= :startOfDay AND o.createdAt < :endOfDay")
    Optional<Integer> findLastSequenceForToday(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
