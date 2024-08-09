package br.com.fiap.tech_challenge.core.domain.models;

import java.util.List;

public class OrderPage {

	private final List<Order> content;

	private final DomainPage page;

	public OrderPage(List<Order> content, DomainPage page) {
		this.content = content;
		this.page = page;
	}

	public List<Order> getContent() {
		return content;
	}

	public DomainPage getPage() {
		return page;
	}

}
