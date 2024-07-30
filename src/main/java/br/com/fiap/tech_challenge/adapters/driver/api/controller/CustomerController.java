package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.CustomerRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.CustomerResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.mapper.CustomerMapper;
import br.com.fiap.tech_challenge.adapters.driver.api.openapi.CustomerControllerOpenApi;
import br.com.fiap.tech_challenge.core.domain.usecases.customer.CreateCustomerUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.customer.FindCustomerByDocumentUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController implements CustomerControllerOpenApi {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final FindCustomerByDocumentUseCase findCustomerByDocumentUseCase;
    private final CustomerMapper mapper;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase, FindCustomerByDocumentUseCase findCustomerByDocumentUseCase, CustomerMapper mapper) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.findCustomerByDocumentUseCase = findCustomerByDocumentUseCase;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/{document}")
    public ResponseEntity<CustomerResponseDTO> findByDocument(@PathVariable("document") final String document){
        var customerFound = findCustomerByDocumentUseCase.findByDocument(document);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseDTO(customerFound));
    }

    @Override
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(@RequestBody @Valid CustomerRequestDTO customerDTO){
        var customerSaved = createCustomerUseCase.create(mapper.toCustomer(customerDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerResponseDTO(customerSaved));
    }
}
