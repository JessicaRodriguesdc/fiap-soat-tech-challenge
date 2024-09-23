package br.com.fiap.tech_challenge.application.usecase.order;

import br.com.fiap.tech_challenge.domain.models.Order;

import java.util.List;

public interface FindWorkItemsUseCase {

	List<List<Order>> findWorkItems();

}
