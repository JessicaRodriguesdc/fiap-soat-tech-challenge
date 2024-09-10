package br.com.fiap.tech_challenge.application.exceptions;

public class AlreadyExistsException extends RuntimeException {

	public AlreadyExistsException(String message) {
		super(message);
	}

}
