package br.com.fiap.tech_challenge.core.domain.exceptions;

public class AlreadyInStatusException extends RuntimeException {

    public AlreadyInStatusException(String message){
        super(message);
    }
}
