package com.developermy.crosscutting.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionMapping extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = {BadRequestException.class})
	protected ResponseEntity<Object> handleDateValidationException(BadRequestException ex, WebRequest request){
		return handleExceptionInternal(ex, "", new HttpHeaders(),  HttpStatus.BAD_REQUEST,  request);
	} 
	
}
