package com.developermy.feature.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Valid
public record FeatureRequestDTO( 
		@JsonProperty("full_name") 
		@NotBlank(message = "Full name is required")
        @Size(max = 20, message = "Full name must be less than or equal to 20 characters")
		String fullName, 
		
		@NotBlank(message = "Password is required")
		@Size(max = 20, message = "Password must be less than or equal to 20 characters")
		String password) {

}
