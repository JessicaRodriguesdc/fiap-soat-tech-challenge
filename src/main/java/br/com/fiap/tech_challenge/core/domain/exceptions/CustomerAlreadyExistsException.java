package br.com.fiap.tech_challenge.core.domain.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String message){
        super(message);
    }
}
