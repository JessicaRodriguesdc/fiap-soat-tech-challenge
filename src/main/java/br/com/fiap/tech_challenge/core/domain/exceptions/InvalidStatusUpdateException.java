package br.com.fiap.tech_challenge.core.domain.exceptions;

public class InvalidStatusUpdateException extends RuntimeException {

	public InvalidStatusUpdateException(String message) {
		super(message);
	}

}
