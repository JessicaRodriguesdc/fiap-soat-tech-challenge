package br.com.fiap.tech_challenge.infra.gateway.database.mapper;

import br.com.fiap.tech_challenge.domain.models.pageable.CustomPage;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageMapper<T> {

	public CustomPageable<T> toDomainPage(Page<T> page) {
		return new CustomPageable<>(page.getContent(), this.buildDomainPage(page));
	}

	private CustomPage buildDomainPage(Page<T> page) {
		return new CustomPage((long) page.getTotalPages(), page.getTotalElements(), (long) page.getSize(),
				(long) page.getNumber(), page.isFirst(), page.isLast(), (long) page.getNumberOfElements(),
				page.isEmpty());
	}

}
