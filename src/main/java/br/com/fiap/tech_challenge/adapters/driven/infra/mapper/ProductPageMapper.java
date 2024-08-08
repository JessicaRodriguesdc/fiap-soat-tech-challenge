package br.com.fiap.tech_challenge.adapters.driven.infra.mapper;

import br.com.fiap.tech_challenge.core.domain.models.product.PageablePageableProduct;
import br.com.fiap.tech_challenge.core.domain.models.product.PageableProduct;
import br.com.fiap.tech_challenge.core.domain.models.product.PageableSortProduct;
import br.com.fiap.tech_challenge.core.domain.models.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ProductPageMapper {

	public PageableProduct toDomainPage(Page<Product> page) {
		return this.buildPageableProduct(page);
	}

	private PageableProduct buildPageableProduct(Page<Product> page) {
		return new PageableProduct(page.getTotalElements(), page.getTotalElements(), (long) page.getSize(),
				page.getContent(), (long) page.getNumber(), this.buildPageableSortOrder(page), page.isFirst(),
				page.isLast(), (long) page.getNumberOfElements(), this.buildPageablePageable(page), page.isEmpty());
	}

	private PageableSortProduct buildPageablePageableSort(Page<Product> page) {
		return new PageableSortProduct(page.getPageable().getSort().isEmpty(), page.getPageable().getSort().isSorted(),
				page.getPageable().getSort().isUnsorted());
	}

	private PageablePageableProduct buildPageablePageable(Page<Product> page) {
		return new PageablePageableProduct((long) page.getPageable().getPageNumber(),
				(long) page.getPageable().getPageSize(), this.buildPageablePageableSort(page),
				page.getPageable().getOffset(), page.getPageable().isPaged(), page.getPageable().isUnpaged());
	}

	private PageableSortProduct buildPageableSortOrder(Page<Product> page) {
		return new PageableSortProduct(page.getSort().isEmpty(), page.getSort().isSorted(),
				page.getSort().isUnsorted());
	}

}
