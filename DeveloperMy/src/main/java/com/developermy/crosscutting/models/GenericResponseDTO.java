package com.developermy.crosscutting.models;

import com.developermy.crosscutting.enums.ResponseMessage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponseDTO<T> {

	private T data;

	private int statusCode;

	private String statusName;

	private ResponseMessage responseMessage;

}