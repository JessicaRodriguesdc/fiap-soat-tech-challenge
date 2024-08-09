package br.com.fiap.tech_challenge.core.domain.models;

public class DomainPage {

	private Long totalPages;

	private Long totalElements;

	private Long size;

	private Long number;

	private Boolean first;

	private Boolean last;

	private Long numberOfElements;

	private Boolean empty;

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
