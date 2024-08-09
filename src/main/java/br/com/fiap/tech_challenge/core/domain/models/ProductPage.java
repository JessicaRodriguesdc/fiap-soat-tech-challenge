package br.com.fiap.tech_challenge.core.domain.models;

import java.util.List;

public class ProductPage {

	private final List<Product> content;

	private final DomainPage page;

	public ProductPage(List<Product> content, DomainPage page) {
		this.content = content;
		this.page = page;
	}

	public List<Product> getContent() {
		return content;
	}

	public DomainPage getPage() {
		return page;
	}

}
