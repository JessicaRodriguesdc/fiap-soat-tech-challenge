package br.com.fiap.tech_challenge.core.domain.models;

public class DomainPage {

	private final Long totalPages;

	private final Long totalElements;

	private final Long size;

	private final Long number;

	private final Boolean first;

	private final Boolean last;

	private final Long numberOfElements;

	private final Boolean empty;

	public DomainPage(Long totalPages, Long totalElements, Long size, Long number, Boolean first, Boolean last,
			Long numberOfElements, Boolean empty) {
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.size = size;
		this.number = number;
		this.first = first;
		this.last = last;
		this.numberOfElements = numberOfElements;
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

	public Long getNumber() {
		return number;
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

	public Boolean getEmpty() {
		return empty;
	}

}
