package com.developermy.feature.models;

import lombok.Builder;
import lombok.Data;

//Never Use the constructors
//Methods with small number of parameter
//Open Close Principle,
@Data
@Builder
public class FeatureResponseDTO {

	private Long id;

	private String fullName;

}