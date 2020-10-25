package com.jas.exception;

import java.time.format.DateTimeParseException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jas.dto.Mensagem;

@RestControllerAdvice
public class ResponseEntityExceptionCustom extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String defaultMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
		return new ResponseEntity<>(new Mensagem(defaultMessage), status);
	}
	
	@ExceptionHandler({DateTimeParseException.class, GenericException.class})
	public ResponseEntity<Mensagem> ParseDataException(RuntimeException ex) {
		return new ResponseEntity<>(new Mensagem(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
}
