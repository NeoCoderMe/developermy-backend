package com.developermy.crosscutting.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.developermy.crosscutting.enums.ResultStatus;
import com.developermy.crosscutting.models.GenericErrorMessage;
import com.developermy.crosscutting.models.GenericResponseDTO;

@ControllerAdvice
public class ControllerExceptionMapping extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { BadRequestException.class })
	protected ResponseEntity<Object> handleDateValidationException(BadRequestException ex, WebRequest request) {
		return formatException(ex, HttpStatus.BAD_REQUEST, request);
	}

	private ResponseEntity<Object> formatException(Exception ex, HttpStatus status, WebRequest request) {
		GenericErrorMessage errorMessage = GenericErrorMessage.builder()
			.localizedMessage(ex.getLocalizedMessage())
			.cause(String.valueOf(ex.getCause()))
			.message(ex.getMessage())
			.build();
		GenericResponseDTO<Object> genericResponseDTO = GenericResponseDTO.builder()
			.statusCode(status.value())
			.statusName(status.name())
			.resultStatus(ResultStatus.FAILURE)
			.data(errorMessage)
			.build();
		return handleExceptionInternal(ex, genericResponseDTO, new HttpHeaders(), status, request);
	}

}
