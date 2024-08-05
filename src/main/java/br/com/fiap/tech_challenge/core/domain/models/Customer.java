package br.com.fiap.tech_challenge.core.domain.models;

import java.util.UUID;

public class Customer {

	private UUID id;

	private String name;

	private String document;

	private String email;

	public Customer(UUID id, String name, String document, String email) {
		this.id = id;
		this.name = name;
		this.document = document;
		this.email = email;
	}

	public Customer(String name, String document, String email) {
		this.name = name;
		this.document = document;
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDocument() {
		return document;
	}

	public String getEmail() {
		return email;
	}

}
