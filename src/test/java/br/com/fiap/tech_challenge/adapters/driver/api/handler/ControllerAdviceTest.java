package br.com.fiap.tech_challenge.adapters.driver.api.handler;

import br.com.fiap.tech_challenge.core.domain.exceptions.AlreadyExistsException;
import br.com.fiap.tech_challenge.core.domain.exceptions.AlreadyInStatusException;
import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ControllerAdviceTest {

    @Mock
    private WebRequest mockWebRequest;

    @Mock
    private MethodParameter methodParameter;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ControllerAdvice controllerAdvice;

    @Test
    @DisplayName("Should return HTTP Status Bad Request when application throws AlreadyExistsException")
    void handleAlreadyExists() {
        AlreadyExistsException exception = new AlreadyExistsException("");

        assertEquals(controllerAdvice.alreadyExists(exception).getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Should return HTTP Status Not Found when application throws DoesNotExistException")
    void handleNotFound() {
        DoesNotExistException exception = new DoesNotExistException("");

        assertEquals(controllerAdvice.notFound(exception).getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Should return HTTP Status Conflict when application throws AlreadyInStatusException")
    void handleAlreadyInStatus() {
        AlreadyInStatusException exception = new AlreadyInStatusException("");

        assertEquals(controllerAdvice.alreadyInStatus(exception).getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    @DisplayName("Should return HTTP Status Bad Request when application throws DateTimeException")
    void dateTimeException() {
        DateTimeException exception = new DateTimeException("");

        assertEquals(controllerAdvice.dateTimeException(exception).getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("Should return HTTP Status Bad Request when application throws MethodArgumentNotValidException")
    void handleMethodArgumentNotValid() {
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(methodParameter, bindingResult);
        HttpHeaders headers = new HttpHeaders();
        HttpStatusCode status = HttpStatus.BAD_REQUEST;

        assertEquals(controllerAdvice.handleMethodArgumentNotValid(exception, headers, status, mockWebRequest).getStatusCode(), status);
    }

    @Test
    @DisplayName("Should return HTTP Status Bad Request when application throws HttpMessageNotReadableException")
    void handleHttpMessageNotReadable() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("");
        HttpHeaders headers = new HttpHeaders();
        HttpStatusCode status = HttpStatus.BAD_REQUEST;

        assertEquals(controllerAdvice.handleHttpMessageNotReadable(exception, headers, status, mockWebRequest).getStatusCode(), status);
    }

    @Test
    @DisplayName("Should return HTTP Status Internal Server Error when application throws Exception")
    void handle500Error() {
        Exception exception = new Exception();

        assertEquals(controllerAdvice.handle500Error(exception).getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}