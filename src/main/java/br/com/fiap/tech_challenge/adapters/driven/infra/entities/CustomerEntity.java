package br.com.fiap.tech_challenge.adapters.driven.infra.entities;

import br.com.fiap.tech_challenge.core.domain.models.Customer;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;

	private String name;

	private String document;

	private String email;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public CustomerEntity() {
	}

	public CustomerEntity(UUID id, String name, String document, String email) {
		this.id = id;
		this.name = name;
		this.document = document;
		this.email = email;
	}

	public CustomerEntity(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.document = customer.getDocument();
		this.email = customer.getEmail();
	}

	public Customer toCustomer() {
		return new Customer(id, name, document, email);
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
