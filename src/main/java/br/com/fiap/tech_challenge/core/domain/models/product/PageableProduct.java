package br.com.fiap.tech_challenge.core.domain.models.product;

import java.util.List;

public class PageableProduct {

	private Long totalPages;

	private Long totalElements;

	private Long size;

	private List<Product> content;

	private Long number;

	private PageableSortProduct sort;

	private Boolean first;

	private Boolean last;

	private Long numberOfElements;

	private PageablePageableProduct pageable;

	private Boolean empty;

	public PageableProduct(Long totalPages, Long totalElements, Long size, List<Product> content, Long number,
			PageableSortProduct sort, Boolean first, Boolean last, Long numberOfElements,
			PageablePageableProduct pageable, Boolean empty) {
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.size = size;
		this.content = content;
		this.number = number;
		this.sort = sort;
		this.first = first;
		this.last = last;
		this.numberOfElements = numberOfElements;
		this.pageable = pageable;
		this.empty = empty;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public Long getSize() {
		return size;
	}

	public List<Product> getContent() {
		return content;
	}

	public Long getNumber() {
		return number;
	}

	public PageableSortProduct getSort() {
		return sort;
	}

	public Boolean getFirst() {
		return first;
	}

	public Boolean getLast() {
		return last;
	}

	public Long getNumberOfElements() {
		return numberOfElements;
	}

	public PageablePageableProduct getPageable() {
		return pageable;
	}

	public Boolean getEmpty() {
		return empty;
	}

}
