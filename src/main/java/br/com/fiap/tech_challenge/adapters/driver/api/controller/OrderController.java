package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.CreateOrderRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.CreateOrderResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.mapper.OrderMapper;
import br.com.fiap.tech_challenge.core.domain.usecases.order.CreateOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final OrderMapper mapper;

    public OrderController(CreateOrderUseCase createOrderUseCase, OrderMapper mapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    private ResponseEntity<CreateOrderResponseDTO> create(@RequestBody @Valid CreateOrderRequestDTO orderRequest){
        var mapperCreateOrder = mapper.toCreateOrder(orderRequest);
        var order = createOrderUseCase.create(mapperCreateOrder);
        var response = new CreateOrderResponseDTO(order.getId(), order.getPaymentId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
