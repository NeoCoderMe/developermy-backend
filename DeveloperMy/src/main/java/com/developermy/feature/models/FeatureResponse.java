package com.developermy.feature.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeatureResponse {

	private Long id;

	@JsonProperty("full_name")
	private String fullName;
	 
}