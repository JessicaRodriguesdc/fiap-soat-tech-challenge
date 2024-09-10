package br.com.fiap.tech_challenge.infra.entrypoint.controller.mapper;

import br.com.fiap.tech_challenge.infra.entrypoint.controller.dto.CustomerRequestDTO;
import br.com.fiap.tech_challenge.domain.models.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

	public Customer toCustomer(CustomerRequestDTO dto) {
		return new Customer(dto.name(), dto.document(), dto.email());
	}

}
