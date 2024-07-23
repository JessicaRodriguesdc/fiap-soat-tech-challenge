package br.com.fiap.tech_challenge.adapters.driver.api.handler;

import br.com.fiap.tech_challenge.core.domain.exceptions.ResourceNotFoundException;
import br.com.fiap.tech_challenge.adapters.driver.api.handler.dto.ResponseErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ResponseErrorDTO> exceptionHandler(ResourceNotFoundException error){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseErrorDTO(
                        HttpStatus.NOT_FOUND.value(),
                        error.getMessage()
                ));
    }
}
