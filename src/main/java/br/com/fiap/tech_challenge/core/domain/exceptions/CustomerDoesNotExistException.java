package br.com.fiap.tech_challenge.core.domain.exceptions;

public class CustomerDoesNotExistException extends RuntimeException {

    public CustomerDoesNotExistException(String message){
        super(message);
    }
}
