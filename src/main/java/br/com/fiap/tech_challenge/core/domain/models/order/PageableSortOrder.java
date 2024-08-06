package br.com.fiap.tech_challenge.core.domain.models.order;

public class PageableSortOrder {

	private Boolean empty;

	private Boolean sorted;

	private Boolean unsorted;

	public PageableSortOrder(Boolean empty, Boolean sorted, Boolean unsorted) {
		this.empty = empty;
		this.sorted = sorted;
		this.unsorted = unsorted;
	}

	public Boolean getEmpty() {
		return empty;
	}

	public Boolean getSorted() {
		return sorted;
	}

	public Boolean getUnsorted() {
		return unsorted;
	}

}
