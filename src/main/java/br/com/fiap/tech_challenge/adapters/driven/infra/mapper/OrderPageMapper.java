package br.com.fiap.tech_challenge.adapters.driven.infra.mapper;

import br.com.fiap.tech_challenge.core.domain.models.order.Order;
import br.com.fiap.tech_challenge.core.domain.models.order.PageableOrder;
import br.com.fiap.tech_challenge.core.domain.models.order.PageablePageableOrder;
import br.com.fiap.tech_challenge.core.domain.models.order.PageableSortOrder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class OrderPageMapper {

	public PageableOrder toDomainPage(Page<Order> page) {
		return this.buildPageableOrder(page);
	}

	private PageableOrder buildPageableOrder(Page<Order> page) {
		return new PageableOrder(page.getTotalElements(), page.getTotalElements(), (long) page.getSize(),
				page.getContent(), (long) page.getNumber(), this.buildPageableSortOrder(page), page.isFirst(),
				page.isLast(), (long) page.getNumberOfElements(), this.buildPageablePageable(page), page.isEmpty());
	}

	private PageableSortOrder buildPageablePageableSort(Page<Order> page) {
		return new PageableSortOrder(page.getPageable().getSort().isEmpty(), page.getPageable().getSort().isSorted(),
				page.getPageable().getSort().isUnsorted());
	}

	private PageablePageableOrder buildPageablePageable(Page<Order> page) {
		return new PageablePageableOrder((long) page.getPageable().getPageNumber(),
				(long) page.getPageable().getPageSize(), this.buildPageablePageableSort(page),
				page.getPageable().getOffset(), page.getPageable().isPaged(), page.getPageable().isUnpaged());
	}

	private PageableSortOrder buildPageableSortOrder(Page<Order> page) {
		return new PageableSortOrder(page.getSort().isEmpty(), page.getSort().isSorted(), page.getSort().isUnsorted());
	}

}
