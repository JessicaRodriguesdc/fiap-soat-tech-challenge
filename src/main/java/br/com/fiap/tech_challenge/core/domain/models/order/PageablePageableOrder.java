package br.com.fiap.tech_challenge.core.domain.models.order;

public class PageablePageableOrder {

	private Long pageNumber;

	private Long pageSize;

	private PageableSortOrder sort;

	private Long offset;

	private Boolean paged;

	private Boolean unpaged;

	public PageablePageableOrder(Long pageNumber, Long pageSize, PageableSortOrder sort, Long offset, Boolean paged,
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

	public PageableSortOrder getSort() {
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
