package br.com.fiap.tech_challenge.domain.models;

import br.com.fiap.tech_challenge.domain.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

	private Customer customer;

	private UUID id;

	private String name;

	private String document;

	private String email;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
	@DisplayName("Should return Customer attributes as the object was created")
	void shouldReturnCustomerAttributesAsTheObjectWasCreated() {
		customer = new Customer(id, name, document, email);

		assertNotNull(customer);
		assertEquals(id, customer.getId());
		assertEquals(name, customer.getName());
		assertEquals(document, customer.getDocument());
		assertEquals(email, customer.getEmail());
	}

	@Test
	@DisplayName("Should return Customer attributes as the object was created without ID")
	void shouldReturnCustomerAttributesAsTheObjectWasCreatedWithoutId() {
		customer = new Customer(name, document, email);

		assertNotNull(customer);
		assertNull(customer.getId());
		assertEquals(name, customer.getName());
		assertEquals(document, customer.getDocument());
		assertEquals(email, customer.getEmail());
	}

	private void buildArranges() {
		id = UUID.randomUUID();
		name = "Walter White";
		document = "31739380037";
		email = "heisenberg@gmail.com";
	}

}