package com.developermy.crosscutting.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.developermy.crosscutting.enums.ResultStatus;
import com.developermy.crosscutting.models.GenericResponseDTO;

public abstract class BaseController {

	protected <T> ResponseEntity<GenericResponseDTO<T>> buildBadRequest(T data) {
		return buildResponse(HttpStatus.BAD_REQUEST, data, ResultStatus.FAILURE);
	}

	protected <T> ResponseEntity<GenericResponseDTO<T>> forBiddenRequest(T data) {
		return buildResponse(HttpStatus.FORBIDDEN, data, ResultStatus.FAILURE);
	}

	protected ResponseEntity<GenericResponseDTO<Void>> buildNoContentResponse() {
		return buildResponse(HttpStatus.NO_CONTENT, null, ResultStatus.SUCCESS);
	}

	protected ResponseEntity<GenericResponseDTO<Void>> buildTooManyRequest() {
		return buildResponse(HttpStatus.TOO_MANY_REQUESTS, null, ResultStatus.FAILURE);
	}

	protected <T> ResponseEntity<GenericResponseDTO<T>> buildTooManyRequest(T data) {
		return buildResponse(HttpStatus.TOO_MANY_REQUESTS, data, ResultStatus.FAILURE);
	}

	protected <T> ResponseEntity<GenericResponseDTO<T>> buildOkResponse(T data) {
		return buildResponse(HttpStatus.OK, data, ResultStatus.SUCCESS);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <T> ResponseEntity<GenericResponseDTO<T>> buildResponse(HttpStatus status, T data,
			ResultStatus resultStatus) {
		GenericResponseDTO genericResponse = GenericResponseDTO.builder()
			.statusCode(status.value())
			.statusName(status.name())
			.resultStatus(resultStatus)
			.data(data)
			.build();

		return new ResponseEntity<>(genericResponse, status);
	}

}
