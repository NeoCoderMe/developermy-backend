package com.developermy.crosscutting.models;

import com.developermy.crosscutting.enums.ResultStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponseDTO<T> {

	private T data;

	@JsonProperty("status_code")
	private int statusCode;

	@JsonProperty("status_name")
	private String statusName;

	@JsonProperty("result_status")
	private ResultStatus resultStatus;

}