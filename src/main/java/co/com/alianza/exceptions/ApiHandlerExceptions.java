package co.com.alianza.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiHandlerExceptions {	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseException> resourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
		ResponseException response = new ResponseException();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserConflictException.class)
	public ResponseEntity<ResponseException> userConflictException(UserConflictException ex, WebRequest webRequest) {
		ResponseException response = new ResponseException();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.CONFLICT.value());

		return new ResponseEntity(response, HttpStatus.CONFLICT);
	}

}
