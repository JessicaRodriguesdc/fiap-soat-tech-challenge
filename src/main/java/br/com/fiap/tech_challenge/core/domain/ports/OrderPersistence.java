package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.Order;

public interface OrderPersistence {
    Order create(Order customer);
    Integer getLastSequence();
}
