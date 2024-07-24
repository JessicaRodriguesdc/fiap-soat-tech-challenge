package br.com.fiap.tech_challenge.adapters.driver.api.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;


public class ProblemDTO {
    @JsonProperty("message")
    private String message;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    public ProblemDTO(String message, LocalDateTime dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }
}
