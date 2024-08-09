package br.com.fiap.tech_challenge.adapters.driven.infra.mapper;

import br.com.fiap.tech_challenge.core.domain.models.DomainPage;
import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.models.ProductPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ProductPageMapper {

	public ProductPage toDomainPage(Page<Product> page) {
		return new ProductPage(page.getContent(), this.buildDomainPage(page));
	}

	private DomainPage buildDomainPage(Page<Product> page) {
		return new DomainPage((long) page.getTotalPages(), page.getTotalElements(), (long) page.getSize(),
				(long) page.getNumber(), page.isFirst(), page.isLast(), (long) page.getNumberOfElements(),
				page.isEmpty());
	}
}
