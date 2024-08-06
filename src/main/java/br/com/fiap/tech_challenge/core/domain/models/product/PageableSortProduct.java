package br.com.fiap.tech_challenge.core.domain.models.product;

public class PageableSortProduct {

	private Boolean empty;

	private Boolean sorted;

	private Boolean unsorted;

	public PageableSortProduct(Boolean empty, Boolean sorted, Boolean unsorted) {
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
