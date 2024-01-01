package com.developermy.feature.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

//Never Use the constructors
//Methods with small number of parameter
//Open Close Principle,
//This is not a Record
@Data
@Builder
public class FeatureResponseDTO {

	private Long id;

	@JsonProperty("full_name")
	private String fullName;

}