package com.developermy.crosscutting.configs;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.developermy.crosscutting.enums.ResultStatus;
import com.developermy.crosscutting.exceptions.BadRequestException;
import com.developermy.crosscutting.exceptions.NotFoundException;
import com.developermy.crosscutting.models.GenericErrorMessageDTO;
import com.developermy.crosscutting.models.GenericResponse;

import io.jsonwebtoken.JwtException;

@ControllerAdvice
public class ControllerExceptionMapping extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { BadRequestException.class })
	protected ResponseEntity<Object> handleDateValidationException(BadRequestException ex, WebRequest request) {
		return formatException(ex, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { NotFoundException.class })
	protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
		return formatException(ex, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(value = { JwtException.class })
	protected ResponseEntity<Object> handleJwtException(JwtException ex, WebRequest request) {
		return formatException(ex, HttpStatus.UNAUTHORIZED, request);
	}

	private ResponseEntity<Object> formatException(Exception ex, HttpStatus status, WebRequest request) {
		GenericErrorMessageDTO errorMessage = GenericErrorMessageDTO.builder()
			.localizedMessage(ex.getLocalizedMessage())
			.cause(String.valueOf(ex.getCause()))
			.message(ex.getMessage())
			.build();
		GenericResponse<Object> genericResponseDTO = GenericResponse.builder()
			.statusCode(status.value())
			.statusName(status.name())
			.resultStatus(ResultStatus.FAILURE)
			.data(errorMessage)
			.build();
		return handleExceptionInternal(ex, genericResponseDTO, new HttpHeaders(), status, request);
	}

}
