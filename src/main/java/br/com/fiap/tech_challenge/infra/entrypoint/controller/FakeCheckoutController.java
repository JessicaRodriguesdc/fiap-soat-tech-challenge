package br.com.fiap.tech_challenge.infra.entrypoint.controller;

import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.FakeCheckoutRequestDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.openapi.FakeCheckoutControllerOpenApi;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.application.usecase.order.UpdateOrderStatusUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/fake-checkout")
public class FakeCheckoutController implements FakeCheckoutControllerOpenApi {

	private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

	public FakeCheckoutController(UpdateOrderStatusUseCase updateOrderStatusUseCase) {
		this.updateOrderStatusUseCase = updateOrderStatusUseCase;
	}

	@PutMapping
	public ResponseEntity<Void> checkout(@RequestBody @Valid FakeCheckoutRequestDTO fakeCheckoutRequestDTO) {
		updateOrderStatusUseCase.updateStatusById(OrderStatusEnum.PREPARING,
				UUID.fromString(fakeCheckoutRequestDTO.orderId()));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
