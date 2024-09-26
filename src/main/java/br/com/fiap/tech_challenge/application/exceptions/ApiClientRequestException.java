package br.com.fiap.tech_challenge.application.exceptions;

public class ApiClientRequestException extends RuntimeException {

	public ApiClientRequestException(String message) {
		super(message);
	}

}