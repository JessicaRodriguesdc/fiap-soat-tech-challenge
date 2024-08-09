package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.CreateOrderRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.CreateOrderResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.OrderPageResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.mapper.OrderMapper;
import br.com.fiap.tech_challenge.adapters.driver.api.openapi.OrderControllerOpenApi;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.OrderPage;
import br.com.fiap.tech_challenge.core.domain.usecases.order.CreateOrderUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.order.FindPaidOrdersUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
public class OrderController implements OrderControllerOpenApi {

	private final CreateOrderUseCase createOrderUseCase;

	private final FindPaidOrdersUseCase findPaidOrdersUseCase;

	private final OrderMapper mapper;

	public OrderController(CreateOrderUseCase createOrderUseCase, FindPaidOrdersUseCase findPaidOrdersUseCase,
			OrderMapper mapper) {
		this.createOrderUseCase = createOrderUseCase;
		this.findPaidOrdersUseCase = findPaidOrdersUseCase;
		this.mapper = mapper;
	}

	@Override
	@GetMapping
	public ResponseEntity<OrderPageResponseDTO> findByIsPaidAndStatus(@RequestParam("status") OrderStatusEnum status,
			@RequestParam("isPaid") Boolean isPaid, @RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {

		var pageableOrder = findPaidOrdersUseCase.findByIsPaidAndStatus(status, isPaid, page, size);

		return ResponseEntity.status(HttpStatus.OK).body(new OrderPageResponseDTO(pageableOrder));
	}

	@Override
	@PostMapping
	public ResponseEntity<CreateOrderResponseDTO> create(@RequestBody @Valid CreateOrderRequestDTO orderRequest) {
		var mapperCreateOrder = mapper.toCreateOrder(orderRequest);
		var order = createOrderUseCase.create(mapperCreateOrder);
		var response = new CreateOrderResponseDTO(order.getId(), order.getPaymentId());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
