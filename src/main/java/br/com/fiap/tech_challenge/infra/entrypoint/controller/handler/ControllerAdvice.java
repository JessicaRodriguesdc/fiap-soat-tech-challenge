package br.com.fiap.tech_challenge.infra.entrypoint.controller.handler;

import br.com.fiap.tech_challenge.application.exceptions.AlreadyExistsException;
import br.com.fiap.tech_challenge.application.exceptions.AlreadyInStatusException;
import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.application.exceptions.InvalidStatusUpdateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.DateTimeException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<?> alreadyExists(RuntimeException ex) {
		var error = new ProblemDTO(ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(DoesNotExistException.class)
	public ResponseEntity<?> notFound(RuntimeException ex) {
		var error = new ProblemDTO(ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(AlreadyInStatusException.class)
	public ResponseEntity<?> alreadyInStatus(RuntimeException ex) {
		var error = new ProblemDTO(ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	@ExceptionHandler(InvalidStatusUpdateException.class)
	public ResponseEntity<?> invalidStatusUpdate(RuntimeException ex) {
		var error = new ProblemDTO(ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		var errors = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(errors.stream().map(ErrorsValidateData::new).toList());
	}

	@ExceptionHandler(DateTimeException.class)
	public ResponseEntity<?> dateTimeException(DateTimeException ex) {
		var error = new ProblemDTO(ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.badRequest().body(error);
	}

	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		var error = new ProblemDTO(ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDTO> handle500Error(Exception ex) {
		var error = new ProblemDTO("Error: " + ex.getLocalizedMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

}
