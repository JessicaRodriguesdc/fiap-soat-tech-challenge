package br.com.fiap.tech_challenge.infra.entrypoint.controller;

import br.com.fiap.tech_challenge.application.usecase.order.CreateOrderUseCase;
import br.com.fiap.tech_challenge.application.usecase.order.FindWorkItemsUseCase;
import br.com.fiap.tech_challenge.application.usecase.order.IsPaidUseCase;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.CreateOrderRequestDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.CreateOrderResponseDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.OrderWorkItemsResponseDTO;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.mapper.OrderMapper;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.openapi.OrderControllerOpenApi;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/orders")
public class OrderController implements OrderControllerOpenApi {

	private final CreateOrderUseCase createOrderUseCase;

	private final FindWorkItemsUseCase findWorkItemsUseCase;

	private final OrderMapper mapper;

	private final IsPaidUseCase isPaidUseCase;

	public OrderController(CreateOrderUseCase createOrderUseCase, IsPaidUseCase isOrderPaid,
			FindWorkItemsUseCase findWorkItemsUseCase, OrderMapper mapper) {
		this.createOrderUseCase = createOrderUseCase;
		this.findWorkItemsUseCase = findWorkItemsUseCase;
		this.isPaidUseCase = isOrderPaid;
		this.mapper = mapper;
	}

	@Override
	@GetMapping
	public ResponseEntity<OrderWorkItemsResponseDTO> findWorkItems() {
		var workItems = findWorkItemsUseCase.findWorkItems();
		return ResponseEntity.status(HttpStatus.OK).body(new OrderWorkItemsResponseDTO(workItems));
	}

	@Override
	@PostMapping
	public ResponseEntity<CreateOrderResponseDTO> create(@RequestBody @Valid CreateOrderRequestDTO orderRequest) {
		var mapperCreateOrder = mapper.toCreateOrder(orderRequest);
		var order = createOrderUseCase.create(mapperCreateOrder);
		var response = new CreateOrderResponseDTO(order.getId(), order.getSequence(), order.getPaymentId());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Override
	@GetMapping("/{id}/paid-status")
	public ResponseEntity<Boolean> isOrderPaid(@PathVariable("id") final UUID id) {
		var response = isPaidUseCase.isOrderPaid(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
