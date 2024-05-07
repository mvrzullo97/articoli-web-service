package com.betashop.webapp.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ErrorResponse> exceptionNotFoundHandler(Exception ex){
		
		ErrorResponse err = new ErrorResponse();
		
		err.setCodice(HttpStatus.NOT_FOUND.value());
		err.setMex(ex.getMessage());
		System.out.println(ex.getMessage());
		System.out.println(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ErrorResponse>(err, new HttpHeaders(), HttpStatus.NOT_FOUND);
		
	}
	
}
