package br.com.fiap.tech_challenge.application.exceptions;

public class AlreadyInStatusException extends RuntimeException {

	public AlreadyInStatusException(String message) {
		super(message);
	}

}
