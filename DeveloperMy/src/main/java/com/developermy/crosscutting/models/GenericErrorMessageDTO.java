package com.developermy.crosscutting.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Base Object Response for errors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericErrorMessageDTO {

	@JsonProperty("localized_message")
	private String localizedMessage;

	private String message;

	private String cause;

}
