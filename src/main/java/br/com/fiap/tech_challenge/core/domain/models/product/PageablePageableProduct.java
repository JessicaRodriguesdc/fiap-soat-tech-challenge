package br.com.fiap.tech_challenge.core.domain.models.product;

public class PageablePageableProduct {

	private Long pageNumber;

	private Long pageSize;

	private PageableSortProduct sort;

	private Long offset;

	private Boolean paged;

	private Boolean unpaged;

	public PageablePageableProduct(Long pageNumber, Long pageSize, PageableSortProduct sort, Long offset, Boolean paged,
			Boolean unpaged) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.sort = sort;
		this.offset = offset;
		this.paged = paged;
		this.unpaged = unpaged;
	}

	public Long getPageNumber() {
		return pageNumber;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public PageableSortProduct getSort() {
		return sort;
	}

	public Long getOffset() {
		return offset;
	}

	public Boolean getPaged() {
		return paged;
	}

	public Boolean getUnpaged() {
		return unpaged;
	}

}
