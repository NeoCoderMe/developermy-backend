package com.developermy.crosscutting.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.developermy.crosscutting.enums.ResultStatus;
import com.developermy.crosscutting.models.GenericResponse;

public abstract class BaseController {

	protected <T> ResponseEntity<GenericResponse<T>> buildBadRequest(T data) {
		return buildResponse(HttpStatus.BAD_REQUEST, data, ResultStatus.FAILURE);
	}

	protected <T> ResponseEntity<GenericResponse<T>> forBiddenRequest(T data) {
		return buildResponse(HttpStatus.FORBIDDEN, data, ResultStatus.FAILURE);
	}

	protected ResponseEntity<GenericResponse<Void>> buildNoContentResponse() {
		return buildResponse(HttpStatus.NO_CONTENT, null, ResultStatus.SUCCESS);
	}

	protected ResponseEntity<GenericResponse<Void>> buildTooManyRequest() {
		return buildResponse(HttpStatus.TOO_MANY_REQUESTS, null, ResultStatus.FAILURE);
	}

	protected <T> ResponseEntity<GenericResponse<T>> buildTooManyRequest(T data) {
		return buildResponse(HttpStatus.TOO_MANY_REQUESTS, data, ResultStatus.FAILURE);
	}

	protected <T> ResponseEntity<GenericResponse<T>> buildOkResponse(T data) {
		return buildResponse(HttpStatus.OK, data, ResultStatus.SUCCESS);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <T> ResponseEntity<GenericResponse<T>> buildResponse(HttpStatus status, T data, ResultStatus resultStatus) {
		GenericResponse genericResponse = GenericResponse.builder()
			.statusCode(status.value())
			.statusName(status.name())
			.resultStatus(resultStatus)
			.data(data)
			.build();

		return new ResponseEntity<>(genericResponse, status);
	}

}
