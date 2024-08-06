package br.com.fiap.tech_challenge.core.domain.models.order;

import java.util.List;

public class PageableOrder {

	private Long totalPages;

	private Long totalElements;

	private Long size;

	private List<Order> content;

	private Long number;

	private PageableSortOrder sort;

	private Boolean first;

	private Boolean last;

	private Long numberOfElements;

	private PageablePageableOrder pageable;

	private Boolean empty;

	public PageableOrder(Long totalPages, Long totalElements, Long size, List<Order> content, Long number,
			PageableSortOrder sort, Boolean first, Boolean last, Long numberOfElements, PageablePageableOrder pageable,
			Boolean empty) {
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

	public List<Order> getContent() {
		return content;
	}

	public Long getNumber() {
		return number;
	}

	public PageableSortOrder getSort() {
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

	public PageablePageableOrder getPageable() {
		return pageable;
	}

	public Boolean getEmpty() {
		return empty;
	}

}
