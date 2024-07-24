package br.com.fiap.tech_challenge.adapters.driver.api.handler;

import org.springframework.validation.FieldError;

public record ErrorsValidateData(String field, String message){
    public ErrorsValidateData(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}