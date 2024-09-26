package br.com.fiap.tech_challenge.application.usecase.order;

import java.util.UUID;

public interface IsPaidUseCase {

	Boolean isOrderPaid(UUID id);

}
