package br.com.fiap.tech_challenge.core.domain.exceptions;

public class DoesNotExistException extends RuntimeException {

	public DoesNotExistException(String message) {
		super(message);
	}

}
