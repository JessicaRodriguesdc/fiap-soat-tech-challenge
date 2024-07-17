package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.controller.dto.CustomerRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.controller.dto.CustomerResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.mapper.CustomerMapper;
import br.com.fiap.tech_challenge.core.domain.usecases.customer.CreateCustomerUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final CustomerMapper mapper;

    public Controller(CreateCustomerUseCase createCustomerUseCase, CustomerMapper mapper) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    private ResponseEntity<CustomerResponseDTO> create(@RequestBody @Valid CustomerRequestDTO customerDTO){
        var customerSaved = createCustomerUseCase.create(mapper.toCustomer(customerDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerResponseDTO(customerSaved));
    }
}
