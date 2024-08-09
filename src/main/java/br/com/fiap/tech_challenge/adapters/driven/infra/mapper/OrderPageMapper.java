package br.com.fiap.tech_challenge.adapters.driven.infra.mapper;

import br.com.fiap.tech_challenge.core.domain.models.DomainPage;
import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.OrderPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class OrderPageMapper {

	public OrderPage toDomainPage(Page<Order> page) {
		return new OrderPage(page.getContent(), this.buildDomainPage(page));
	}

	private DomainPage buildDomainPage(Page<Order> page) {
		return new DomainPage((long) page.getTotalPages(), page.getTotalElements(), (long) page.getSize(),
				(long) page.getNumber(), page.isFirst(), page.isLast(), (long) page.getNumberOfElements(),
				page.isEmpty());
	}

}
