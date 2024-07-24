package br.com.fiap.tech_challenge.adapters.driver.api.mapper;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.CustomerRequestDTO;
import br.com.fiap.tech_challenge.core.domain.models.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequestDTO dto){
        return new Customer(dto.name(), dto.document(), dto.email());
    }
}
