package com.developermy.crosscutting.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericErrorMessage {

	@JsonProperty("localized_message")
	private String localizedMessage;

	private String message;

	private String cause;

}
