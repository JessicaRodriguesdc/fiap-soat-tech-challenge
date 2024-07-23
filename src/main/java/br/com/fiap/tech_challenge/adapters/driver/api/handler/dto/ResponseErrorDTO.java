package br.com.fiap.tech_challenge.adapters.driver.api.handler.dto;

import java.lang.reflect.Constructor;

public class ResponseErrorDTO {
    private int statusCode;
    private String message;

    public ResponseErrorDTO(int statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseErrorDTO{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}
