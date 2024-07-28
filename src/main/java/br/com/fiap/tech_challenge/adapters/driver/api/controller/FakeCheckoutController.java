package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.FakeCheckoutRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.OrderResponseDTO;
import br.com.fiap.tech_challenge.core.domain.usecases.order.UpdateOrderStatusUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/fake-checkout")
public class FakeCheckoutController {

    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    public FakeCheckoutController(
            UpdateOrderStatusUseCase updateOrderStatusUseCase
    ) {
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
    }

    @PostMapping()
    private ResponseEntity<OrderResponseDTO> create(
            @RequestBody @Valid FakeCheckoutRequestDTO fakeCheckoutRequestDTO
    ) {
        var updatedOrder = updateOrderStatusUseCase.updateStatusById(
                "PREPARING",
                UUID.fromString(fakeCheckoutRequestDTO.orderId())
        );
        return ResponseEntity.status(HttpStatus.OK).body(new OrderResponseDTO(updatedOrder));
    }
}