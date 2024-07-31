package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.CreateOrderRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.CreateOrderResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.PaidOrdersPaginatedResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.mapper.OrderMapper;
import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatus;
import br.com.fiap.tech_challenge.core.domain.usecases.order.CreateOrderUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.order.FindPaidOrdersUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final FindPaidOrdersUseCase findPaidOrdersUseCase;
    private final OrderMapper mapper;

    public OrderController(CreateOrderUseCase createOrderUseCase,
                           FindPaidOrdersUseCase findPaidOrdersUseCase,
                           OrderMapper mapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.findPaidOrdersUseCase = findPaidOrdersUseCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<PaidOrdersPaginatedResponseDTO> findAllIsPaidOrders(@RequestParam("status") OrderStatus status,
                                                                              Pageable pageable) {

        Page<Order> ordersPage = findPaidOrdersUseCase.findAllPaidOrders(status, pageable);

        List<PaidOrdersPaginatedResponseDTO.OrderSummary> content = ordersPage.getContent().stream()
                .map(order -> {
                    var customerName = order.getCustomer() != null ? order.getCustomer().getName() : "";
                    return new PaidOrdersPaginatedResponseDTO.OrderSummary(
                            order.getSequence(),
                            customerName,
                            order.getCreatedAt(),
                            order.getUpdatedAt()
                    );
                }).toList();

        PaidOrdersPaginatedResponseDTO response = new PaidOrdersPaginatedResponseDTO(content, pageable);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    private ResponseEntity<CreateOrderResponseDTO> create(@RequestBody @Valid CreateOrderRequestDTO orderRequest){
        var mapperCreateOrder = mapper.toCreateOrder(orderRequest);
        var order = createOrderUseCase.create(mapperCreateOrder);
        var response = new CreateOrderResponseDTO(order.getId(), order.getPaymentId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
