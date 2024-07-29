package br.com.fiap.tech_challenge.adapters.driver.api.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


@Schema(name = "ProblemDto")
public class ProblemDTO {
    @Schema(example = "Error Message")
    @JsonProperty("message")
    private String message;

    @Schema(example = "2024-07-24T22:13:58Z")
    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    public ProblemDTO(String message, LocalDateTime dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }
}
